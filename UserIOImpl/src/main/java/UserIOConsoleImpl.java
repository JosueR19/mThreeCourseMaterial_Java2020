import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO {
    Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        return sc.nextLine();
    }

    @Override
    public int readInt(String prompt) {
        print(prompt);
        return Integer.parseInt(sc.nextLine());
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int value;
        do{
            print(prompt);
            value = Integer.parseInt(sc.nextLine());
        }while(value < min || value > max);
        return value;
    }

    @Override
    public double readDouble(String prompt) {
        print(prompt);
        return Double.parseDouble(sc.nextLine());
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double value;
        do{
            print(prompt);
            value = Double.parseDouble(sc.nextLine());
        }while(value < min || value > max);
        return value;
    }

    @Override
    public float readFloat(String prompt) {
        print(prompt);
        return Float.parseFloat(sc.nextLine());
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float value;
        do{
            print(prompt);
            value = Float.parseFloat(sc.nextLine());
        }while(value < min || value > max);
        return value;
    }

    @Override
    public long readLong(String prompt) {
        print(prompt);
        return Long.parseLong(sc.nextLine());
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        Long value;
        do{
            print(prompt);
            value = Long.parseLong(sc.nextLine());
        }while(value < min || value > max);
        return value;
    }

}


