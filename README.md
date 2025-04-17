## 4월 17일(7주차)
### ○ 객체 배열 선언과 생성 과정

```java
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
```
```java
1. 배열 레퍼런스 변수 언언
2. 레퍼런스 배열 생성
3. 배열의 각 원소 객체 생성

Circle [] c;
c = new Circle[5];

for(int i =0; i<c.length; i++){
    c[i] = new Circle(i);
}
for(int i=0; i<c.length; i++){
    System.out.print((int)(c[i].getArea() + " "));
}
```
### ○ 객체 배열 만들기 연습
```java
import java.util.Scanner;
class Book{
    String title, author;
    public Book(String title, String author){
        this.title = title;
        this.author = author;
    }
}
public class BookArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Book [] book = new Book[2];

        for(int i = 0; i<book.length; i++){
            System.out.print("제목>> ");
            String title = scanner.nextLine();
            System.out.print("저자>> ");
            String author = scanner.nextLine();
            book[i] = new Book(title, author);
        }
        for(int i=0; i<book.length; i++)
            System.out.print("(" + book[i].title + ", " + book[i].author + ")");
    }
}
```
## ○ 메소드
### ○ 매개 변수가 byte, int, double 등 기본 타입으로 선언되었을 때
##### -> 호출자가 건네는 값이 매개 변수에 복사되어 전달. 실 인자 값은 변경되지 않음

### ○ 객체가 전달되는 경우
##### -> 객체의 레퍼런스만 전달 : 매개 변수가 실 인자 객체 공유

### ○ 배열이 전될되는 경우
##### -> 배열 레퍼런스가 매개 변수에 전달 : 배열 통째로 전달되지 않음
##### -> 객체가 전달되는 경우와 동일 : 매개 변수가 실인자의 배열 공유
### ○ 인자로 배열이 전달되는 예
##### -> char[] 배열을 전달받아 배열 속의 공백(' ') 문자를 ','로 대치하는 메소드로 작성

#### ○ 인자로 배열이 전달되는 예
```java
public class ArrayPassing {
    static void EX48ArrayParameter(char a[]){
        for(int i = 0; i<a.length; i++){
            if(a[i] == ' ')
                a[i] = ',';
        }
    }

    static void printCharArray(char a[]){
        for(int i = 0; i<a.length; i++)
            System.out.print(a[i]);
        System.out.println();
    }
    public static void main(String[] args) {
        char c[] = {'T', 'h', 'i', 's', ' ', 'i', 's', ' ', 'a', ' ', 'p', 'e', 'n', 'c', 'i', 'l', '.'};
        printCharArray(c);
        EX48ArrayParameter(c);
        printCharArray(c);
    }
}
```
### 객체 치환 시 주의할 점
##### -> 객체 치환은 객체 복사가 아니며, 레퍼런스의 복사이다.
```java
public class Samp{
    int id;
    public Samp(int x) {this.id = x;}
    public void set(int x) {this.id = x;}
    public int get() {return this.id};

    public static void main(String [] args){
        Samp ob1 = new Samp(3);
        Samp ob2 = new Samp(4);
        Samp s;

        s = ob2;
        ob1 = ob2;
        System.out.println("ob1.id = " + ob1.get());
        System.out.println("ob2.id = " + ob2.get());
    }
}
```
## 객체 소멸
##### ● new로 할당 받은 객체와 메모리를 JVM으로 되돌려 주는 행위
##### ● 자바는 객체 소멸 연산자 없음
##### ● 객체 소멸은 JVM의 고유한 역할
##### ● C/C++에서는 할당 받은 객체를 개발자가 프로그램 내에서 삭제해야 함
##### ● C/C++의 프로그램 작성을 어렵게 만드는 요인
##### ● 자바에서는 사용하지 않는 객체나 배열을 돌려주는 코딩 책임으로부터 개발자 해방
```java
//가비지 발생
public class GarbageEx{
    public static void main(String[] args){
        String a = new String("Good");
        String b = new String("Bad");
        String c = new String("Normal");
        String d, e;
        a = null;
        d = c;
        c = null;
    }
}
// a가 null이 되면 "Good"는 가비지, c는 null이지만 d=c로 인해 Normal은 d
```
## 가비지 컬렉션
* JVM이 가비지 자동 회수
* 가용 메모리 공간이 일정 이하로 부족해질 떄
* 가비지를 수거하여 가용 메모리 공간으로 확보
* 가비지 컬렉터(garbage collector)에 의 자동 수행

* 강제 가비지 컬렉션 자동 수행 : System 또는 Runtime 객체의 gc() 메소드 호출
```
System.gc();
```
* 이 코드는 JVM에 강력한 가비지 컬렉션 요청
* 그러나 JVM이 가비지 컬렉션 시점을 전적으로 판단

## 자바 패키지 개념
* 패키지 
* 상호 관련 있는 클래스 파일(컴파일된 .class)을 지정하여 관리하는 디렉터리
* 자바 응용프로그램은 하나 이상의 패키지로 구성

## 접근 지정자
* 자바의 접근 지정자 4가지 : private, protected, public, 디폴트(접근지정자 생략)
* 접근 지정자의 목적
* 클래스나 일부 멤버를 공개하여 다른 클래스에서 접근하도록 허용
* 객체 지향 언어의 캡슐화 정책은 멤버를 보호하는 것 
* ㄴ> 접근 지정은 캡슐화에 묶인 보호를 일부 해제할 목적으로 사용
* 접근 지정자에 따른 클래스나 멤버의 공개 범위

### 클래스 접근 지정
* 다른 클래스에서 사용하도록 허용할 지 지정
* public 클래스 : 다른 모든 클래스에게 접근 허용
* 디폴트 클래스(접근 지정자 생략) : 같은 패키지의 클래스에게만 접근 허용
```java
public class World{ //public 클래스
    --------
}
class local{ // 디폴트 클래스
    --------
}
```

## 멤버 접근 지정
* public 멤버 : 패키지에 관계 없이 모든 클래스에게 접근 허용
* pricate 멤버 : 동일 클래스 내에서만 접근 허용. 상속 받은 서브 클래스에서 접근 불가.
* private 멤버 :
* 같은 패키지 내의 다른 모든 클래스에게 접근 허용
* 상속 받은 서브 클래스는 다른 패키지에 

## 멤버의 접근 지정자
* 다음 코드의 두 클래스 Smaple과 AccessEx 클래스는 동일한 패키지에 저장된다. 컴파일 오류를 찾아 내고 이유를 설명하라.
* Sample 클래스의 a와 c는 각각 public, defualt 지정자로 선언이 되었으므로, 같은 패키지에 속한 AccessEx 클래스에서 접근 가능
* b는 private으로 선언이 되었으므로 AccessEx 클래스에서 접근 불가능

## static 멤버
* static 멤버 선언
```java
class StaticSample{
    int n; //non-static 필드
    void g() {...} //non-static 메소드
    static int m; //static 필드
    static void f() {...} //static 메소드
}
```
*객체 생성과 non-static 멤버의 생성
##### : non-static 멤버는 객체가 생성될 때, 객체마다 생긴다.

## static 멤버의 생성
* static 멤버는 클래스당 하나만 생성
* 객체들에 의해 공유됨
```java
class StaticSample{
    int n;
    void g() {...}
    static int m;
    static void f() {...}

    // StaticSample b1 = new StaticSample() 생성 후 
    // b1(n, g()) | (m, f())
    // StaticSample b2 = new StaticSample()
    // b1(n, g()), b2(n, g()) | (m, f())
}
```
## static 멤버 사용
* 클래스 이름으로 접근 가능
```java
StaticSample.n = 3; //클래스 이름으로 static 필드 접근
StaticSample.f(); // 클래스 이름으로 static 메소드 호출
```
* 객체의 멤버로 접근 가능
```java
StaticSample b1 = new StaticSample();
b1.m = 3; //객체 이름으로 static 필드 접근
b1.f(); // 객체 이름으로 static 메소드 호출
```
* non-static 멤버는 클래스 이름으로 접근 안 됨
```java
StaticSample.n = 5; // n은 non-static이므로 컴파일 오류
StaticSameple.g(); // g() 메소드는 non-static이므로 컴파일 오류
```

## static의 활용
* 전역 변수와 전역 함수를 만들 때 활용
* 공유 멤버를 만들 때 : static으로 선언한 멤버는 클래스의 객체들 사이에 공유

# 4-11 static 멤버를 가진 Clac 클래스 작성
```java
Class Calc{
    public static int abs(int a) {return a>0?a:-a; }
    public static int max(int a, int b) {return (a>b)>a:b; }
    public static int min(int a, int b) {return (a>b)>b:a; }
}

public class CalcEx{
    public static void main(String[] args){
        System.out.println(Calc.abs(-5));
        System.out.println(Calc.max(10, 8));
        System.out.println(Calc.min(-3, -8));
    }
}
```
## static 메소드의 제약 조건 1
* static 메소드는 오직 static 멤버만 접근 가능
* 객체가 생성되지 않은 상황에서도 static 메소드는 실행될 수 있기 때문에, non-static 멤버 활용 불가
* non-static 메소드는 static 멤버 사용 가능
---


## 4월 10일(6주차)
### try-catch-finaly
#### 0으로 나누는 예외에 대처하는 try-catch 블록 만들기
```java
import java.util.Scanner;
public class DivideByZero {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int dividend;
        int divisor;

        System.out.println("나뉨수를 입력하세요.");
        dividend = scanner.nextInt();
        System.out.println("나눗수를 입력하세요.");
        divisor = scanner.nextInt();
        try{
        System.out.println(dividend + "를 " + divisor + "로 나누면 몫은 " + dividend/divisor + "입니다.");
        }
        catch(ArithmeticException e){
            System.out.println("나눌 수 없습니다.");
        }
        finally{
        scanner.close();
        }
    }
}
```
#### 입력 오류 시 발생하는 예외
```java
import java.util.Scanner;
import java.util.InputMismatchException;

public class InputException {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("정수 3개를 입력하세요.");

        int sum=0, n=0;

        for(int i=0; i<3; i++){
            System.out.println(i + ">>");
            try{
                n = scanner.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("정수가 아닙니다. 다시 입력하세요");
                scanner.next();
                i--;
                continue;
            }
            sum += n;
        }
        System.out.println("합은 " + sum);
        scanner.close();
    }
}
```
## 절차 ○ 객체 지향 프로그램
```
● 절차 지향 프로그래밍
 √ 작업 순서를 표현하는 컴퓨터 명령 집합
 √ 함수들의 집합으로 프로그램 작성

● 객체 지향 프로그래밍
 √ 컴퓨터가 수행하는 작업을 객체들 간의 상호 작용으로 표현
 √ 클래스 혹은 객체들의 집합으로 프로그램 작성
```
## 클래스와 객체
```
● 클래스 : 객체의 속성과 행위 선언. 객체의 설계도 혹은 틀.

● 객체 : 클래스의 틀로 찍어낸 실체
 √ 프로그램 실행 중에 생성되는 실체
 √ 메모리 공간은 갖는 구체적인 실체
 √ 인스턴스라고도 부름

● 사례
 √ 클레스 : 소나타 자동차,  객체 : 출고된 실제 소나타 100대
 √ 클래스 : 벽시계,        객체 : 우리집에 벽에 걸린 벽시계들
 √ 클래스 : 책상,          객체 : 우리가 사용중인 실제 책상들 
```
## 자바 클래스 구성
```
● 클래스
 √ class 키워드로 구성
 √ 클래서 멤버는 필드와 메소드
 √ 접근 지정자는 public
```
## Circle 클래스의 객체 생셩 및 활용
### 
```java
public class Circle {
    int raidus;
    String name;
    
    public double getArea(){
        return 3.14*raidus*raidus;
    }

    public static void main(String[] args) {
        Circle pizza;
        pizza = new Circle();
        pizza.raidus = 10;
        pizza.name = "자바피자";
        double area = pizza.getArea();
        System.out.println(pizza.name + "의 면적은 " + area);

        Circle donut = new Circle();
        donut.raidus = 2;
        donut.name = "자바도넛";
        area = donut.getArea();
        System.out.println(donut.name + "의 면적은 " + area);
    }
}
```
```java
public class ExCircle {
    int raidus;
    String name;

    public ExCircle(){
        raidus = 1; name = "";
    }
    public ExCircle(int r, String n){
        raidus = r; name = n;
    }
    public double getArea(){
        return 3.14*raidus*raidus;
    }

    public static void main(String[] args) {
        ExCircle pizza = new ExCircle(10, "자바피자");
        double area = pizza.getArea();
        System.out.println(pizza.name + "의 면적은 " + area);

        ExCircle donut = new ExCircle();
        donut.name = "도넛피자";
        area = donut.getArea();
        System.out.println(donut.name + "의 면적은" + area);
    }
}
```
### 클래스 
```java
public class Book {
    String title;
    String author;

    public Book(String t){
        title = t;
        author = "작자미상";
    }

    public Book(String t, String a){
        title = t;
        author = a;
    }
    public static void main(String[] args) {
        Book javaBook = new Book("Java", "황기태");
        Book bible = new Book("Bible");

        System.out.println("제목은 " + javaBook.title + "작가는 " + javaBook.author);
        System.out.println("제목은 " + bible.title + "작가는 " + bible.author);
    }
}

```

---
## 4월 3일(5주차)
### 2중 중첩을 이용한 구구단
```java
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
```
### While문
```java
while문
```
### 배열 선언 및 생성
```java
import java.util.Scanner;
public class ArrayAccess {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int intarray[];
        intarray = new int[5];
        int max=0;
        System.out.println("양수를 5개 입력하세요.");
        for(int i=0; i<5; i++){
            intarray[i] = scanner.nextInt();
            if(intarray[i] > max) max = intarray[i];
        }
        System.out.println("가장 큰 수는 " + max + "입니다.");
        System.out.println("배열의 길이는 " + intarray.length);

        scanner.close();
    }
}

```
### for-each 문
```java
public class foreachEx {
    public static void main(String[] args){
        int [] n = {1, 2, 3, 4, 5};
        int sum = 0;
        for(int k : n){
            System.out.print(k + "");
            sum+=k;
        }
        System.out.println("합은 " + sum);

        String f[] = {"사과 ", "배 ", "바나나 ", "체리 ", "딸기 ", "포도"};
        for(String s : f){
            System.out.print(s + "");
        }
    }
}

```
### 다차원 배열
```java
public class ScoreAverage {
    public static void main(String[] args) {
        double score[][] = {{3.3, 3.4},
                            {3.5, 3.6},
                            {3.7, 4.0},
                            {4.1, 4.2}};
        
        double sum = 0;
        for(int year=0; year<score.length; year++){
            for(int term=0; term<score[year].length; term++){
                sum += score[year][term];
            }
        }
        int n=score.length;
        int m=score[0].length;
        System.out.println("4년 전체 평점 평균은 " + sum/(n*m));

    }
}
```

---

## 3월 27일(4주차)

```java
```
---


## 3월 20일(3주차)

```java
```
---

---

## 3월 13일(2주차)
# h1 tag
## h2
### h3
#### 가가
##### 나나
###### 노노


---


## 3월 6일(1주차)


```java
```

---

* 가가가
* 나나나


1. 나나나
2. 가가가

```java
import java.util.Scanner;

public class HelloWorld {

    public static void main(String[] args) {

        // Creates a reader instance which takes
        // input from standard input - keyboard
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter a number: ");

        // nextInt() reads the next integer from the keyboard
        int number = reader.nextInt();

        // println() prints the following line to the output screen
        System.out.println("You entered: " + number);
    }
}
```