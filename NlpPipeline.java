import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class NlpPipeline {

    public static void main(String[] args) {
        String file = "C:\\Users\\wagne\\OneDrive\\Documents\\GitHub\\NLPpipeline-\\test.text";
        String[] word = segmentword(file);
        List<String[]> tokensPerSentence = tokenizeword(word);
        Map<String, String> posTags = manualPosTag("pos_labels.csv");
        Map<String, String> lemmas = Lemmatization("lemmatization-en.txt");
        Set<String> stopWords = buildStopWordsSet();

        printFinalResult(word, tokensPerSentence, posTags, lemmas, stopWords);
    }

    private static String[] segmentword(String file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String str = reader.readLine();
            String[] arrOfStr = str.split("[, ?.@]+");

            for (String a : arrOfStr)
                System.out.println(a.trim());
                
            reader.close();
            return arrOfStr;

        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    private static List<String[]> tokenizeword(String[] word) {
        List<String[]> tokensPerSentence = new ArrayList<>();
        for (String sentence : word) {
            tokensPerSentence.add(sentence.split("\\s+"));
        }
        return tokensPerSentence;
    }

    private static Map<String, String> manualPosTag(String csvFilePath) {
        Map<String, String> posTags = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String word = parts[0].trim();
                    String tag = parts[1].trim();
                    posTags.put(word, tag);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return posTags;
    }

    private static Map<String, String> Lemmatization(String filePath) {
        Map<String, String> lemmas = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("   ");
                if (parts.length == 2) {
                    String word = parts[0].trim();
                    String lemma = parts[1].trim();
                    lemmas.put(word, lemma);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lemmas;
    }

    private static Set<String> buildStopWordsSet() {
        return new HashSet<>(Arrays.asList("the", "and", "is"));
    }

    private static void printFinalResult(String[] word, List<String[]> tokensPerSentence,
            Map<String, String> posTags, Map<String, String> lemmas,
            Set<String> stopWords) {
        for (int i = 0; i < word.length; i++) {
            System.out.println("Word " + (i + 1) + ": \"" + word[i] + "\"");
            String[] tokens = tokensPerSentence.get(i);
            for (String token : tokens) {
                String lemma = lemmas.getOrDefault(token, token);
                if (!stopWords.contains(token)) {
                    System.out.println("  Token: " + token + " (" + lemma + ")");
                } else {
                    System.out.println("  Removed Token: " + token);
                }
            }
            System.out.println();
        }
    }
}
