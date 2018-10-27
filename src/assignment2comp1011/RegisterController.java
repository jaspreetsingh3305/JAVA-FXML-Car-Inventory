/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2comp1011;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author user
 */
public class RegisterController implements Initializable {

    @FXML private TextField userNameField;

    @FXML private PasswordField passwordField;

    @FXML private Label label;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        label.setText("");
    }    
        public void loadMainView(ActionEvent event) throws IOException{
        SceneChanger sc = new SceneChanger();
            sc.changeScenes(event, "Startup.fxml", "Car Inventory");
    }
        public void createUser(ActionEvent event) throws NoSuchAlgorithmException, IOException{
        try{
            User newPhone = new User(userNameField.getText(),passwordField.getText());
            SceneChanger sc = new SceneChanger();
            sc.changeScenes(event, "LogIn.fxml", "Car Inventory");
            
        }
        catch (IllegalArgumentException e)
        {
            label.setText(e.getMessage());
        }
        catch (SQLException ex)
        {
            label.setText(ex.getMessage());        }
        }
        
}
