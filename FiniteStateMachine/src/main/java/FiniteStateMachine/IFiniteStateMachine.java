package FiniteStateMachine;

interface IFiniteStateMachine {
    void setStartState(IState startState);

    void setEndState(IState endState);

    void addState(IState startState, IState newState, Action action);

    void removeState(String targetStateDesc);

    IState getCurrentState();

    IState getStartState();

    IState getEndState();

    void transit(Action action);
}