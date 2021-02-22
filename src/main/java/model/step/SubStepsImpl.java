package model.step;

//import model.Products;

public class SubStepsImpl implements SubSteps {
    //private StepType type;
    private String code;
    private int time500mq;
    private String name;
    private String description;
    //private List<Products> productsList = company.getProductsByStepType(this.type);



    public SubStepsImpl(final String code, final int time, final String name, final String description) {
        this.code = code;
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

    /**
     * 
     * @return the code.
     */
    @Override
    public String getCode() {
        return this.code;
    }

}
