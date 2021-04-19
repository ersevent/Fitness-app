package Product;

import java.util.HashMap;
import java.util.Scanner;

public class ProductManager {
    private HashMap<Product, Integer> products;

    public ProductManager(){
        products = new HashMap<>();
    }

    public void addProduct(Product product, int quantity){
        products.put(product, quantity);
    }

    public void addProductAdmin(){
        Scanner in = new Scanner(System.in);
        System.out.print("Give me the product's name: ");
        String name = in.next();
        System.out.print("identification number: ");
        String idNumber = in.next();
        System.out.print("measure: ");
        String measure = in.next();
        System.out.print("price: ");
        int price = in.nextInt();
        System.out.print("quantity: ");
        int quantity = in.nextInt();

        addProduct(new Product(name, idNumber, measure, price, true), quantity);
        System.out.println("Product has been added.");
        fileWriterProducts fw = new fileWriterProducts("src\\Product\\Products.txt", getHashMap());
    }

    public void deleteProductAdmin(){
        Scanner in = new Scanner(System.in);
        String removeID = null;
        do {
            System.out.print("Give me the ID number of the product, which one you would like to delete (type help for product list): ");
            removeID = in.next();
            if (removeID.equals("help")) print();
        }while(removeID.equals("help"));

        for(Product product : products.keySet()){
            if(product.getIdentificationNumber().equals(removeID)){
                products.remove(product);
                System.out.println("Product has been deleted.");
                fileWriterProducts fw = new fileWriterProducts("src\\Product\\Products.txt", getHashMap());
                return;
            }
        }
        System.err.println("Product not found.");
    }

    public Cart addToCart(Cart cart){
        cart.addToCart(products);
        return cart;
    }

    public void print(){
        for(Product product : products.keySet()){
            System.out.println(product.getIdentificationNumber() + ", " + product.getName() + ", " + products.get(product) + " " + product.getMeasure()  + ", " + product.getPrice() + " Ft/" + product.getMeasure());
        }
        System.out.println();
    }

    public HashMap<Product, Integer> getHashMap(){
        return products;
    }
}
