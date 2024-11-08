import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

import javax.swing.JFileChooser;

public class App {

    public static void main(String[] args) {
        HashSet<String> dictionary = readDictionary();
        if (dictionary.size() != 72875) {
            System.out.println("Error reading dictionary.");
            return;
        }

        File inputFile = getInputFileNameFromUser();
        if (inputFile == null) {
            System.out.println("No file selected. Exiting.");
            return;
        }

        checkFile(inputFile, dictionary);
    }

    private static HashSet<String> readDictionary() {
        HashSet<String> dictionary = new HashSet<>();
        try {
            Scanner filein = new Scanner(
                    new File("src/words.txt"));
            while (filein.hasNext()) {
                String word = filein.next().toLowerCase();
                dictionary.add(word);
            }
            filein.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return dictionary;
    }

    private static void checkFile(File inputFile, HashSet<String> dictionary) {
        try {
            Scanner in = new Scanner(inputFile);
            in.useDelimiter("[^a-zA-Z]+");

            while (in.hasNext()) {
                String word = in.next().toLowerCase();
                if (!dictionary.contains(word)) {
                    System.out.println(word + ": ");
                    TreeSet<String> suggestions = corrections(word, dictionary);
                    if (suggestions.isEmpty()) {
                        System.out.println("(no suggestions)");
                    } else {
                        suggestions.forEach(System.out::println);
                    }
                    System.out.println();
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static File getInputFileNameFromUser() {
        JFileChooser fileDialog = new JFileChooser();
        fileDialog.setDialogTitle("Select File for Input");
        int option = fileDialog.showOpenDialog(null);

        if (option != JFileChooser.APPROVE_OPTION) {
            return null;
        } else {
            return fileDialog.getSelectedFile();
        }
    }

    private static TreeSet<String> corrections(String badWord, HashSet<String> dictionary) {
        TreeSet<String> suggestions = new TreeSet<>();

        // Delete any one of the letters from the misspelled word
        for (int i = 0; i < badWord.length(); i++) {
            String deletedChar = badWord.substring(0, i) + badWord.substring(i + 1);
            if (dictionary.contains(deletedChar)) {
                suggestions.add(deletedChar);
            }
        }

        // Change any letter in the misspelled word to any other letter
        for (int i = 0; i < badWord.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                String changedChar = badWord.substring(0, i) + ch + badWord.substring(i + 1);
                if (dictionary.contains(changedChar)) {
                    suggestions.add(changedChar);
                }
            }
        }

        // Insert any letter at any point in the misspelled word
        for (int i = 0; i <= badWord.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                String insertedChar = badWord.substring(0, i) + ch + badWord.substring(i);
                if (dictionary.contains(insertedChar)) {
                    suggestions.add(insertedChar);
                }
            }
        }

        // Swap any two neighboring characters in the misspelled word
        for (int i = 0; i < badWord.length() - 1; i++) {
            String swappedChars = badWord.substring(0, i) + badWord.charAt(i + 1) + badWord.charAt(i) +
                    badWord.substring(i + 2);
            if (dictionary.contains(swappedChars)) {
                suggestions.add(swappedChars);
            }
        }

        // Insert a space at any point in the misspelled word
        for (int i = 0; i <= badWord.length(); i++) {
            String withSpace = badWord.substring(0, i) + " " + badWord.substring(i);
            if (dictionary.contains(withSpace)) {
                suggestions.add(withSpace);
            }
        }

        return suggestions;
    }
}
