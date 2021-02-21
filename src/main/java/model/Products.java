package model;

public interface Products {
    String getCode();
    String getStepType(); //TODO change String to Step
    String getName();
    String getDescription();
    double getPricePerLitre();
    double getLitersPer500Mq();
    void setCode(String code);
    void setStepType(String stepType);
    void setName(String name);
    void setDescription(String descr);
    void setPricePerLitre(double newPrice);
    void setLitersPer500MQ(double newUsage);
}
