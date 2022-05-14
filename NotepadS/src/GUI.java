import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;

public class GUI {

    GUI() {
        titleScreen();
    }

    void titleScreen() {

        // Declare gui content

        JFrame ts_frame = new JFrame("Notepad - Menu"); // Declare the frame
        ts_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ts_frame.setResizable(false);

        JPanel option_panel = new JPanel(); // Declare the panel
        option_panel.setLayout(new FlowLayout());

        JButton new_btn = new JButton("New file");
        new_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                createNew();
            }
        });

        JButton open_btn = new JButton("Open existing");
        open_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openExisting();
            }
        });

        JButton delete_btn = new JButton("Delete");
        delete_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                deleteFile();
            }
        });

        // Pack gui content

        ts_frame.add(option_panel);
        option_panel.add(new_btn);
        option_panel.add(open_btn);
        option_panel.add(delete_btn);

        ts_frame.pack();
        ts_frame.setVisible(true);

    }

    void createNew() {

        JFrame create_frame = new JFrame("Create new file");
        create_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        create_frame.setResizable(false);
        create_frame.setLayout(new GridLayout(3,2));

        JLabel name_lbl = new JLabel("Name");
        JTextField name_tf = new JTextField(20);

        JButton submitbutton = new JButton("Create");
        submitbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FileEngine.createNewFile(name_tf.getText());
                titleScreen();
                create_frame.dispose();
            }
        });

        JButton cancelbutton = new JButton("Cancel");
        cancelbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                create_frame.dispose();
            }
        });

        // Pack content

        create_frame.add(name_lbl);
        create_frame.add(name_tf);
        create_frame.add(submitbutton);
        create_frame.add(cancelbutton);

        create_frame.pack();
        create_frame.setVisible(true);

    }

    void openExisting() {

        JFrame oe_frame = new JFrame("Open existing file");
        oe_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        oe_frame.setResizable(false);
        oe_frame.setLayout(new BorderLayout());

        JFileChooser filechooser = new JFileChooser(FileEngine.filelocation);
        filechooser.setApproveButtonText("Open");
        filechooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) { // DECLARES THE FILE CONTENT IN FILEENGINE

            }
        });

        // Pack Content
        oe_frame.add(filechooser);

        oe_frame.pack();
        oe_frame.setVisible(true);

    }

    void showFile(String filecontent) {

        JFrame show_frame = new JFrame("Your note: ");
        show_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        show_frame.setResizable(false);
        show_frame.setLayout(new BorderLayout());

        JTextArea textarea = new JTextArea(20,50);
        textarea.setText(filecontent);

        JPanel btn_panel = new JPanel();
        btn_panel.setLayout(new FlowLayout());

        JButton savebutton = new JButton("Save"); // Add save function
        savebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FileEngine.saveToFile(textarea.getText());

            }
        });

        JButton cancelbutton = new JButton("Close and exit"); // Exit
        cancelbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                titleScreen();
                show_frame.dispose();
            }
        });

        // Pack content

        btn_panel.add(savebutton);
        btn_panel.add(cancelbutton);

        show_frame.add(textarea, BorderLayout.NORTH);
        show_frame.add(btn_panel, BorderLayout.SOUTH);

        show_frame.pack();
        show_frame.setVisible(true);

    }

    void deleteFile() {

        JFrame del_frame = new JFrame("Delete file");
        del_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        del_frame.setResizable(false);
        del_frame.setLayout(new BorderLayout());

        JFileChooser filechooser = new JFileChooser(FileEngine.filelocation);
        filechooser.setApproveButtonText("Delete");
        filechooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) { // DECLARES THE FILE CONTENT IN FILEENGINE
                try {
                    File f = filechooser.getSelectedFile();
                    FileEngine.deleteFile(f);

                    titleScreen();
                    del_frame.dispose();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Pack Content
        del_frame.add(filechooser);

        del_frame.pack();
        del_frame.setVisible(true);


    }

}
