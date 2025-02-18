package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;



public class Withdraw extends JFrame implements ActionListener{
    JTextField t1;
    JButton b1,b2;
    String pin;
    Withdraw(String pin){
    	
    	this.pin=pin;
    	
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel l1=new JLabel("MAXIMUM WITHDRAWAL IS RS.10,000");
        l1.setBounds(460,180,700,35);
        l1.setFont(new Font("System",Font.BOLD,16));
        l1.setForeground(Color.WHITE);
        l3.add(l1);

        JLabel l2=new JLabel("PLEASE ENTER THE AMOUNT");
        l2.setBounds(460,220,400,35);
        l2.setFont(new Font("System",Font.BOLD,16));
        l2.setForeground(Color.WHITE);
        l3.add(l2);

        t1=new JTextField();
        t1.setBounds(460,260,320,25);
        t1.setBackground(new Color(65,125,128));
        t1.setForeground(Color.WHITE);
        t1.setFont(new Font("Raleway",Font.BOLD,22));
        l3.add(t1);

        b1=new JButton("WITHDRAWAL");
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

   
    public static void main(String args[]){
        new Withdraw("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==b1) {
            try {
                String amount = t1.getText();
                Date date = new Date();
                if (t1.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the Amount you want to withdraw");
                } else {
                    Conn c = new Conn();
                    ResultSet resultSet = c.statement.executeQuery("select * from bank where pin = '" + pin + "'");
                    int balance = 0;
                    while (resultSet.next()) {
                        if (resultSet.getString("type").equals("Deposit")) {
                            balance += Integer.parseInt(resultSet.getString("amount"));
                        } else {
                            balance -= Integer.parseInt(resultSet.getString("amount"));
                        }
                    }
                    if (balance < Integer.parseInt(amount)) {
                        JOptionPane.showMessageDialog(null, "Insuffient Balance");
                        return;
                    }

                    c.statement.executeUpdate("insert into bank values('" + pin + "', '" + date + "', 'Withdrawl', '" + amount + "' )");
                    JOptionPane.showMessageDialog(null, "Rs. " + amount + " Debited Successfully");
                    setVisible(false);
                    new Main(pin);

                }
            } catch (Exception E) {

            }
        } else if (e.getSource()==b2) {
            setVisible(false);
            new Main(pin);
        }
    }
}
