public enum Availability {
    AVAILABLE,
    ARRESTED,
    DESERTED,
    ILL,
    DEAD,
    RETIRED
}

public class Agent {
    private Availability state;

    public Agent() {
        state = Availability.AVAILABLE;
    }

    public void freeUp() {
        state = Availability.AVAILABLE;
    }

    public void arrest() {
        state = Availability.ARRESTED;
    }

    public void leave() {
        state = Availability.DESERTED;
    }

    public void infect() {
        state = Availability.ILL;
    }

    public void kill() {
        state = Availability.DEAD;
    }

    public void retire() {
        state = Availability.RETIRED;
    }

    public Availability getState() {
        return state;
    }
}

