package controller;

import java.util.ArrayList;
import java.util.List;
import model.step.Step;


public class ProcessImpl implements Process {

    private static final ProcessImpl SINGLETON = new ProcessImpl();
    private final List<Step> stepList = new ArrayList<>();

    public ProcessImpl() { }
    public static ProcessImpl getInstance() {
        return SINGLETON;
    }

    /**
     * 
     */
    @Override
    public List<Step> getList() {
        return this.stepList;
    }

    /**
     * 
     */
    @Override
    public void addStep(final Step step) {
        this.stepList.add(step);

    }

    /**
     * 
     */
    @Override
    public void removeStep(final Step step) {
        this.stepList.remove(step);

    }

}
