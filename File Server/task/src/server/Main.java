package server;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.net.*;
public class Main {
    private static int PORT = 23456;
    public static void main(String[] args) {
        System.out.println("Server started!");
        try(ServerSocket server =new ServerSocket(PORT)){
              try(Socket socket = server.accept();
                  DataInputStream input = new DataInputStream(socket.getInputStream());
                   DataOutputStream output = new DataOutputStream(socket.getOutputStream())){

                  String msg = input.readUTF();
                  System.out.println("Received: " + msg);
                  output.writeUTF("All files were sent!");
                  System.out.println("Sent: All files were sent!");

              }
              catch (Exception e){
                  System.out.println("something went wrong with server");
              }
        }
        catch (Exception e){
            System.out.println("something went wrong with server");
        }

        }
}


