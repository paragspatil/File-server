package server;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> files = new ArrayList<>();
        List<String> validNamesList = new ArrayList<>(Arrays.asList("file1","file2","file3","file4","file5","file6","file7","file8","file9","file10"));
        String  input = scanner.nextLine();
        while (!input.equals("exit")){
            String[] commands = input.split(" ");
            switch (commands[0]) {
                case "add":
                addfile(commands[1], files,validNamesList);
                break;

                case "get":
                    getFile(commands[1], files);
                    break;

                case "delete":
                    deleteFile(commands[1], files);
                    break;
            }
          input = scanner.nextLine();
        }
    }



    public static void addfile(String fileName, List<String> files,List<String> validNamesList){
        if(!files.contains(fileName) && validNamesList.contains(fileName)) {
            files.add(fileName);
            System.out.println("The file " + fileName + " added successfully");
        }
        else {
            System.out.println("Cannot add the file " +fileName);
        }
    }

    public static void deleteFile(String fileName, List<String> files){
       if(files.contains(fileName)){
           files.remove(fileName);
           System.out.println("The file " + fileName + " was deleted");
       }
       else {
           System.out.println("The file " + fileName +" not found");
       }
    }

    public static void getFile(String fileName, List<String> files){
      if(files.contains(fileName)){
          System.out.println("The file " + fileName + " was sent");
      }
      else {
          System.out.println("The file " + fileName +" not found");
      }
    }
}

