public class Car {
    String carName;
    String whichLane;
    public Car(String carName,String whichLane) {
        this.carName = carName;
        this.whichLane = whichLane;
    }

    public String getCarName() {
        return this.carName;
    }
    public String getWhichLane() {
        return this.whichLane;
    }

    @Override
    public String toString() {
        return carName;
    }
}
