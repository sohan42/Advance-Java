package javaf;

import java.awt.*;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

class MyComponent extends JComponent{
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D)g; //cast graphics to Graphics2D
        
        Font f1 = new Font("Serif",Font.ITALIC,36);
        //g2D.setColor(Color.red);
        g2D.setFont(f1);
        g2D.drawString("Java", 20, 40);
        
        try {
            //Custom font
            Font cf = Font.createFont(Font.TRUETYPE_FONT, new File("F:\\Fonts\\Blacksword.otf")).deriveFont(28f);
            g2D.setFont(cf);
            g2D.setColor(Color.red);
            g2D.drawString("This is my Custom font", 20, 80);
        } catch (IOException | FontFormatException ex) {
            ex.printStackTrace();
        }
        
        Image i = new ImageIcon("..//JavaF//src//Image//art.png").getImage();
        g2D.drawImage(i, 80, 80, this);
    }
} 
//classroom code: 6nbshrv
class JavaC extends JFrame{
    private static int count = 0;
    JavaC(){
       /* MyComponent mComp = new MyComponent(); //Creating object of component class
        add(mComp);    //adding object of component class on current frame
        pack();*/     
        setTitle("ABC"); //To set title of frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exits when cross button is clicked
        setVisible(true); //To make frame visible
        //setLocation(250,250);//To set the display location of the frame
        setSize(400,300); //Size of the frame
        //setBounds(250,250,400,300); //set locaiton and size of the frame
        //setResizable(false);
        setLocationRelativeTo(null); //To place frame at center 
        setLayout(null);
        Image img = new ImageIcon("..//JavaF//src//Image//hand.jpg").getImage(); //To get image
        setIconImage(img); //To set image as icon   
        
        /*addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent e){
                System.out.println("Mouse clicked at (" + e.getX() + ", " + e.getY() + ")");
            }
        });
        
        addKeyListener(new KeyAdapter(){
        public void keyTyped(KeyEvent e){
                System.out.println("Key typed: "+e.getKeyChar());
            }
        });
        
        addWindowListener(new WindowAdapter(){
        public void windowClosing(WindowEvent e){
                System.out.println("Window Closing...");
            }
        });*/
        
        JButton b = new JButton("Click here!");
        b.setBounds(40, 40, 100, 20);
        add(b);
        
        /*JLabel l = new JLabel("Null");
        l.setBounds(40, 80, 100, 20);
        add(l);*/
        
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count++;
               JOptionPane.showMessageDialog(null, "Count: "+count);
            }
        });
    }
}

class JavaLayout extends JFrame{
    void flowLayout(){
        setLayout(new FlowLayout());
        for(int i=1;i<=5;i++){
            add(new JButton("Button "+i));
        }
    }
    
    void borderLayout(){
        setLayout(new BorderLayout());

        add(new JButton("North"), BorderLayout.NORTH);
        add(new JButton("South"), BorderLayout.SOUTH);
        add(new JButton("East"), BorderLayout.EAST);
        add(new JButton("West"), BorderLayout.WEST);
        add(new JButton("Center"), BorderLayout.CENTER);
    }
    
    void gridLayout(){
        setLayout(new GridLayout(3, 3));
        for (int i = 1; i <= 9; i++) {
            add(new JButton(""+i));
        }
    }
    
    void boxLayout(){
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));//Vertical allignment
        for(int i =1;i<=5;i++){
            add(new Button(""+i));
        }
    }
    
    void gridBagLayout(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Button 1
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JButton("Button 1"), gbc);

        // Button 2
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(new JButton("Button 2"), gbc);

        // Button 3
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Span two columns
        add(new JButton("Button 3"), gbc);
    }
}

public class JavaF{
    public static void main(String[] args) {
        /*EventQueue.invokeLater(()->{
            new JavaF();
        });*/
        
    }
}
