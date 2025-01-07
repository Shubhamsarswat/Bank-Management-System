package com.example.project;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;;

public class deposit extends JFrame implements ActionListener{
    String pin;
    JTextField textField;
    JButton b1, b2;
    deposit(String pin){
        this.pin = pin; 
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1550, 830);
        add(image);

        JLabel l1 = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
        l1.setFont(new Font("System", Font.BOLD, 16));
        l1.setBounds(460, 180, 400, 35);
        l1.setForeground(Color.WHITE);
        image.add(l1);

        textField = new JTextField();
        textField.setBackground(new Color(65, 125, 128));
        textField.setForeground(Color.WHITE);
        textField.setFont(new Font("Raleway", Font.BOLD, 22));
        textField.setBounds(460, 230, 320, 25);
        image.add(textField);

        b1 = new JButton("Deposit");
        b1.setBounds(700, 362, 150, 35);
        b1.setBackground(new Color(65, 125, 128));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("Raleway", Font.BOLD, 14));
        b1.addActionListener(this);
        image.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(700, 406, 150, 35);
        b2.setBackground(new Color(65, 125, 128));
        b2.setForeground(Color.WHITE);
        b2.setFont(new Font("Raleway", Font.BOLD, 14));
        b2.addActionListener(this);
        image.add(b2);


        setLayout(null);
        setSize(1550, 1080);
        setLocation(0,0);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            String amount = textField.getText();
            Date date = new Date();
            if(e.getSource() == b1) {
                if(textField.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter the amount you want to deposit.");
                } else {
                    Conn conn = new Conn();
                    conn.statement.executeUpdate("insert into bank values('"+pin+"', '"+date+"','deposit', '"+amount+"')");
                    JOptionPane.showMessageDialog(null, "Rs. " + amount + " Deposited Succesfully.");
                    new main_class(pin);
                    setVisible(false);
                }
            } else if(e.getSource() == b2) {
                setVisible(false);
                new main_class(pin);
            }
        } catch(Exception E) {
            E.printStackTrace(); 
        }
    }
    public static void main(String[] args) {
        new deposit("");
    }
}
