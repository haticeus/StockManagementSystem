/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import personel.DB;
import personel.PersonelIslemleri;


public class Yönetici {
    
     private Connection con = null;
     private  Statement statement = null;
    
    private PreparedStatement preparedStatement = null;
    
    
    
    public Yönetici() {
    
         String url = "jdbc:mysql://" + DB.host + ":" + DB.port + "/" + DB.db_ismi +"?useUnicode=true&characterEncoding=utf8";
        
        try{
        
           Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver bağlandı");
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Bulunamadı...");
        }
        
        try {
            
            con = DriverManager.getConnection(url, DB.kullanici_adi, DB.parola);
            System.out.println("Bağlantı Başarılı...");
            
        } catch (SQLException ex) {
            System.out.println("Bağlantı başarısız...");
           
        } 
    
    }
    
    
    public boolean girisYap(String kullanici_adi, String parola ) {
    
       
       
        try {
            String sorgu = "Select * From myadmin where username = ? and password = ?";
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1, kullanici_adi);
            preparedStatement.setString(2, parola);
            
            ResultSet rs = preparedStatement.executeQuery();
            
               return rs.next();
            
        } catch (SQLException ex) {
            Logger.getLogger(PersonelIslemleri.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Başarısız kontrol...");
            return false;
        }
       
    }
    
    
}