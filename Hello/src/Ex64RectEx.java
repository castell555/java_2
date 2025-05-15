class Rect{
    int width, height;

    public Rect(int width, int height){
        this.width = width;
        this.height = height;
    }
    public boolean equals(Object obj){
        if(!(obj instanceof Rect)) return false; //obj가 Rect 타입이 아닐 경우
        Rect p = (Rect) obj;
        return this.width * this.height == p.width * p.height;
        //point p = (point)obj; obj를 point 타입으로 다운 캐스팅
    }
}

public class Ex64RectEx{
    public static void main(String[] args){
        Rect a = new Rect(2, 3);
        Rect b = new Rect(3, 2);
        Rect c = new Rect(3, 4);

        if(a.equals(b))
            System.out.println("a is equals to b");
        if(a.equals(c))
            System.out.println("a is equals to c");
        if(b.equals(c))
            System.out.println("b is equals to c");
    }
}