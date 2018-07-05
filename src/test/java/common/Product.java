package common;

public class Product {
    public String name;
    public double price;
    public int quantity;

    public Product(String name, double price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString(){
        return String.format(name + ", $ " + price + " * " + quantity);
    }
}
