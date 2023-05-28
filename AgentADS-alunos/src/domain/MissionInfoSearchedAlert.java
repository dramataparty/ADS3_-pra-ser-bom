package domain;

/**
 * A classe MissionInfoSearchedAlert representa um alerta de informações de missão pesquisadas.
 * Implementa a interface Alert.
 */
public class MissionInfoSearchedAlert implements Alert {
    private String subject;
    private String message;

    /**
     * Construtor da classe MissionInfoSearchedAlert.
     * 
     * @param subject O assunto do alerta.
     * @param message A mensagem do alerta.
     */
    public MissionInfoSearchedAlert(String subject, String message) {
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
