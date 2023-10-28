public class Car {
    String carName;
    String whichLane;

    int enterTime;
    int exitTime;

    public Car(String carName,String whichLane, double enterTime) {
        this.carName = carName;
        this.whichLane = whichLane;
    }

    public String getCarName() {
        return this.carName;
    }
    public String getWhichLane() {
        return this.whichLane;
    }

    public int getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(int enterTime) {
        this.enterTime = enterTime;
    }

    public int getExitTime() {
        return exitTime;
    }

    public void setExitTime(int exitTime) {
        this.exitTime = exitTime;
    }

    @Override
    public String toString() {
        return carName;
    }
}
