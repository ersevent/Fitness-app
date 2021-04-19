package Product;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class fileWriterProducts {

    public fileWriterProducts(String filePath, HashMap<Product, Integer> products) {
        BufferedWriter bf;
        try {
            bf = new BufferedWriter(new FileWriter(new File(filePath)));
            for(Product product : products.keySet()){
                bf.write("\n" + product.getName() + " " + product.getIdentificationNumber() + " " +
                        product.getMeasure() + " " + product.getPrice() + " " + product.isInStock()
                        + " " + products.get(product));
            }
            bf.close();
        } catch (IOException e) {}
    }
}
