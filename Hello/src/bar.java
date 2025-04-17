public class bar {
    public static void main(String[] args) {
        int a=5, b=7;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a + ", " + b);

        final double PI = 3.14;
        double radius = 10.2;
        double circleArea = radius*radius*PI;

        System.out.print("반지름 "+ radius + ", ");
        System.out.println("원의 면적 = " + circleArea);
    }
    
}
