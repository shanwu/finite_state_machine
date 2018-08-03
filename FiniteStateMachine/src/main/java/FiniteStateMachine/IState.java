package FiniteStateMachine;

import java.util.Map;

/**
 * the public interface for others to get state related info such as state name
 * and mappings to connected states.
 */
interface IState {
    // Returns the mapping for which one action will lead to another state
    Map<String, IState> getAdjacentStates();

    String getStateDesc();

    void addTransit(Action action, IState nextState);

    void removeTransit(String targetStateDesc);
}