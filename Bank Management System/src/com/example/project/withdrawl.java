package com.example.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.Date;

public class withdrawl extends JFrame implements ActionListener {
    String pin;
    JButton b1, b2;
    JTextField textField;

    withdrawl(String pin) {
        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1550, 830);
        add(image);

        JLabel l1 = new JLabel("MAXIMUM WITHDRAWL IS Rs.10,000");
        l1.setFont(new Font("System", Font.BOLD, 16));
        l1.setForeground(Color.WHITE);
        l1.setBounds(460, 180, 400, 35);
        image.add(l1);

        JLabel l2 = new JLabel("PLEASE ENTER YOUR AMOUNT");
        l2.setFont(new Font("Raleway", Font.BOLD, 16));
        l2.setBounds(460, 220, 400, 35);
        l2.setForeground(Color.WHITE);
        image.add(l2);

        textField = new JTextField();
        textField.setFont(new Font("Raleway", Font.BOLD, 22));
        textField.setForeground(Color.WHITE);
        textField.setBackground(new Color(65, 125, 128));
        textField.setBounds(460, 260, 320, 25);
        image.add(textField);

        b1 = new JButton("WITHDRAW");
        b1.setBounds(700, 362, 150, 35);
        b1.setBackground(new Color(65, 125, 128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        image.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(700, 406, 150, 35);
        b2.setBackground(new Color(65, 125, 128));
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        image.add(b2);

        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == b1) {
            try {
                String amount = textField.getText();
                Date date = new Date();
                if (e.getSource() == b1) {
                    if (textField.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Please enter the amount you want to withdraw");
                    } else {
                        Conn conn = new Conn();
                        ResultSet resultSet = conn.statement
                                .executeQuery("select * from bank where pin = '" + pin + "'");
                        int balance = 0;
                        while (resultSet.next()) {
                            if (resultSet.getString("type").equals("deposit")) {
                                balance += Integer.parseInt(resultSet.getString("amount"));
                            } else {
                                balance -= Integer.parseInt(resultSet.getString("amount"));
                            }
                        }
                        if (balance < Integer.parseInt(amount)) {
                            JOptionPane.showMessageDialog(null, "Insufficient Balance");
                            return;
                        }
                        conn.statement.executeUpdate( "insert into bank values('"+pin+"', '"+date+"', 'Withdrawl', '"+amount+"')");
                        JOptionPane.showMessageDialog(null, "Rs. " + amount + " Debited Successfully");
                        setVisible(false);
                        new main_class(pin);
                    }
                }
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else if(e.getSource() == b2) {
            setVisible(false);
            new main_class(pin);
        }
    }

    public static void main(String[] args) {
        new withdrawl("");
    }
}
