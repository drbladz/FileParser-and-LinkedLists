import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void fileVowelProcess(Scanner sc, String scName, String fileName) throws IOException {
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
                        || rwords.charAt(i) == 'A' || rwords.charAt(i) == 'E' || rwords.charAt(i) == 'I' || rwords.charAt(i) == 'O' || rwords.charAt(i) == 'U') {
                    count++;
                }

            }
            if (count > 3) {
                vw.add(rwords);
            }

        }
        pw.println("The word count is: " + vw.size());
        for (int i = 0; i < vw.size(); i++) {
            pw.println(vw.get(i));
        }
        pw.close();
        sc.close();
    }

    public static void fileObsessiveProcess(Scanner sc, String scName, String fileName) throws IOException {
        PrintWriter pw = null;
        String word = "";
        String rwords = "";
        ArrayList<String> ow = new ArrayList<String>();
        try {
            pw = new PrintWriter(new FileOutputStream(fileName, true));

        } catch (FileNotFoundException e) {
            System.out.println("Could not create a file.");
            System.out.println("Program will terminate.");
            System.exit(0);
        }
        while (sc.hasNext()) {

            word = sc.next();
            rwords = word.replaceAll("[^a-zA-Z0-9]", "");

            for (int i=0; i<rwords.length();i++){
                if(rwords.charAt(0)=='o'|| rwords.charAt(0)=='O'){
                    ow.add(rwords);
                    break;
                }
            }
        }
        pw.println("The word count is: " + ow.size());
        for (int i = 0; i < ow.size(); i++) {
            pw.println(ow.get(i));
        }
        pw.close();
        sc.close();
    }

    public static void fileDistinctProcess(Scanner sc, String scName, String fileName) throws IOException {
        PrintWriter pw = null;
        String word = "";
        String rwords = "";
        ArrayList<String> dw = new ArrayList<String>();
        try {
            pw = new PrintWriter(new FileOutputStream(fileName, true));

        } catch (FileNotFoundException e) {
            System.out.println("Could not create a file.");
            System.out.println("Program will terminate.");
            System.exit(0);
        }
        while (sc.hasNext()) {
            word = sc.next();
            rwords = word.replaceAll("[^a-zA-Z0-9]", "");
            if(!(dw.contains(rwords))){
                dw.add(rwords);
            }

        }
        pw.println("The word count is: " + dw.size());
        for (int i = 0; i < dw.size(); i++) {
            pw.println(dw.get(i));
        }
        pw.close();
        sc.close();
    }


    public static void main(String[] args) throws IOException {
        System.out.println("hello");


        Scanner sc = null;
        Scanner sc2 = null;
        Scanner sc3 = null;

        try {
            sc = new Scanner(new FileInputStream("history_of_java.txt"));
            sc2 = new Scanner(new FileInputStream("history_of_java.txt"));
            sc3 = new Scanner(new FileInputStream("history_of_java.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Could not open input file for reading. Please check if file exists");
            System.out.println("Program will terminate.");
            System.exit(0);
        }

        fileVowelProcess(sc, "history_of_java.txt", "vowel_verbiage.txt");
        fileObsessiveProcess(sc2, "history_of_java.txt", "obsessive_o.txt");
        fileDistinctProcess(sc3, "history_of_java.txt", "distinct_data.txt");


    }
}
