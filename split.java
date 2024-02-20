import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class split {
    public static void main(String[] args) {

        String file = "C:\\Users\\wagne\\OneDrive\\Documents\\GitHub\\NLPpipeline-\\test.text";

         try (BufferedReader reader = new BufferedReader(new FileReader(file));
             Scanner scanner = new Scanner(System.in);
             FileWriter writer = new FileWriter("pos_labels.csv")) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("[, ?.@]+");

            for (String word : words) {
                System.out.print("Enter POS label for '" + word + "': ");
                String label = scanner.nextLine();

                writer.write(word + "," + label + "\n");
            }
        }

            System.out.println("POS labels saved to pos_labels.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
