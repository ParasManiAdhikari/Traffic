import java.util.Random;

public class test {
    public static void main(String[] args) {
        int even = 0,odd = 0;
        Random a = new Random();
        for (int i = 0; i < 10 ; i++) {
            double value = a.nextDouble();
            if( value < 0.5) {
                even ++;
            }else {
                odd++;
            }
            System.out.println(value);
        }
        System.out.printf("even is :%d \nodd is : %d",even,odd);
    }
}
