public class CricleA {
    int radius;
    public CricleA(int radius){
        this.radius = radius;
    }
    void set(int radius){
        this.radius = radius;
    }
    public static void main(String[] args) {
        CricleA ob1 = new CricleA(1);
        CricleA ob2 = new CricleA(2);
        CricleA ob3 = new CricleA(3);

        ob1.set(4);
        ob2.set(5);
        ob3.set(6);
    }
}
