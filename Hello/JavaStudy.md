## 3월 6일(1주차)

```java
```
---

## 3월 13일(2주차)

```java
```
---

## 3월 20일(3주차)

```java
```
---

## 3월 27일(4주차)

```java
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
