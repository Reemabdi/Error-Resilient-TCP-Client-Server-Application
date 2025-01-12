# Error-Resilient-TCP-Client-Server-Application

This project implements a TCP-based client-server architecture to facilitate communication between a client and a server. The client sends messages to the server with a checksum for error detection. The server validates the checksum and responds accordingly.

## Features
Checksum Validation: Ensures data integrity by validating the message checksum.
Error Simulation: Simulates message corruption with configurable error probabilities.
Graceful Termination: Allows the client and server to terminate the connection cleanly.
Real-Time Communication: Messages are exchanged and validated in real time.


## Technologies Used
Java: For implementing the client-server architecture.
TCP/IP Protocol: Reliable communication over the network.
Socket Programming: For data exchange between client and server.


## Installation and Usage
Clone this repository:
git clone https://github.com/yourusername/tcp-client-server.git
cd tcp-client-server
Compile the Java files:

javac TCP_Server.java TCP_Client.java
Start the server:

java TCP_Server

Run the client in a separate terminal:

java TCP_Client


## Code Structure
Client (TCP_Client.java)
Main Functions:

Connects to the server using IP and port.
Sends messages along with a checksum.
Simulates errors in messages based on probability.
Receives and displays server responses.
Key Methods:

calculateChecksum(String message): Computes the 16-bit one's complement checksum for error detection.
simulateError(String message, double errorProbability): Introduces random errors into the message.
Server (TCP_Server.java)
Main Functions:

Listens for incoming connections from clients.
Validates received messages by comparing checksums.
Responds with acknowledgment or error messages.
Key Methods:

calculateChecksum(String message): Recomputes the checksum to validate the integrity of the message.

### Error Handling
Common Issues
Connection Refused: Ensure the server is running before starting the client.
Invalid Message Format: Messages must follow the format message:checksum.
Checksum Mismatch: Indicates message corruption (simulated or actual).
Solutions
Check IP address and port configurations.
Adjust firewall settings to allow traffic.
Restart the server or client in case of unexpected behavior.

### How It Works
Server:

Initializes on a specified port and waits for client connections.
Processes incoming messages and validates checksums.
Client:

Connects to the server.
Sends messages with checksums and receives responses.
Communication:

Messages are exchanged in real-time, and errors are simulated with a random probability.
