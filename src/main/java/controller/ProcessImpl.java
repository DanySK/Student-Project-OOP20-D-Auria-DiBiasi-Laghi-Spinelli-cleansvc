package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.step.SubSteps;
import model.step.enumerations.StepType;
import model.users.Clients;

public class ProcessImpl implements Process {

    private static final ProcessImpl SINGLETON = new ProcessImpl();
    private final List<SubSteps> stepList = new ArrayList<>();
    private final List<StepType> stepTypeList = new ArrayList<>();

    public ProcessImpl() { }
    public static ProcessImpl getInstance() {
        return SINGLETON;
    }

    /**
     * @return a list of SubSteps.
     */
    @Override
    public List<SubSteps> getSubStepsList() {
        return this.stepList;
    }

    /**
     * Method adds to stepList a subStep.
     */
    @Override
    public void addStep(final SubSteps s) {
       this.stepList.add(s);

    }

    /**
     * Method removes from stepList a subStep.
     */
    @Override
    public void removeStep(final SubSteps s) {
        this.stepList.remove(s);

    }

    /**@return a list of StepType.
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
    /**
     * 
     */
    @Override
    public Optional<List<SubSteps>> getSubStepsByStepType(final String type) {
        List<SubSteps> sBySteps = new ArrayList<>();
        for (SubSteps subSteps : this.stepList) {
            if (subSteps.getStepType().getType().equals(type)) {
                sBySteps.add(subSteps);
            }
        }
        return (sBySteps.isEmpty()) ? Optional.empty() : Optional.of(sBySteps);
    }

    /**
     * 
     */
    @Override
    public double getProportialTime(final double value, final Clients s, final int staff) {
        double tot = 0;
        tot = (value * s.getMqStructure()) / (500 * staff);
        return Math.floor(tot);
    }
    /**
     * 
     */
    @Override
    public double getProportialCost(final double value, final Clients s) {
        return getProportialTime(value, s, 1);
    }
    /**
     * 
     */
    @Override
    public double getIncome(final double value) {
        double tot = 0;
        tot = value * 1.5 * 1.22;
        return Math.floor(tot);
    }
}
