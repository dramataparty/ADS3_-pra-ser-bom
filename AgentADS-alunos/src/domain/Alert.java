package domain;

/**
 * A interface Alert define tipos de alertas que se querem observados.
 * Ela contém dois métodos:
 * - {@link #getSubjectName()} que retorna o nome do sujeito que provocou o alerta.
 * - {@link #getMessage()} que retorna a mensagem associada ao alerta.
 */
public interface Alert {
    
    /**
     * Retorna o nome do sujeito que provocou o alerta.
     *
     * @return O nome do sujeito que provocou o alerta.
     */
    String getSubjectName();
    
    /**
     * Retorna a mensagem associada ao alerta.
     *
     * @return A mensagem associada ao alerta.
     */
    String getMessage();
}
