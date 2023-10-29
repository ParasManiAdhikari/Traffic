import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
    // Lanes with their probabilities
    private static Lane A1 = new Lane("A1", 0.25);
    private static Lane A2 = new Lane("A2", 0.25);
    private static Lane A3 = new Lane("A3", 0.07);
    private static Lane B1 = new Lane("B1", 0.05);
    private static Lane C1 = new Lane("C1", 0.17);
    private static Lane C2 = new Lane("C2", 0.20);
    private static Lane D1 = new Lane("D1", 0.07);

    // Aufgabe 2
    private static int sizeOfA3 = 3;

    // Aufgabe 1a - Lists to save average Dwell Times
    private static List<Double> dwellA1 = new ArrayList<>();
    private static List<Double> dwellA2 = new ArrayList<>();
    private static List<Double> dwellA3 = new ArrayList<>();
    private static List<Double> dwellB1 = new ArrayList<>();
    private static List<Double> dwellC1 = new ArrayList<>();
    private static List<Double> dwellC2 = new ArrayList<>();
    private static List<Double> dwellD1 = new ArrayList<>();


    // Aufgabe 1b - Lists to save remaining cars
    private static List<Double> remainingCarsA1 = new ArrayList<>();
    private static List<Double> remainingCarsA2 = new ArrayList<>();
    private static List<Double> remainingCarsA3 = new ArrayList<>();
    private static List<Double> remainingCarsB1 = new ArrayList<>();
    private static List<Double> remainingCarsC1 = new ArrayList<>();
    private static List<Double> remainingCarsC2 = new ArrayList<>();
    private static List<Double> remainingCarsD1 = new ArrayList<>();

    public static void main(String[] args) {

        for (int k = 0; k < 30; k++){

            // 1 SIMULATION
            for (int time = 0; time < 120; time++) {
                System.out.println("\n-------------------- SECOND " + time + " --------------------");
                addNewRandomCar(time);      // Adds cars to Lanes
                printLanes();               // Shows the updated Lanes
                removeCars(time);           // Removes cars from lanes
                printLanes();               // Shows the updated Lanes
            }
            saveDwellTimes();               // Saves Dwell Times of 1 Simulation in a List
            saveRemainingCars();
            resetLanes();                    // Resets Lanes for next Simulation
        }
        printAllAverageDwellTimes();
        printAllAverageRemainingCars();

    }

    private static void printAllAverageRemainingCars(){
        System.out.println("1b) --------------- Remaining Cars");
//        System.out.println("Remaining cars at A1 :" + remainingCarsA1);
        System.out.println("Average: " + calculateAverage(remainingCarsA1));
//        System.out.println("Remaining cars at A2 :" + remainingCarsA2);
        System.out.println("Average: " + calculateAverage(remainingCarsA2));
//        System.out.println("Remaining cars at A3 :" + remainingCarsA3);
        System.out.println("Average: " + calculateAverage(remainingCarsA3));
//        System.out.println("Remaining cars at B1 :" + remainingCarsB1);
        System.out.println("Average: " + calculateAverage(remainingCarsB1));
//        System.out.println("Remaining cars at C1 :" + remainingCarsC1);
        System.out.println("Average: " + calculateAverage(remainingCarsC1));
//        System.out.println("Remaining cars at C2 :" + remainingCarsC2);
        System.out.println("Average: " + calculateAverage(remainingCarsC2));
//        System.out.println("Remaining cars at D1 :" + remainingCarsD1);
        System.out.println("Average: " + calculateAverage(remainingCarsD1));

    }

    private static void saveRemainingCars() {
        remainingCarsA1.add((double) A1.totalCar);
        remainingCarsA2.add((double) A2.totalCar);
        remainingCarsA3.add((double) A3.totalCar);
        remainingCarsB1.add((double) B1.totalCar);
        remainingCarsC1.add((double) C1.totalCar);
        remainingCarsC2.add((double) C2.totalCar);
        remainingCarsD1.add((double) D1.totalCar);
    }

    private static void addNewRandomCar(int time) {
        System.out.println("\n*---- Adding Cars ----*");
        A1.addCar(time);
        addToA2(time);                      // this method adds new car to the smaller list between A1 and A2
        addToA3(time);                      // this method check if there is free space in lane A3 otherwise add it to A2 after index 2.
        B1.addCar(time);
        C1.addCar(time);
        addToC2(time);                      // this method check if the smaller size between C1 and C2 lane and add new car to it.
        D1.addCar(time);
        System.out.println();
    }

    private static void printLanes() {
        System.out.println("--lanes--");
        printCars(A1.getCars(), A1.laneName);
        printCars(A2.getCars(), A2.laneName);
        printCars(A3.getCars(), A3.laneName);
        printCars(B1.getCars(), B1.laneName);
        printCars(C1.getCars(), C1.laneName);
        printCars(C2.getCars(), C2.laneName);
        printCars(D1.getCars(), D1.laneName);
    }

    private static void saveDwellTimes() {
        dwellA1.add(getDwellTimes(A1));
        dwellA2.add(getDwellTimes(A2));
        dwellA3.add(getDwellTimes(A3));
        dwellB1.add(getDwellTimes(B1));
        dwellC1.add(getDwellTimes(C1));
        dwellC2.add(getDwellTimes(C2));
        dwellD1.add(getDwellTimes(D1));
    }

    private static void printAllAverageDwellTimes() {
        System.out.println("\nANSWERS: \n1a) --------------- DWELL TIME");
//        System.out.print(dwellA1);
        System.out.println(" | Average :" + calculateAverage(dwellA1));
//        System.out.print(dwellA2);
        System.out.println(" | Average :" + calculateAverage(dwellA2));
//        System.out.print(dwellA3);
        System.out.println(" | Average :" + calculateAverage(dwellA3));
//        System.out.print(dwellB1);
        System.out.println(" | Average :" + calculateAverage(dwellB1));
//        System.out.print(dwellC1);
        System.out.println(" | Average :" + calculateAverage(dwellC1));
//        System.out.print(dwellC2);
        System.out.println(" | Average :" + calculateAverage(dwellC2));
//        System.out.print(dwellD1);
        System.out.println(" | Average :" + calculateAverage(dwellD1));
    }

    public static String calculateAverage(List<Double> numbers) {
        double a = numbers.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);

        return new DecimalFormat("#.##").format(a);
    }

    private static void resetLanes() {
        A1 =  new Lane("A1", 0.25);
        A2 =  new Lane("A2", 0.25);
        A3 =  new Lane("A3", 0.07);
        B1 =  new Lane("B1", 0.05);
        C1 =  new Lane("C1", 0.17);
        C2 =  new Lane("C2", 0.20);
        D1 =  new Lane("D1", 0.07);
    }

    private static double getDwellTimes(Lane lane) {
        if(lane.exitedCars.size() != 0){
            int LaneDwellTimeSum = 0;
            for(Car car : lane.exitedCars){
                int dwellTime = car.exitTime - car.enterTime;
//            System.out.print(dwellTime + " | ");
                LaneDwellTimeSum += dwellTime;
            }
//        System.out.println();
            double averageDwellTime = LaneDwellTimeSum / lane.exitedCars.size();
            return averageDwellTime;
        }
        return 0;
    }

    public static void addToA3(int time) {

        if (A2.getCars().size() < sizeOfA3){             // If A2 has < 3 elements a car go to A3
            if (A3.getCars().size() < sizeOfA3){          // if A3 is not full then add to A3
                A3.addCar(time);
            }
        } else {                                       // otherwise
            if (A3.addOrNot()){                        // if a car really meet the probablity of A3 then we will add it to A2
                A2.addCar(A3.laneName,time,A3.carNumber);            // but we will remember that its belong to A3
            }
            System.out.println();
        }
    }

    private static void addToA2(int time) {                     // this method add an element of A2 to either A2 or A1
        if (A2.getCars().size() <= A1.getCars().size()) {       // based on smaller size of the lane
            A2.addCar(time);
        } else {
            System.out.print("A2 -> A1 : ");
            A1.addCar(time);
        }
    }

    private static void addToC2(int time) {
        if (C2.getCars().size() < C1.getCars().size()) {                 // this method add an element of C2 to either C2 or C1
            C2.addCar(time);                                             // based on smaller size of the lane
        } else {
            C1.addCar(time);
        }
    }

    private static void printCars(Queue<Car> cars, String laneName) {
        System.out.println(laneName);
        if (!cars.isEmpty()) {
            for (Car car : cars) {
                System.out.print(" | " + car.carName);
            }
            System.out.println();
        }

    }

    private static void removeCars(int time) {
        int timeModulo60 = time%60;  // Modulo of 60 for case distinction
        System.out.println("\n*---- Removing Cars ----*");
        if (timeModulo60 < 39) {
            A1.removeCar(time);                          // try to remove a car from A1
            tryToRemoveFromA2(time);
            if (timeModulo60 < 10) {                    // this will run only for ten seconds
                A3.removeCar(time);                     // try to remove from A3
                tryToRemoveFromA2(time);
            } else {
                if (timeModulo60 >= 14)   {            // this will run from 14 to 39 second
                    C1.removeCar(time);
                    C2.removeCar(time);

                }
            }
        } else {
            if (43 <= timeModulo60 && timeModulo60 < 55) {
                B1.removeCar(time);
                D1.removeCar(time);
            }
        }
        System.out.println();
    }
    public static void removeFromA2(int time) {
        if (A2.getCars().size() > sizeOfA3) {
            if (A2.getCars().get(sizeOfA3).whichLane.equals(A3.laneName)) {    // if there is car waiting at position 3 to go to A3
                if (A2.getCars().get(sizeOfA3).whichLane.isEmpty()) {        // and before it there is space
                    if (A3.getCars().size() < sizeOfA3) {             // and A3 is not full
                        A3.addCar(A2.getCars().remove(sizeOfA3));      // then remove from A2 and add to A3
                    }
                    if (A2.getCars().size() >= sizeOfA3) {         // check in A2 if there is further more car which belongs to A3
                        removeFromA2(time);                        // the method is called again
                    } else {                                        // if size is not greater than 3
                        A2.removeCar(time);                         // it means there is no chance to find car which belongs to A3
                        // so try to remove a car from A2
                    }
                } else {                                           // if there is no space infront of it then try to remove a car from A2
                    if (A2.removeCar(time)) {                       // if a car is removed then  make an space at place 2
                        A2.getCars().add(2, new Car("Empty", "", time));
                    }
                }
            }
        }
    }

    public static void tryToRemoveFromA2(int time) {
        if (A2.getCars().size() <= sizeOfA3) {                      // if size of A2 is smaller then no need to car of A3 in A2
            A2.removeCar(time);                                      // simply try to remove a car from A2
        } else {
            if (A2.getCars().get(sizeOfA3).whichLane.equals(A3.laneName)){     // otherwise check at position 3 if there is car from A3 waiting
                removeFromA2(time);
            } else {
                A2.removeCar(time);                                     // if not simply try to remove a car from A2
            }
        }
    }
}


