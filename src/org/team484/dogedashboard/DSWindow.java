package org.team484.dogedashboard;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

class DSWindow extends JComponent {
    private static final long serialVersionUID = 1L;
    public static int windowWidth = 1366;
    public static int windowHeight = 725;
    public static int robotSizeMultiplier = 8;
    double gyro;
    double objectX;
    double objectY;
    double objectRotation;
    int objectType;
    boolean objectExist;
    double robotSpeed;
    double armAngle;
    double armExtend;
    double amperage;
    boolean[] motorStatus;
    int setAutonomous;
    double armMaxExtend = 14.25;
    double armLength = 47.5;
    boolean isDisabled;
    float alpha2;
    int image;
    public DSWindow(boolean[] motorStatus, double gyro, double objectX, double objectY, double objectRotation, int objectType, boolean objectExist, double robotSpeed, double armAngle, double armExtend, double amperage, int setAutonomous, boolean isDisabled, float alpha2, int image) {
        this.robotSpeed = Math.abs(robotSpeed);
        this.gyro = gyro;
        this.objectX = objectX;
        this.objectY = objectY;
        this.objectType = objectType;
        this.objectExist = objectExist;
        this.objectRotation = objectRotation;
        this.armAngle = armAngle;
        this.armExtend = armExtend;
        this.amperage = amperage;
        this.motorStatus = motorStatus;
        this.setAutonomous = setAutonomous;
        this.isDisabled = isDisabled;
        if (alpha2 > 1) {
        	this.alpha2 = 1;
        } else {
        this.alpha2 = alpha2;
        }
        this.image = image;
    }
    @Override
    public void paint (Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Graphics2D g3 = g2;
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(hints);
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(0,0,windowWidth,windowHeight);
        Font font;
        
        ImageIcon hintImg = null;
        try {
        	if (image == 1) {
        		hintImg = new ImageIcon(Class.forName("org.team484.dogedashboard.DSWindow").getResource("1.png"));
        	} else if (image == 2) {
        		hintImg = new ImageIcon(Class.forName("org.team484.dogedashboard.DSWindow").getResource("2.png"));
        	} else if (image == 3) {
        		hintImg = new ImageIcon(Class.forName("org.team484.dogedashboard.DSWindow").getResource("3.png"));
        	} else if (image == 4) {
        		hintImg = new ImageIcon(Class.forName("org.team484.dogedashboard.DSWindow").getResource("4.png"));
        	} else if (image == 5) {
        		hintImg = new ImageIcon(Class.forName("org.team484.dogedashboard.DSWindow").getResource("5.png"));
        	} else {
        		hintImg = new ImageIcon();
        	}
        } catch (Exception ex) {
        	Logger.getLogger(DSWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image hint = hintImg.getImage();
        //ErrorInfo
        int errorLocation = 200;
        g2.setColor(Color.RED);
        if (motorStatus[0]) {
        	g2.drawString("Error: Front Left Motor Power", 20, errorLocation);
        	errorLocation = errorLocation + 20;
        }
        if (motorStatus[1]) {
        	g2.drawString("Error: Rear Left Motor Power", 20, errorLocation);
        	errorLocation = errorLocation + 20;
        }
        if (motorStatus[2]) {
        	g2.drawString("Error: Front Right Motor Power", 20, errorLocation);
        	errorLocation = errorLocation + 20;
        }
        if (motorStatus[3]) {
        	g2.drawString("Error: Rear Right Motor Power", 20, errorLocation);
        	errorLocation = errorLocation + 20;
        }
        if (motorStatus[4]) {
        	g2.drawString("Error: Front Left Motor High Current Draw", 20, errorLocation);
        	errorLocation = errorLocation + 20;
        }
        if (motorStatus[5]) {
        	g2.drawString("Error: Rear Left Motor High Current Draw", 20, errorLocation);
        	errorLocation = errorLocation + 20;
        }
        if (motorStatus[6]) {
        	g2.drawString("Error: Front Right Motor High Current Draw", 20, errorLocation);
        	errorLocation = errorLocation + 20;
        }
        if (motorStatus[7]) {
        	g2.drawString("Error: Rear Right Motor High Current Draw", 20, errorLocation);
        	errorLocation = errorLocation + 20;
        }
        if (motorStatus[8]) {
        	g2.drawString("Error: Tote Pickup Motor High Current Draw", 20, errorLocation);
        	errorLocation = errorLocation + 20;
        }
        if (motorStatus[9]) {
        	g2.drawString("Error: Arm Motor High Current Draw", 20, errorLocation);
        	errorLocation = errorLocation + 20;
        }     
        if (motorStatus[10]) {
        	g2.drawString("Error: Arm Extension Motor High Current Draw", 20, errorLocation);
        	errorLocation = errorLocation + 20;
        } 
        if (motorStatus[11]) {
        	g2.drawString("Error: Gyroscope", 20, errorLocation);
        }
        
        g2.setColor(Color.BLACK);
        g2.fillOval(20,20,150,150);
        g2.setColor(Color.GRAY);
        g2.drawOval(20, 20, 150, 150);
        g2.setColor(Color.RED);
        g2.rotate(robotSpeed * Math.PI / 10.0, 95, 95);
        g2.drawLine(95, 95, 20, 95);
        g2.drawLine(95, 96, 20, 95);
        g2.drawLine(95, 94, 20, 95);
        g2.rotate(-robotSpeed * Math.PI / 10.0, 95, 95);
        g2.drawString("FPS: " + Math.round(robotSpeed * 10.0) / 10.0, 70, 130);
        g2.setColor(Color.GRAY);
        g2.fillRect(1266, 25, 75, 150);
        g2.setColor(Color.RED);
        g2.fillRect(1266, 175 - (int) (amperage / 120 * 150), 75, (int) (amperage / 120 * 150));
        g2.setColor(Color.BLACK);
        font = new Font("Verdana", Font.BOLD, 14);
        g2.setFont(font);
        g2.drawString("Amps: " + Math.round(amperage), 1270, 190);
      //Auto mode
        
        font = new Font("Verdana", Font.BOLD, 32);
        g2.setFont(font);
        float alpha = 1;
        if (!isDisabled) {
        	alpha = 0.2f;
        }
        g2.setColor(new Color(0,1,0,alpha));
        g2.fillRoundRect(windowWidth - 120, windowHeight - 160, 100, 100,24,24);
        g2.setColor(new Color(0,0,0,alpha));
        g2.drawRoundRect(windowWidth - 120, windowHeight - 160, 100, 100,24,24);
        g2.setColor(new Color(1,1,1,alpha));
        font = new Font("Helvetica", Font.BOLD, 26);
        g2.setFont(font);
        g2.drawString("Auto", windowWidth - 100, windowHeight-130);
        font = new Font("Verdana", Font.BOLD, 38);
        g2.setFont(font);
        g2.drawString("" + setAutonomous, windowWidth - 83, windowHeight - 80);
        //Robot
        if (alpha2 == 0 || image == 0) {
        g2.translate(windowWidth/2 - 27 * robotSizeMultiplier/2, windowHeight/2 - 32 * robotSizeMultiplier/2);
        
        g2.rotate(Math.toRadians(gyro), robotSizeMultiplier * 27 / 2, robotSizeMultiplier * 32 / 2);
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(0 * robotSizeMultiplier, 0 * robotSizeMultiplier, 27 * robotSizeMultiplier, 1 * robotSizeMultiplier);
        g2.fillRect(0 * robotSizeMultiplier, 1 * robotSizeMultiplier, 1 * robotSizeMultiplier, 30 * robotSizeMultiplier);
        g2.fillRect(0 * robotSizeMultiplier, 31 * robotSizeMultiplier, 27 * robotSizeMultiplier, 1 * robotSizeMultiplier);
        g2.fillRect(26 * robotSizeMultiplier,1 * robotSizeMultiplier,1 * robotSizeMultiplier,30 * robotSizeMultiplier);
        
        g2.fillRect(5 * robotSizeMultiplier, 1 * robotSizeMultiplier, 1 * robotSizeMultiplier, 30 * robotSizeMultiplier);
        g2.fillRect(21 * robotSizeMultiplier, 1 * robotSizeMultiplier, 1 * robotSizeMultiplier, 30 * robotSizeMultiplier);
        
        g2.fillRect(3 * robotSizeMultiplier, 2 * robotSizeMultiplier, 1 * robotSizeMultiplier, 6 * robotSizeMultiplier);
        g2.fillRect(23 * robotSizeMultiplier, 2 * robotSizeMultiplier, 1 * robotSizeMultiplier, 6 * robotSizeMultiplier);
        
        g2.fillRect(2 * robotSizeMultiplier, 24 * robotSizeMultiplier, 1 * robotSizeMultiplier, 6 * robotSizeMultiplier);
        g2.fillRect(24 * robotSizeMultiplier, 24 * robotSizeMultiplier, 1 * robotSizeMultiplier, 6 * robotSizeMultiplier);
        
        g2.fillRect((int)(13 * robotSizeMultiplier), -1 * robotSizeMultiplier, 1 * robotSizeMultiplier, 1 * robotSizeMultiplier);
        g2.setColor(Color.WHITE);
        g2.fillRect(6 * robotSizeMultiplier, 1 * robotSizeMultiplier, 15 * robotSizeMultiplier, 30 * robotSizeMultiplier);
        g2.setColor(Color.GRAY);
        g2.fillRect(1* robotSizeMultiplier, (int) (15.5* robotSizeMultiplier), 4* robotSizeMultiplier, 1* robotSizeMultiplier);
        g2.fillRect(22* robotSizeMultiplier, (int) (15.5* robotSizeMultiplier), 4* robotSizeMultiplier, 1* robotSizeMultiplier);
        g2.setColor(Color.BLACK);
        g2.drawRect(0 * robotSizeMultiplier, 0 * robotSizeMultiplier, 27 * robotSizeMultiplier, 1 * robotSizeMultiplier);
        g2.drawRect(0 * robotSizeMultiplier, 1 * robotSizeMultiplier, 1 * robotSizeMultiplier, 30 * robotSizeMultiplier);
        g2.drawRect(0 * robotSizeMultiplier, 31 * robotSizeMultiplier, 27 * robotSizeMultiplier, 1 * robotSizeMultiplier);
        g2.drawRect(26 * robotSizeMultiplier,1 * robotSizeMultiplier,1 * robotSizeMultiplier,30 * robotSizeMultiplier);
        g2.drawRect(5 * robotSizeMultiplier, 1 * robotSizeMultiplier, 1 * robotSizeMultiplier, 30 * robotSizeMultiplier);
        g2.drawRect(21 * robotSizeMultiplier, 1 * robotSizeMultiplier, 1 * robotSizeMultiplier, 30 * robotSizeMultiplier);
        
        g2.fillRect(2 * robotSizeMultiplier, 3 * robotSizeMultiplier, 1 * robotSizeMultiplier, 14 * robotSizeMultiplier);
        g2.fillRect(3 * robotSizeMultiplier, 15 * robotSizeMultiplier, 1 * robotSizeMultiplier, 14 * robotSizeMultiplier);
        
        g2.fillRect(24 * robotSizeMultiplier, 3 * robotSizeMultiplier, 1 * robotSizeMultiplier, 14 * robotSizeMultiplier);
        g2.fillRect(23 * robotSizeMultiplier, 15 * robotSizeMultiplier, 1 * robotSizeMultiplier, 14 * robotSizeMultiplier);
        font = new Font("Verdana", Font.BOLD, 26);
        g2.setFont(font);
        g2.setColor(Color.RED);
        g2.drawString("Front", (int)(8.5 * robotSizeMultiplier), 4 * robotSizeMultiplier);
        g2.setColor(Color.GRAY);
        g2.fillRect(20 * robotSizeMultiplier, 19 * robotSizeMultiplier, 1 * robotSizeMultiplier, 1 * robotSizeMultiplier);
        g2.fillRect(20 * robotSizeMultiplier, 22 * robotSizeMultiplier, 1 * robotSizeMultiplier, 1 * robotSizeMultiplier);
        g2.fillRect(20 * robotSizeMultiplier, (int) (20.25 * robotSizeMultiplier), 1 * robotSizeMultiplier + (int) (Math.sin(Math.toRadians(armAngle)) * armLength * robotSizeMultiplier), (int) (1.5 * robotSizeMultiplier));
        double armExtendLength = 0;
        if (armExtend == 0) {
        	armExtendLength = 1 * Math.sin(Math.toRadians(armAngle));
        } else if (armExtend == 1) {
        	armExtendLength = 1 + armMaxExtend / 2 * Math.sin(Math.toRadians(armAngle));
        } else {
        	armExtendLength = 1 + armMaxExtend * Math.sin(Math.toRadians(armAngle));
        }
        g2.fillRect((int) (Math.sin(Math.toRadians(armAngle)) * armLength * robotSizeMultiplier) + 21 * robotSizeMultiplier, (int) (20.5 * robotSizeMultiplier), (int)(armExtendLength * robotSizeMultiplier), 1 * robotSizeMultiplier);
        if (objectExist) {
        	if (objectType == 1) { //recycle can
        		g2.setColor(Color.GREEN);
        		g2.fillOval((int)(27/2+objectX-10.0) * robotSizeMultiplier, (int)(-objectY-10)*robotSizeMultiplier, 20 * robotSizeMultiplier, 20*robotSizeMultiplier);
        	} else if (objectType == 2) {
        		g2.setColor(Color.YELLOW);
        		g2.rotate(Math.toRadians(objectRotation), (objectX + 27/2) * robotSizeMultiplier, -objectY * robotSizeMultiplier);
        		g2.fillRect((int)objectX * robotSizeMultiplier, (int)(-objectY - 17/2.0) * robotSizeMultiplier, 27* robotSizeMultiplier, 17 * robotSizeMultiplier);
        	}
        }
        } else {
        	AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) alpha2);
        	g2.setComposite(ac);
        	g2.drawImage(hint, 300, 100, 766, 480,null);
        }
        
    }
}