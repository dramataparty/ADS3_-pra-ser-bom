public enum DocNature {
    TO_AGENT("ToAg"),
    FROM_AGENT("FrAg"),
    INTERNAL("Intr")
    private final String word;

    FileType(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }
}
public enum Availability {
    ARRESTED,
    DESERTED,
    ILL,
    DEAD,
    RETIRED
}