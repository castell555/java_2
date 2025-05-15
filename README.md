# ● JAVA 수업---------------(202230318 윤준서)
---
## 5월 15일(11주차)
### package의 운영 방법
* 패키지 이름은 도메일 기반으로 시작 형식 : com.회사이름.프로젝트명.기능명 -> 충돌 방지(전 세계 어디서든 유일한 패키지명 확보 가능) / 모듈별 분리 가능
* 기능/역할별로 하위 패키지를 구분 : utils. controller, service 등
* 디렉토리 구조와 package 선언을 정확히 일치해야 합니다.
* import는 필요한 만큼만, * 전체 import는 피하는 것이 좋습니다.<br/>

### 모듈 개념
* java 9에서 도입된 개념
* 패키지와 이미지 등의 리소스를 담은 컨테이너
* 모듈 파일(.jmod)로 저장

## 자바 플랫폼의 모듈화
* 자바 플랫폼
    * 자바의 개발 환경(JDK)과 자바의 실행 환경(JRE)를 지칭. java se(자바 api) 포함.
    * 자바 API의 모든 클래스가 여러 개의 모듈로 재구성됨
    * 모듈 파일은 JDK의 jmods 디렉터리에 저장하여 배포
* 모듈 파일로부터 모듈을 푸는 명령
    * jmod extract "C:\Program Files\Java\jdk-17.0.3.+7\jmods\java.base.jmod"

## 자바 모듈화의 목적
* 자바 컴포넌트들을 필요에 따라 조립하여 사용하기 위함
* 컴퓨터 시스템의 불필요한 부담 감소
    * 세일한 모듈화를 토앻 필요 없는 모듈이 로드되지 않게 함
    * 소형 IoT 장치에도 자바 응용프로그램이 실행되고 성능을 유지하게 함

## Object 클래스
* 모든 자바 클래스는 반드시 Object를 상속받도록 자동 컴파일
* 모든 클래스의 수퍼 클래스
* 모든 클래스가 상속받는 공통 메소드 포함

```java
Object 클래스로 객체의 속성 알아내기

class Point{
    int x, y;
    public Point(int x, int y){
        this.x = x; this.y = y;
    }
}
public class ObjectPropertyEx{
    public static void main(String [] args){
        Point p = new Point(2,3);
        System.out.println(p.getClass().getName());
        System.out.println(p.hashCode());
        System.out.println(p.toString());
    }
}
```
## toString() 메소드, 객체를 문자열로 변환
* 각 클래스는 toString()을 오버라이딩하여 자신만의 문자열 리턴 가능
    * 객체를 문자열로 반환
    * 원형 : public String toString();

* 컴파일러에 의한 toString() 자동 변환
    * 객체 + 문자열 -> 객체.toString() + 문자열로 자동 변환
    * 객체를 단독으로 사용 하는 경우 -> 객체.toString()으로 자동 변환

```java
point 클래스에 toString() 작성

class Point{
    private int x,y;
    public Point(int x, int y){
        this.x = x; this.y = y;
    }
    public String toString(){
        return "Point(" + x + "," + y + ")";
    }
}
public class ToStringEx {
    public static void main(String[] args) {
        Point a = new Point(2,3);
        System.out.println(a.toString());
        System.out.println(a);
    }
}
```
```java
Point 클래스의 equals() 작성

class Point3{
    int x, y;
    public Point3(int x, int y){
        this.x = x; this.y = y;
    }
    public boolean equals(Object obj){
        Point3 p = (Point3)obj; //obj를 Point 타입으로 다운 캐스팅
        if(x == p.x && y == p.y) return true;
        else return false;
    }
}
public class EqualsEx{
    public static void main(String[] args){
        Point3 a = new Point3(2,3);
        Point3 b = new Point3(2,3);
        Point3 c = new Point3(3,4);
        if(a == b) System.out.println("a==b");
        if(a.equals(b)) System.out.println("a is equals to b");
        if(a.equals(c)) System.out.println("a is equals to c");
    }
}
```
```java
class Rect{
    int width, height;

    public Rect(int width, int height){
        this.width = width;
        this.height = height;
    }
    public boolean equals(Object obj){
        if(!(obj instanceof Rect)) return false;
        Rect p = (Rect) obj;
        reutnr this.width * this.height == p.width * p.height;
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
```

## Wrapper 클래스
* wrapper 클래스 : 자바의 기본 타입을 클래스화 한 8개 클래스를 통칭
* 용도 : 객체만 사용할 수 있는 컬렉션 등에 기본 타입의 값을 사용하기 위해 Wrapper 객체로 만들어 사용
* 객체를 생성하기 위해선 .valueOf()를 사용

```java
Wrapper 객체로부터 기본 타입 값 알아내기

Integer i = integer.valueOf(10);
int ii = i.intvalue();
```

```java
문자열을 기본 데이터 타입으로 변환

int i = integer.parseint("123");
boolean b = Boolean.parseBoolean("true");
double f = Double.parseDouble("3.14");
```

## 박싱과 언박싱
* 박싱(boxing) : 기본 타입의 값을 Wrapper 객체로 변환하는 것
* 언박싱(unboxing) : Wrapper 객체에 들어 있는 기본 타입의 값을 빼내는 것. 박싱의 반대
```java
Integer ten = Integer.valueOf(10); //박싱
int n = ten.intValue(); //언박싱
```

* 자동 박싱과 자동 언박싱 : JDK1.5부터 박싱과 언박싱은 자동으로 이루어지도록 컴파일됨
```java
Integer ten = 10; //자동 박싱
int n = ten; //자동 언박싱
```

## String 생성과 특징
* String 클래스는 문자열을 나타냄
* 스트링 리터럴(문자열 리터럴)은 String 객체로 처리됨
* 스트링 객체의 생성 사례
```java
String str1 = "abcd"; //대문자이므로 클래스
char data[] = {'a', 'b', 'c', 'd'};
String str2 = new String(data);
String str3 = new String("abcd"); //str2와 str3는 모두 "abcd" 스트링
```

### 스트링 리터럴과 new String()
* 스트링 리터럴
    * 자바 가상 기계 내부에서 리터럴 테이블에 저장되고 관리됨
    * 응용프로그램에서 공유됨
    * 스트링 리터럴 사례) String s = "Hello";

* new String()으로 생성된 스트링
    * 스트링 객체는 힙에 생성
    * 스트링은 공유되지 않음

### String 활용
* 스트링 비교, equals()와 compareTo()
    * 스트링 비교에 == 연산자 절대 사용 금지
    * equals() : 스트링이 같으면 true, 아니면 false 리턴
```java
String java = "java";
if(java.equals("Java")) //True
```

* int compareTo(String anotherString)
    * 문자열이 같으면 0 리턴
    * 이 문자열이 anotherString 보다 먼저 나오면 음수 리턴
    * 이 문자열이 anotherString 보다 나중에 나오면 양수 리턴
```java
String java = "Java";
String cpp = "C++";
int res = java.compareTo(cpp);
if(res == 0) System.out.println("the same");
else if(res < 0) System.out.println(java + " < " + cpp);
else System.out.println(java + " > " + cpp);
```

### String 활용
* 공백 제거, String trim()
* 키보드나 파일로부터 스트링을 입력받을 때, 스트링 앞뒤에 공백이 끼는 경우, trim()을 사용해 스트링 앞뒤에 있는 공백 문자를 제거한 스트링 리턴

```java
예제 6-6
```
---
## 5월 08일(10주차)
### 추상 클래스
* 추상 메소드
    * abstract로 선언된 메소드, 메소드의 코드는 없고 원형만 선언
```java
abstract public String getName(); //추상 메소드
abstract public String fail() {return "Good bye"}; //추상 메소드 아님, 컴파일 오류
```
* 추상 클래스
    * 추상 메소드를 가지며, abstract로 선언된 클래스
    * 추상 메소드 없이, abstract로 선언한 클래스

* 추상 클래스는 온전한 클래스가 아니기 때문에 인스턴스를 생성할 수 없음
```java
JComponent p;
p = new JComponent(); //컴파일 오류, 추상 클래스의 인스턴스 생성 불가
```
* 추상 클래스 상속
    * 추상 클래스를 상속받으면 추상 클래스가 됨
    * 서브 클래스도 abstract로 선언해야 함
```java
abstract class A{ //추상 클래스
    abstract public int add(int x, int y); //추상 매소드
}
abstract class B{ //추상 클래스
    public void show() {system.out.println("B")}
}
```

* 추상 클래스 구현
    * 서브 클래스에서 슈퍼 클래스의 추상 메소드 구현(오버라이딩)
    * 추상 클래스를 구현한 서브 클래스는 추상 클래스 아님

## 추상 클래스의 목적
* 추상 클래스의 목적
    * 상속을 위한 슈퍼 클래스로 활용하는 것
    * 서브 클래스에서 추상 메소드 구현
    * 다향성 실현

* 추상 클래스 Calculator를 상속받는 GoodCalc 클래스를 구현하라
```java
abstract class Calculator{
    public abstract int add(int a, int b);

    public abstract int substract(int a, int b);

    public abstract double average(int[] a);
}



public class GoodCalc extends Calculator{
    @Override
    public int add(int a, int b){ // 추상 메소드 구현
        return a + b;
    }

    public int substract(int a, int b){ // 추상 메소드 구현
        return a - b;
    }

    public double average(int[] a){ // 추상 메소드 구현
        double sum=0;
        for(int i=0; i<a.length i++)
            sum += a[i];
        return sum/a.length;
    }

    public static void main(String [] args){
        GoodCalc c = new GoodCalc();
        System.out.println(c.add(2,3));
        System.out.println(c.substract(2,3));
        System.out.println(c.average(new int [] (2,3,4)));
    }
}
```
## 자바의 인터페이스
* 소프트웨어를 규격화된 모듈로 만들고, 인터페이스가 맞는 모듈을 조립하듯이 응용프로그램을 작성하기 위해서 사용
* 자바의 인터페이스
    * 클래스가 구현해야 할 메소드들이 선언되는 추상형

```java
인터페이스의 사례
Interface PhoneInterface(){
    int BUTTONS = 20; // 상수 필드 선언
    void sendCall(); // 추상 메소드
    void receiveCall(); // 추상 메소드
}
```
## 인터페이스의 구성 요소들의 특징
* 상수 : public만 혀용, public static final 생략
* 추상 메소드 : public abstract 생략 가능
* default 메소드 : 
    * 인터페이스에 코드가 작성된 메소드
    * 인터페이스를 구현하는 클래스에 자동 상속
    * public 접근 지정만 허용, 생략 가능

* pricate 메소드 : 
    * 인터페이스 내에 메소드 코드가 작성되어야 함
    * 인터페이스 내에 있는 다른 메소드에 의해서만 호출 가능
* static 메소드 : public, pricate 모두 지정 가능. 생략하면 pubic

* 인터페이스 간에 상속 가능
    - 인터ㅔ이스를 상속하여 확장된 인터페이스 작성 가능
    * extends 키워드로 상속 선언

* 인터페이스 다중 상속 허용
```java
interface MoblicePhoneInterface extends PhoneInterface(){
    void sendSNS();     // 추상 메소드 추가
    void receiveSNS();  // 추상 메소드 추가
}
```
* 인터페이스의 추상 메소드를 모두 구현한 클래스 작성
    * implements 키워드 사용
    * 여러 개의 인터페이스 동시 구현 가능

* 인터페이스 구현 사례
    * PhoneInterface 인터페이스 구현한 SamsungPhone 클래스
```java
class SamsungPhone implements PhoneInterface{
    public void 
}
```
```java
interface PhoneInterface{
    final int TIMEOUT = 10000;
    void sendCall();
    void receiveCall();
}
interface MobilePhoneInterface extends PhoneInterface{
    void sendSMS();
    void receiveSMS();
}
interface MP3Interface{
    public void play();
    public void stop();
}
class PDA{
    public int calculate(int x, int y) {return x + y;}
}

class SmartPhone extends PDA implements MobilePhoneInterface, MP3Interface{
    public void sendCall() {System.out.println("전화 걸기");}
    public void receiveCall() {System.out.println("전화 받기");}
    public void sendSMS() {System.out.println("SMS 보내기");}
    public void receiveSMS() {System.out.println("SMS 받기");}

    public void play(){System.out.println("음악 재생");}
    public void stop() {System.out.println("재생 중지");}

    public void schedule() {System.out.println("일정 관리");}
}
public class InterfaceEX{
    public static void main(String [] args){
        SmartPhone p = new SmartPhone();
        p.sendCall();
        p.play();
        System.out.println(p.calculate(3, 5));
        p.schedule();
    }
}
```
# 6.1 패키지
* 패키지(package)
    * 서로 관련된 클래스와 인터페이스를 컴파일한 클래스 파일들을 묶어 놓은 디렉터리
    * 하나의 응용프로그램은 한 개 이상의 패키지로 작성
    * 패키지는 jar 파일로 압축할 수 있음

* 모듈(module)
    * 여러 패키지와 이미지 등의 자원을 모아 놓은 컨테이너
    * 하나의 모듈을 하나의 .jmod 파일에 저장

* Java 9부터 모듈화 도입
    * 플랫폼의 모듈화 : Java 9부터 자바 API의 모든 클래스들(자바 실행 환경)을 패키지 기반에서 모듈들로 완전히 재구성
    * 응용프로그램의 모듈화 : 클래스들은 패키지로 만들고, 다시 패키지를 모듈로 만듦, 모듈 프로그래밍은 어렵고 복잡

* 모듈화의 목적
    * java 9부터 자바 API를 여러 모듈(99개)로 분활 : java 8까지는 rt.jar의 한 파일에 모든 API 저장. 현재 70개로 정리됨
    * 응용프로그램이 실행할 때 꼭 필요한 모듈들로만 실행 환경 구축 : 메모리 자원이 열약한 작은 소형 기기에 꼭 필요한 모듈로 구성된 작은 크기의 실행 이미지를 만들기 위함

* 모듈의 현실
* java 9부터 전면적으로 도입
* 복잡한 개념
* 큰 자바 응용프로그램에는 개발, 유지보수 등에 적합
* 모듈화 작업은 매우 중요한 개념이며, 소규모 프로젝트부터 적용해야 대형 프로젝트 쉡게 도입, 활용할 수 있음

---

* 다른 패키지에 작성된 클래스 사용
    * import를 이용하지 않는 경우
    * 소스에 클래스 이름의 완전 경로명 사용

* 필요한 클래스만 import
    * 소스 시작 부분에 클래스의 경로명 import
    * import 패키지.클래스
    * 소스에는 클래스 명만 명시하면 됨

* 패키지 전체를 import
    * 소스 싲가 부분에 패키지의 경로명.* import
    * import 패키지.*
    * 소스에는 클래스 명만 명시하면 됨
    * import java.util.*;
---
* 클래스 파일이 저장되는 위치는
    * 클래스나 인터페이스가 컴파일 되면 클래스 파일 생성
    * 클래스 파일은 패키지로 선언된 디렉터리에 저장

* 패키지 선언
    * 소스 파일의 맨 앞에 컴파일 후 저장된 패키지 지정 -> 패키지명;
---
### 디폴트 패키지
* package 선언문이 없는 자바 소스 파일의 경우
    * 컴파일러는 클래스나 인터페이스를 디폴트 패키지에 소속시킴
    * 디폴트 패키지 -> 현재 디렉터리
---
### 패키지의 운영 방법
* 패키지 이름은 도메인 기반으로 시작 형식 : com.회사이름.프로젝트명.기능명 -> 충돌 방지(전 세계 어디서든 유일한 패키지명 확보 가능) / 모듈별 분리 가능
* 기능/역할별로 하위 패키지를 구분 : utils, service 등
* 디렉터리 구조와 package 선언을 정확히 일치
* import는 필요한 만큼만
## 5월 1일(9주차-시험)
---
## 4월 18일(8주차)

### final 클래스와 메소드
* final 클래스 더 이상 클래스 상속 불가
* final 필드 : 상수를 선언할 때 사용
```java
class SharedClass{
    public static final double PI = 3.14;
}
```
* 상수 필드는 선언 시에 초기 값을 지정하여야 한다.
* 상수 필드는 실행중에 값을 변경할 수 없다.

### static 메소드의 제약 조건
* static 메소드는 this 사용불가
* static 메소드는 객체 없어도 사용 가능하므로, this 레퍼런스 사용할 수 없음

---
# ○ 상속
## 상속의 필요성
* 상속이 없는 경우 중복된 멤버를 가진 4개의 클래스
* 상속을 이용한 경우 중복이 제거되고 간결해진 클래스 구조

## 클래스 상속과 객체
* 상속 선언 : extends 키워드 사용
* 부모 클래스를 물려받아 자식 클래스를 확장한다는 의미
* 부모 클래스 -> 슈퍼 클래스(super class)
* 자식 클래스 -> 서브 크래스(sub class)
```java
class point{
    int x,y;
    ...
}
class ColorPoint extends Point{
    String color;
    ...
}
```

## 서브 클래스 객체의 모양
* 슈퍼 글래스 객체와 서브 클래스의 객체는 별개
* 서브 클래스 객체는 슈퍼 클래스 멤버 포함

```java
// 예제 5-1
Public class Ex51ColorPointEx{
    Public static void main(String[] args){
        Point p = new Point(); // Point 객체 생성
        p.set(1, 2); // Point 클래스의 set() 호출
        p.showPoint();

        ColorPoint cp = new ColorPoint();
        cp.set(3, 4); // Point 클래스의 set() 호출
        cp.setColor("red"); // ColorPoint의 setColor() 호출
        cp.showColorPoint(); // 컬러와 좌표 출력
    }
}
class Point{
    private int x,y; // 한 점을 구성하는 x, y 좌표
    public void set(int x, int y){
        this.x = x; this.y = y;
    }
    public void showPoint(){
        System.out.println("(" + x + "," + y + ")");
    }
}

class ColorPoint extends Point{
    private String color;
    Class Point{

    }
}
// Point를 상속받은 ColorPoint 선언
class ColorPoint extends Point{
    private String color;

    public void setColor(String color){
        this.color = color;
    }

    public void showColorPoint(){
        System.out.print(color);
        showPoint();
    }
}
```

## 자바 상속의 특징
* 클래스 다중 상속 불허
    * 하나의 클래스가 둘 이상의 부모 클래스를 동시에 상속받는 것을 말합니다.
* C++는 다중 상속 가능
* C++는 다중 상속으로 멤버가 중복 생성되는 문제 있음.
    * 부모 클래스 간에 계층적인 관계가 있을 경우, 중복된 멤버가 생성될 수 있다.
    * 모호성 문제 : 두 부모 클래스에 동일한 이름의 멤버가 존재할 경우, 어떤 부모의 멤버를 호출해야 할지 모호해진다.
* 자바는 인터페이스의 다중 상속 허용
    * 다중 상속과 유사한 기능을 제공
* 모든 자바 클래스는 묵시적으로 Object클래스 상속받음
* java.lang.Object는 클래스는 모든 클래스의 슈퍼 클래스

## 슈퍼 클래스 멤버에 대한 서브 클래스의 접근
* 슈퍼 클래스의 private 멤버 : 서브 클래스에 접근할 수 없음
* 슈퍼 클래스의 디폴트 멤버 : 서브 클래스가 동일한 패키지에 있을 때, 접근 가능
* 슈퍼 클래스의 public 멤버 : 서브 클래스는 항상 접근 가능
* 슈퍼 클래스의 protected 멤버 : 
    * 같은 패키지 내의 모든 클래스 접근 허용 
    * 패키지 여부와 상관없이 서브 클래스는 접근 가능

## 서브 클래스/슈퍼 클래스의 생성자 호출과 실행
* 서브 클래스의 객체가 생성될 때 : 슈퍼클래스 생성자와 서브 클래스 생성자 모두 실행
* 호출 순서 : 서브 클래스의 생성자 먼저 호출 -> 슈퍼 클래스 생성자 호출
* 실행 순서 : 슈퍼 클래스의 생성자가 먼저 실행 -> 서브 클래스의 생성자 실행

## 서브 클래스와 슈퍼 클래스의 생성자 선택
* 슈퍼 클래스와 서브 클래스 : 각각 여러 개의 생성자 작성 가능
* 서브 클래스의 객체가 생성될 때 : 슈퍼 클래스 생성자 1개와 서브 클래스 생성자 1개가 실행

* 서브 클래스의 생성자와 슈퍼 클래스의 생성자가 결정되는 방식
    1. 개발자의 명시적 선택
        * 서브 클래스 개발자가 슈퍼 클래스의 생성자 명시적 선택
        * super() 키워드를 이용하여 선택

    2. 컴파일러가 기본 생성자 선택
        * 서브 클래스 개발자가 슈퍼 클래스의 생성자를 선택하지 않는 경우
        * 컴파일러가 자동으로 슈퍼 클래스의 기본 생성자 선택

## super()로 슈퍼 클래스의 생성자 명시적 선택
* super() : 서브 클래스에서 명시적으로 슈퍼 클래스의 생성자 선택 호출
* 사용 방식
    * super(parameter);
    * 인지를 이용하여 슈퍼 클래스의 적당한 생성자 호출.
    * 반드시 서브 클래스 생성자 코드의 제일 첫 라인에 와야 함.

## super()를 활용한 ColorPoint 작성
```java
// 예제 5-2
Class Point{
    private int x, y; // 한 점을 구성하는 x, y 좌표
    Point(){
        this.x = this.y = 0;
    }
    Point(int x, int y){
        this.x = this.y = y;
    }
    void showPoint(){ // 점의 좌표 출력
        System.out.println("(" + x + "," + y + ")");
    }
}

class ColorPoint extends Point{ // Point를 상속받은 ColorPoint 선언
    private String color; // 점의 색
    ColorPoint(int x, int y, String color){
        super(x, y); // Point의 생성자 Point(x, y) 호출
        this.color = color;
    }
    void showColorPoint(){ // 컬러 점의 좌표 출력
        System.out.print(color);
        showPoint(); // Point 클래스의 showPoint() 호출
    }
}

public class SuperEx{
    public static void main(String[] args){
        ColorPoint cp = new ColorPoint(5, 6, "blue");
        cp.showColorPoint();
    }
}
// 예제 5-1을 실행시키고 5-2를 실행시키면 오류가 발생하게 되는데 이때는 클래스 이름을 바꿔주면 해결
```
## 업케스팅 개념
* 하위 클래스의 래퍼런스는 상위 클래스를 가리킬 수 없지만, 상위 클래스의 래퍼런스는 하위 클래스를 가리킬 수 있다는 설명.

## 업케스팅
    * 생물이 들어가는 박스에 사람이나 코끼리를 넣어도 무방.
    * 사람이나 코끼리 모두 생물을 상속받았기 때문.
* 업케스팅이란?
    * 서브 클래스의 레퍼런스를 슈퍼 클래스 레퍼런스에 대입
    * 슈퍼 클래스 레퍼런스로 서브 클래스 객체를 가리키게 되는 현상
    ```java
    class Person{} // 슈퍼 클래스
    class Student extends Person{}
    Person p;
    Student s = new Student();
    p = s; // 업캐스팅
    ```
    ```java
    p.grade = "A"; // grade는 Person의 멤버가 아니므로 컴파일 오류
    ```
## 다운캐스팅
* 슈퍼 클래스 래퍼런스를 서브 클래스 레퍼런스에 대입
* 업캐스팅 된 것을 다시 원래대로 되돌리는 것
* 반드시 명시적 타입 변환 지정
```java
class Person{}
class Student extends Person{}

Person p = new Student("이재문"); // 업캐스팅

Student s = (Student)p; //다운캐스팅, 강제타입변환
```
## 업캐스팅 레퍼런스로 객체 구별
* 업캐스팅된 레퍼런스로는 객체의 실제 타입을 구분하기 어려움
* 슈퍼 클래스는 여러 서브 클래스에 상속되기 때문
* 예를 들어 아래의 클래스 계층 구조에서, p가 가리키는 객체가 Person 객체인지, Studen 객체인지, Professor 객체인지 구분하기 어려움
```java
Person p = new Person();
Person p = new Student(); // 업캐스팅
Person p = new Professer(); // 업캐스팅
```

## instanceog 연산자 사용
* 래퍼런스가 가리키는객체의 타입 식별 : 연산의 결과는 true/false의 불린 값으로 반환
* instanceof 연산자 사용 사례
```java
Person p = new Professor();

if(p instanceof Person) // true
if(p instanceof Student) // false. Student를 상속받지 않기 때문
if(p instanceof Researcher) // true
if(p instanceof Professor) // true
```

## 메소드 오버라이딩의 개념
* 서브 클래스에서 슈퍼 클래스의 메소드 중복 작성
* 슈퍼 클래스의 메소드 무력화, 항상 서브 클래스에 오버라이딩한 메소드가 실행되도록 보장
* "메소드 무시하기"로 번역되기도 함
오버라이딩 조건
* 오버라이딩 조건
    * 슈퍼 클래스 메소드의 원형(메소드 이름, 인자 타입 및 개수, 리턴 타입) 동일하게 작성

## 서브 클래스 객체와 오버라이딩된 메소드 호출
* 오버라이딩 한 메소드가 실행됨을 보장
```java
class A{
    void f(){
        System.out.println("A의 f() 호출");
    }
}
class B extends A{
    void f(){
        System.out.println("B의 f() 호출");
    }
}
```

## 오버라이딩의 목적, 다형성 실현
* 오버라이딩으로 다형성 실현
* 하나의 인터페이스(같은 이름)에 서로 다른 구현
* 슈퍼 클래스의 메소드를 서브 클래스에서 각각 목적에 맞게 다르게 구현

## 동적 바인딩 - 오버라이딩된 메소드 호출
* SuperObject 하나만 있는 응용프로그램의 경우 혹은 상속받은경우 모두 동적 바인딩을 한다.
    * 오버라이딩 메소드가 항상 호출된다.
    * SUperObject는 키워드가 아님

## super 키워드로 슈퍼 클래스의 멤버 접근
* 슈퍼 클래스의 멤버를 접근할 때 사용되는 레퍼런스. super.슈퍼클래스의 멤버
* 서브 클래스에서만 사용
* 슈퍼 클래스의 필드 접근
* 슈퍼 클래스의 메소드 호출 시 super로 이루어지는 메소드 호출 : 정적 바인딩

## 오버로딩과 오버라이딩

---


## 4월 17일(7주차)
## static 메소드의 제약 조건 1
* static 메소드는 오직 static 멤버만 접근 가능
* 객체가 생성되지 않은 상황에서도 static 메소드는 실행될 수 있기 때문에, non-static 멤버 활용 불가
* non-static 메소드는 static 멤버 사용 가능

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

## static의 활용
* 전역 변수와 전역 함수를 만들 때 활용
* 공유 멤버를 만들 때 : static으로 선언한 멤버는 클래스의 객체들 사이에 공유

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
* 객체 생성과 non-static 멤버의 생성
##### : non-static 멤버는 객체가 생성될 때, 객체마다 생긴다.

## 멤버의 접근 지정자
* 다음 코드의 두 클래스 Smaple과 AccessEx 클래스는 동일한 패키지에 저장된다. 컴파일 오류를 찾아 내고 이유를 설명하라.
* Sample 클래스의 a와 c는 각각 public, defualt 지정자로 선언이 되었으므로, 같은 패키지에 속한 AccessEx 클래스에서 접근 가능
* b는 private으로 선언이 되었으므로 AccessEx 클래스에서 접근 불가능

## 멤버 접근 지정
* public 멤버 : 패키지에 관계 없이 모든 클래스에게 접근 허용
* pricate 멤버 : 동일 클래스 내에서만 접근 허용. 상속 받은 서브 클래스에서 접근 불가.
* private 멤버 :
* 같은 패키지 내의 다른 모든 클래스에게 접근 허용
* 상속 받은 서브 클래스는 다른 패키지에 

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

## 접근 지정자
* 자바의 접근 지정자 4가지 : private, protected, public, 디폴트(접근지정자 생략)
* 접근 지정자의 목적
* 클래스나 일부 멤버를 공개하여 다른 클래스에서 접근하도록 허용
* 객체 지향 언어의 캡슐화 정책은 멤버를 보호하는 것 
* ㄴ> 접근 지정은 캡슐화에 묶인 보호를 일부 해제할 목적으로 사용
* 접근 지정자에 따른 클래스나 멤버의 공개 범위

## 자바 패키지 개념
* 패키지 
* 상호 관련 있는 클래스 파일(컴파일된 .class)을 지정하여 관리하는 디렉터리
* 자바 응용프로그램은 하나 이상의 패키지로 구성

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
### ○ 객체 배열 선언과 생성 과정
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