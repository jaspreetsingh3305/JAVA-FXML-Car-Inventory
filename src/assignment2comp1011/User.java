/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2comp1011;

import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.function.Predicate;

public class User {
    private String userName;
    private String password;

    
    public User(String userName, String password) throws SQLException, NoSuchAlgorithmException  {
        setUserName(userName);
        setPassword(password);
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String Password) throws NoSuchAlgorithmException, SQLException {
        
        Predicate<String> ps=(p) ->p.length()>=8;
        boolean testedPwd= ps.test(Password);
       
       if(testedPwd){
        byte[] salt=PasswordGenerator.getSalt();        
        this.password = PasswordGenerator.getPW(Password, salt);
        insertData(salt);
       }
       else
           throw new IllegalArgumentException("Password must be atleast 8 characters");
    }

    private void insertData(byte[] generatedSalt) throws SQLException {

        Blob blob = new javax.sql.rowset.serial.SerialBlob(generatedSalt);
        Connection conn = null;
        PreparedStatement preparedStatement =null;
        
        try {
            conn=DriverManager.getConnection("jdbc:mysql://aws.computerstudi.es:3306/gc200360513", "gc200360513","nUDgkNa2zj");
            String sql="INSERT INTO credentials VALUES (?,?,?)";
            preparedStatement= conn.prepareStatement(sql);
            
          
             preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
           preparedStatement.setBlob(3, blob);
                     
            preparedStatement.execute();
            
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         finally{
            if(preparedStatement !=null)
                  preparedStatement.close();
            if(conn != null)
                conn.close();
        }

        
    }
}
