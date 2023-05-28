package domain;

public class MissionInfoSearchedAlert implements Alert {
    private String subject;
    private String message;

    public MissionInfoSearchedAlert(String subject, String message) {
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