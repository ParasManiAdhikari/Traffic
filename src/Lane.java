import java.text.DecimalFormat;
import java.util.*;

public class Lane {
    String laneName;
    LinkedList<Car> cars = new LinkedList<>();
    LinkedList<Car> exitedCars = new LinkedList<>();
    double probablityToAdd;
    int totalCar = 0;


    // Constructor
    public Lane(String laneName,double probablity) {
        this.laneName = laneName;
        this.probablityToAdd = probablity;
    }

    public String getLaneName() {
        return laneName;
    }

    public void setLaneName(String laneName) {
        this.laneName = laneName;
    }

    public LinkedList<Car> getCars() {
        return cars;
    }
    public void addCar(String laneName, int time) {
        Random random = new Random();
        double generatedProbablity = random.nextDouble();
        if (generatedProbablity < this.probablityToAdd) {
            cars.add(new Car("car" + totalCar++,laneName, time));
        }
    }
    public boolean addOrNot() {
        Random random = new Random();
        double generatedProbablity = random.nextDouble();
        if (generatedProbablity < this.probablityToAdd) {
            System.out.print("Probability at " + laneName + ": " + new DecimalFormat("#.##").format(generatedProbablity) + " < " + this.probablityToAdd);
            return true;
        } else {
            System.out.print("Probability at " + laneName + ": " + new DecimalFormat("#.##").format(generatedProbablity) + " < " + this.probablityToAdd);
            return false;
        }
    }

    public void addCar(int time) {
        if (addOrNot()){
            cars.add(new Car("car" + totalCar++,this.laneName, time));
            System.out.print(" ✔");
        }
        System.out.println();

    }

    public void addCar(Car car) {
        cars.add(car);
    }
    public boolean removeCar(int time) {
        Random random = new Random();
        double generatedProbablity = random.nextDouble();
        if(cars.isEmpty()){
            System.out.println(laneName.substring(0,2) + " Empty");
            return false;
        }
        else if (generatedProbablity < 0.50) {
            Car toDelete = cars.poll();
            toDelete.exitTime = time;
            exitedCars.add(toDelete);
            System.out.println("CHECKING EXITED LIST : " + exitedCars);
            System.out.println(laneName + " REMOVED " + toDelete);
            return true;
        } else {
            System.out.println(laneName + " Remove Probability False");
            return false;
        }
    }

}
