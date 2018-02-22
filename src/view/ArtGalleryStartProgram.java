package view;

//Sheri Westhoff
import java.util.List;
import java.util.Scanner;

import controller.ArtGalleryHelper;
import model.Artwork;

public class ArtGalleryStartProgram {

	static Scanner in = new Scanner(System.in);
	static ArtGalleryHelper agh = new ArtGalleryHelper();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runMenu();

	}

	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("--- Welcome to our awesome Gallery list! ---");
		while (goAgain) {
			System.out.println("\n*  Select an option:");
			System.out.println("*  1 -- Add an item");
			System.out.println("*  2 -- Edit an item");
			System.out.println("*  3 -- Delete an item");
			System.out.println("*  4 -- View the list");
			System.out.println("*  5 -- Exit the awesome program");
			System.out.print("*  Your selection: ");
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addAnItem();
			} else if (selection == 2) {
				editAnItem();
			} else if (selection == 3) {
				deleteAnItem();
			} else if (selection == 4) {
				viewTheList();
			} else {
				// lih.cleanUp();
				System.out.println("   Goodbye!   ");
				goAgain = false;
			}
		}
	}

	private static void addAnItem() {
		// TODO Auto-generated method stub
		System.out.print("Enter the Artist's Name: ");
		String artistName = in.nextLine();
		System.out.print("Enter the Title of the piece: ");
		String title = in.nextLine();
		// *
		System.out.print("What type of media is the art piece used? ");
		String media = in.nextLine();

		System.out.print("What year was the piece created? ");
		String year = in.nextLine();

		System.out.print("What is the value of the piece? ");
		Double value = in.nextDouble();

		Artwork toAdd = new Artwork(title, artistName, media, year, value);
		agh.insertItem(toAdd);

	}

	private static void deleteAnItem() {
		// TODO Auto-generated method stub
		System.out.print("Enter the Artist's Name: ");
		String artistName = in.nextLine();
		System.out.print("Enter the Title of the piece: ");
		String title = in.nextLine();

		// System.out.print("What type of media is the art piece used? ");
		// String media = in.nextLine();

		// System.out.print("What year was the piece created? ");
		// String year = in.nextLine();

		// System.out.print("What is the value of the piece? ");
		// Double value = in.nextDouble();

		// GalleryList toDelete = new GalleryList(title, artistName, media, year,
		// value);
		Artwork toDelete = new Artwork(title, artistName);

		agh.deleteItem(toDelete);
	}

	private static void editAnItem() {
		// TODO Auto-generated method stub
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by Artist");
		System.out.println("2 : Search by Media");
		int searchBy = in.nextInt();
		in.nextLine();
		List<Artwork> foundItems;
		if (searchBy == 1) {
			System.out.print("Enter the Artist name: ");
			String artistName = in.nextLine();

			foundItems = agh.searchByItemByArtist(artistName);

		} else {
			System.out.print("Enter the media: ");
			String mediaName = in.nextLine();
			foundItems = agh.searchForItemByMedia(mediaName);

		}

		if (!foundItems.isEmpty()) {
			System.out.println("Found Results.");
			for (Artwork l : foundItems) {
				System.out.println(l.getId() + " : " + l.toString());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();

			Artwork toEdit = agh.searchForItemById(idToEdit);
			System.out.println("Retrieved " + toEdit.getValue() + " from " + toEdit.getTitle());
			System.out.println("1 : Update Value");
			System.out.println("2 : Update Title");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New Value: ");
				Double newValue = in.nextDouble();
				toEdit.setValue(newValue);
			} else if (update == 2) {
				System.out.print("New Title: ");
				String newTitle = in.nextLine();
				toEdit.setTitle(newTitle);

			}

			agh.updateArtwork(toEdit);

		} else {
			System.out.println("---- No results found");
		}

	}

	private static void viewTheList() {
		// TODO Auto-generated method stub
		List<Artwork> allItems = agh.showAllItems();
		for (Artwork agh : allItems) {
			System.out.println(agh.displayArtwork());
		}

	}

}
