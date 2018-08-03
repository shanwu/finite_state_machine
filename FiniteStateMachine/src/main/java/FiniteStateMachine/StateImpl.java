package FiniteStateMachine;

import java.util.HashMap;
import java.util.Map;

public class StateImpl implements IState {
    private HashMap<String, IState> mMapping = new HashMap<>();
    private String mStateName;

    public StateImpl(String stateName) {
        mStateName = stateName;
    }

    @Override
    public Map<String, IState> getAdjacentStates() {
        return mMapping;
    }

    @Override
    public String getStateDesc() {
        return mStateName;
    }

    @Override
    public void addTransit(Action action, IState state) {
        mMapping.put(action.toString(), state);
    }

    @Override
    public void removeTransit(String targetStateDesc) {
        // get action which directs to target state
        String targetAction = null;
        for (Map.Entry<String, IState> entry : mMapping.entrySet()) {
            IState state = entry.getValue();
            if (state.getStateDesc().equals(targetStateDesc)) {
                targetAction = entry.getKey();
            }
        }
        mMapping.remove(targetAction);
    }

}