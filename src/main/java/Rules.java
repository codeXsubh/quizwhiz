import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rules extends JFrame implements ActionListener{
    String name;
    JButton start, back;


    Rules(String name) {
        this.name = name;
        getContentPane().setBackground(	new Color(255,  253,  208));
        setLayout(null);


        JLabel heading = new JLabel("Welcome, " + name + " !" );
        heading.setBounds(320, 50, 500, 100);
        heading.setFont(new Font("Serif", Font.BOLD, 26));
        heading.setForeground(new Color(204, 6, 92));
        add(heading);


        JLabel rules = new JLabel();
        rules.setBounds(20, 120, 750, 350);
        rules.setFont(new Font("Verdana", Font.BOLD, 18));
        rules.setForeground(new Color(0, 0, 0));
        rules.setText(
                "<html>" +
                        "1. Read Each Question Carefully And Answer As Accurately As Possible.<br><br>" +
                        "2. You Can Answer Only Once; After Clicking Next, You Cannot Go Back. <br><br>" +
                        "3. Only Four Lifelines Are Available — Use Them Wisely.<br><br>" +
                        "4. Think Critically — Every Answer Impacts Your Final Score.<br><br>" +
                        "5. Time Is Limited; Manage It Wisely.<br><br>" +
                        "6. Integrity And Honesty Are Expected At All Times." +
                        "</html>"

        );
        add(rules);

        JLabel rule2 = new JLabel();
        rule2.setBounds(110, 420, 750, 170);
        rule2.setFont(new Font(" serif", Font.BOLD, 22));
        rule2.setForeground(new Color(94, 12, 158));
        rule2.setText(
                "<html>" +
                        "!! Good Luck, May Your First Guess Always Be Right !!"+"<br><br>" +
                        "</html>"
        );
        add(rule2);





        back =  new JButton("BACK");
        back.setBounds(310, 520, 100, 30);
        back.setBackground(new Color(85, 67, 101));
        back.setForeground(Color.black);
        back.setFont(new Font("Verdana", Font.BOLD, 15));
        back.addActionListener(this);
        add(back);


        start = new JButton("START");
        start.setBounds(410, 522, 100, 25);
        start.setBackground(new Color(85, 67, 101));
        start.setForeground(Color.black);
        start.setFont(new Font("Verdana", Font.BOLD, 15));
        start.addActionListener(this);
        add(start);



        setSize(800,650);
        setLocation( 300,150);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start){
            setVisible(false);
            new Questions(name);

        } else {
            setVisible(false);
            new Login();

        }
    }

    public static void main(String[] args) {
        new Rules("User");

    }}


