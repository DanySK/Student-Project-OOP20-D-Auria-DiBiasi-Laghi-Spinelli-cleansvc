package controller;



import java.util.List;
import java.util.Optional;

import model.step.Step;
import model.step.SubSteps;
import model.step.enumerations.StepType;
import model.users.Clients;

public interface Process {

    List<SubSteps> getSubStepsList();

    List<StepType> getStepTypeList();

    void addStep(SubSteps s);

    void removeStep(SubSteps s);

    Optional<SubSteps> searchSubStep(String code);


}
