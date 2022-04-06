import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Scanner;

public class Main {
	
	static String emptyline = " \n";
	public static void main(String[] args) {
		
		optionMenu();
		
	}
	
	static void createNotepad() {
		
		System.out.println(emptyline + "-- Create Notepad --");
		
		Scanner get_file_name = new Scanner(System.in);
		System.out.println("Enter name of the new notepad: ");
		
		try {
			File file_to_create = new File("files/"+get_file_name.nextLine()+".txt");
		    
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
		}
		
		System.out.println("Done.");
		optionMenu();
		
	}
	
	static void writeNote() {
		
		System.out.println(emptyline + "-- Write a note --");
		
		// SEE THE DIRECTORY CONTENT
		File directory_path = new File("files/");
		String[] content = directory_path.list();
		System.out.println("Your notepads:");
		
		for (int i = 0; i < content.length; i++) {
			System.out.println("<"+i+">"+ " " + content[i]);
		}
		
		// Choose the file
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
			Scanner get_note = new Scanner(System.in);
			System.out.println("Enter your note (Leave empty if you only wanna read):");
			String note = get_note.nextLine() + "\n";
			
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

		}
		catch (Exception e) {
			System.out.println("An error occurred: " + e);
			writeNote();
		}
		
		System.out.println("Done.");
		optionMenu();
		
	}

	
	static void optionMenu() {
		
		System.out.println(emptyline + "-- Option Menu --");
		Scanner get_opt = new Scanner(System.in);
		System.out.println("[1] Create new notepad \n[2] Write to and existing notepad \n[00] Exit");
		try {
			int opt = get_opt.nextInt();
			
			if (opt == 1) {
				createNotepad();
			}
			else if (opt == 2) {
				writeNote();
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
