package serverthread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread {
    public static void main(String args[]) throws IOException {
        ServerSocket ss = new ServerSocket(5010);
        System.out.println("Server is Connected at port: " + ss.getLocalPort());
        System.out.println("Connection building...");
        System.out.println("Waiting For Client...");

        Socket s = ss.accept();
        DataInputStream input = new DataInputStream(s.getInputStream());
        DataOutputStream output = new DataOutputStream(s.getOutputStream());

        DataInputStream serverInput = new DataInputStream(System.in);

        String clientMessage;
        String serverMessage;

        while (true) {
            if (input.available() > 0) {
                clientMessage = input.readUTF();
                System.out.println("Client: " + clientMessage);

                if (clientMessage.equalsIgnoreCase("bye")) {
                    break;
                }
            }

            if (serverInput.available() > 0) {
                serverMessage = serverInput.readLine();
                output.writeUTF(serverMessage);
            }
        }

        s.close();
        input.close();
        output.close();
                ss.close();
    }
}
