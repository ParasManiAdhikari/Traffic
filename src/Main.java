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
        //            System.out.println("----------------- DWELL TIME -------------");
//            System.out.println("A1 : " + A1.exitedCars);
//            System.out.println("AVG DWELL TIME OF A1: " + getDwellTimes(A1));
//            System.out.println("A2 : " + A2.exitedCars);
//            System.out.println("AVG DWELL TIME OF A2: " + getDwellTimes(A2));
//            System.out.println("A3 : " + A3.exitedCars);
//            System.out.println("AVG DWELL TIME OF A3: " + getDwellTimes(A3));
//            System.out.println("AVG DWELL TIME OF B1: " + getDwellTimes(B1));
//            System.out.println("AVG DWELL TIME OF C1: " + getDwellTimes(C1));
//            System.out.println("AVG DWELL TIME OF C2: " + getDwellTimes(C2));
//            System.out.println("AVG DWELL TIME OF D1: " + getDwellTimes(D1));
//
//            System.out.println("---------- Cars Left At the End --------");
//            System.out.println("TOTAL CARS A1: " + A1.totalCar);
//            System.out.println("TOTAL CARS A2: " + A2.totalCar);
//            System.out.println("TOTAL CARS A3: " + A3.totalCar);
//            System.out.println("TOTAL CARS B1: " + B1.totalCar);
//            System.out.println("TOTAL CARS C1: " + C1.totalCar);
//            System.out.println("TOTAL CARS C2: " + C2.totalCar);
//            System.out.println("TOTAL CARS D1: " + D1.totalCar);
//        printAllAverageRemainingCars();

    }

    private static void printAllAverageRemainingCars(){
        System.out.println("1b) --------------- Remaining Cars");
        System.out.println("Remaining cars at A1 :" + remainingCarsA1);
        System.out.println("Average: " + calculateAverage(remainingCarsA1));
        System.out.println("Remaining cars at A2 :" + remainingCarsA2);
        System.out.println("Average: " + calculateAverage(remainingCarsA2));
        System.out.println("Remaining cars at A3 :" + remainingCarsA3);
        System.out.println("Average: " + calculateAverage(remainingCarsA3));
        System.out.println("Remaining cars at B1 :" + remainingCarsB1);
        System.out.println("Average: " + calculateAverage(remainingCarsB1));
        System.out.println("Remaining cars at C1 :" + remainingCarsC1);
        System.out.println("Average: " + calculateAverage(remainingCarsC1));
        System.out.println("Remaining cars at C2 :" + remainingCarsC2);
        System.out.println("Average: " + calculateAverage(remainingCarsC2));
        System.out.println("Remaining cars at D1 :" + remainingCarsD1);
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
        System.out.println("\nANSWERS: 1a) --------------- DWELL TIME");
        System.out.print(dwellA1);
        System.out.println(" | Average :" + calculateAverage(dwellA1));
        System.out.print(dwellA2);
        System.out.println(" | Average :" + calculateAverage(dwellA2));
        System.out.print(dwellA3);
        System.out.println(" | Average :" + calculateAverage(dwellA3));
        System.out.print(dwellB1);
        System.out.println(" | Average :" + calculateAverage(dwellB1));
        System.out.print(dwellC1);
        System.out.println(" | Average :" + calculateAverage(dwellC1));
        System.out.print(dwellC2);
        System.out.println(" | Average :" + calculateAverage(dwellC2));
        System.out.print(dwellD1);
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

        if (A2.getCars().size() < sizeOfA3){
            if (A3.getCars().size() < sizeOfA3){
                A3.addCar(time);
            }
        } else {
            if (A3.addOrNot()) {
                A2.addCar(A3.laneName, time, A3.carNumber);
                A3.carNumber++;
            }
            System.out.println();
        }
    }

    private static void addToA2(int time) {
        if (A2.getCars().size() <= A1.getCars().size()) {
            A2.addCar(time);
        } else {
            System.out.print("A2 -> A1 : ");
            A1.addCar(time);
        }
    }

    private static void addToC2(int time) {
        if (C2.getCars().size() < C1.getCars().size()) {
            C2.addCar(time);
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
            A1.removeCar(time);
            // A2
            if (A2.getCars().size() <= sizeOfA3) {
                A2.removeCar(time);
            } else {
                if (A2.getCars().get(3).whichLane.equals(A3.laneName)){
                    removeFromA2(time);
                } else {
                    A2.removeCar(time);
                }
            }
            if (timeModulo60 < 10) {
                A3.removeCar(time);
                if (A2.getCars().size() <= sizeOfA3) {
                    A2.removeCar(time);
                } else {
                    if (A2.getCars().get(3).whichLane.equals(A3.laneName)){
                        removeFromA2(time);
                    } else {
                        A2.removeCar(time);
                    }
                }
            } else if(timeModulo60 < 14) {
                C1.removeCar(time);
                C2.removeCar(time);
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
        if (A2.getCars().get(3).whichLane.equals(A3.laneName)) {                        // if in A2 lane at index 3 there is car waiting to go to A3
            if (A2.getCars().get(2).whichLane.isEmpty()) {                               // then add this car to lane A3
                if (A3.getCars().size() < sizeOfA3) {
                    A3.addCar(A2.getCars().remove(3));
                }
                if (A2.getCars().size() == sizeOfA3) {
                    removeFromA2(time);
                } else {
                    A2.removeCar(time);
                }
            } else {
                if (A2.removeCar(time)) {                                                      // if a car is removed from A2
                    A2.getCars().add(2, new Car("Empty", "", time));        // then make space by stopping the waiting car at position 4
                }
            }
        }
    }
}


