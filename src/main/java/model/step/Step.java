package model.step;

import model.step.enumerations.StepType;

@SuppressWarnings("hiding")
public class Step<StepType, SubStep> {

    private StepType stepType;
    private SubSteps subSteps;

    public Step(final StepType stepType, final SubSteps subSteps) {
        super();
        this.stepType = stepType;
        this.subSteps = subSteps;
    }
    /**
     * 
     * @return a stepType.
     */
    public StepType getStepType() {
        return stepType;
    }

    /**
     * 
     * @return a subStep.
     */
    public SubSteps getSubSteps() {
        return subSteps;
    }


}
