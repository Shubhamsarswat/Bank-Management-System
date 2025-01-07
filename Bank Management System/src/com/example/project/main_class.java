package com.example.project;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class main_class extends JFrame implements ActionListener{
    JButton b1, b2, b3, b4, b5, b6, b7;
    String pin;
    main_class(String pin) {
        this.pin = pin;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1550, 830);

        JLabel l1 = new JLabel("Please Select Your Transaction");
        l1.setBounds(430, 180, 700, 35);
        l1.setFont(new Font("System", Font.BOLD, 28));
        l1.setForeground(Color.WHITE);
        image.add(l1);

        b1 = new JButton("DEPOSIT");
        b1.setBounds(410, 274, 150, 35);
        b1.setBackground(new Color(65, 125, 128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        image.add(b1);

        b2 = new JButton("CASH WITHDRAWL");
        b2.setBounds(700, 274, 150, 35);
        b2.setBackground(new Color(65, 125, 128));
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        image.add(b2);

        b3 = new JButton("FAST CASH");
        b3.setBounds(410, 318, 150, 35);
        b3.setBackground(new Color(65, 125, 128));
        b3.setForeground(Color.WHITE);
        b3.addActionListener(this);
        image.add(b3);

        b4 = new JButton("MINI STATEMENT");
        b4.setBounds(700, 318, 150, 35);
        b4.setBackground(new Color(65, 125, 128));
        b4.setForeground(Color.WHITE);
        b4.addActionListener(this);
        image.add(b4);

        b5 = new JButton("PIN CHANGE");
        b5.setBounds(410, 362, 150, 35);
        b5.setBackground(new Color(65, 125, 128));
        b5.setForeground(Color.WHITE);
        b5.addActionListener(this);
        image.add(b5);

        b6 = new JButton("BALANCE ENQUIRY");
        b6.setBounds(700, 362, 150, 35);
        b6.setBackground(new Color(65, 125, 128));
        b6.setForeground(Color.WHITE);
        b6.addActionListener(this);
        image.add(b6);

        b7 = new JButton("EXIT");
        b7.setBounds(700, 406, 150, 35);
        b7.setBackground(new Color(65, 125, 128));
        b7.setForeground(Color.WHITE);
        b7.addActionListener(this);
        image.add(b7);


        add(image);
        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if(e.getSource() == b1) {
                new deposit(pin);
                setVisible(false);
            } else if(e.getSource() == b7) {
                System.exit(0);
            } else if(e.getSource() == b2) {
                new withdrawl(pin);
                setVisible(false); 
            } else if(e.getSource() == b6) {
                new BalanceEnquiry(pin);
                setVisible(false);
            } else if(e.getSource() == b3) {
                new FastCash(pin);
                setVisible(false);
            } else if(e.getSource() == b5) {
                new Pin(pin);
                setVisible(false);
            } else if(e.getSource() == b4){
                new mini(pin);
                setVisible(false);
            }
        } catch(Exception E) {
            E.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new main_class("");
    }
}
