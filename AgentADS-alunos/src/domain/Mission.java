package domain;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe que representa uma missão.
 */
public class Mission implements Iterable<String>, PropertyChangeListener {
    private String codeName;
    private Agent responsibleAgent;
    private List<Agent> participants;
    private List<MissionObserver> observers;

    /**
     * Construtor da classe Mission.
     *
     * @param codeName         O nome da missão.
     * @param responsibleAgent O agente responsável pela missão.
     */
    public Mission(String codeName, Agent responsibleAgent) {
        this.codeName = codeName;
        this.responsibleAgent = responsibleAgent;
        this.participants = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    /**
     * Adiciona um participante à missão.
     *
     * @param agent O agente a ser adicionado como participante.
     */
    public void addParticipant(Agent agent) {
        participants.add(agent);
    }

    /**
     * Adiciona um observador à missão.
     *
     * @param observer O observador a ser adicionado.
     */
    public void addObserver(MissionObserver observer) {
        observers.add(observer);
    }

    /**
     * Remove um observador da missão.
     *
     * @param observer O observador a ser removido.
     */
    public void removeObserver(MissionObserver observer) {
        observers.remove(observer);
    }

    /**
     * Obtém o nome da missão.
     *
     * @return O nome da missão.
     */
    public String getCodeName() {
        return codeName;
    }

    /**
     * Obtém o agente responsável pela missão.
     *
     * @return O agente responsável.
     */
    public Agent getResponsibleAgent() {
        return responsibleAgent;
    }

    /**
     * Obtém os participantes da missão.
     *
     * @return Os participantes da missão.
     */
    public Iterable<String> getParticipants() {
        notifyObservers("Mission " + codeName + " info was searched");
        List<String> participantCodeNames = new ArrayList<>();
        for (Agent agent : participants) {
            if (!agent.equals(responsibleAgent)) {
                participantCodeNames.add(agent.getCodeName());
            }
        }
        return participantCodeNames;
    }

    /**
     * Método chamado quando ocorre uma alteração em uma propriedade.
     *
     * @param ev O evento de alteração de propriedade.
     */
    @Override
    public void propertyChange(PropertyChangeEvent ev) {
        if (ev.getNewValue() instanceof AgentUnavailableAlert) {
            AgentUnavailableAlert alert = (AgentUnavailableAlert) ev.getNewValue();
            String agentToRemove = alert.getSubjectName();

            Agent removedAgent = null;
            for (Agent agent : participants) {
                if (agent.getCodeName().equals(agentToRemove)) {
                    removedAgent = agent;
                    break;
                }
            }

            if (removedAgent != null) {
                participants.remove(removedAgent);
                removedAgent.removeObserver(this);
            }
        }
    }

    /**
     * Notifica os observadores da missão com uma determinada mensagem.
     *
     * @param message A mensagem a ser enviada aos observadores.
     */
    private void notifyObservers(String message) {
        MissionInfoSearchedAlert alert = new MissionInfoSearchedAlert(message);
        for (MissionObserver observer : observers) {
            observer.receiveAlert(alert);
        }
    }

    /**
