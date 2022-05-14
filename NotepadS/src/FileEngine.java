import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileEngine {

    static String filecontent;
    static String filename;
    static String filelocation = "./Files/";


    static void createNewFile(String filename) {

        try {
            File file = new File(filelocation + filename + ".txt");
            filename = filelocation + filename + ".txt";

            if (file.createNewFile()) {
                System.out.println("File created");
                filecontent = "";
                filecontent = Files.readString(Path.of(filelocation + filename + ".txt"));
            }
            else {
                System.out.println("File already exists.");
            }
        }
        catch (IOException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }

    }

    static void openExistingFile(File file) {

        filename = file.getAbsolutePath();

        try {
            FileReader fileReader = new FileReader(file);
            filecontent = Files.readString(Path.of(file.getAbsolutePath()));
            System.out.println(filecontent);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void saveToFile(String newcontent) {
        filecontent = newcontent;

        try {
            FileWriter fw = new FileWriter(filename);
            fw.write(filecontent);
            fw.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void deleteFile(File f) {

        if (f.delete()) {
            System.out.println("File was deleted");
        }
        else {
            System.out.println("Delete failed");
        }

    }

    static void createDefaultFolder() {
        File file = new File("Files");
        file.mkdir();
    }

}
