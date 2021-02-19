package model.step;

import java.util.List;

import controller.Company;
import controller.CompanyImpl;
import model.Products;
import model.step.enumerations.StepType;


public class SubStepsImpl implements SubSteps {
    private StepType type;
    private int time500mq;
    private String name;
    private String description;
    private Company company = CompanyImpl.getInstance();
    private List<Products> productsList = company.getProductsByStepType(this.type);


    public SubStepsImpl(final StepType type, final int time, final String name, final String description) {
        this.type = type;
        this.time500mq = time;
        this.name = name;
        this.description = description;
    }


    /**
     * @return the name of subStep.
     */
    @Override
    public String getName() {
        return this.name;
    }


    /**
     * @return the description of subStep.
     */
    @Override
    public String getDescription() {
        return this.description;
    }


    /**
     * Returns the cleaning time of 500 square meters by an employee.
     * @return the cleaning time.
     */
    @Override
    public int getTime() {
        return this.time500mq;
    }

}
