import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;

public class Questions extends JFrame {

    private List<QuestionModel> questionList;
    private int[] userAnswers;
    private int count = 0;
    private int score = 0;

    private int timer = 15;
    private Timer t;

    private int lifelineUsedCount = 0;
    private final int maxLifelineUses = 4;

    JLabel questionText, timerLabel;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup groupoptions;
    JButton lifeline;

    String name;

    Questions(String username) {

        this.name = username;

        questionList = ApiQuestionLoader.loadFromApi();
        Collections.shuffle(questionList);

        userAnswers = new int[questionList.size()];
        for (int i = 0; i < userAnswers.length; i++) {
            userAnswers[i] = -1;
        }

        setTitle("QuizWhiz - Questions");
        setBounds(110, 0, 1240, 900);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/Quiz.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1240, 390);
        add(image);

        questionText = new JLabel();
        questionText.setVerticalAlignment(SwingConstants.TOP);
        questionText.setFont(new Font("Verdana", Font.BOLD, 17));
        questionText.setForeground(new Color(102, 0, 204));
        add(questionText);

        timerLabel = new JLabel("Time Left: 15");
        timerLabel.setBounds(1050, 450, 200, 30);
        timerLabel.setFont(new Font("Verdana", Font.BOLD, 17));
        timerLabel.setForeground(Color.RED);
        add(timerLabel);

        opt1 = new JRadioButton();
        opt1.setFont(new Font("Times New Roman", Font.BOLD, 16));
        add(opt1);

        opt2 = new JRadioButton();
        opt2.setFont(new Font("Times New Roman", Font.BOLD, 16));
        add(opt2);

        opt3 = new JRadioButton();
        opt3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        add(opt3);

        opt4 = new JRadioButton();
        opt4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        add(opt4);

        groupoptions = new ButtonGroup();
        groupoptions.add(opt1);
        groupoptions.add(opt2);
        groupoptions.add(opt3);
        groupoptions.add(opt4);

        opt1.setActionCommand("0");
        opt2.setActionCommand("1");
        opt3.setActionCommand("2");
        opt4.setActionCommand("3");

        lifeline = new JButton("50-50");
        lifeline.setBounds(650, 490, 150, 30);
        lifeline.addActionListener(e -> useLifeline());
        add(lifeline);

        JButton next = new JButton("Next");
        next.setBounds(650, 540, 150, 30);
        next.addActionListener(e -> nextQuestion());
        add(next);

        JButton submit = new JButton("Submit");
        submit.setBounds(650, 590, 150, 30);
        submit.addActionListener(e -> submitQuiz());
        add(submit);

        t = new Timer(1000, e -> handleTimer());

        startQuestion();
        setVisible(true);
    }

    private void startQuestion() {

        QuestionModel q = questionList.get(count);

        questionText.setText("<html><body style='width:850px'><b>"
                + (count + 1) + ".</b> "
                + q.getQuestion()
                + "</body></html>");

        questionText.setSize(850, Short.MAX_VALUE);
        Dimension d = questionText.getPreferredSize();
        questionText.setBounds(150, 420, 850, d.height);

        int optionStartY = 450 + d.height + 20;

        String[] options = q.getOptions();

        opt1.setText(options[0]);
        opt2.setText(options[1]);
        opt3.setText(options[2]);
        opt4.setText(options[3]);

        opt1.setBounds(170, optionStartY, 550, 30);
        opt2.setBounds(170, optionStartY + 40, 550, 30);
        opt3.setBounds(170, optionStartY + 80, 550, 30);
        opt4.setBounds(170, optionStartY + 120, 550, 30);

        groupoptions.clearSelection();

        opt1.setEnabled(true);
        opt2.setEnabled(true);
        opt3.setEnabled(true);
        opt4.setEnabled(true);

        lifeline.setEnabled(lifelineUsedCount < maxLifelineUses);

        timer = 15;
        timerLabel.setText("Time Left: " + timer);

        t.stop();
        t.start();
    }

    private void handleTimer() {
        timer--;
        timerLabel.setText("Time Left: " + timer);

        if (timer <= 0) {
            saveAnswer();
            nextAuto();
        }
    }

    private void saveAnswer() {
        if (groupoptions.getSelection() != null) {
            userAnswers[count] =
                    Integer.parseInt(groupoptions.getSelection().getActionCommand());
        }
    }

    private void nextQuestion() {
        saveAnswer();
        if (count < questionList.size() - 1) {
            count++;
            startQuestion();
        }
    }

    private void nextAuto() {
        if (count < questionList.size() - 1) {
            count++;
            startQuestion();
        } else {
            t.stop();
            calculateScore();
            new Score(name, score);
            setVisible(false);
        }
    }

    private void submitQuiz() {
        t.stop();
        saveAnswer();
        calculateScore();
        new Score(name, score);
        setVisible(false);
    }

    private void calculateScore() {
        score = 0;
        for (int i = 0; i < questionList.size(); i++) {
            if (userAnswers[i] == questionList.get(i).getAnswer()) {
                score++;
            }
        }
    }

    private void useLifeline() {
        if (lifelineUsedCount >= maxLifelineUses) {
            lifeline.setEnabled(false);
            return;
        }

        lifelineUsedCount++;
        lifeline.setEnabled(false);

        int correct = questionList.get(count).getAnswer();
        JRadioButton[] options = {opt1, opt2, opt3, opt4};

        int disabled = 0;
        for (int i = 0; i < options.length; i++) {
            if (i != correct && disabled < 2) {
                options[i].setEnabled(false);
                disabled++;
            }
        }
    }

    public static void main(String[] args) {
        new Questions("TestUser");
    }
}