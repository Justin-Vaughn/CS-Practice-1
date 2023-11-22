import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // creates the ArrayList for the lego sets
        ArrayList<LegoSet> legoSets = parseSetsCSV("sets.csv");

        // parses the instructions to filter
        String[][] filter = filterInstructions();

        // parse the themes into a HashMap
        HashMap<String, Integer> themes = parseThemesCSV("themes.csv");

        // filter the sets into an array
        ArrayList<LegoSet> sets = filterSets(legoSets, filter, themes, 0);
        System.out.println(sets);

        // write the contents to a new CSV file
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

                // creates an array from the row in the CSV file
                String[] legoSetRaw = scanner.nextLine().split(",");

                // ignore first line of the CSV file
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

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

    public static String[][] filterInstructions() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please Enter Instructions for Filter:");
//        String[] filterInstructions = scanner.nextLine().split(",");
        String[] filterInstructions = {"Theme = Christmas", "Year <= 2000", "Part < 1000"};


        // creates the filter array
        String[][] filter = new String[filterInstructions.length][3];

        // loops to fill each filter array from the instructions
        for (int i = 0; i < filterInstructions.length; i++) {

            filter[i] = filterInstructions[i].split(" ");
        }

        return filter;
    }

    public static HashMap<String, Integer> parseThemesCSV(String path) {
        HashMap<String, Integer> themes = new HashMap<>();

        try {
            Scanner scanner = new Scanner(new File(path));
            boolean firstLine = true;

            while (scanner.hasNext()) {
                String[] line = scanner.nextLine().split(",");

                // skips first line of CSV file
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String themeName = line[1];
                int themeID = Integer.parseInt(line[0]);

                // adds non-duplicate themes to hashmap
                themes.putIfAbsent(themeName, themeID);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return themes;
    }

    public static ArrayList<LegoSet> filterSets(ArrayList<LegoSet> sets, String[][] filter, HashMap<String, Integer> themes, int z) {
        ArrayList<LegoSet> filteredSets = new ArrayList<>();

        // recursively loops through the sets, base case: z == filter.length -1
        if (z == filter.length - 1) {
            return sets;
        } else {
            if (filter[z][0].equalsIgnoreCase("theme")) {
                // filter for theme

                for (LegoSet set : sets) {
                    if (set.getThemeID() == themes.get(filter[z][2])) {
                        // the set theme ID matches the theme in the value in HashMap
                        filteredSets.add(set);
                    }
                }

            } else if (filter[z][0].equalsIgnoreCase("year")) {
                // filter for year

                for (LegoSet set : sets) {
                    if (filter[z][0].equalsIgnoreCase("<")
                            && set.getYear() < Integer.parseInt(filter[z][2])) {
                        // the year is less than given value
                        filteredSets.add(set);

                    } else if (filter[z][1].equalsIgnoreCase(">")
                            && set.getYear() > Integer.parseInt(filter[z][2])) {
                        // the year is greater than given value
                        filteredSets.add(set);

                    } else if (filter[z][1].equalsIgnoreCase("<=")
                            && set.getYear() <= Integer.parseInt(filter[z][2])) {
                        // the year is less than or equal to the given value
                        filteredSets.add(set);

                    } else if (filter[z][1].equalsIgnoreCase(">=")
                            && set.getYear() >= Integer.parseInt(filter[z][2])) {
                        // the year is greater than or equal to the given value
                        filteredSets.add(set);

                    } else if (filter[z][1].equalsIgnoreCase("=")
                            && set.getYear() == Integer.parseInt(filter[z][2])) {
                        // the year is equal than given value
                        filteredSets.add(set);
                    }
                }

            } else if (filter[z][0].equalsIgnoreCase("parts")) {
                // filter for parts

                for (LegoSet set : sets) {
                    if (filter[z][1].equalsIgnoreCase("<")
                            && set.getNumOfParts() < Integer.parseInt(filter[z][2])) {
                        // the year is less than given value
                        filteredSets.add(set);

                    } else if (filter[z][1].equalsIgnoreCase(">")
                            && set.getNumOfParts() > Integer.parseInt(filter[z][2])) {
                        // the year is greater than given value
                        filteredSets.add(set);

                    } else if (filter[z][1].equalsIgnoreCase("<=")
                            && set.getNumOfParts() <= Integer.parseInt(filter[z][2])) {
                        // the year is less than or equal to the given value
                        filteredSets.add(set);

                    } else if (filter[z][1].equalsIgnoreCase(">=")
                            && set.getNumOfParts() >= Integer.parseInt(filter[z][2])) {
                        // the year is greater than or equal to the given value
                        filteredSets.add(set);

                    } else if (filter[z][1].equalsIgnoreCase("=")
                            && set.getNumOfParts() == Integer.parseInt(filter[z][2])) {
                        // the year is equal than given value
                        filteredSets.add(set);
                    }
                }
            }
        }
        return filterSets(filteredSets, filter, themes, ++z);
    }
}