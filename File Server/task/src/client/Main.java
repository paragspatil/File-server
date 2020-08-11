package client;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 23456;
    public static void main(String[] args) {
        try(Socket socket = new Socket(ADDRESS,PORT);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream())){
            output.writeUTF("Give me everything you have!");
            System.out.println("Sent: Give me everything you have!");
            String msg = input.readUTF();
            System.out.println("Received: " + msg);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
