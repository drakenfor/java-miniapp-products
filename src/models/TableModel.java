/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import Files.Filedb;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import productObject.Product;

/**
 *
 * @author ROBERT TORRES
 */
public class TableModel{
    public DefaultTableModel tableModel;
    public DefaultTableModel tableModelBoleta;
    private Filedb db = Filedb.filedb();
    
    public TableModel(){
        createModel();
    }
    
    private void createModel(){        
        tableModel = new DefaultTableModel(null, new String [] {"Código", "Nombre", "Pecio"}){
            
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }    
            
        };
        
        Object object [] = null;
        
        for (int i = 0; i < db.products.size(); i ++ ){
            tableModel.addRow(object);
            tableModel.setValueAt(db.products.get(i).code, i, 0);
            tableModel.setValueAt(db.products.get(i).name, i, 1);
            tableModel.setValueAt(db.products.get(i).price, i, 2);
        }
    }
    
    public void modelSearch(ArrayList<Product> products){
        tableModel = new DefaultTableModel(null, new String [] {"Código", "Nombre", "Pecio"}){
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
            }
        };
        
        Object object []= null;
        
        for (int i = 0; i < products.size(); i ++ ){
            tableModel.addRow(object);
            tableModel.setValueAt(products.get(i).code, i, 0);
            tableModel.setValueAt(products.get(i).name, i, 1);
            tableModel.setValueAt(products.get(i).price, i, 2);
        }
        
    }
    
    public DefaultTableModel modelPoducts(){
        
        tableModel = new DefaultTableModel(null, new String [] {"Código", "Nombre", "Pecio"}){
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
            }
        };
        
        Object object []= null;
       
        
        for (int i = 0; i < db.productsBoleta.size() ; i ++ ){
            tableModel.addRow(object);
            tableModel.setValueAt(db.productsBoleta.get(i).code, i, 0);
            tableModel.setValueAt(db.productsBoleta.get(i).name, i, 1);
            tableModel.setValueAt(db.productsBoleta.get(i).price, i, 2);
        }
        
        return tableModel;
        
    }
    
}
