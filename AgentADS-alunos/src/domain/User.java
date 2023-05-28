package domain;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe que representa um utilizador.
 */
public class User implements PropertyChangeListener {
    private String username;
    private Set<String> alerts;

    /**
     * Construtor da classe User.
     *
     * @param username O nome de utilizador do utilizador.
     */
    public User(String username) {
        this.username = username;
        this.alerts = new HashSet<>();
    }

    /**
     * Método chamado quando ocorre uma alteração em uma propriedade.
     *
     * @param ev O evento de alteração de propriedade.
     */
    @Override
    public void propertyChange(PropertyChangeEvent ev) {
        Object newValue = ev.getNewValue();
        if (newValue instanceof AgentUnavailableAlert ||
                newValue instanceof AgentInfoSearchedAlert ||
                newValue instanceof MissionInfoSearchedAlert) {
            Alert alert = (Alert) newValue;
            alerts.add(alert.getMessage());
        }
    }

    /**
     * Retorna uma representação em forma de string do utilizador.
     *
     * @return A representação em string do utilizador.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User: ").append(username).append("\n");
        sb.append("Alerts: ").append("\n");
        for (String alert : alerts) {
            sb.append(alert).append("\n");
        }
        return sb.toString();
    }
}
