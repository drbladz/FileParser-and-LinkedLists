import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void fileVowelProcess(Scanner sc, String scName, String fileName) {
        PrintWriter pw = null;
        String word = "";
        ArrayList<String> vw = new ArrayList<String>();
        try {
            pw = new PrintWriter(new FileOutputStream(fileName, true));

        } catch (FileNotFoundException e) {
            System.out.println("Could not create a file.");
            System.out.println("Program will terminate.");
            System.exit(0);
        }
        while (sc.hasNext()) {
            int count = 0;

            word = sc.next();
            String rwords = word.replaceAll("[^a-zA-Z0-9]", "");

            for (int i = 0; i < rwords.length(); i++) {
                if (rwords.charAt(i) == 'a' || rwords.charAt(i) == 'e' || rwords.charAt(i) == 'i' || rwords.charAt(i) == 'o' || rwords.charAt(i) == 'u'
                        || rwords.charAt(i) == 'A'|| rwords.charAt(i) == 'E'|| rwords.charAt(i) == 'I'|| rwords.charAt(i) == 'O'|| rwords.charAt(i) == 'U') {
                    count++;
                }

            }
            if (count > 3) {
                vw.add(rwords);
                //pw.print(word + "\n");
            }

        }
        pw.println("The word count is: " + vw.size());
        for (int i = 0; i< vw.size(); i++){
            pw.println(vw.get(i));
        }
        pw.close();
        sc.close();
    }

    public static void main(String[] args) {
        System.out.println("hello");


        Scanner sc = null;

        try {
            sc = new Scanner(new FileInputStream("history_of_java.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Could not open input file for reading. Please check if file exists");
            System.out.println("Program will terminate.");
            System.exit(0);
        }

        fileVowelProcess(sc, "history_of_java.txt", "vowel_verbiage.txt");

    }
}
