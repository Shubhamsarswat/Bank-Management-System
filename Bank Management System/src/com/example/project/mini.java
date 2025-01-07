package com.example.project;
import java.awt.*;

import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class mini extends JFrame implements ActionListener{
    String pin;
    JButton button;
    mini(String pin) {
        this.pin = pin;

        JLabel l1 = new JLabel();
        l1.setBounds(20, 140, 400, 200);
        add(l1);

        JLabel l2 = new JLabel("SRISHTI ");
        l2.setFont(new Font("System", Font.BOLD, 15));
        l2.setBounds(150, 20, 200, 20);
        add(l2);

        JLabel l3 = new JLabel();
        l3.setBounds(20, 80, 300, 20);
        add(l3);

        JLabel l4 = new JLabel();
        l4.setBounds(20, 400, 300, 20);
        add(l4);

        try{
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from login where pin = '"+pin+"'");  
            while (resultSet.next()) {
                l3.setText("Card Number: " + resultSet.getString("card_number").substring(0,4) + "XXXXXXXX" + resultSet.getString("card_number").substring(12));
            }
        } catch(Exception E) {
            E.printStackTrace();
        }

        try{
            int balance = 0; 
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from bank where pin = '"+pin+"'");
            while (resultSet.next()) {
                l1.setText(l1.getText() + "<html>" + resultSet.getString("date")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + resultSet.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + resultSet.getString("amount") + "<br><br><html>");
                if(resultSet.getString("type").equals("deposit")) {
                    balance += Integer.parseInt(resultSet.getString("amount"));
                } else {
                    balance -= Integer.parseInt(resultSet.getString("amount"));
                }
            }

            l4.setText("Your Total Balance is Rs. " + balance);
        } catch(Exception e) {
            e.printStackTrace();
        }

        button = new JButton("EXIT");
        button.setBounds(20, 500, 100, 25);
        button.addActionListener(this);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        add(button);

        
        getContentPane().setBackground(new Color(255, 204, 204));
        setLayout(null);
        setSize(400, 600);
        setLocation(20, 20);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new main_class(pin);
    }
    public static void main(String[] args) {
        new mini("");
    }
}
