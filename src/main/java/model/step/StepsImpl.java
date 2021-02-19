package model.step;

import model.step.enumerations.StepType;

public class StepsImpl implements Steps {
    private double time;
    private double price;
    private StepType type;

    public StepsImpl(final double price, final double time, final StepType type) {
       this.price = price;
       this.time = time;
       this.type = type;
    }

    /**
     * Returns the cleaning time of 500 square meters by an employee.
     * @return the cleaning time.
     */
    @Override
    public double getTime500mq() {
        return this.time;
    }


    /**
     * Returns the price for cleaning 500 square meters by an employee.
     * @return the price.
     */
    @Override
    public double getPrice500mq() {
        return this.price;
    }

    /**
     * Returns step's type.
     * @return the type.
     */
    @Override
    public StepType getType() {
        return this.type;
    }

}
