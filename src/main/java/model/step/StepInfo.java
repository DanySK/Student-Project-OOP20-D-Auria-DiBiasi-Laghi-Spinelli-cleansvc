package model.step;

import java.util.Date;
import java.util.Optional;

public interface StepInfo extends Steps {

    /**
     * Method returns the StartDate when the step was created.
     * @return the StartDate when the step was created.
     */
    Date getStartDate();

    /**
     * Method returns the EndDate when the step was completed
     * or an empty optional if not already completed.
     * @return the EndDate when the step was.
     */
    Optional<Date> getEndDate();

}
