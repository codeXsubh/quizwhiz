import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener{
    JButton rules, back;
    JTextField tfname;

    Login() {
        getContentPane().setBackground(new Color( 255,  253,  208));
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/login.png"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 600, 500);
        add(image);






        JLabel heading = new JLabel("QuizWhiz");
        heading.setBounds(800, 50, 300, 60);
        heading.setFont(new Font("Verdana", Font.BOLD, 50));
        heading.setForeground(new Color(18, 16, 16));
        add(heading);






        JLabel name = new JLabel("Enter First Name");
        name.setBounds(840, 150, 300, 65);
        name.setFont(new Font("Verdana", Font.BOLD, 20));
        name.setForeground(new Color(16, 20, 25));
        add(name);




        tfname = new JTextField();
        tfname.setBounds(785,200,300,25);
        tfname.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(tfname);



        rules = new JButton("NEXT");
        rules.setBounds(940, 270, 130, 30);
        rules.setBackground(new Color(85, 67, 101));
        rules.setForeground(Color.black);
        rules.setFont(new Font("Verdana", Font.BOLD, 15));
        rules.addActionListener(this);
        add(rules);



        back =  new JButton("BACK");
        back.setBounds(800, 270, 130, 30);
        back.setBackground(new Color(85, 67, 101));
        back.setForeground(Color.black);
        back.setFont(new Font("Verdana", Font.BOLD, 15));
        back.addActionListener(this);
        add(back);





        setSize(1200, 500);
        setLocation(150, 250);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == rules) {
            String name = tfname.getText();
            setVisible(false);
            new Rules(name);
        } else if (e.getSource() == back){
            setVisible(false);
        }

    }

    public static void main(String[] args) {
        new Login();
    }
}
