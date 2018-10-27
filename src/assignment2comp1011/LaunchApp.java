package assignment2comp1011;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class LaunchApp extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, NoSuchAlgorithmException {
                     
    launch(args);
             }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
         Parent root = FXMLLoader.load(getClass().getResource("Startup.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Edit User");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
}
