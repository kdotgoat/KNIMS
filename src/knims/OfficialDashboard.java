/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package knims;

import java.nio.file.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.*;
import javax.imageio.ImageIO;
import java.net.*;
import java.util.Base64;
import java.io.*;
import com.google.gson.JsonObject;
import java.awt.geom.AffineTransform;
import java.lang.reflect.Method;


import java.util.Set;
public class OfficialDashboard extends javax.swing.JFrame {

    private JLabel lblTitle, lblApplicant, lblLastName, lblPhone, lblResidence, lblStatus;
    private JLabel lblImage1, lblImage2, lblImage3, lblImage4, lblImage5; // For displaying images
    private JButton btnFetchDetails, btnApprove, btnReject,notifyUserButton,backButton;
    private String applicantName, lastName, phoneNumber, residence;
   
   public OfficialDashboard() {
     
       initComponents();{
          initComponents();
          
        setTitle("Official Dashboard");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(new Color(240, 240, 240));
        
        lblTitle = new JLabel("Approval Module", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setForeground(new Color(25, 118, 210));
        add(lblTitle, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);
        
        // Left panel for applicant details
        JPanel detailsPanel = new JPanel(new GridBagLayout());
        detailsPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        detailsPanel.add(new JLabel("Applicant First Name: "), gbc);
        gbc.gridx = 1;
        lblApplicant = new JLabel("N/A");
        detailsPanel.add(lblApplicant, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        detailsPanel.add(new JLabel("Applicant Last Name: "), gbc);
        gbc.gridx = 1;
        lblLastName = new JLabel("N/A");
        detailsPanel.add(lblLastName, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        detailsPanel.add(new JLabel("Residence: "), gbc);
        gbc.gridx = 1;
        lblResidence = new JLabel("N/A");
        detailsPanel.add(lblResidence, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        detailsPanel.add(new JLabel("Phone Number: "), gbc);
        gbc.gridx = 1;
        lblPhone = new JLabel("N/A");
        detailsPanel.add(lblPhone, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        detailsPanel.add(new JLabel("Approval Status: "), gbc);
        gbc.gridx = 1;
        lblStatus = new JLabel("N/A");
        detailsPanel.add(lblStatus, gbc);
        
        mainPanel.add(detailsPanel, BorderLayout.WEST);
        
        
        // Right panel for images
        JPanel imagePanel = new JPanel(new GridLayout(2, 2, 10, 10));
        imagePanel.setBackground(Color.WHITE);
        lblImage1 = new JLabel();
        lblImage2 = new JLabel();
        lblImage3 = new JLabel();
        lblImage4 = new JLabel();
        lblImage5 = new JLabel();
        
        addImageClickListener(lblImage1);
        addImageClickListener(lblImage2);
        addImageClickListener(lblImage3);
        addImageClickListener(lblImage4);
        addImageClickListener(lblImage5);
        
        imagePanel.add(lblImage1);
        imagePanel.add(lblImage2);
        imagePanel.add(lblImage3);
        imagePanel.add(lblImage4);
        imagePanel.add(lblImage5);
        
        mainPanel.add(imagePanel, BorderLayout.EAST);
        add(mainPanel, BorderLayout.CENTER);
        
        // Button panel at bottom
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        buttonPanel.setBackground(new Color(240, 240, 240));
        
        btnFetchDetails = new JButton("View Details");
        btnApprove = new JButton("Approve");
        btnReject = new JButton("Reject");
        notifyUserButton = new JButton("Notify User");
        backButton = new JButton("Back");
        
        
        customizeButton(btnFetchDetails);
        customizeButton(btnApprove);
        customizeButton(btnReject);
        customizeButton(notifyUserButton);
        customizeButton(backButton);
        
        btnFetchDetails.addActionListener(e -> fetchAndDisplayDetails());
        btnApprove.addActionListener(e -> updateApprovalStatus("Approved"));
        btnReject.addActionListener(e -> {
            updateApprovalStatus("Rejected");

        });
        notifyUserButton.addActionListener(e -> notifyUser());
        
        backButton.addActionListener(e -> {
            Login loginFrame = new Login();
            loginFrame.setVisible(true);
            loginFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            dispose();
        });

        
        
        
        buttonPanel.add(btnFetchDetails);
        buttonPanel.add(btnApprove);
        buttonPanel.add(btnReject);
        buttonPanel.add(notifyUserButton);
        buttonPanel.add(backButton);

        
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
   
    }
   private void customizeButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(30, 136, 229));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }
    

     private void fetchAndDisplayDetails() {
        String url = "jdbc:mysql://localhost:3306/knims";
String user = "root";
String password = "Wi0797563115#"; // Update with your MySQL password

try (Connection conn = DriverManager.getConnection(url, user, password)) {
    String query = "SELECT Name, Last_Name, Phone_no, approval_status, image1, image2, image3, image4, image5, AreaOfResidence FROM Registration WHERE approval_status = 'Pending' LIMIT 1";
    PreparedStatement stmt = conn.prepareStatement(query);
    ResultSet rs = stmt.executeQuery();

    if (rs.next()) {
        // Basic details
        applicantName = rs.getString("Name");
        phoneNumber = rs.getString("Phone_no");
        lastName = rs.getString("Last_Name");
        residence = rs.getString("AreaOfResidence");

        lblApplicant.setText(applicantName);
        lblLastName.setText(lastName);
        lblResidence.setText(residence);
        lblPhone.setText(phoneNumber);
        lblStatus.setText(rs.getString("approval_status"));

        // Images - null-safe loading
        byte[] img1 = rs.getBytes("image1");
        byte[] img2 = rs.getBytes("image2");
        byte[] img3 = rs.getBytes("image3");
        byte[] img4 = rs.getBytes("image4");
        byte[] img5 = rs.getBytes("image5");

        lblImage1.setIcon(img1 != null ? scaleImage(img1, 200, 200) : null);
        lblImage2.setIcon(img2 != null ? scaleImage(img2, 200, 200) : null);
        lblImage3.setIcon(img3 != null ? scaleImage(img3, 200, 200) : null);
        lblImage4.setIcon(img4 != null ? scaleImage(img4, 200, 200) : null);
        lblImage5.setIcon(img5 != null ? scaleImage(img5, 200, 200) : null);

    } else {
        JOptionPane.showMessageDialog(this, "No pending applications found.");
    }
} catch (SQLException ex) {
    JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
}
    }
     
      private void updateApprovalStatus(String status) {
       if (applicantName == null) {
            JOptionPane.showMessageDialog(this, "Please fetch details first.");
            return;
        }

        String url = "jdbc:mysql://localhost:3306/knims";
        String user = "root";
        String password = "Wi0797563115#";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE Registration SET approval_status = ? WHERE Name = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, status);
            stmt.setString(2, applicantName);
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Application " + status + " successfully.");
                lblStatus.setText(status);
            } else {
                JOptionPane.showMessageDialog(this, "Error updating status.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        }
    }

      
private ImageIcon scaleImage(byte[] imgBytes, int width, int height) {
     if (imgBytes == null || imgBytes.length == 0) {
        System.out.println("No image data found!");
        return new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)); // Return blank image
    }
    try {
        ByteArrayInputStream bis = new ByteArrayInputStream(imgBytes);
        BufferedImage img = ImageIO.read(bis);

        if (img == null) {
            System.out.println("Image could not be decoded!");
            return new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)); // Return blank image
        }

        // Convert image to avoid black rendering
        BufferedImage convertedImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = convertedImg.createGraphics();
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        Image scaled = convertedImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    } catch (Exception e) {
        e.printStackTrace();
        return new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)); // Return blank image
    }
}
 private void addImageClickListener(JLabel label) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ImageIcon icon = (ImageIcon) label.getIcon();
                if (icon != null) {
                    showImageInNewWindow(icon);
                }
            }
        });
    }

    private void showImageInNewWindow(ImageIcon icon) {
      JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Scale image to fit the window while maintaining aspect ratio
        Image scaledImage = icon.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel lbl = new JLabel(scaledIcon);
        frame.add(new JScrollPane(lbl));
        frame.setVisible(true);

    }
 
private void notifyUser() {
    if (phoneNumber == null || phoneNumber.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please fetch applicant details first.");
        return;
    }

    String apiUrl = "https://api.bulksms.com/v1/messages";
    String username = "muktar24"; // Your BulkSMS username
    String password = "Muktar321"; // Your BulkSMS password

    // Construct a unique message
    String message = "Dear " + applicantName + ", your ID application has been " +
                     lblStatus.getText() + ". You can proceed for biometrics with traction number: 54.";

    // Create JSON payload
    String jsonData = "{"
                    + "\"to\": \"" + phoneNumber + "\","
                    + "\"encoding\": \"TEXT\","
                    + "\"body\": \"" + message + "\""
                    + "}";

    try {
        URL url = new URL(apiUrl);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.setDoOutput(true);
        request.setRequestMethod("POST");
        request.setRequestProperty("Content-Type", "application/json");

        // Basic Authentication header
        String authStr = username + ":" + password;
        String authEncoded = Base64.getEncoder().encodeToString(authStr.getBytes());
        request.setRequestProperty("Authorization", "Basic " + authEncoded);

        // Send request
        try (OutputStreamWriter out = new OutputStreamWriter(request.getOutputStream())) {
            out.write(jsonData);
        }

        // Handle response
        int responseCode = request.getResponseCode();
        InputStream responseStream = (responseCode >= 200 && responseCode < 300) ?
                request.getInputStream() : request.getErrorStream();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(responseStream))) {
            String line;
            StringBuilder responseBuilder = new StringBuilder();
            while ((line = in.readLine()) != null) {
                responseBuilder.append(line).append("\n");
            }

            if (responseCode >= 200 && responseCode < 300) {
                JOptionPane.showMessageDialog(this, "User notified successfully.");
            } else {
                System.err.println("SMS API Error Response: " + responseBuilder);
                JOptionPane.showMessageDialog(this, "Failed to send SMS notification.\n" + responseBuilder.toString());
            }
        }

        request.disconnect();
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error sending SMS: " + e.getMessage());
    }
}
// private void Reject(){
//     if (phoneNumber == null || phoneNumber.isEmpty()) {
//        JOptionPane.showMessageDialog(this, "Please fetch applicant details first.");
//        return;
//    }
//
//    String apiUrl = ";
//    String username = "nyivaaa"; // Your BulkSMS username
//    String password = "Nyivak4321"; // Your BulkSMS password
//
//    // Construct a unique message
//    String message = "Dear " + applicantName + ", your ID application has been " +
//                     lblStatus.getText() + ".Go back for more inquiry.";
//
//    // Create JSON payload
//    String jsonData = "{"
//                    + "\"to\": \"" + phoneNumber + "\","
//                    + "\"encoding\": \"TEXT\","
//                    + "\"body\": \"" + message + "\""
//                    + "}";
//
//    try {
//        URL url = new URL(apiUrl);
//        HttpURLConnection request = (HttpURLConnection) url.openConnection();
//        request.setDoOutput(true);
//        request.setRequestMethod("POST");
//        request.setRequestProperty("Content-Type", "application/json");
//
//        // Basic Authentication header
//        String authStr = username + ":" + password;
//        String authEncoded = Base64.getEncoder().encodeToString(authStr.getBytes());
//        request.setRequestProperty("Authorization", "Basic " + authEncoded);
//
//        // Send request
//        try (OutputStreamWriter out = new OutputStreamWriter(request.getOutputStream())) {
//            out.write(jsonData);
//        }
//
//        // Handle response
//        int responseCode = request.getResponseCode();
//        InputStream responseStream = (responseCode >= 200 && responseCode < 300) ?
//                request.getInputStream() : request.getErrorStream();
//
//        try (BufferedReader in = new BufferedReader(new InputStreamReader(responseStream))) {
//            String line;
//            StringBuilder responseBuilder = new StringBuilder();
//            while ((line = in.readLine()) != null) {
//                responseBuilder.append(line).append("\n");
//            }
//
//            if (responseCode >= 200 && responseCode < 300) {
//                JOptionPane.showMessageDialog(this, "User notified successfully.");
//            } else {
//                System.err.println("SMS API Error Response: " + responseBuilder);
//                JOptionPane.showMessageDialog(this, "Failed to send SMS notification.\n" + responseBuilder.toString());
//            }
//        }
//
//        request.disconnect();
//    } catch (Exception e) {
//        e.printStackTrace();
//        JOptionPane.showMessageDialog(this, "Error sending SMS: " + e.getMessage());
//    }
// }


     
   

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  

    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(() -> {
            new OfficialDashboard().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
