package model;

import model.step.enumerations.StepType;

public class ProductsImpl implements Products {

    private String code;
    private String name;
    private String description;
    private double priceL;
    private double usage500mq;
    private StepType step;

    public ProductsImpl(final String code, final StepType step, final String name, final String description, final double priceLitre, final double usage500mq) {
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
    public void setCode(final String code) {
        this.code = code;
    }
    
    @Override
    public void setStepType(final StepType stepType) {
        this.step = stepType;
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public void setDescription(final String descr) {
        this.description = descr;
    }

    @Override
    public void setPricePerLitre(final double newPrice) {
        this.priceL = newPrice;
    }

    @Override
    public void setLitersPer500MQ(final double newUsage) {
        this.usage500mq = newUsage;
    }
}
