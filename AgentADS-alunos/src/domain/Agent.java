import java.util.ArrayList;
import java.util.List;

// Agent class
class Agent {
    private String codeName;
    private IdentityCodifierStrategy codifierStrategy;
    private List<Document> documents;
    private Availability state;

    public Agent(String codeName) {
        this.codeName = codeName;
        this.codifierStrategy = new MyCodifierStrategy();
        this.documents = new ArrayList<>();
        this.state = Availability.AVAILABLE;
    }

    public String getCodifierName() {
        String name = codifierStrategy.getName();
        String message = "Agent " + codeName + " codifier name was searched";

    notify    s(new AgentInfoSearchedAlert(message));
        return name;
    }

    public Iterable<String> documentReferences() {
        List<String> references = new ArrayList<>();
        for (Document document : documents) {
            references.add(document.getReference());
        }
        String message = "Agent " + codeName + " doc references were searched";

    notify    s(new AgentInfoSearchedAlert(message));
        return references;
    }

    public Iterable<String> decodifiedDocText(String ref) {
        List<String> decodedText = new ArrayList<>();
        for (Document document : documents) {
            if (document.getReference().equals(ref)) {
                Iterable<String> lines = codifierStrategy.decode(codeName, List.of(document.getText()));
                for (String line : lines) {
                    decodedText.add(line);
                }
                String message = "Agent " + codeName + " document " + ref + " was searched";

    notify    s(new AgentInfoSearchedAlert(message));
                break;
            }

    }return decodedText;}

    public void becomeUnavailable(String unavail) {
        try {
            state = Availability.valueOf(unavail);
            String message = "Agent " + codeName + " became " + unavail;

    notify    s(new AgentUnavailableAlert(message));
        }catch(

    IllegalArgumentException e)
    {
        System.out.println("Invalid availability value: " + unavail);
    }
    }

    public void addDocument(Document document) {
        documents.add(document);
    }

    public Availability getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Agent: " + codeName + ", State: " + state;
    }
}
