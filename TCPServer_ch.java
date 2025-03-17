import java.io.*;
import java.net.*;

class TCPServer_ch {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket( 6789)) {
            System.out.println("Server started and listening on port 6789.");
            Socket connectionSocket = serverSocket.accept();
            System.out.println("Client connected.");

            DataInputStream inFromClient = new DataInputStream(connectionSocket.getInputStream());
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            while (true) {
                String clientMessage = inFromClient.readUTF();
                if (clientMessage.equalsIgnoreCase("QUIT")) {
                    System.out.println("Client has terminated the connection.");
                    break;
                }
                int receivedChecksum = Integer.parseInt(clientMessage.split(" ")[1]);
                String receivedMessage = clientMessage.split(" ")[0];
                int calculatedChecksum = calculateChecksum(receivedMessage);

                int checksum = calculateChecksum(receivedMessage);
                
                if (receivedChecksum != calculatedChecksum) {
                    System.out.println("The Received Message is not correct");
                    outToClient.writeUTF("The Received Message is not correct");
                } else {
                    System.out.println("Server: " + receivedMessage);
                    outToClient.writeUTF("The Received Message is correct");
                }
                
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int calculateChecksum(String message) {
        int checksum = 0;
        for (char c : message.toCharArray()) {
            checksum += c;
        }
        return checksum;
    }
}