package controller;



import java.util.List;

import model.step.Step;

public interface Process {

   List<Step> getList();

    void addStep(Step step);

    void removeStep(Step step);


}
