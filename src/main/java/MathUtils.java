import java.util.Optional;

public class MathUtils {

    public int add(int a, int b) {
        return a + b;
    }

    public int divide(int a, int b) {
        return a / b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double computerCircleArea(double radius) {
        return Math.PI * radius * radius;
    }

    public boolean isOddNumbers(int number) {
        return number % 2 != 0;
    }

    public boolean isBlank(String string) {
        return string == null || string.trim().isEmpty();
    }
}
