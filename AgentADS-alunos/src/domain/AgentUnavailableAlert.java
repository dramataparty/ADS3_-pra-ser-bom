package domain;

/**
 * A classe AgentUnavailableAlert representa um alerta de agente indisponível.
 * Implementa a interface Alert.
 */
public class AgentUnavailableAlert implements Alert {
    private String subject;
    private String message;

    /**
     * Construtor da classe AgentUnavailableAlert.
     * 
     * @param subject O assunto do alerta.
     * @param message A mensagem do alerta.
     */
    public AgentUnavailableAlert(String subject, String message) {
        this.subject = subject;
        this.message = message;
    }

    /**
     * Retorna o nome do assunto do alerta.
     * 
     * @return O nome do assunto do alerta.
     */
    @Override
    public String getSubjectName() {
        return subject;
    }

    /**
     * Retorna a mensagem do alerta.
     * 
     * @return A mensagem do alerta.
     */
    @Override
    public String getMessage() {
        return message;
    }
}
