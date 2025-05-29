# ● JAVA 수업---------------(202230318 윤준서)
---
## 5월 29일(13주차)
* 컨테이너
    * 다른 컴포넌트를 포할할 수 있는 GUI 컴포넌트 : java.awt.Container를 상속받음
    * 다른 컨테이너에 포함될 수 있음
    * AWM 컨테이너 : Panel, Frame, Applet, Dialog, Window
    * Swing 컨테이너 : JPanel, JFrame, JApplet, JDialog, JWindow

* 컴포넌트
    * 컨테이너에 포함되어야 화면에 출력될 수 있는 GUI 객체
    * 다른 컴포넌트를 포함할 수 없는 순수 컴포넌트
    * 모든 GUI 컴포넌트가 상속받는 클래스 : java.awt.Component
    * 스윙 컴포넌트가 상속받는 클래스 : javax.swing.Jcomponent

* 최상위 컨테이너
    * 다른 컨테이너에 포함되지 않고도 화면에 출력되며, 독립적으로 존재 가능한 컨테이너
    * 스스로 화면에 자신을 출력하는 컨테이너 : JFrame, JDialog, JApplet

```java
import javax.swing.JFrame;

public class Ex81MyFrame extends JFrame{
    public Ex81MyFrame(){
        setTitel("300x300 스윙 프레임 만들기");
        setSize(300, 300);
        setVisible(true);
    }
    public static void main(String[] args){
        Ex81MyFrame frame = new Ex81MyFrame();
    }
}
```
### Swing 응용프로그램에서 main()의 기능과 위치
* 스윙 응용프로그램에서 main()의 기능 최소화 바람직
    * 스윙 응용프로그램이 실행되는 시작점으로서의 기능만
    * 스윙 프레임을 생성하는 정도의 코드로 최소화
    ```java
    public static void main(String[] args){
        MyFrame frame = new MyFrame();
    }
    ```
* Frame 객체를 생성하고 사용하지 않기 때문에 worrying이 발생합니다.

### 프레임에 컴포넌트 붙이기
* 타이틀 달기
    * super()나 setTitle() 이용

* 컨텐트팬에 컴포넌트 달기
    * 컨텐트팬이란? 스윙 컴포넌트들이 부착되는 공간
    * 컨텐트팬 알아내기 : 스위 프레임에 붙은 디폴트 컨탠트팬 알아내기
    * 컨텐트팬에 컴포넌트 붙이기
    * 컨텐트팬 변경

### 컨텐트팬에 대한 JDK 1.5 이후의 추가 사항
* 1.5 이전
    * 프레임의 컨텐트팬을 알아내어, 반드시 컨텐트팬에 컴포넌트 부착

* JDK 1.5 이후 추가된 사항
    * 프레임에 컴포넌트를 부착하면 프레임이 대신 컨텐트팬에 부착

* 저자의 결론
    * JDK 1.5이전처럼 직접 컨텐트팬에 컴포넌트를 부착하는 것이 바람직함
    * 컨텐트팬 다루기 능력 필요하기 때문
    * 컴포넌트의 부모가 프레임이 아닌, 컨텐트팬임을 알고 명확히 사용할 필요

* 1.5이후 추가된 기능을 사용하는 것이 가독성이 좋으며, Content Pane을 다루는 능력이 반드시 필요한 것은 아닙니다.

```java
// 3개의 버튼 컴포넌트를 가진 스윙 프레임 만들기
// 예제 8-2
import javax.swing.*;
import java.awt.*;

public class ContentPaneEx extends JFrame{
    ContentPaneEx(){
        setTitle("ContentPane과 JFrame 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setBackground(Color.ORANGE);
        contentPane.setLayout(new FlowLayout());

        contentPane.add(new JButton("OK"));
        contentPane.add(new JButton("Cancel"));
        contentPane.add(new JButton("Ignore"));

        setSize(300, 150);
        setVisible(true);
    }
    public static void main(String[] args){
        new ContentPaneEx();
    }
}
```
### Swing 응용 프로그램의 종료
* 응용프로그램 내에서 스스로 종료하는 방법
    * 언제 어디서나 무조건 종료 System.exit(0);

* 프레임의 오른쪽 상단의 종료버튼(X)이 클릭되면 어떤 일이 일어나는가?
    * 프레임 종료, 프레임 윈도우를 닫음 : 프레임이 화면에서 보이지 않게 됨

* 프레임이 보이지 않게 되지만 응용프로그램이 종료한 것 아님
    * 키보드나 마우스 입력을 받지 못함
    * 다시 setVisible(true)를 호출하면, 보이게 되고 이전 처럼 작동함

* 프레임 종료버튼이 클릭될 때, 프레임과 함께 프로그램을 종료 시키는 방법
    * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

### 컨테이너와 배치, 배치관리자 개념
* 컨테이너의 배치관리자
    * 컨테이너마다 하나의 배치관리자 존재
    * 컨테이너 마다

### 배치 관리자 대표 유형 4가지
* FlowLayout 배치관리자
    * 컴포넌트가 삽입되는 순서대로 왼쪽에서 오른쪽으로 배치
    * 배치할 공간이 없으면 아래로 내려와서 반복한다

* BorderLayout 배치관리자
    * 컨테이너의 공간을 동,서,남,북,중앙의 5개 영역으로 나눔
    * 5개 영역 중 응용프로그램에서 지정한 영역에 컴포넌트 배치

* GridLayout 배치관리자
    * 격자형태로 맞춰서 배치

* CardLayout 배치관리자
    * 일렬로 배치

### 컨테이너와 디폴트 배치관리자
* 컨테이너의 디폴트 배치관리자 : 컨테이너 생성시 자동으로 생성되는 배치관리자

### 컨테이너에 새로운 배치관리자 설정
* setLayout 메소드 호출 : lm을 새로운 배치관리자로 설정

[사례]
* JPanel 컨테이너에 BorderLayout 배치관리자를 설정하는 예
    ```java
    JPanel p = new JPanel();
    p.setLayout(new BorderLayout());
    ```

* 컨텐트팬의 배치관리자를 FlowLayout 배치관리자로 설정
    ```java
    Container c = frame.getConentPane();
    c.setLayout(new FlowLayout());
    ```

* 오류
    ```java
    c.setLayout(FlewLayout);
    ```

```java
// FlowLayout 배치관리자 활용
// 예제 8-3
import javax.swing.*;
import java.awt.*;

public class FlowLayoutEx extends JFrame{
    FlowLayoutEx(){
        setTitle("FlowLayout 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();

        contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 40));

        contentPane.add(new JButton("add"));
        contentPane.add(new JButton("sub"));
        contentPane.add(new JButton("mul"));
        contentPane.add(new JButton("div"));
        contentPane.add(new JButton("Calculate"));

        setSize(300, 200);
        setVisible(true);
    }
    public static void main(String[] args) {
        new FlowLayoutEx();
    }
}
```

### BorderLayout 배치관리자
* 배치방법
    * 컨테이너 공간을 5 구역으로 분활, 배치 : 동, 서, 남, 북, 중앙

### BorderLayout 생성자와 add() 메소드
* 생산자
    * BorderLayout()
    * BorderLayout(hGap, vGap)

### GridLayout 배치관리자
* 배치방법
    * GridLayout()
    * GridLayout(int raws, int cols)
    * GirdLayout(int rows, int cols, int hGap, int vGap)
    * rows : 격자의 행수
    * cals : 격자의 열수
    * hGap : 수평 간격, 픽셀 단위


### 배치관리자 없는 컨테이너
* 배치관리자가 없는 컨테이너가 필요한 경우
    - 응용프로그램에서 직접 컴포넌트의 크기와 위치를 결정하고자 하는 경우
    1. 컴포넌트의 크기나 위치를 개발자 임의로 결정하고자 하는 경우
    2. 게임 프로그램과 같이 시간이나 마우스/키보드의 입력에 따라 컴포넌트들의 위치와 크기가 수시로 변하는 경우
    3. 여러 컴포넌트들이 서로 겹쳐 출력하고자 하는 경우

* 컨테이너의 배치 관리자 제거 방법
    * container.setLayout(null);

* 컨테이너의 배치관리자가 없어지면, 컴포넌트에 대한 어떤 배치도 없음


### 컴포넌트의 절대 위치와 크기 설정
* 배치관리자에 없는 컨테이너에 컴포넌트를 삽입할 때
    * 프로그램에서 컴포넌트의 절대 크기와 위치 설정
    * 컴포넌트들이 서로 겹치게 할 수 있음

```java
// 배치관리자 없는 컨테이너에 컴포넌트를 절대 위치와 절대 크기로 지정
// 예제 8-6

```


---





# 9장
### 이벤트 기반 프로그래밍
* 이벤트 기반 프로그래밍
    * 이벤트의 발생에 의해 프로그램 흐름이 결정되는 방식
        * 이벤트가 발생하면 이벤트를 처맇는 루틴 실행
        * 실행될 코드는 이벤트의 발생에 의해 전적으로 결정

    * 반대되는 개념 : 배치 실행
        - 프로그램의 개발자가 프로그램의 흐름을 결정하는 방식

    * 이벤트 종류
        * 사용자의 입력 : 마우스 드래그, 마우스 클릭...
        * 센서로부터의 입력, 네트워크로부터 데이터 송수신
        * 다른 응용프로그램이나 다른 스레드로부터의 메세지

* 이벤트 기반 응용 프로그램의 구조
    * 각 이벤트마다 처리하느 리스너 코드 보유

* GUI 응용프로그램은 이벤트 기반 프로그래밍으로 작성됨
    * GUI 라이브러리 종류 : C++의 MFC, C# GUI, Visual Basic, X window, Android 등
    * 자바의 AWT와 Swing

---

### 이벤트 처리 과정
1. 이벤트 발생
2. 이벤트 객체 생성
3. 응용프로그램에 작성된 이벤트 리스너 찾기
4. 이벤트 리스너 실행

---

### 이벤트 객체
* 이벤트 객체
    * 발생한 이벤트에 관한 정보를 가진 객체
    * 이벤트 리스너에 전달됨
        * : 이벤트 리스너 코드가 발생한 이벤트에 대한 상황을 파악할 수 있게 함

* 이벤트 객체가 포함하는 정보
    * 이벤트 종류와 이벤트 소스
    * 이벤트가 발생한 화면 좌표 및 컴포넌트 내 좌표
    * 이벤트가 밸생한 버튼이나 메뉴 아이템의 문자열
    * 클릭된 마우스 버튼 번호 및 마우스의 클릭 횟수
    * 키의 코드 값과 문자 값
    * 체크박스, 라디오버튼 등과 같은 컴포넌트에 이벤트가 발생하였다면 체크 상태

* 이벤트 소스를 알아 내는 메소드 : Object getSource()
    * 발생한 이벤트의 소스 컴포넌트 리턴
    * Object 타입으로 리턴하므로 캐스팅하여 사용
    * 모든 이벤트 객체에 대해 적용
---

### 리스너 인터페이스
* 리스너 인터페이스 : 이벤트를 처리하는 자바 프로그램 코드, 클래스로 작성
* 자바는 다양한 리스너 인테페이스 제공
* 사용자의 이벤트 리스너 작성
    * 자바의 리스너 인터페이스를 상속받아 구현

---


### 이벤트 리스너 작성 과제 사례
1. 이벤트와 이벤트 리스너 선택
    * 버튼 클릭을 처리하고자 하는 경우
    * 이벤트 : Action 이벤트, 이벤트 리스너 : ActionListener

2. 이벤트 리스너 클래스 작성 : ActionListner 인터페이스 구현
    ```java
    class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){ // 버튼이 클릭될 때 호출되는 메소드
            JButton b = (JButton)e.getSource(); // 사용자가 클릭한 버튼 알아내기
            if(b.getText().equals("Action")) // 버튼의 현재 문자열이 "Action"인지 비교
                b.setText("액션"); // JButton의 setText()을 호출하여 문자열 변경
            else
                b.set.Text("Action"); // JButton의 setText()를 호출하여 문자열 변경
        }
    }
    ```

3. 이벤트 리스너 등록
    * 이벤트를 받아 처리하고자 하는 컴포넌트에 이벤트 리스너 등록
    * component.addxxxListener(listener);
---

### 이벤트 리스너 작성 방법
* 독립 클래스로 작성
    * 이벤트 리스너를 완전한 클래스로 작성
    * 이벤트 리스너를 여러 곳에서 사용할 때 적합

* 내부 클래스로 작성
    * 클래스 안에 멤버처럼 클래스 작성
    * 이벤트 리스너를 특정 클래스에서만 사용할 때 적합

* 익명 클래스(anonymous class)로 작성
    * 클래스의 이름 없이 간단히 리스너 작성
    * 클래스 조차 만들 필요 없이 리스너 코드가 간단한 경우에 적합




## 5월 22일(12주차)
### StringBuffer 클래스
* 가변 스트링을 다루는 클래스
* StringBuffer 객체 생성
* String 클래스와 달리 문자열 변경 가능
    * 가변크기의 버퍼를 가지고 있어 문자열 수정 가능
    * 문자열의 수정이 많은 작업에 적합

* 스트링 조작 사례
```java
StringBuffer sb = new StringBuffer("This");

sb.append(" is pencil.");   // sb = "This is pencil."
sb.insert(7, " my");        // sb = "This is my pencil."
sb.replace(8, 10, "your");  // sb = "This is your pencil."
system.out.println(sb);     // "This is your pencil." 출력
```

### StringTokenizer 클래스
* 구분 문자를 기준으로 문자열을 분리하는 클래스
    * 구분 문자(delimiter) : 문자열을 구분할 때 사용되는 문자
    * 토큰(token) : 구분 문자로 분리된 문자열
    * 카운트 토큰(counttoken) : 토큰의 개수 리턴

* 예) ㄱ
    ```java
    String query = "name=kitae&addr=seoul&age=21";
    StringTokenizer st = new StringTokenizer(query, "&");
    ```
```java
//예제 6-7
import java.util.StringTokenizer;
public class StringTokenizerEx{
    public static void main(String[] args){
        String query = "name=kitae&addr=seoul&age=21";
        StringTokenizer st = new StringTokenizer(query, "&");

        int n = st.countTokens();                 //분리된 토큰 개수
        System.out.println("토큰 개수 = " + n); //
        while(st.hasMoreTokens()){
            String token = st.nextToken();
            System.out.println(token);
        }
    }
}
```
### Math 클래스
* 기본 산술 연산 메소드를 제공하는 클래스
* 모든 메소드는 static으로 선언
    * 클래스 이름으로 호출 가능

* Math.random() 메소드로 난수 발행
    * random()은 0보다 크거나 같고 1.0보다 작은 실수 난수 발생
    * 1에서 100까지의 랜덤 정수 10개를 발생시키는 코드 사례
    ```java
    for(int x=0; x<10; x++){
        int n = (int)(Math.random()*100 + 1); // 1~100의 랜덤 정수 발생
        System.out.println(n);
    }
    ```
* java.util.Random 클래스를 이용하여 난수 발생 가능
    ```java
    Random r = new Random();
    int n = r.nextInt();        // 음수, 양수, 0을 포함하여 자ㅏㅂ의 정수 범위의 난수 발생
    int m = r.nextInt(100);     // 0에서 99사이(0과 99포함)의 정수 난수 발생
    ```
```java
//예제 6-8
public class MathEx{
    public static void main(String[] args){
        System.out.println(Math.abs(-3.14)); // 절댓값 구하가ㅣ
        System.out.println(Math.sqrt(9.0));  // 제곱근
        System.out.println(Math.exp(2));     // e의 2승
        System.out.println(Math.round(3.14));// 반올림

        //[1, 45] 사이의 정수형 난수 5개 발생
        System.out.println("이번 주 행운의 번호는 ");
        for(int i=0; i<5; i++)
            System.out.print((int)(Math.random()*45 + 1) + " "); //난수 발생
    }
}
```
---
# 7장
## 컬랙션
### 컬랙션의 개념
* 요소(elment)라고 불리는 가변 개수의 객체들의 저장소
    * 객체들의 컨테이너라고도 불림
    * 요소의 개수에 따라 크기 자동 조절
    * 요소의 삽입, 삭제에 따른 요소의 위치 자동 이동

* 고정 크기의 배열을 다루는 어려움 해소
* 다양한 객체들의 삽입, 삭제 검색 등의 관리 용이

### 컬랙션 특징
1. 컬랙션은 제네릭(generics) 기법으로 구현
* 제너릭
    * 특정 타입만 다루지 않고, 여러 종류의 타입으로 변신할 수 있도록 클래스나 메소드를 일반화 시키는 기법
    * 클래스나 인터페이스 이름에 <E>, <K>, <V> 등 타입 매개변수 포함
* 제네릭 컬랙션 사례 : 백터 Vector<E>
    - <E>에서 E에 구체적인 타입을 주어 구체적인 타입만 다루는 백터로 활용
    - 점수만 다루는 컬랙션 백터 Vector(Integer)
    - 문자열만 다루는 컬렉션 백터 Vector<String>

2. 컬랙션의 요소는 객체만 가능
* int, char, double 등의 기본 타입으로 구체화 불가
* 컬렉션 사례
    ```java
    Vector<int> v = new Vector<int>();          // 컴파일 오류, int 사용 불가
    Vector<Integer> v = new Vector<Integer>();  // 정상 코드
    ```

### 제네릭의 기본 개념
* 제네릭
    * JDK 1.5부터 도입
    * 모든 종류의 데이터 타입을 다룰 수 있도록 일반화된 타입 매개 변수로 클래스(인터페이스)나 메소드를 작성하는 기법
    * C++의 탬플릿(Template)과 동일




### Vector<E>의 특성
* <E>에서 사용할 요소의 특징 타입으로 구체화
* 배열을 가변 크기로 다룰 수 있게 하는 컨테이너
    * 배열의 길이 제한 극복
    * 요소의 개수가 넘치면 자동으로 길이 조절

* 요소 객체들을 삽입, 삭제, 검색하는 컨테이너
    * 삽입, 삭제에 따라 자동으로 요소의 위치 조정

* Vector에 삽입 가능한 것
    * 객체, null
    * 기본 타입의 값은 Wrapper 객체로 만들어 저장

* Vector에 객체 삽입
    * 백터의 맨 뒤, 중간에 객체 사입 가능

* Vector에서 객체 삭제
    * 임의의 위치에 있는 객체 삭제 가능




### Vector<Integer> 백터 컬렉션 내부
* add를 사용해 요소를 삽입하고 get()을 이용하여 요소를 검색
```java
Vector<Integer> v = new Vector<Integer>(7); // 초기 용량이 7인 벡터 생성
```



### 컬렉션 생성문의 진화 : Java 7, Java 10
* Java 7 이전
    ```java
    Vector<Integer> v = new Vector<Integer>();
    ```
* java 7 이후
    * 컴파일러의 타입 추론 기능 추가
    * <>(다이아몬드 연산자)에 타입 매개변수 생략
    ```java
    Vector<Integer> v = new Vector<>();
    ```
* Java 10 이후
    * var 키워드 도입, 컴파일러의 지역 변수 타입 추론 가능
    ```java
    var v = new Vector<Integer>();
    ```


### point 클래스만 다루는 Vector<Point> 컬렉션 활용
```java
// 예제 7-2
import java.util.Vector;

class Point{
    private int x, y;
    public Point(int x, int y){
        public 
    }
}
```

### ArrayList<E>
* 가변 크기 배열을 구현한 클래스
    * <E>에 요소로 사용할 특정 타입으로 구체화

* 백터와 거의 동일
    * 요소 삽입, 갖게, 검색 등 벡터 기능과 거의 동일함
    * 벡터와 달리 스레드 동기화 기능 없음
    * 다수 스레드가 동시에 ArrayList에 잡근할 때 동기화되지 않음
    * 개발자가 스레드 동기화 코드 작성


### ArrayList와 Vector의 차이
* ArrayList와 Vector는 모두 동적으로 크기가 늘어나는 배열 기반의 리스트 클래스입니다.
* ArrayList와 Vector 비교 요약
동기화 여부 : 비도익화 | 동기화
성능 : 빠름 | 느림
기본 크기 증가 : 1.5배씩 증가 | 2배씩 증가


* 요즘은 ArrayList가 기본 선택지입니다.
* Vector는 이제 거의 사용하지 않고, 멀티 스레드가 필요한면 다른 방법을 씁니다



### 컬렉션의 순차 검색을 위한 Iterator
* Interator<E> 인터페이스
    * 리스트 구조의 컬렉션에서 요소의 순차 검색을 위한 인터페이스
    * Vector<E>, ArrayList<E>, LinkedList<E>가 상속받는 인터페이스

* Iterator 객체 얻어내기
    * 컬렉션의 iterator() 메소드 호출 : 해당 컬렉션을 순차 검색할 수 있는 iterator 객체 리턴
    ```java
    Vector<Integer> v = new Vector<Integer>();
    Iterator<Integer> it = v.iterator();
    ```

    * 컬렉션 검색 코드
    ```java
    while(it.hasNext()){
        int n = it.next();
        ....
    }
    ```


```java
//예제 7-4

```


### HashMap<K, V>
* 키와 값의 쌍으로 구성되는 요소를 다루는 컬렉션
    * K : 키로 사용할 요소의 타입
    * V : 값으로 사용할 요소의 타입
    * 키와 값이 한 쌍으로 삽입
    * 값을 검색하기 위해서는 반드시 키 이용

* 삽입 및 검색어 빠른 특징
    * 요소 삽입 : put() 메소드
    * 요소 검색 : get() 메소드


```java
//예제 7-5
import java.util.*;
public class HashMappDicEx{
    public static void main(String[] args){
        HashMap<String, String> dic = new HashMap<String, String>();

        dic.put("baby", "아기");
        dic.put("love", "사랑");
        dic.put("apple", "사과");

        Set<String> keys = dic.keySet();
        Iterator<String> it = keys.iterator();
        while(it.hasNext()){
            String key = it.next();
            String value = dic.get(key);
            System.out.print("(" + key + "," + value + ")");
        }
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        for(int i=0; i<3; i++){
            System.out.print("찾고 싶은 단어는 ?");
            String eng = scanner.next();
            String kor = dic.get(eng);
            if(kor == null) System.out.println(eng + "는 없는 단어입니다.");
            else System.out.println(kor);
        }
    }
}

```



### 제네릭 만들기
* 제네릭 클래스 작성 : 클래스 이름 옆에 일반화된 타입 매개 변수 추가
```java
public class MyClass(T){
    T val:
    void set(T a){
        val = a;
    }
    T get(){
        return val;
    }
}
```

# 8장
### 자바의 GUI
* GUI : 사용자가 편리하게 입출력 할 수 있도록 그래픽으로 화면을 구성하고, 마우스나 키보드로 입력 받을 수 있도록 지원하는 사용자 인터페이스
* 자바 언어에서 GUI 응용프로그램 작성 : AWT와 Swing 패키지에 강력한 GUI 컴포넌트 제공.

[AWT(Abstract windowing Toolkit) 패키지]
* 자바가 처음 나왔을 때부터 배포된 GUI 패키지, 최근에는 거의 사용하지 않음
* AWT 컴포넌트는 중량 컴포넌트(heavy weight component)
* AWT 컴포넌트의 그리기는 운영체제에 의해 이루어지며, 운영체제에 자원을 많이 소모하고 부담을 줌
* 운영체제가 직접 그리기 때문에 속도는 빠름



### 자바의 GUI Swing 패키지
* AWT 기술을 기반으로 작성된 자바 라이브러리
* 모든 AWT 기능 + 추가된 풍부하고 화려한 고급 컴포넌트
* AWT컴포넌트를 모두 스윙으로 재작성.
* AWT 컴포넌트 이름 앞에 J자를 덧붙임
* 순수 자바 언어로 구현
* 스윙 컴포넌트는 경량 컴포넌트
* 스윙 컴포넌트는 운영체제의 도움을 받지 않고, 직접 그리기 때문에 운영체제에 부담을 주지 안음
* 현재 자바의 GUI 표준으로 사용됨





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