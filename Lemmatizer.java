import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Lemmatizer {
    private static Map<String, String> lookupTable = new HashMap<>();
    public static void main(String[] args) {

        String file = "C:\\Users\\wagne\\OneDrive\\Documents\\GitHub\\NLPpipeline-\\lemmatization-en.txt";
        loadLookupTable(file);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a word: ");
        String input = scanner.nextLine().trim();

        String lemma = lookupTable.getOrDefault(input, "Not found");
        System.out.println("Lemma for '" + input + "': " + lemma);
    }

    private static void loadLookupTable(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("  ");
                if (tokens.length == 2) {
                    String inflection = tokens[0].trim();
                    String lemma = tokens[1].trim();
                    lookupTable.put(inflection, lemma);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}