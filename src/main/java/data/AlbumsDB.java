/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;
import model.Product;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DIABLO
 */
public class AlbumsDB {
    private static final Map<String, Product> products = new LinkedHashMap<>();

    static {
        products.put("86ftb", new Product("86ftb", "86 (the band) - True Life Songs and Pictures", 14.95));
        products.put("pf01", new Product("pf01", "Paddlefoot - The first CD", 12.95));
        products.put("pf02", new Product("pf02", "Paddlefoot - The second CD", 14.95));
        products.put("jr01", new Product("jr01", "Joe Rut - Genuine Wood Grained Finish", 14.95));
    }

    public static Product getProduct(String code) {
        return products.get(code);
    }

    public static List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
}
