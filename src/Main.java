// -----------------------------------------------------
// Assignment 4
// Written by: Sam Mojaverian 40174101 and Tahmid Rahman 40174589
// -----------------------------------------------------


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    /**
     * Vowel processing method
     * @param sc
     * @param f
     * @param fileName
     * @throws IOException
     */
    public static void fileVowelProcess(Scanner sc, File f, String fileName) throws IOException {
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

    /**
     * Obsessive O processing method
     * @param sc
     * @param f
     * @param fileName
     * @throws IOException
     */
    public static void fileObsessiveProcess(Scanner sc, File f, String fileName) throws IOException {
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

    /**
     * distinct processing method
     * @param sc
     * @param f
     * @param fileName
     * @throws IOException
     */
    public static void fileDistinctProcess(Scanner sc, File f, String fileName) throws IOException {
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

    /**
     * main method
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        System.out.println("hello");


        Scanner sc = null;
        Scanner sc2 = null;
        Scanner sc3 = null;
        Scanner scanner = new Scanner (System.in);
        System.out.println("Enter Name of Desired Input File: ");
        String fileName = scanner.next();
        File chosen = new File(fileName);
        try {
            sc = new Scanner(new FileInputStream(chosen));
            sc2 = new Scanner(new FileInputStream(chosen));
            sc3 = new Scanner(new FileInputStream(chosen));
        } catch (FileNotFoundException e) {
            System.out.println("Could not open input file for reading. Please check if file exists");
            System.out.println("Program will terminate.");
            System.exit(0);
        }


        fileVowelProcess(sc, chosen, "vowel_verbiage.txt");
        fileObsessiveProcess(sc2, chosen, "obsessive_o.txt");
        fileDistinctProcess(sc3, chosen, "distinct_data.txt");


    }
}
