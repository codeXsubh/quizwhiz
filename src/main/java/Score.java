import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Score extends JFrame implements ActionListener {

    JLabel qno;

    public Score(String name, int score) {
        setBounds(350, 150, 850, 660);
        getContentPane().setBackground((new Color(251, 251, 247)));
        setLayout(null);

        // Ensure your image path is correct
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/score.png"));
        Image i2 = i1.getImage().getScaledInstance(400, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 100, 850, 290);
        add(image);

        qno = new JLabel("Hey! Your Final Score Is: " + score + "! ");
        qno.setBounds(260, 150, 770, 660);
        qno.setFont(new Font("Times New Roman", Font.BOLD, 30));
        qno.setForeground(new Color(19, 119, 140));
        add(qno);

        JButton submit = new JButton("Play Again");
        submit.setBounds(350, 550, 150, 35);
        submit.setBackground(new Color(61, 30, 64));
        submit.setForeground(Color.white);
        submit.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        submit.setFocusPainted(false);
        submit.setContentAreaFilled(true);
        submit.setOpaque(true);
        submit.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        add(submit);


        submit.addActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("Play Again button clicked!");

        new Login();

        setVisible(false);
    }

    public static void main(String[] args) {

        new Score("TestUser", 10);
    }
}