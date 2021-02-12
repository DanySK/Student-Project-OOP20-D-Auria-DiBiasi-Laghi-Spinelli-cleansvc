package model;

public class ProductsImpl implements Products {

    private String code;
    private String description;
    private double price;
    //private Step step;
    
    public ProductsImpl(String code, String description, double price) {
        this.code = code;
        this.description = description;
        this.price = price;
    }
    
    
    @Override
    public String getCode() {
        return this.code;
    }


    @Override
    public String getDescription() {
        return this.description;
    }


    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    
}
