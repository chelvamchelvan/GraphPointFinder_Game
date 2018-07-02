import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GraphPointFinder {

    public static JTextField xbox;
    public static JTextField ybox;
    public static JLabel xCorrectAns;
    public static JLabel yCorrectAns;
    public static boolean nextButStatus = false;
    public static int correctAnsCount = 0;
    public static int questCount=20;
    public static JLabel finished_count_label;
    public static JLabel ques_count_label;
    public  static boolean play_status = false;
    public static boolean play_but_status = true;
    public static JLabel time_count_label;
    public static boolean restart_status = false;


    public static void main(String args[]) {

        MakeGraphics mc = new MakeGraphics();

        JFrame f = new JFrame();
        f.setSize(1200, 800);
        f.setTitle("Graph Point Finder");
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(mc);
        f.setResizable(false);

        Font font = new Font("Consolas",Font.BOLD,30);

        JLabel xlabel = new JLabel("X");
        xlabel.setFont(font);
        xlabel.setForeground(Color.BLUE);
        xlabel.setBounds(900,20,50,40);
        f.add(xlabel);

        xbox = new JTextField();
        xbox.setBounds(950,20,50,40);
        f.add(xbox);

        xCorrectAns = new JLabel("");
        xCorrectAns.setFont(font);
        xCorrectAns.setForeground(Color.BLUE);
        xCorrectAns.setBounds(1050,20,50,40);
        f.add(xCorrectAns);

        JLabel ylabel = new JLabel("Y");
        ylabel.setFont(font);
        ylabel.setForeground(Color.BLUE);
        ylabel.setBounds(900,100,50,40);
        f.add(ylabel);

        ybox = new JTextField();
        ybox.setBounds(950,100,50,40);
        f.add(ybox);

        yCorrectAns= new JLabel("");
        yCorrectAns.setFont(font);
        yCorrectAns.setForeground(Color.BLUE);
        yCorrectAns.setBounds(1050,100,50,40);
        f.add(yCorrectAns);

        JButton submit = new JButton("Submit");
        submit.setForeground(Color.BLUE);
        submit.setBounds(900,200,100,50);
        submit.addActionListener(new submitAction());
        f.add(submit);

        JButton next_but = new JButton("Next");
        next_but.setForeground(Color.BLUE);
        next_but.setBounds(1020,200,100,50);
        next_but.addActionListener(new next_butAction());
        f.add(next_but);

        Font font1 = new Font("Consolas",Font.BOLD,15);

        JLabel ques_label = new JLabel("Questations:");
        ques_label.setFont(font1);
        ques_label.setBounds(900,300,100,50);
        f.add(ques_label);

        ques_count_label = new JLabel("20");
        ques_count_label.setFont(font1);
        ques_count_label.setForeground(Color.BLUE);
        ques_count_label.setBounds(1000,300,20,50);
        f.add(ques_count_label);

        JLabel finish_label = new JLabel("Correct:");
        finish_label.setFont(font1);
        finish_label.setBounds(900,350,100,50);
        f.add(finish_label);

        finished_count_label= new JLabel("0");
        finished_count_label.setFont(font1);
        finished_count_label.setForeground(Color.BLUE);
        finished_count_label.setBounds(1000,350,20,50);
        f.add(finished_count_label);

        JLabel time_label = new JLabel("Time:");
        time_label.setFont(font1);
        time_label.setBounds(900,400,100,50);
        f.add(time_label);

        time_count_label = new JLabel("02:30");
        time_count_label.setFont(font1);
        time_count_label.setForeground(Color.BLUE);
        time_count_label.setBounds(1000,400,50,50);
        f.add(time_count_label);

        JButton play_but = new JButton("Play");
        play_but.setBounds(900,500,100,50);
        play_but.setForeground(Color.BLUE);
        play_but.addActionListener(new playButAction());
        f.add(play_but);

        JButton restart_but = new JButton("Restart");
        restart_but.setBounds(900,600,100,50);
        restart_but.setForeground(Color.BLUE);
        restart_but.addActionListener(new restartButAction());
        f.add(restart_but);

        f.setVisible(true);

    }
}

class MakeGraphics extends JPanel{

    static boolean status = true;
    public static int xpoint,ypoint;

    MakeGraphics(){
        setSize(820,720);
        setForeground(Color.BLUE);
    }

    public void paint(Graphics g) {

        int[] x = {60,95,130,165,200,235,270,305,340,375,410,445,480,515,550,585,620,655,690,725,760};
        int[] y = {68,98,128,158,188,218,248,278,308,338,368,398,428,458,488,518,548,578,608,638,668};
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;

        BufferedImage img = null;
        try{
            File file = new File("../image/GraphSheet.JPG");
            img = ImageIO.read(new File(file.toURI()));
        }
        catch (IOException e) {
            System.out.println("Error is: "+e);
        }

        g.drawImage(img,20,20,800,700,null);

        Random rn = new Random();

        if(status==true) {
            xpoint = x[rn.nextInt(21)];
            ypoint = y[rn.nextInt(21)];
            status = false;
        }

        if(GraphPointFinder.play_status==true) {
            Ellipse2D.Double circle = new Ellipse2D.Double(xpoint, ypoint, 10, 10);
            g2d.fill(circle);
        }

        repaint();
    }
}


class submitAction extends  MakeGraphics implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {

        if(GraphPointFinder.play_status==true){
            if(GraphPointFinder.nextButStatus==false){
                int xAns = 0;
                int yAns = 0;

                try {
                    xAns = Integer.parseInt(GraphPointFinder.xbox.getText());
                    yAns = Integer.parseInt(GraphPointFinder.ybox.getText());
                }

                catch (Exception a){

                }

                int xPointReal = ((xpoint-60)/35)-10;
                int yPointReal = -(((ypoint-68)/30)-10);

                if(xPointReal==xAns){
                    GraphPointFinder.xbox.setBackground(Color.GREEN);
                }

                else{
                    GraphPointFinder.xbox.setBackground(Color.RED);
                    GraphPointFinder.xCorrectAns.setText(Integer.toString(xPointReal));
                }

                if(yPointReal==yAns){
                    GraphPointFinder.ybox.setBackground(Color.GREEN);
                }

                else{
                    GraphPointFinder.ybox.setBackground(Color.RED);
                    GraphPointFinder.yCorrectAns.setText(Integer.toString(yPointReal));
                }

                if(xPointReal==xAns && yPointReal==yAns){

                    GraphPointFinder.correctAnsCount = GraphPointFinder.correctAnsCount+1;
                }

                GraphPointFinder.finished_count_label.setText(Integer.toString(GraphPointFinder.correctAnsCount));
                GraphPointFinder.nextButStatus = true;
            }

            else{
                JOptionPane.showMessageDialog(null,"Please go to next question","Alert",JOptionPane.WARNING_MESSAGE);
            }
            GraphPointFinder.play_but_status=false;
        }

        else{
            JOptionPane.showMessageDialog(null,"please start play","warrning",JOptionPane.WARNING_MESSAGE);
        }

    }
}

class next_butAction extends GraphPointFinder implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {

        if(play_status==true){
            if(nextButStatus==true){
                xCorrectAns.setText("");
                yCorrectAns.setText("");

                xbox.setBackground(Color.WHITE);
                ybox.setBackground(Color.WHITE);

                xbox.setText("");
                ybox.setText("");

                questCount = questCount-1;
                ques_count_label.setText(Integer.toString(questCount));

                if(questCount==0){
                    int marks = GraphPointFinder.correctAnsCount*5;
                    JOptionPane.showMessageDialog(null,"Your Score is : "+Integer.toString(marks),"Game score",JOptionPane.INFORMATION_MESSAGE);
                    Restart rs = new Restart();
                    rs.restartFunction();
                }

                xbox.requestFocusInWindow();
                MakeGraphics mc = new MakeGraphics();
                mc.status = true;
                nextButStatus = false;
            }

            else{
                JOptionPane.showMessageDialog(null,"Please submit answer","Alert",JOptionPane.WARNING_MESSAGE);
            }
        }

        else{
            JOptionPane.showMessageDialog(null,"please start play","Warning",JOptionPane.WARNING_MESSAGE);
        }

    }
}

class playButAction implements ActionListener{

    static String final_time;

    @Override
    public void actionPerformed(ActionEvent e) {
        Timer timer = new Timer();

        if(GraphPointFinder.play_but_status==true) {
            GraphPointFinder.play_status = true;
            GraphPointFinder.play_but_status = false;
            GraphPointFinder.xbox.requestFocusInWindow();

            final int MAX_SECONDS = 0;

            TimerTask tt = new TimerTask() {
                public int seconds = 150;
                @Override
                public void run() {
                    if (seconds >= MAX_SECONDS) {
                        final_time = "0"+Integer.toString(seconds/60)+":"+Integer.toString(seconds%60);
                        GraphPointFinder.time_count_label.setText(final_time);

                        if (seconds == 0){
                            int marks = GraphPointFinder.correctAnsCount*5;
                            JOptionPane.showMessageDialog(null,"Your Score is : "+Integer.toString(marks),"Game score",JOptionPane.INFORMATION_MESSAGE);
                            Restart rs = new Restart();
                            rs.restartFunction();

                        }
                        if(GraphPointFinder.questCount==0){
                            cancel();
                        }

                        if(GraphPointFinder.restart_status==true){
                            cancel();
                            GraphPointFinder.time_count_label.setText("02:30");
                            GraphPointFinder.restart_status = false;
                        }
                        seconds--;
                    }
                }
            };
            timer.schedule(tt, 0, 1000);
        }
        else{
            JOptionPane.showMessageDialog(null,"Please finish or restart this game","warning",JOptionPane.WARNING_MESSAGE);
        }

    }
}

class Restart{

    public void restartFunction(){
        GraphPointFinder.play_but_status=true;
        GraphPointFinder.play_status = false;
        GraphPointFinder.nextButStatus = false;
        GraphPointFinder.ques_count_label.setText("20");
        GraphPointFinder.finished_count_label.setText("0");
        GraphPointFinder.time_count_label.setText("02:30");
        GraphPointFinder.correctAnsCount = 0;
        GraphPointFinder.questCount = 20;
        GraphPointFinder.xbox.setText("");
        GraphPointFinder.xbox.setBackground(Color.WHITE);
        GraphPointFinder.ybox.setText("");
        GraphPointFinder.ybox.setBackground(Color.WHITE);
        GraphPointFinder.xCorrectAns.setText("");
        GraphPointFinder.yCorrectAns.setText("");
    }
}

class restartButAction implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        Restart rs = new Restart();
        rs.restartFunction();

        GraphPointFinder.restart_status = true;
    }
}
