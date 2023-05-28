package domain;

public class AgentUnavailableAlert implements Alert {
    private String subject;
    private String message;

    public AgentUnavailableAlert(String subject, String message) {
        this.subject = subject;
        this.message = message;
    }

    @Override
    public String getSubjectName() {
        return subject;
    }

    @Override
    public String getMessage() {
        return message;
    }
}