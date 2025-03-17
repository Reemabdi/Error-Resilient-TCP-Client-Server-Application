import java.io.*;
import java.net.*;
import java.util.Random;

class TCPClient_ch {
    public static void main(String[] args) {
        try (Socket clientSocket = new Socket("192.168.1.19", 6789)) {
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            Random random = new Random();
           
            while (true) {
                System.out.print("Enter message: ");
                String message = userInput.readLine();

                if (message.isEmpty()) {
                    System.out.println("Entered message is empty. Please enter a valid message.");
                    continue;
                }

                int check4send = calculateChecksum(message);

                if (message.equalsIgnoreCase("QUIT")) {
                    outToServer.writeUTF(message );
                    break;
                }
              //error
                double errorProbability = 0.5; // Change to 0.3 or 0.8 as needed
                if (random.nextDouble() < errorProbability) {
                    message = introduceError(message);
                }
                
                
                outToServer.writeUTF(message +" "+check4send);
                
                String serverResponse = inFromServer.readUTF();

                System.out.println(serverResponse);
                
            }
        } catch (IOException e) {
            System.out.println("Server is down, please try later.");
        }
    }

    private static int calculateChecksum(String message) {
        int checksum = 0;
        for (char c : message.toCharArray()) {
            checksum += c;
        }
        return checksum;
    }

    private static String introduceError(String message) {
        char[] chars = message.toCharArray();
        int index = new Random().nextInt(chars.length);
        chars[index] = (char) (chars[index] + 1);
        return new String(chars);
    }
}
