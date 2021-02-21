package controller;

import java.util.ArrayList;
import java.util.List;
import model.step.Step;
import model.step.SubSteps;
import model.step.enumerations.StepType;


public class ProcessImpl implements Process {

    private static final ProcessImpl SINGLETON = new ProcessImpl();
    private final List<Step<StepType, SubSteps>> stepList = new ArrayList<>();

    public ProcessImpl() { }
    public static ProcessImpl getInstance() {
        return SINGLETON;
    }

    /**
     * 
     */
    @Override
    public List<Step<StepType, SubSteps>> getSubStepsList() {
        return this.stepList;
    }

    /**
     * 
     */
    @Override
    public void addStep(final Step<StepType, SubSteps> step) {
       this.stepList.add(step);

    }

    /**
     * 
     */
    @Override
    public void removeStep(final Step<StepType, SubSteps> step) {
        this.stepList.remove(step);

    }


}
