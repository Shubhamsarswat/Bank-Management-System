package com.example.project;

import java.awt.*;

import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {
    String pin;
    JButton b1, b2, b3, b4, b5, b6, b7;

    FastCash(String pin) {
        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1550, 830);
        add(image);

        JLabel l1 = new JLabel("SELECT WITHDRAWL AMOUNT");
        l1.setFont(new Font("System", Font.BOLD, 23));
        l1.setBounds(445, 180, 700, 35);
        l1.setForeground(Color.WHITE);
        image.add(l1);

        b1 = new JButton("Rs. 100");
        b1.setForeground(Color.WHITE);
        b1.setBackground(new Color(65, 125, 128));
        b1.setBounds(410, 274, 150, 35);
        b1.addActionListener(this);
        image.add(b1);

        b2 = new JButton("Rs. 500");
        b2.setForeground(Color.WHITE);
        b2.setBackground(new Color(65, 125, 128));
        b2.setBounds(700, 274, 150, 35);
        b2.addActionListener(this);
        image.add(b2);

        b3 = new JButton("Rs. 1000");
        b3.setBounds(410, 318, 150, 35);
        b3.setBackground(new Color(65, 125, 128));
        b3.addActionListener(this);
        b3.setForeground(Color.WHITE);
        image.add(b3);

        b4 = new JButton("Rs. 2000");
        b4.setForeground(Color.WHITE);
        b4.setBackground(new Color(65, 125, 128));
        b4.setBounds(700, 318, 150, 35);
        b4.addActionListener(this);
        image.add(b4);

        b5 = new JButton("Rs. 5000");
        b5.setForeground(Color.WHITE);
        b5.setBackground(new Color(65, 125, 128));
        b5.setBounds(410, 362, 150, 35);
        b5.addActionListener(this);
        image.add(b5);

        b6 = new JButton("Rs. 10000");
        b6.setForeground(Color.WHITE);
        b6.setBackground(new Color(65, 125, 128));
        b6.setBounds(700, 362, 150, 35);
        b6.addActionListener(this);
        image.add(b6);

        b7 = new JButton("BACK");
        b7.setForeground(Color.WHITE);
        b7.setBackground(new Color(65, 125, 128));
        b7.setBounds(700, 406, 150, 35);
        b7.addActionListener(this);
        image.add(b7);

        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b7) {
            setVisible(false);
            new main_class(pin);
        } else {
            String amount = ((JButton) e.getSource()).getText().substring(4);
            Conn c = new Conn();
            Date date = new Date();
            try {
                ResultSet resultSet = c.statement.executeQuery("select * from bank where pin = '"+pin+"'");
                int balance = 0;
                while(resultSet.next()) {
                    if(resultSet.getString("type").equals("deposit")) {
                        balance += Integer.parseInt(resultSet.getString("amount"));
                    } else {
                        balance -= Integer.parseInt(resultSet.getString("amount"));
                    }
                }
                
                if(e.getSource() != b7 && balance < Integer.parseInt(amount)) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                } 
                c.statement.executeUpdate("insert into bank values('"+pin+"', '"+date+"', 'Withdrawl', '"+amount+"')");
                JOptionPane.showMessageDialog(null, "Rs. " + amount + " Debited Successfully");
            } catch (Exception E) {
                E.printStackTrace();
            }
            setVisible(false);
            new main_class(pin);
        }
    }

    public static void main(String[] args) {
        new FastCash("");
    }
}
