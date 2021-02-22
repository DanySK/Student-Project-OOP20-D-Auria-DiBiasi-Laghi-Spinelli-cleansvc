package model;

import model.step.enumerations.StepType;

public interface Products {
    String getCode();
    StepType getStepType();
    String getName();
    String getDescription();
    double getPricePerLitre();
    double getLitersPer500Mq();
    void setCode(String code);
    void setStepType(StepType stepType);
    void setName(String name);
    void setDescription(String descr);
    void setPricePerLitre(double newPrice);
    void setLitersPer500MQ(double newUsage);
}
