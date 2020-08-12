package client;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 23456;
    public static void main(String[] args) {
        boolean exitFlag = true;
        Scanner scanner = new Scanner(System.in);
        try(Socket socket = new Socket(ADDRESS,PORT);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream())){
            while (exitFlag) {
                System.out.println("Enter action (1 - get the file, 2 - create a file, 3 - delete the file):");
                String choice = scanner.nextLine();
                output.writeUTF(choice);
                switch (choice) {
                    case "2":
                        System.out.println("Enter filename:");
                        String fileName = scanner.nextLine();
                        System.out.println("Enter file content:");
                        String content = scanner.nextLine();
                        output.writeUTF(fileName);
                        output.writeUTF(content);
                        System.out.println("The request was sent.");
                        String status = input.readUTF();
                        if(status.equals("200")){
                            System.out.println("Ok, the response says that the file was created!");
                        }
                        else {
                            System.out.println("Ok, the response says that creating the file was forbidden!");
                        }
                        break;

                    case "1":
                        System.out.println("Enter filename:");
                        String getFileName = scanner.nextLine();
                        output.writeUTF(getFileName);
                        System.out.println("The request was sent.");
                        String getFileStatus = input.readUTF();
                        if(getFileStatus.equals("200")){
                            String fileContent = input.readUTF();
                            System.out.println("Ok, the content of the file is: " + fileContent);
                        }
                        else {
                            System.out.println("Ok, response says that the file was not found!");
                        }


                        break;

                    case "3":
                        System.out.println("Enter filename:");
                        String deleteFileName = scanner.nextLine();
                        output.writeUTF(deleteFileName);
                        System.out.println("The request was sent.");
                        String deleteStatus = input.readUTF();
                        if(deleteStatus.equals("200")){
                            System.out.println("Ok, the response says that the file was successfully deleted!");
                        }
                        else{
                            System.out.println("Ok, the response says that the file was not found!");
                        }
                        break;

                    case "exit":
                       // output.writeUTF(choice);
                        exitFlag = false;
                        break;


                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
