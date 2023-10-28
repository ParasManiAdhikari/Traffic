import java.util.Queue;

public class Main {
    private static final Lane A1 = new Lane("A1", 0.25);
    private static final Lane A2 = new Lane("A2", 0.25);
    private static final Lane A3 = new Lane("A3", 0.07);
    private static final Lane B = new Lane("B1", 0.05);
    private static final Lane C1 = new Lane("C1", 0.17);
    private static final Lane C2 = new Lane("C2", 0.20);
    private static final Lane D = new Lane("D1", 0.07);

    public static void main(String[] args) {

        for (int time = 0; time < 60; time++) {
            System.out.println("\n-------------------- SECOND " + time + " --------------------");
            System.out.println("\n*---- Adding Cars ----*");
            addNewRandomCar();
            System.out.println();
            printLanes();
            System.out.println("\n*---- Removing Cars ----*");
            removeCars(time);
            System.out.println();
            printLanes();
        }
    }

    public static void addToA3() {
        if (A3.getCars().size() == 3) {
            GoToA2(A3.laneName);
        } else {
            A3.addCar();
        }
    }

    private static void GoToA2(String laneName) {
        if (A2.getCars().size() >= 3) {
            A2.addCar(laneName);
        } else {
            for (int i = A2.getCars().size(); i < 3; i++) {
                A2.addCar(new Car("", ""));
            }
        }
    }

    private static void addToA2() {
        if (A2.getCars().size() < A1.getCars().size()) {
            A2.addCar();
        } else {
            A1.addCar();
        }
    }

    private static void addToC2() {
        if (C2.getCars().size() < C1.getCars().size()) {
            C2.addCar();
        } else {
            C1.addCar();
        }
    }

    private static void printLanes() {
        System.out.println("--lanes--");
        printCars(A1.getCars(), A1.laneName);
        printCars(A2.getCars(), A2.laneName);
        printCars(A3.getCars(), A3.laneName);
        printCars(B.getCars(), B.laneName);
        printCars(C1.getCars(), C1.laneName);
        printCars(C2.getCars(), C2.laneName);
        printCars(D.getCars(), D.laneName);
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

    private static void addNewRandomCar() {
        A1.addCar();
        addToA2(); // this method add new car to smaller list between A1 and A2
        addToA3();  // this method check if there is free space in lane A3 otherwise add it to A2 after index 2.
        B.addCar();
        C1.addCar();
        addToC2();  // this method check if the smaller size between C1 and C2 lane and add new car to it.
        D.addCar();
    }

    private static void removeCars(int time) {
        if (time < 39) {
            A1.removeCar();
            if (time < 10) {
                if (A3.getCars().size() < 3) { // A3 is not full
                    A3.removeCar();
                    A2.removeCar();
                } else { // A3 is full
                    if (A3.removeCar()) { // in Case When a car from A3 is removed
                        if (A2.getCars().size() <= 3) { // In Case size of A2 is less than 3 or equal to  3
                            A2.removeCar();
                        } else { // in Case size is greater than 3
                            removeFromA2();
                        }
                    } else { // in Case When a car from A3 is not removed
                        removeFromA2WhenA3Full();
                    }
                }
            } else {
                if (time < 14) {
                    if (A3.getCars().size() == 3) {
                        removeFromA2WhenA3Full();
                    } else {
                        removeFromA2();
                    }
                } else {
                    if (A3.getCars().size() == 3) {
                        removeFromA2WhenA3Full();
                    } else {
                        removeFromA2();
                    }
                    C1.removeCar();
                    C2.removeCar();
                }
            }
        } else {
            if (43 <= time && time < 55) {
                B.removeCar();
                D.removeCar();
            }
        }
    }

    public static void removeFromA2() {
        if (A2.getCars().size() <= 3) {
            A2.removeCar();
        } else {
            if (A2.getCars().get(3).whichLane.equals(A3.laneName)) { // if in A2 lane at index 3 there is car waiting to go to A3
                if (A2.getCars().get(2).whichLane.isEmpty()) {       // and if infront of this car there is no car
                    A3.addCar(A2.getCars().remove(3));        // then add this car to lane A3
                    removeFromA2WhenA3Full();
                } else {
                    if (A2.removeCar()) {                                     // if a car is removed from A2
                        A2.getCars().add(2, new Car("", "")); // and make space by stopping the waiting car at position 4
                    }
                }
            } else { // if there is no car waiting to change the lane to A3 then simply remove a car from A2.
                A2.removeCar();
            }
        }

    }

    public static void removeFromA2WhenA3Full() {
        if (A2.getCars().size() <= 3) {
            A2.removeCar();
        } else {
            if (A2.getCars().get(3).whichLane.equals(A3.laneName)) {              // if there is car waiting to go to A3
                if (A2.removeCar()) {                                               // if a car is removed from A2
                    A2.getCars().add(2, new Car("", ""));     // then make space by stopping the waiting car at position 4
                }
            } else {                                                               // if no car is waiting at position 4
                A2.removeCar();                                                     // then just remove the first car.
            }
        }
    }
}


