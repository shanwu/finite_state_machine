package FiniteStateMachine;

/**
 * the class which will cause the transition of states.
 */
public class Action {
    private String mActionName;

    public Action(String actionName) {
        mActionName = actionName;
    }

    String getActionName() {
        return mActionName;
    }

    @Override
    public String toString() {
        return mActionName;
    }

}