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




public class Agent {
    private AgentState state;

    public Agent() {
        state = AgentState.AVAILABLE
    }

    public void free_up() {
        state = AgentState.AVAILABLE;
    }

    public void arrest() {
        state = AgentState.ARRESTED;
    }
    public void leave() {
        state = AgentState.DESERTED;
    }

    public void infect() {
        state = AgentState.ILL;
    }

    public void kill() {
        state = AgentState.DEAD;
    }

    public void retire(){
        state = AgentState.RETIRED;
    }

    public AgentState getState() {
        return state;
    }
}
public enum Availability {
    AVAILABLE,
    ARRESTED,
    DESERTED,
    ILL,
    DEAD,
    RETIRED
}