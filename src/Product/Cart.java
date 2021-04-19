package Product;

import java.util.HashMap;
import java.util.Scanner;

public class Cart{
    HashMap<Product, Integer> cart;

    public Cart(){
        cart = new HashMap<>();
    }

    public void addToCart(HashMap<Product, Integer> products) {
        int quantity;
        Scanner sc = new Scanner(System.in);
        String input;

        do {
            System.out.println("\n\n-------------------");
            System.out.print("Type help for product list!\nAdd to cart (Product's ID): ");
            input = sc.next();
            if (input.equals("help")) {
                for (Product product : products.keySet()) {
                    System.out.println(product.getIdentificationNumber() + ", " + product.getName() + ", " + products.get(product) + " " + product.getMeasure() + ", " + product.getPrice() + " Ft/" + product.getMeasure());
                }
            } else {
                for (Product product : products.keySet()) {
                    if (product.getIdentificationNumber().equals(input)) {
                        System.out.print("How many would you like to buy: ");
                        quantity = sc.nextInt();
                        if (quantity <= products.get(product)) {
                            cart.put(product, quantity);
                            return;
                        } else {
                            System.out.println("Only " + products.get(product) + " available.");
                            return;
                        }
                    }
                }
                System.err.println("Product not found.");
                return;
            }
        } while(input.equals("help"));
    }

    public void deleteFromCart(){
        Scanner sc = new Scanner(System.in);
        String idNumber = "";
        do{
            System.out.print("Type help for products in the cart!\nWhich product would you like to remove: ");
            idNumber = sc.next();
            if (idNumber.equals("help")) {
                whatIsInTheCart();
                if(cart.isEmpty()){
                    return;
                }
            }
        }while(idNumber.equals("help"));
        System.out.print("How many would you like to remove: ");
        int quanity = sc.nextInt();

        for(Product product: cart.keySet()){
            if(product.getIdentificationNumber().equals(idNumber)){
                cart.put(product, cart.get(product)-quanity);
                if(cart.get(product)<1){
                    cart.remove(product);
                    return;
                }
                else{
                    return;
                }
            }
        }
        System.err.println("Product not found.");
    }

    public void whatIsInTheCart(){
        if(cart.isEmpty()){
            System.out.println("Cart is empty.");
        }
        else {
            for (Product product : cart.keySet()) {
                System.out.println(product.toString() + " * " + cart.get(product) + " ");
            }
        }
    }

    public void buy(ProductManager productManager){
        if(cart.isEmpty()) System.out.println("Cart is empty.");
        else {
            HashMap<Product, Integer> products = productManager.getHashMap();
            for (Product productFromCart : cart.keySet()) {
                for (Product productFromManager : products.keySet()) {
                    if (productFromCart.getIdentificationNumber() == productFromManager.getIdentificationNumber()) {
                        products.put(productFromManager, products.get(productFromManager) - cart.get(productFromCart));
                    }
                }
            }
            cart.clear();
            fileWriterProducts fw = new fileWriterProducts("src\\Product\\Products.txt", productManager.getHashMap());
        }
    }
}
