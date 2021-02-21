package controller;



import java.util.List;

import model.step.Step;
import model.step.SubSteps;
import model.step.enumerations.StepType;

public interface Process {

    List<Step<StepType, SubSteps>> getSubStepsList();

    void addStep(Step<StepType, SubSteps> step);

    void removeStep(Step<StepType, SubSteps> step);


}
