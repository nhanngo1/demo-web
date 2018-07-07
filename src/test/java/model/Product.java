package model;

public class Product {
    public String name;
    public String color;
    public String size;
    public int quantity;
    public double price;
    public double discountPercent;
    public double oldPrice;

//    public Product(String name, double price, int quantity){
//        this.name = name;
//        this.price = price;
//        this.quantity = quantity;
//    }

    public Product(String name, String color, String size, int quantity, double price){
        this.name = name;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
        this.discountPercent = 0.00;
        this.oldPrice = 0.00;
    }

    public Product(String name, String color, String size, int quantity, double price, double discountPercent, double oldPrice){
        this.name = name;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
        this.discountPercent = discountPercent;
        this.oldPrice = oldPrice;
    }

    @Override
    public String toString(){
        //return String.format(name + ", $ " + price + " * " + quantity);
        return String.format("%s Color: %s, Size: %s, Quantity: %d, Price:%.2f ", name, color, size, quantity, price);

    }
}
