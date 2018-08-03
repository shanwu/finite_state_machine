package FiniteStateMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FiniteStateMachineImpl implements IFiniteStateMachine {
    private IState mStartState;
    private IState mEndState;
    private IState mCurrentState;
    private ArrayList<IState> mAllStates = new ArrayList<>();
    private HashMap<String, ArrayList<IState>> mMapForAllStates = new HashMap<>();

    public FiniteStateMachineImpl(){}
    @Override
    public void setStartState(IState startState) {
        mStartState = startState;
        mCurrentState = startState;
        mAllStates.add(startState);
        // todo: might have some value
        mMapForAllStates.put(startState.getStateDesc(), new ArrayList<IState>());
    }

    @Override
    public void setEndState(IState endState) {
        mEndState = endState;
        mAllStates.add(endState);
        mMapForAllStates.put(endState.getStateDesc(), new ArrayList<IState>());
    }

    @Override
    public void addState(IState startState, IState newState, Action action) {
        // validate startState, newState and action

        // update mapping in finite state machine
        mAllStates.add(newState);
        final String startStateDesc = startState.getStateDesc();
        final String newStateDesc = newState.getStateDesc();
        mMapForAllStates.put(newStateDesc, new ArrayList<IState>());
        ArrayList<IState> adjacentStateList = null;
        if (mMapForAllStates.containsKey(startStateDesc)) {
            adjacentStateList = mMapForAllStates.get(startStateDesc);
            adjacentStateList.add(newState);
        } else {
            mAllStates.add(startState);
            adjacentStateList = new ArrayList<>();
            adjacentStateList.add(newState);
        }
        mMapForAllStates.put(startStateDesc, adjacentStateList);

        // update mapping in startState
        for (IState state : mAllStates) {
            boolean isStartState = state.getStateDesc().equals(startState.getStateDesc());
            if (isStartState) {
                startState.addTransit(action, newState);
            }
        }
    }

    @Override
    public void removeState(String targetStateDesc) {
        // validate state
        if (!mMapForAllStates.containsKey(targetStateDesc)) {
            throw new RuntimeException("Don't have state: " + targetStateDesc);
        } else {
            // remove from mapping
            mMapForAllStates.remove(targetStateDesc);
        }

        // update all state
        IState targetState = null;
        for (IState state : mAllStates) {
            if (state.getStateDesc().equals(targetStateDesc)) {
                targetState = state;
            } else {
                state.removeTransit(targetStateDesc);
            }
        }

        mAllStates.remove(targetState);

    }

    @Override
    public IState getCurrentState() {
        return mCurrentState;
    }

    @Override
    public void transit(Action action) {
        if (mCurrentState == null) {
            throw new RuntimeException("Please setup start state");
        }
        Map<String, IState> localMapping = mCurrentState.getAdjacentStates();
        if (localMapping.containsKey(action.toString())) {
            mCurrentState = localMapping.get(action.toString());
        } else {
            throw new RuntimeException("No action start from current state");
        }
    }

    @Override
    public IState getStartState() {
        return mStartState;
    }

    @Override
    public IState getEndState() {
        return mEndState;
    }
}