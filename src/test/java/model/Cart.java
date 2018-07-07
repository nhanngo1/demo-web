package model;

import java.util.ArrayList;
import java.util.List;


public class Cart {

    public List<Product> products = new ArrayList<Product>();

    public void addProduct(Product selectedProd){
        if (products.size() == 0) {
            // add new item to card
            products.add(selectedProd);
        } else {
            int numberOfSelectedItems = products.size();
            for (int i = 0; i < numberOfSelectedItems; i++) {
                if (products.get(i).name.equals(selectedProd.name) &&
                        products.get(i).color.equals(selectedProd.color) &&
                        products.get(i).size.equals(selectedProd.size)) {
                    // update quantity if the selected product is already added to card before
                    products.get(i).quantity += 1;
                    products.get(i).price = selectedProd.price;
                } else {
                    // add new item to card
                    products.add(selectedProd);
                }
            }
        }
    }
}
