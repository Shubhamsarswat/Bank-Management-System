package com.example.project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class BalanceEnquiry extends JFrame implements ActionListener{
    String pin;
    JLabel l1, l2;
    JButton b1;
    BalanceEnquiry(String pin) {
        this.pin = pin;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1550, 830);
        add(image);

        l1 = new JLabel("YOUR CURRENT BALANCE IS RS ");
        l1.setFont(new Font("System", Font.BOLD, 16));
        l1.setBounds(430, 180, 700, 35);
        l1.setForeground(Color.WHITE);
        image.add(l1);

        l2 = new JLabel("");
        l2.setFont(new Font("System", Font.BOLD, 16));
        l2.setBounds(430, 220, 400, 35);
        l2.setForeground(Color.WHITE);
        image.add(l2);

        b1 = new JButton("BACK");
        b1.setBounds(700, 406, 150, 35);
        b1.setBackground(new Color(65, 125, 128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        image.add(b1);

        int balance = 0;
        try{
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from bank where pin = '"+pin+"'");
            while (resultSet.next()) {
                if(resultSet.getString("type").equals("deposit")) {
                    balance += Integer.parseInt(resultSet.getString("amount"));
                } else {
                    balance -= Integer.parseInt(resultSet.getString("amount"));
                }
            }
        } catch(Exception E) {
            E.printStackTrace();
        }

        l2.setText("" + balance);

        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new main_class(pin);
    }
    public static void main(String[] args) {
        new BalanceEnquiry("");
    }
}
