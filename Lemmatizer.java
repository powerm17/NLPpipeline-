import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Lemmatizer {
    private static Map<String, String> lookupTable = new HashMap<>();
    public static void main(String[] args) {

        String file = "C:\\Users\\wagne\\OneDrive\\Documents\\GitHub\\NLPpipeline-\\lemmatization-en.txt";
        lemmatize(file);
        
    }

    static String lemmatize(String file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
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
        return file;
    }
}