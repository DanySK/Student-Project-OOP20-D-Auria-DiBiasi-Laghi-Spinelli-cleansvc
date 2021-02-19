package model.step;

import java.util.List;
import model.step.enumerations.StepType;


public class SubStepsImpl implements SubSteps {
    private StepType type;
    private int time500mq;
    private String name;
    private String description;
    

    public SubStepsImpl(final StepType type, final int time, final String name, final String description) {
        this.type = type;
        this.time500mq = time;
        this.name = name;
        this.description = description;
    }


    /**
     * @return
     */
    @Override
    public String getName() {
        return this.name;
    }


    /**
     * @return
     */
    @Override
    public String getDescription() {
        return this.description;
    }


    /**
     * @return
     */
    @Override
    public int getTime() {
        return this.time500mq;
    }

}
