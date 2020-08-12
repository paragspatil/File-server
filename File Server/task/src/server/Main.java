package server;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.net.*;
public class Main {
    private static int PORT = 23456;
    public static void main(String[] args) {
        boolean exitFlag = true;
        //System.out.println("Server started!");
        //System.out.println(System.getProperty("user.dir"));
        try (ServerSocket server = new ServerSocket(PORT)) {

                try (Socket socket = server.accept();
                     DataInputStream input = new DataInputStream(socket.getInputStream());
                     DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {
                    while (exitFlag) {
                        String msg = input.readUTF();
                        //System.out.println(msg);
                        switch (msg) {
                            case "2":
                                //System.out.println("enterring case 1");
                                String fileName = input.readUTF();
                                String fileContent = input.readUTF();
                                String filePath = "E:\\trash\\File Server\\File Server\\task\\src\\server\\data\\" + fileName;
                                File newFile = new File(filePath);
                                //System.out.println(newFile.exists());
                                if (newFile.exists()) {
                                    output.writeUTF("403");
                                } else {


                                    try (PrintWriter writer = new PrintWriter(newFile)) {
                                        writer.println(fileContent);
                                        output.writeUTF("200");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                break;

                            case "1":
                                String getFileName = input.readUTF();
                                String getFilePath = "E:\\trash\\File Server\\File Server\\task\\src\\server\\data\\" + getFileName;
                                File getFile = new File(getFilePath);
                                if(getFile.exists()){
                                    String content  = new String(Files.readAllBytes(Paths.get(getFilePath)));
                                    output.writeUTF("200");
                                    output.writeUTF(content);
                                }
                                else {
                                    output.writeUTF("404");
                                }
                                break;

                            case "3":
                                String deleteFileName = input.readUTF();
                                String deleteFilePath = "E:\\trash\\File Server\\File Server\\task\\src\\server\\data\\" + deleteFileName;
                                File deleteFile = new File(deleteFilePath);
                                if(deleteFile.exists()){
                                    deleteFile.delete();
                                    output.writeUTF("200");
                                }
                                else {
                                    output.writeUTF("404");
                                }
                                break;

                            case "exit":
                                exitFlag = false;
                                break;
                        }
                    }

                } catch (Exception e) {
                    System.out.println("something went wrong with server");
                }
            }

        catch(Exception e){
                System.out.println("something went wrong with server");
            }

        }


        public static void createFile(String fileName, String fileContent){
          //.../server/data/

        }
}


