package model;

public interface Products {

    //public Step getStep();
    public String getName();
    public String getDescription();
    public double getPricePerLitre();
    public double getLitersPer500Mq();
    
    public void setPricePerLitre(double newPrice);
    public void setLitersPer500MQ(double newUsage);
}
