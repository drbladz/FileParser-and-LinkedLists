import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class ProcessWishlist {

	public static void main(String[] args) {

		ShowList first = new ShowList();
		ShowList second = new ShowList();
		Scanner sc = null;

		try {
			sc = new Scanner(new FileInputStream("TVGuide.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("The file TVGuide.txt could not be found.");
			System.exit(0);
		}

		while (sc.hasNext()) {
			String[] string_info = new String[2];
			double[] double_info = new double[2];
			int info_nb = 1;
			String line;

			if (info_nb == 1) {
				String[] split = new String[2];
				line = sc.nextLine();
				split = line.split(" ");
				string_info[0] = split[0];
				string_info[1] = split[1];
				info_nb++;
			}
			if (info_nb == 2) {
				String[] split = new String[2];
				line = sc.nextLine();
				split = line.split(" ");
				double_info[0] = Double.parseDouble(split[1]);
				info_nb++;
			}
			if (info_nb == 3) {
				String[] split = new String[2];
				line = sc.nextLine();
				split = line.split(" ");
				double_info[1] = Double.parseDouble(split[1]);
				info_nb++;
			}
			if (info_nb == 4) { //create Show object with the 4 info and reset loop
				info_nb = 1;
				line = sc.nextLine();

				info_nb = 0;
			}
			Show CurrentShow = new Show(string_info[0], string_info[1], double_info[0], double_info[1]);

			if (!(first.contains(CurrentShow.showName))) { //check if same show repeated
				first.addToStart(CurrentShow);
			}


		}

		first.showListContents();


	}
}
