package model;

public interface Products {
    public String getStep(); //TODO change String to Step
    public String getName();
    public String getDescription();
    public double getPricePerLitre();
    public double getLitersPer500Mq();
    public void setPricePerLitre(double newPrice);
    public void setLitersPer500MQ(double newUsage);
}
