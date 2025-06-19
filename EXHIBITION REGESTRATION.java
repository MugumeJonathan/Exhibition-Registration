/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *

 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class ExhibitionGUI {
    private Participant participant;
    private JFrame frame;
    private JTextField regIdField, nameField, facultyField, projectField, contactField, emailField;
    private JLabel imageLabel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ExhibitionGUI().createGUI());
    }

    private void createGUI() {
        participant = new Participant();
        frame = new JFrame("Exhibition Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);

    
        JPanel formPanel = new JPanel(new GridLayout(7, 2));
        addField(formPanel, "Registration ID:", regIdField = new JTextField());
        addField(formPanel, "Student Name:", nameField = new JTextField());
  

        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel, "Register", e -> registerParticipant());
        addButton(buttonPanel, "Search", e -> searchParticipant());
    

        
        JButton uploadBtn = new JButton("Upload Image");
        uploadBtn.addActionListener(e -> uploadImage());
        buttonPanel.add(uploadBtn);
        imageLabel = new JLabel("No image selected", SwingConstants.CENTER);

       
        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(imageLabel, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    private void registerParticipant() {
        participant.setRegistrationId(regIdField.getText());
        participant.setStudentName(nameField.getText());
        // Set other fields...
        if (participant.register()) {
            JOptionPane.showMessageDialog(frame, "Registration successful!");
        }
    }

    private void uploadImage() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            participant.setImagePath(file.getAbsolutePath());
            imageLabel.setIcon(new ImageIcon(participant.getImagePath()));
        }
    }

 
    private void addField(JPanel panel, String label, JTextField field) {
        panel.add(new JLabel(label));
        panel.add(field);
    }

    private void addButton(JPanel panel, String text, ActionListener action) {
        JButton btn = new JButton(text);
        btn.addActionListener(action);
        panel.add(btn);
    }
}
