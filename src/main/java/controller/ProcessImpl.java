package controller;

import java.util.ArrayList;
import java.util.List;

import org.checkerframework.framework.qual.SubtypeOf;

import model.step.Step;
import model.step.SubSteps;
import model.step.enumerations.StepType;


public class ProcessImpl implements Process {

    private static final ProcessImpl SINGLETON = new ProcessImpl();
    private final List<SubSteps> stepList = new ArrayList<>();
    private final List<StepType> stepTypeList = new ArrayList<>();

    public ProcessImpl() { }
    public static ProcessImpl getInstance() {
        return SINGLETON;
    }

    /**
     * 
     */
    @Override
    public List<SubSteps> getSubStepsList() {
        return this.stepList;
    }

    /**
     * 
     */
    @Override
    public void addStep(final SubSteps s) {
       this.stepList.add(s);

    }

    /**
     * 
     */
    @Override
    public void removeStep(final SubSteps s) {
        this.stepList.remove(s);

    }

    /**
     * 
     */
    @Override
    public List<StepType> getStepTypeList() {
        if (this.stepTypeList.isEmpty()) {
            for (StepType step : StepType.values()) {
                stepTypeList.add(step);
            }
        }
        return this.stepTypeList;
    }

}
