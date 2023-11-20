import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<LegoSet> legoSets = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File("sets.csv"));

            boolean isFirstLine = true;

            while (scanner.hasNext()) {
                String line = scanner.nextLine();

                if (isFirstLine) {   // ignore first line
                    isFirstLine = false;
                    continue;
                }

                String[] legoSetRaw = line.split(",");

                if (legoSetRaw.length != 5) {
                    continue;
                }

                LegoSet set =
                        new LegoSet(
                                legoSetRaw[0],
                                legoSetRaw[1],
                                Integer.parseInt(legoSetRaw[2]),
                                Integer.parseInt(legoSetRaw[3]),
                                Integer.parseInt(legoSetRaw[4])
                        );
                legoSets.add(set);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scanner scanner2 = new Scanner(System.in);
        System.out.println("What do you want to filter? [Year | Theme | Parts]:");
        String filterType = scanner2.nextLine();

        System.out.println("Enter an inequality [\"less\" | \"equals\" | \"more\"]:");
        String inequality = scanner2.nextLine();

        System.out.println("Enter a range for the inequality");
        int range = scanner2.nextInt();

        for (LegoSet legoSet : legoSets) {
            if (filterType.equalsIgnoreCase("year")) {
                if (inequality.equalsIgnoreCase("less")) {
                    if (legoSet.getYear() < range) {
                        System.out.println(legoSet);
                    }
                } else if (inequality.equalsIgnoreCase("equals")) {
                    if (legoSet.getYear() == range) {
                        System.out.println(legoSet);
                    }
                } else if (inequality.equalsIgnoreCase("more")) {
                    if (legoSet.getYear() > range) {
                        System.out.println(legoSet);
                    }
                }

            } else if (filterType.equalsIgnoreCase("theme")) {
                // add later

            } else if (filterType.equalsIgnoreCase("parts")) {
                if (inequality.equalsIgnoreCase("less")) {
                    if (legoSet.getNumOfParts() < range) {
                        System.out.println(legoSet);
                    }
                } else if (inequality.equalsIgnoreCase("equals")) {
                    if (legoSet.getNumOfParts() == range) {
                        System.out.println(legoSet);
                    }
                } else if (inequality.equalsIgnoreCase("more")) {
                    if (legoSet.getNumOfParts() > range) {
                        System.out.println(legoSet);
                    }
                }
            }
        }
    }
}