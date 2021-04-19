import java.io.*;
import java.util.Scanner;

public class Main {

    public static void fileProcess(Scanner sc, String scName, String fileName){
        PrintWriter pw = null;

        try{
            pw = new PrintWriter(new FileOutputStream(fileName, true));

        } catch (FileNotFoundException e) {
            System.out.println("Could not create a file.");
            System.out.println("Program will terminate.");
            System.exit(0);
        }
        while(sc.hasNextLine()){
            int count = 0;


            String word = sc.next();

            for (int i = 0; i< word.length(); i++){
                if(word.charAt(i)=='a' || word.charAt(i)=='e' || word.charAt(i)=='i' || word.charAt(i)=='o' || word.charAt(i)=='u'){
                    count++;
                }

            }
                if(count > 3){
                    pw.print(word + "\n");
                }

        }
        pw.close();
        sc.close();
        }

    public static void main(String[] args) {
        System.out.println("hello");


        Scanner sc = null;

        try{
            sc = new Scanner(new FileInputStream("history_of_java.txt"));
        }
        catch (FileNotFoundException e) {
            System.out.println("Could not open input file for reading. Please check if file exists");
            System.out.println("Program will terminate.");
            System.exit(0);
        }
        fileProcess(sc,"history_of_java.txt", "testfile.txt");
        System.out.println("yo");
    }
}
