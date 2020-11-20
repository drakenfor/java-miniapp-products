/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Files;

import com.google.gson.Gson;
import com.google.gson.internal.StringMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import productObject.Product;

/**
 *
 * @author ROBERT TORRES
 */
public class Filedb {
    private File archivo = null;
    private FileReader fr = null;
    private BufferedReader br = null;
    private FileWriter fw = null;
    private final String path = "d:\\db.json";
    
    //Producto par editar o guardar prdoducto
    public Product productEdit = new Product ("","","");
    
    int index;
    
    
    //Para la lista de producutos
    public ArrayList<Product> products = new ArrayList<Product>();
    public ArrayList<Product> productsBoleta = new ArrayList<Product>();
    
    //para leer archivos de tipo json
    private final  Gson gson = new Gson();
    
    
    //Patron singleton
    private static Filedb filedb;
    
    private Filedb () {
        readProducts();
    }
    
    public static Filedb filedb () {
        if (filedb == null)
            filedb = new Filedb();
        
        return filedb;
    }
    //Fin del singleton
    
    public void removeProductOfCart(String code){
        
       productsBoleta.remove(searchCode(code));
        
        //saveProduct();
    }
    
    public void addProductToCart(String code, String name, String price){
        /*
        Product product = new Product(code, name, price);
        
        //Define si se va agregar o editar el producto
        if (index >= 0 ){
            products.remove(index);
            products.add(index, product);
        } else {
            products.add(product);
        }
        saveProduct();
        */
        
        Product product = new Product (code, name, price);
        productsBoleta.add(product);
    }
    
    public void removeProductdb(String code){
        
       products.remove(searchCode(code));
       saveProduct();
    }
    
    
    public void addProductdb(String code, String name, String price){
        Product product = new Product(code, name, price);
        
        //Define si se va agregar o editar el producto
        if (index >= 0 ){
            products.remove(index);
            products.add(index, product);
        } else {
            products.add(product);
        }
        saveProduct();
    }
    
    
    
    //funcion para editar el producto
    public void editProduct(String code){
        productEdit = products.get(searchCode(code));
    }
    
    
    //Funcion para agregar un nuevo producto
    public void newProduct(){
        index = -1;
        productEdit = new Product("","","");
    }
    
    
    
    //Funcion para buscar un producto en la lista de productos
    public int searchCode(String code){
        index = -1;
        for (int i = 0; i < products.size(); i++) {
                if (products.get(i).code.equals(code)){
                    index = i;
                    break;
                }   
            }
        
        return index;
    }
    
    //Funcion para guardar el producto
    public void saveProduct(){
        
        //Convirtemos la lista de productos a un string de tipo json
        String json = gson.toJson(products);
        
        try {
            fw = new FileWriter(path);
            PrintWriter bw = new PrintWriter(fw);
            bw.write(json);
        } catch (IOException e ){
            System.out.println(e.toString());
        } finally {
            try {
            if (fw != null )
                fw.close();
            } catch (IOException e2) {
               System.out.println(e2.toString());
           }
        }
        
    }
    
    //Funcion para leer un producto
    private void readProducts (){
        String json = "";
        
        try {
         archivo = new File (path);
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         String linea;
         while((linea=br.readLine())!=null)
            json += linea;
        
         
         ArrayList<StringMap> jsonList =  gson.fromJson(json, ArrayList.class);
         
         //Llenar la lista de mis productos
            for (int i = 0; i < jsonList.size(); i++) {
                products.add(new Product(
                        jsonList.get(i).get("code").toString(),
                        jsonList.get(i).get("name").toString(),
                        jsonList.get(i).get("price").toString()
                ));
            }
      }
      catch(IOException e){
          System.out.println(e.toString());
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (IOException e2){ 
             System.out.println(e2.toString());
         }
      }
    }
    
    public double total (){
        double total = 0;
        for (Product product : productsBoleta) {
            total += Double.parseDouble(product.price);
        }
        return total;
    }
}
