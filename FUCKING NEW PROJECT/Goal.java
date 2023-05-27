import java.io.*;

public class Goal implements Serializable{
    private String goal_name;
    private double price;

    public Goal(){

    }
    public Goal(String name, double price){
        this.goal_name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getGoal_name() {
        return goal_name;
    }
    public void setGoal_name(String goal_name) {
        this.goal_name = goal_name;
    }
}
