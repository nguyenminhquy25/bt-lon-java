import java.util.Arrays;

public class App {
    public static String[] handleInput(String str) {
        return str.substring(1).replaceAll("[(),]", " ").split("\\s+");
    }
    public static void main(String[] args) {
        String ip = "(0, 0, 0) (2, 0, 1) (2, 1, 0) (0, 1, 0) (0, 0, 1) (2, 0, 1) (2, 1, 1) (0, 1, 1)";
        Rectangular temp = new Rectangular();
        temp.setPoints(handleInput(ip));
        System.out.println(temp.checkPoints());
    }
}
