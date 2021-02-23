package model;

public interface Products {
    String getCode();
    String getStepType(); //TODO change String to Step
    String getName();
    String getDescription();
    double getPricePerLitre();
    double getLitersPer500Mq();
}
