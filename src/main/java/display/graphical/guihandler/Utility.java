package display.graphical.guihandler;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import java.io.*;

public class Utility {

    public static Image getImageFromFolder(String name) {
        Image image = null;
        try {
            FileInputStream fileIcon = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/icone/" + name);
            image = new Image(fileIcon);
            fileIcon.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static int checkBoxAreEnable(CheckBox checkBox1, CheckBox checkBox2) {
        return (checkBox1 == null) ? 4 : 0;
    }

    public static int inputContentIsGreat(TextField textField) {
        if (textField == null || textField.getText().length() < 6)
            return 5;// pas de mot ou null
        String text = textField.getText();
        int blankSpace = 0;
        int sizeOfLine = 0;

        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ')
                blankSpace += 1;
            else sizeOfLine += 1;
        }
        if (blankSpace > 3) return 1; // trop d'espace.
        if (sizeOfLine > 30) return 2; // trop de caractère utilisé.
        return verifyEntryFormat(text);
    }

    private static int verifyEntryFormat(String playerEntry) {
        for (int i = 0; i < playerEntry.length(); i++) {
            char tmp = playerEntry.charAt(i);
            if (tmp < 48 || (tmp > 57 && tmp < 65) || (tmp > 90 && tmp < 97) || tmp > 122) return 3; //mauvais caractère
        }
        return 0;
    }
}
