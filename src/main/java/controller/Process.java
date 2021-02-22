package controller;



import java.util.List;

import model.step.Step;
import model.step.SubSteps;
import model.step.enumerations.StepType;

public interface Process {

    List<SubSteps> getSubStepsList();

    List<StepType> getStepTypeList();

    void addStep(SubSteps s);

    void removeStep(SubSteps s);


}
