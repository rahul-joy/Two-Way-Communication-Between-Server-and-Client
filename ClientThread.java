package clientthread;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread {
    
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 5010); 
            System.out.println("Client is Connected");
            DataOutputStream output = new DataOutputStream(s.getOutputStream());
            DataInputStream input = new DataInputStream(s.getInputStream()); 
            BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
  
            String str = " ";
            while (!str.equals("bye")) {
                str = read.readLine();
                output.writeUTF(str);
                
                String serverReply = input.readUTF();
                System.out.println("Server: " + serverReply);
            }
            
            output.close();
            read.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}