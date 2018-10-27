package assignment2comp1011;

/**
 *
 * @author user
 */
public class Car {
   private String make,model;
   private int year;
   private double mileage;
    
    public Car(String make,String model,int year, double mileage){
        setMake(make);
        setModel(model);
        setYear(year);
        setMileage(mileage);
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
 
    }
   
  @Override
    public String toString(){
        return this.make+this.model+this.year+this.mileage;
    }
    
}
