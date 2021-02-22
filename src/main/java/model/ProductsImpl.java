package model;

import model.step.enumerations.StepType;

public class ProductsImpl implements Products {

    private String code;
    private String name;
    private String description;
    private double priceL;
    private double usage500mq;
    private StepType step;

    public ProductsImpl(String code, StepType step, String name, String description, double priceLitre, double usage500mq) {
        this.code = code;
        this.step = step;
        this.name = name;
        this.description = description;
        this.priceL = priceLitre;
        this.usage500mq = usage500mq;
    }
    
    @Override
    public String getCode() {
        return this.code;
    }
    
    @Override
    public StepType getStepType() {
        return this.step;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public double getPricePerLitre() {
        return this.priceL;
    }

    @Override
    public double getLitersPer500Mq() {
        return this.usage500mq;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
        
    }
    
    @Override
    public void setStepType(StepType stepType) {
        this.step = stepType;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDescription(String descr) {
        this.description = descr;
    }

    @Override
    public void setPricePerLitre(double newPrice) {
        this.priceL = newPrice;
    }

    @Override
    public void setLitersPer500MQ(double newUsage) {
        this.usage500mq = newUsage;
    }
}
