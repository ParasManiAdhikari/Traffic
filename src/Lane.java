import java.text.DecimalFormat;
import java.util.*;

public class Lane {
    String laneName;
    LinkedList<Car> cars = new LinkedList<>();
    Boolean trafficLightIsGreen;
    double probablityToAdd;
    double probablityToRemove;
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

    public Boolean getTrafficLightIsGreen() {
        return trafficLightIsGreen;
    }

    public void setTrafficLightIsGreen(Boolean trafficLightIsGreen) {
        this.trafficLightIsGreen = trafficLightIsGreen;
    }

    public LinkedList<Car> getCars() {
        return cars;
    }
    public int getTotalCar() {
        return totalCar;
    }
    public void addCar(String laneName) {
        Random random = new Random();
        double generatedProbablity = random.nextDouble();
        if (generatedProbablity < this.probablityToAdd) {
            cars.add(new Car("car" + totalCar++,laneName));
        }
    }
    public void addCar() {
        Random random = new Random();
        double generatedProbablity = random.nextDouble();
        System.out.print("Probability at " + laneName + ": " + new DecimalFormat("#.##").format(generatedProbablity) + " < " + this.probablityToAdd);
        if (generatedProbablity < this.probablityToAdd) {
            cars.add(new Car("car" + totalCar++,this.laneName));
            System.out.print(" ✔");
        }
        System.out.println();

    }
    public void addCar(Car car) {
        cars.add(car);
    }
    public boolean removeCar() {
        Random random = new Random();
        double generatedProbablity = random.nextDouble();
        if(cars.isEmpty()){
            System.out.println(laneName.substring(0,2) + " Empty");
            return false;
        }
        else if (generatedProbablity < 0.50) {
            System.out.println(laneName + " REMOVED " + cars.poll());
            return true;
        } else {
            System.out.println(laneName + " Remove Probability False");
            return false;
        }
    }
    public Map<String,List<String >> carsList() {
        Map<String, List<String>> result = new HashMap<>();
        List<String> a = new LinkedList<>();
        for (int i = 0; i < cars.size();i++) {
            a.add(cars.get(i).carName);
        }
        result.put(this.laneName,a);
        return result;
    }


}
