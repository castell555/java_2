import javax.swing.*;
import java.awt.*;

public class NullContainerEx extends JFrame {
    NullContainerEx(){
        setTitle("배치관리자 없이 절대 위치에 배치하는 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();

        contentPane.setLayout(null);

        JLabel la = new JLabel("Hello, press Buttons!");
        la.setLocation(130, 50);
        la.setSize(200, 20);
    }
}
