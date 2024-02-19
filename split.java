// Java program to demonstrate working of split()

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class split {
    public static void main(String args[]) throws IOException {
        String file = "C:\\Users\\wagne\\OneDrive\\Documents\\GitHub\\NLPpipeline-\\test.text";
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String str = reader.readLine();
            String[] arrOfStr = str.split("\\.");

            for (String a : arrOfStr)
                System.out.println(a.trim());

            // Close reader
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
