package Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Product {
    private String name;
    private String identificationNumber;
    private String measure;
    private int price;
    private boolean inStock;

    public Product(String name, String identificationNumber, String measure, int price, boolean inStock) {
        this.name = name;
        this.identificationNumber = identificationNumber;
        this.measure = measure;
        this.price = price;
        this.inStock = inStock;
    }

    public Product(){

    }

    public void loadFromFile(ProductManager productManager){
        String filePath = "src\\Product\\Products.txt";
        File file = new File(filePath);
        try{
            Scanner sc = new Scanner(file);
            while(sc.hasNext()){
                productManager.addProduct(new Product(sc.next(), sc.next(), sc.next(), sc.nextInt(), sc.nextBoolean()), sc.nextInt());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
        }
    }

    public String getName() {
        return name;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public String getMeasure() {
        return measure;
    }

    public int getPrice() {
        return price;
    }

    public boolean isInStock() {
        return inStock;
    }

    public String toString(){
        return identificationNumber + ", " + name + ", " + measure  + ", " + price + " Ft";
    }

}
