public class LegoSet {
    private String setID;
    private String name;
    private int year;
    private int themeID;
    private int numOfParts;
    public LegoSet(String setID, String name, int year, int themeID, int numOfParts) {
        this.setID = setID;
        this.name = name;
        this.year = year;
        this.themeID = themeID;
        this.numOfParts = numOfParts;
    }

    public String getSetID() {
        return setID;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public int getThemeID() {
        return themeID;
    }

    public int getNumOfParts() {
        return numOfParts;
    }

    public String toString() {
        return "SetID: " + getSetID() + " Name: " + getName() + " Year: " + getYear() + " ThemeID: " + getThemeID() + " numOfParts: " + getNumOfParts();
    }
}
