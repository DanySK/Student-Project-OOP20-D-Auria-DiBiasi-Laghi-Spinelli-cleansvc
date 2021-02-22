package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        for (StepType step : StepType.values()) {
            stepTypeList.add(step);
        }
        return this.stepTypeList;
    }

    /**
     * 
     */
    @Override
    public Optional<SubSteps> searchSubStep(final String code) {
        for (final SubSteps st : this.stepList) {
            if (st.getCode().equals(code)) {
                return Optional.of(st);
            }
        }
        return Optional.empty();
    }

}
