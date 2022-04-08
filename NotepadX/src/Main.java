import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
	
	static String emptyline = " \n";
	public static void main(String[] args) {
		
		createFileDirectory(); // Creates the 'file' directory to store notepads, if it doesnt already exist
		optionMenu(); // Calls the option menu
		
	}
	
	static void createFileDirectory() {
		
		Path path = Paths.get("./files");
		try {
			Files.createDirectory(path);
			System.out.println("Directory 'file' was created");
			
		} 
		catch (IOException e) {
			
		}
		
	}
	
	static void createNotepad() { // CREATE NOTEPAD - creates new notepad
		
		System.out.println(emptyline + "-- Create Notepad --");
		
		@SuppressWarnings("resource")
		Scanner get_file_name = new Scanner(System.in);
		System.out.println("Enter name of the new notepad: ");
		String file_name0 = get_file_name.nextLine();
		String file_name = file_name0.replaceAll("\\s", "");
		
		try {
			File file_to_create = new File("files/"+file_name+".txt");
		    
			if (file_to_create.createNewFile()) {
				System.out.println("File created: " + file_to_create.getName());
				writeNote();
			} else {
				System.out.println("File already exists.");
				createNotepad();
			}
		}
		catch (Exception e) {
			System.out.println("An error occurred: " + e);
			optionMenu();
		}
		
		System.out.println("Done.");
		optionMenu();
		
	}
	
	static void writeNote() { // WRITE NOTE - write note to an existing notepad
		
		System.out.println(emptyline + "-- Write a note --");
		
		// SEE THE DIRECTORY CONTENT
		File directory_path = new File("files/");
		String[] content = directory_path.list();
		System.out.println("Your notepads:");
		
		for (int i = 0; i < content.length; i++) {
			System.out.println("<"+i+">"+ " " + content[i]);
		}
		
		// Choose the file
		@SuppressWarnings("resource")
		Scanner get_np_name = new Scanner(System.in);
		System.out.println(emptyline+"Enter name of the notepad you wanna write to (without the '.txt'): ");
		String notepad_name = "files/" + get_np_name.nextLine() + ".txt" ;
		
		// Show the chosen note
		try {
			FileReader file_to_read = new FileReader(notepad_name);
			BufferedReader bf = new BufferedReader(file_to_read);
			
			System.out.println("Content of the chosen notepad:");
			String line;
			while ((line = bf.readLine()) != null) {
				System.out.println(line);
			}
			System.out.println(emptyline);
			
			
			// Get the note to write
			String note = "";
			System.out.println("Enter your note (Type [*] if you only wanna read, or exit):");
			
			while (true) {
				@SuppressWarnings("resource")
				Scanner get_note = new Scanner(System.in); // Input pre vpisovany text
				String note_add = get_note.nextLine() + "\n"; // Vezme 1 riadok aktualne vpisaneho textu
				
				if (note_add.equals("*\n")) { // AK vpisany text = '*' --> ukonci retazec (vpisovanie)
					break;
				}
				else { // INAK pokracuje
					note = note + note_add; // Prida ho do celkoveho retazca textu					
				}
				
			}
			
			try {
				//
				Writer filewrite = new FileWriter(notepad_name, true);
				filewrite.write(note);
				filewrite.close();
			}
			catch (Exception e) {
				System.out.println("An error occurred.");
				System.out.println(e);
				writeNote();
			}
			
			file_to_read.close();
			bf.close();

		}
		catch (Exception e) {
			System.out.println("An error occurred: " + e);
			optionMenu();
		}
		
		System.out.println("Done.");
		optionMenu();
		
	}
	
	static void deleteNotepad() { // DELETE NOTEPAD - delete a certain notepad
		
		System.out.println(emptyline + "-- Delete a note --");
		
		// SEE THE DIRECTORY CONTENT
		File directory_path = new File("files/");
		String[] content = directory_path.list();
		System.out.println("Your notepads:");
		
		for (int i = 0; i < content.length; i++) {
			System.out.println("<"+i+">"+ " " + content[i]);
		}
		
		// Choose the file
		@SuppressWarnings("resource")
		Scanner get_np_name = new Scanner(System.in);
		System.out.println(emptyline+"Enter name of the notepad you wanna delete (without the '.txt'): ");
		String notepad_name = "files/" + get_np_name.nextLine() + ".txt" ;
		
		// Delete notepad
		try {
			File file_to_delete = new File(notepad_name);
			
			if (file_to_delete.delete()) {
				System.out.println("File " + notepad_name + " has been succesfully deleted");
			}
			else {
				System.out.println("Delete failed.");
			}
			
		}
		catch (Exception e) {
			System.out.println("An error occurred: " + e);
		}
		optionMenu();
		
	}

	
	static void optionMenu() { // OPTION MENU - you choose between options [1 - X] to do certain things
		
		System.out.println(emptyline + "-- Option Menu --");
		@SuppressWarnings("resource")
		Scanner get_opt = new Scanner(System.in);
		System.out.println("[1] Create new notepad \n[2] Write to and existing notepad \n[3] Delete notepad \n[00] Exit");
		try {
			int opt = get_opt.nextInt();
			
			if (opt == 1) {
				createNotepad();
			}
			else if (opt == 2) {
				writeNote();
			}
			else if (opt == 3) {
				deleteNotepad();
			}
			else if (opt == 00) {
				System.exit(0);
			}
		}
		catch (Exception e) {
			System.out.println("Please enter a valid value..");
			optionMenu();
		}
		
	}
}
