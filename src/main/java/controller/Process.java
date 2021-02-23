package controller;

import java.util.List;
import java.util.Optional;

import model.Products;
import model.step.SubSteps;
import model.step.enumerations.StepType;
import model.users.Clients;

public interface Process {

    List<SubSteps> getSubStepsList();

    List<StepType> getStepTypeList();

    void addStep(SubSteps s);

    void removeStep(SubSteps s);

    Optional<SubSteps> searchSubStep(String code);

    Optional<List<SubSteps>> getSubStepsByStepType(String stepType);

    double getProportialTime(double value, Clients s, int staff);
    
    double getProportialEarn(double value, Clients s);

}
