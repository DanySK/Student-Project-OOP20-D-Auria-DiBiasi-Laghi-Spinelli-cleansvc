package model.step;

import model.step.enumerations.StepType;

public class Step {

    private StepType stepType;
    private SubSteps subSteps;

    public Step(final StepType stepType, final SubSteps subSteps) {
        this.stepType = stepType;
        this.subSteps = subSteps;
    }

}
