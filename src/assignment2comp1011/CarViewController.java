/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2comp1011;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author user
 */
public class CarViewController implements Initializable {
   @FXML private TableView<Car> tableView;
    @FXML private TableColumn<Car, String> makeColumn;
    @FXML private TableColumn<Car, String> modelColumn;
    @FXML private TableColumn<Car,Integer> yearColumn;
    @FXML private TableColumn<Car, Double> mileageColumn;
    @FXML private Slider minYearSlider;
    @FXML private Slider maxYearSlider;
    @FXML private Label minYearLabel;
    @FXML private Label maxYearLabel;
    @FXML private ComboBox<String> brandComboBox;
    @FXML private Label label;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   this.minYearSlider.setMin(2010);
        this.minYearSlider.setMax(2015);
        this.minYearSlider.setValue(2010);
        this.minYearLabel.setText(Integer.toString((int)minYearSlider.getValue()));
        
        this.maxYearSlider.setMin(2015);
        this.maxYearSlider.setMax(2018);
        this.maxYearSlider.setValue(2018);
        this.maxYearLabel.setText(Integer.toString((int)maxYearSlider.getValue()));
        this.label.setText("");
         this.makeColumn.setCellValueFactory(
                new PropertyValueFactory<Car, String>("make"));
        this.modelColumn.setCellValueFactory(
                new PropertyValueFactory<Car, String>("model"));
        this.yearColumn.setCellValueFactory(
                new PropertyValueFactory<Car,Integer>("year"));
        this.mileageColumn.setCellValueFactory(
                new PropertyValueFactory<Car, Double>("mileage"));
         try {
            loadData();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage()+ex.getSQLState());
        }
          this.brandComboBox.getSelectionModel().selectFirst();
    }
     
      public void maxYearSliderMoved() throws SQLException
    {
        String label = String.format("%.0f", maxYearSlider.getValue());
        maxYearLabel.setText(label);
        UpdateTableWithSliders();
    }    
      
  public void minYearSliderMoved() throws SQLException
    {
        String label = String.format("%.0f", minYearSlider.getValue());
        minYearLabel.setText(label);
        UpdateTableWithSliders();
           } 
  
  public void UpdateTableWithSliders() throws SQLException{
    
        this.tableView.getItems().clear();
         ObservableList<Car> cars = FXCollections.observableArrayList();
         
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            conn = DriverManager.getConnection("jdbc:mysql://aws.computerstudi.es:3306/"
                    + "gc200360513", "gc200360513", "nUDgkNa2zj");
            
            statement = conn.createStatement();
            
            if(brandComboBox.getValue()!=null){
                
            resultSet = statement.executeQuery("SELECT * FROM cars WHERE year BETWEEN "
                    +minYearSlider.getValue()+" AND "+maxYearSlider.getValue()+
                    " AND make='"+this.brandComboBox.getValue()+"'");
            }
            else{
                
            resultSet = statement.executeQuery("SELECT * FROM cars WHERE year BETWEEN "
                    +minYearSlider.getValue()+" AND "+maxYearSlider.getValue());
            }
            while (resultSet.next())
            {
                Car newCar=new Car(
                    resultSet.getString("make"),
                    resultSet.getString("model"),
                    resultSet.getInt("year"),
                    resultSet.getDouble("mileage"));
                
                    cars.add(newCar);
            }
            tableView.getItems().addAll(cars);
             long numberOfCars= cars.stream()
                 .count();
        String a=Long.toString(numberOfCars);
        label.setText(a);
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        finally
        {
            if (conn != null)
                conn.close();
            if (statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        }
}
     public void loadData() throws SQLException{
       
         ObservableList<Car> cars = FXCollections.observableArrayList();
       List<String> makes = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
     try{
            conn = DriverManager.getConnection("jdbc:mysql://aws.computerstudi.es:3306/"
                    + "gc200360513", "gc200360513", "nUDgkNa2zj");
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM cars");
            while (resultSet.next())
            {
                Car newCar=new Car(
                    resultSet.getString("make"),
                    resultSet.getString("model"),
                    resultSet.getInt("year"),
                    resultSet.getDouble("mileage"));
                
                    cars.add(newCar);
                    
                    makes.add(resultSet.getString("make"));
                         
            }
             tableView.getItems().addAll(cars);
             
            makes=makes.stream().sorted(Comparator.naturalOrder())
                    .collect(Collectors.toList());
           makes=makes.stream().distinct()
                     .collect(Collectors.toList());
           
           brandComboBox.getItems().addAll(makes);
        
         long numberOfCars =cars.stream().count();
        String a=Long.toString(numberOfCars);
        label.setText(a);
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        finally
        {
            if (conn != null)
                conn.close();
            if (statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        }
   
     }
    
}
