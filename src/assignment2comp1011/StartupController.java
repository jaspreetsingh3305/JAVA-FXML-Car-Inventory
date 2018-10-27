/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2comp1011;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author user
 */
public class StartupController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    
    public void LaunhLogIn(ActionEvent event) throws IOException{
        SceneChanger sc = new SceneChanger();
            sc.changeScenes(event, "LogIn.fxml", "Log In");
    }
    public void LaunhRegister(ActionEvent event) throws IOException{
        SceneChanger sc = new SceneChanger();
            sc.changeScenes(event, "Register.fxml", "Register");
    }
    
          
    
}
