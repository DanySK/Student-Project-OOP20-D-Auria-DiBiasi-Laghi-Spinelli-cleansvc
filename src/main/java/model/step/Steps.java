package model.step;

public interface Steps {
    /**
     * Returns true if it is the final state.
     * @return true if it is the final state.
     */
    boolean isEnd();

    /**
     * Returns the name of the step.
     * @return the name of the step.
     */
    String getName();

}
