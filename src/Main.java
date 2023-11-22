import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // creates the ArrayList for the lego sets
        ArrayList<LegoSet> legoSets = parseSetsCSV("sets.csv");

        // parses the instructions to filter
        requestInstructions(legoSets);
    }

    public static ArrayList<LegoSet> parseSetsCSV(String path) {
        // creates ArrayList data structure to store the lego sets
        ArrayList<LegoSet> legoSets = new ArrayList<>();

        try {
            // creates a Scanner object from the file
            Scanner scanner = new Scanner(new File(path));

            boolean isFirstLine = true;

            // loops each line of the file
            while (scanner.hasNext()) {
                String line = scanner.nextLine();

                // ignore first line of the CSV file
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                // creates an array from the row in the CSV file
                String[] legoSetRaw = line.split(",");

                // skip if there are improper commas in the row
                if (legoSetRaw.length != 5) continue;

                // creates the object from the CSV row
                LegoSet set =
                        new LegoSet(
                                legoSetRaw[0],
                                legoSetRaw[1],
                                Integer.parseInt(legoSetRaw[2]),
                                Integer.parseInt(legoSetRaw[3]),
                                Integer.parseInt(legoSetRaw[4])
                        );
                // adds the object to the lego set ArrayList
                legoSets.add(set);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return legoSets;
    }

    public static ArrayList<String[]> requestInstructions(ArrayList<LegoSet> sets) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please Enter Instructions for Filter:");
//        String[] filterInstructions = scanner.nextLine().split(",");
        String[] filterInstructions = {"Theme=Christmas", "Year<=2000", "Part<1000"};

        System.out.println(Arrays.toString(filterInstructions));

    }
}