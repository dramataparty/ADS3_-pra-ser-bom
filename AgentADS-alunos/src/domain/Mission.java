package domain;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Mission implements Iterable<String>, PropertyChangeListener {
    private String codeName;
    private Agent responsibleAgent;
    private List<Agent> participants;
    private List<MissionObserver> observers;

    public Mission(String codeName, Agent responsibleAgent) {
        this.codeName = codeName;
        this.responsibleAgent = responsibleAgent;
        this.participants = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public void addParticipant(Agent agent) {
        participants.add(agent);
    }

    public void addObserver(MissionObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(MissionObserver observer) {
        observers.remove(observer);
    }

    public String getCodeName() {
        return codeName;
    }

    public Agent getResponsibleAgent() {
        return responsibleAgent;
    }

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

    private void notifyObservers(String message) {
        MissionInfoSearchedAlert alert = new MissionInfoSearchedAlert(message);
        for (MissionObserver observer : observers) {
            observer.receiveAlert(alert);
        }
    }

    @Override
    public Iterator<String> iterator() {
        return getParticipants().iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mission: ").append(codeName).append("\n");
        sb.append("Responsible Agent: ").append(responsibleAgent).append("\n");
        sb.append("Participants: ").append("\n");
        for (Agent agent : participants) {
            sb.append(agent).append("\n");
        }
        return sb.toString();
    }
}
