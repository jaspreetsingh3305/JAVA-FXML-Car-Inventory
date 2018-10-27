/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2comp1011;

import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author user
 */
public class LogInController implements Initializable {

    @FXML private TextField userNameField;
    @FXML  private Label label;
    @FXML private TextField passwordField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       label.setText("");
    }    
    public void check(ActionEvent event) throws SQLException, IOException{
        ResultSet resultSet = null;
        Statement statement = null;
        Connection conn = null;
        PreparedStatement preparedStatement =null;
        
        Blob storedSalt=null;
        String storedPwd=null;
        try {
            conn=DriverManager.getConnection("jdbc:mysql://aws.computerstudi.es:3306/gc200360513", "gc200360513","nUDgkNa2zj");
            
            statement = conn.createStatement();
            
            resultSet=statement.executeQuery("select password,salt from credentials where user_name='"+
            this.userNameField.getText()+"';");
            while(resultSet.next()){
                storedSalt=resultSet.getBlob("salt");
                storedPwd=resultSet.getString("password");
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         finally{
            if(preparedStatement !=null)
                  preparedStatement.close();
            if(conn != null)
                conn.close();
        }
        int saltLength= (int)storedSalt.length();
       String newPwd= PasswordGenerator.getPW(passwordField.getText(), storedSalt.getBytes(1, saltLength));
        
       //matching the new hashed pwd to the one already stored for the user
        if(newPwd.equals(storedPwd)){
            
        SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "CarView.fxml", "Car Inventory");
        }
        else{
            label.setText("Wrong Password! Please enter your correct password");
        }
           }
    
    public void loadMainView(ActionEvent event) throws IOException{
        SceneChanger sc = new SceneChanger();
            sc.changeScenes(event, "Startup.fxml", "Car Inventory");
    }
        
    }
   
