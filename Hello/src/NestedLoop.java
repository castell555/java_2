public class NestedLoop {
    public static void main(String[] args){
        for(int a=2; a<10; a++){
            for(int b=1; b<10; b++){
                System.out.print(a + " X " + b + " = " + a*b);
                System.out.print('\t');
            }
            System.out.println();
        }
    }
}
