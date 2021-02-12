package model.step;

import java.util.Date;

public interface StepInfo {
    /**
     * Method returns a {@link Steps} associated to Step.
     * @return a {@link Steps} associated to Step.
     */
    Steps getType();

    /**
     * Method returns the StartDate when the step was created.
     * @return 
     */
    Date getStartDate();

}
