import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Lemmatizer {
    private final Map<String, String> lemmatizationDict;

    public Lemmatizer(String csvFilePath) {
        lemmatizationDict = new HashMap<>();
        loadLemmatizationData(csvFilePath);
    }

    private void loadLemmatizationData(String csvFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String word = parts[0].trim();
                    String lemma = parts[1].trim();
                    lemmatizationDict.put(word, lemma);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String lemmatize(String word) {
        return lemmatizationDict.getOrDefault(word, word);
    }

    public static void main(String[] args) {
        String csvFilePath = "C:\\Users\\wagne\\OneDrive\\Documents\\GitHub\\NLPpipeline-\\pos_labels.csv"; 
        Lemmatizer lemmatizer = new Lemmatizer(csvFilePath);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a word: ");
        String input = scanner.nextLine();
        String lemma = lemmatizer.lemmatize(input);
        System.out.println("Lemmatized form of '" + input + "': " + lemma);
    }
}

