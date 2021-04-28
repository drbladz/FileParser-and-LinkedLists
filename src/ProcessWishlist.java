import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class ProcessWishlist {
	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {

		ShowList first = new ShowList();
		ShowList second = new ShowList();
		Scanner sc = null;
		Scanner sc2 = null;

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
			Show NewShow = new Show(string_info[0], string_info[1], double_info[0], double_info[1]);

			if (!(first.contains(NewShow.showName))) { //check if same show repeated
				first.addToStart(NewShow);
			}


		}

		first.showListContents();

		Scanner kb = new Scanner(System.in);
		System.out.println("Enter the name of the input file that has your watching and wishlist shows: ");
		String input = kb.next(); //Test Interest.txt
		try {
			sc2 = new Scanner(new FileInputStream(input));
		}
		catch (FileNotFoundException e) {
			System.out.println("The file "+ input +" could not be found.");
			System.exit(0);
		}

		ArrayList<String> watching = new ArrayList<String>();
		ArrayList<String> wishlist = new ArrayList<String>();

		int show_nb = 0;
		int add1=0;
		int add2=0;
		while (sc2.hasNext()) {
			String line = sc2.nextLine();
			if (line.equals("Watching")) {
				show_nb=1;
			}
			if (show_nb==1) {
				add1++;
				if(add1==1) {
					line=sc2.nextLine();
				}
				if (!line.equals("Wishlist")) {
					watching.add(line);
				}
				else {
					show_nb=2;
				}
			}
			if (show_nb==2) {
				add2++;
				if(add2==1) {
					line=sc2.nextLine();
				}
				wishlist.add(line);
			}

		}
		sc2.close();
		ArrayList<String> watchable = new ArrayList<String>();
		for (int i=0; i<watching.size(); i++) {
			String Newid = watching.get(i);
			if (first.findbyID(Newid) != null) {
				Show Newshow = first.findbyID(Newid);
				for (int j=0; j<wishlist.size(); j++) {
					String Comparedid = wishlist.get(j);
					if (first.findbyID(Comparedid) != null) {
						Show Comparedshow = first.findbyID(Comparedid);
						String check=Newshow.isOnSameTime(Comparedshow); //Test isOnSameTime method
						if(check.equals("Same Time") || check.equals("Some Overlap")){
							wishlist.remove(j);
							if (!watchable.contains(check)) {
								watchable.add(check);
							}
						}
						else {
							if (i==watching.size()-1) {
								watchable.add(check);
								System.out.println("User can watch "+Comparedshow.getShowName());
							}
						}
					}
				}
			}
		}
		for (int i=0; i<watchable.size(); i++) {
			System.out.println(watchable.get(i));
		}


		//Search the list for IDs that user inputs
		System.out.println("Enter a show ID to check: ");
		String id1 = kb.next();
		if (first.contains(id1)) {
			System.out.println("The show " +id1+ " was found!");
		}
		else {
			System.out.println("The show " +id1+ " was not found!");
		}

		System.out.println("Enter another show ID to check: ");
		String id2 = kb.next();
		if (first.contains(id2)) {
			System.out.println("The show " +id2+ " was found!");
		}
		else {
			System.out.println("The show " +id2+ " was not found!");
		}


		System.out.println("Enter another show ID to check: ");
		String id3 = kb.next();
		if (first.contains(id3)) {
			System.out.println("The show " +id3+ " was found!");
		}
		else{
			System.out.println("The show " +id3+ " was not found!");
		}



		kb.close();
	}
}