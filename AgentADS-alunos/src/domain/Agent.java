import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um agente.
 */
class Agent {
    private String codeName;
    private ICodifierStrategy codifierStrategy;
    private List<Document> documents;
    private Availability state;

    /**
     * Construtor da classe Agent.
     *
     * @param codeName O nome do agente.
     */
    public Agent(String codeName) {
        this.codeName = codeName;
        this.codifierStrategy = new MyCodifierStrategy();
        this.documents = new ArrayList<>();
        this.state = Availability.AVAILABLE;
    }

    /**
     * Obtém o nome do codificador utilizado pelo agente.
     *
     * @return O nome do codificador.
     */
    public String getCodifierName() {
        String name = codifierStrategy.getName();
        String message = "Agent " + codeName + " codifier name was searched";
        notifyObservers(new AgentInfoSearchedAlert(message));
        return name;
    }

    /**
     * Obtém as referências dos documentos do agente.
     *
     * @return As referências dos documentos.
     */
    public Iterable<String> documentReferences() {
        List<String> references = new ArrayList<>();
        for (Document document : documents) {
            references.add(document.getReference());
        }
        String message = "Agent " + codeName + " doc references were searched";
        notifyObservers(new AgentInfoSearchedAlert(message));
        return references;
    }

    /**
     * Obtém o texto decodificado de um documento com base na referência.
     *
     * @param ref A referência do documento.
     * @return O texto decodificado do documento.
     */
    public Iterable<String> decodedDocText(String ref) {
        List<String> decodedText = new ArrayList<>();
        for (Document document : documents) {
            if (document.getReference().equals(ref)) {
                Iterable<String> lines = codifierStrategy.decode(codeName, List.of(document.getText()));
                for (String line : lines) {
                    decodedText.add(line);
                }
                String message = "Agent " + codeName + " document " + ref + " was searched";
                notifyObservers(new AgentInfoSearchedAlert(message));
                break;
            }
        }
        return decodedText;
    }

    /**
     * Torna o agente indisponível.
     *
     * @param unavail O estado de indisponibilidade do agente.
     */
    public void becomeUnavailable(String unavail) {
        try {
            state = Availability.valueOf(unavail.toUpperCase());
            String message = "Agent " + codeName + " became " + unavail;
            notifyObservers(new AgentUnavailableAlert(message));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid availability value: " + unavail);
        }
    }

    /**
     * Adiciona um documento à lista de documentos do agente.
     *
     * @param document O documento a ser adicionado.
     */
    public void addDocument(Document document) {
        documents.add(document);
    }

    /**
     * Obtém o estado atual do agente.
     *
     * @return O estado do agente.
     */
    public Availability getState() {
        return state;
    }

/**
     * Retorna uma representação em forma de string do agente.
     *
     * @return A representação em string do agente.
     */
    @Override
    public String toString() {
        return "Agent: " + codeName + ", State: " + state;
