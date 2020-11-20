/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productObject;

/**
 *
 * @author ROBERT TORRES
 */
public class Product {
    public String code;
    public String name;
    public String price;
    
    public Product(){
        code = "";
        name = "";
        price = "";
    }
    
    public Product(String code, String name, String price){
        this.code = code;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" + "code=" + code + ", name=" + name + ", price=" + price + '}';
    }
    
}
