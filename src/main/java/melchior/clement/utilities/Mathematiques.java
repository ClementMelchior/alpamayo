package melchior.clement.utilities;

public class Mathematiques {
    
    public static int alea() {
        return (int) Math.round(Math.random() * 10);
    }

    public static int alea(int max) {
        return (int) Math.round(Math.random() * max);
    }
}
