package model;

public class ProductsImpl implements Products {

    private String name;
    private String description;
    private double priceL;
    private double usage500mq;
    private String step;

    public ProductsImpl(String step, String name, String description, double priceLitre, double usage500mq) {
        this.step = step;
        this.name = name;
        this.description = description;
        this.priceL = priceLitre;
        this.usage500mq = usage500mq;
    }
    
    @Override
    public String getStep() {
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
    public void setPricePerLitre(double newPrice) {
        this.priceL = newPrice;
    }

    @Override
    public void setLitersPer500MQ(double newUsage) {
        this.usage500mq = newUsage;
    }

}
