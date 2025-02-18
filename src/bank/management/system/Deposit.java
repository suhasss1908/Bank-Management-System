package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.*;


public class Deposit extends JFrame implements ActionListener{
    JTextField t1;
    JButton b1,b2;
    String pin;
    Deposit(String pin){
        this.pin=pin;
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel l1=new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT:");
        l1.setBounds(460,180,400,35);
        l1.setFont(new Font("System",Font.BOLD,16));
        l1.setForeground(Color.WHITE);
        l3.add(l1);

        t1=new JTextField();
        t1.setBounds(460,230,320,25);
        t1.setBackground(new Color(65,125,128));
        t1.setForeground(Color.WHITE);
        t1.setFont(new Font("Raleway",Font.BOLD,22));
        l3.add(t1);

        b1=new JButton("DEPOSIT");
        b1.setBounds(700,362,150,35);
        b1.setBackground(new Color(65,125,128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        l3.add(b1);

        b2=new JButton("BACK");
        b2.setBounds(700,406,150,35);
        b2.setBackground(new Color(65,125,128));
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        l3.add(b2);




        setLayout(null);
        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e){
        try{
            String amount=t1.getText();
            Date d1=new Date();
            if(e.getSource()==b1){
                if(t1.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Please Enter the Amount you need to deposit");
                }
                else{
                    Conn c2=new Conn();
                    c2.statement.executeUpdate("insert into bank values('"+pin+"','"+d1+"','Deposit','"+amount+"')");
                    JOptionPane.showMessageDialog(null, "Deposited SuccessFully");
                    setVisible(false);
                    new Main(pin);
                }
            }
            if(e.getSource()==b2){
                setVisible(false);
                new Main(pin);
            }
        }
        catch(Exception E){
            E.printStackTrace();
        }
    }
    public static void main(String args[]){
        new Deposit("");
    }
}
