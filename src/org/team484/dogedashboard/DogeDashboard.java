package org.team484.dogedashboard;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import edu.wpi.first.wpilibj.networktables.*;

public class DogeDashboard {
	static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	boolean[] motorStatus = new boolean[12];
	public static int selectedAutonomous = 0;
	public static int image = 1;
	public static double opacity = 0;
		public static void main(String[] args) throws InterruptedException {
		System.out.println("Starting Up");
		new DogeDashboard().run();
	}
		public void run() throws InterruptedException {
			NetworkTable.setClientMode();
			NetworkTable.setTeam(484);
			NetworkTable.setIPAddress("roboRIO-484.local");
			NetworkTable dash = NetworkTable.getTable("SmartDashboard");
			JFrame window = new JFrame();
			window.revalidate();
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setTitle("Dashboard");
			window.setResizable(false);
			window.setName("Dashboard");
			window.setBounds(0, 0, 1366, 725);
			MenuBar menuBar = new MenuBar();
			Menu menuAuto = new Menu("Set Auto");
			menuBar.add(menuAuto);
			MenuItem auto0 = new MenuItem("(0) Do Nothing");
			menuAuto.add(auto0);
			MenuItem auto1 = new MenuItem("(1) Backup to Auto");
			menuAuto.add(auto1);
			MenuItem auto2 = new MenuItem("(2) Staging-Zone Tote to Auto");
			menuAuto.add(auto2);
			MenuItem auto3 = new MenuItem("(3) Staging-Zone Can to Auto");
			menuAuto.add(auto3);
			MenuItem auto4 = new MenuItem("(4) Staging-Zone Can&Tote to Auto");
			menuAuto.add(auto4);
			MenuItem auto5 = new MenuItem("(5) Landfill Can To Auto");
			menuAuto.add(auto5);
			window.setMenuBar(menuBar);
			auto0.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					selectedAutonomous = 0;
					image = 0;
					opacity = 0;
					
				}
			});
			auto1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					selectedAutonomous = 1;
					image = 1;
					opacity = 4;
					
				}
			});
			auto2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					selectedAutonomous = 2;
					image = 2;
					opacity = 4;
					
				}
			});
			auto3.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					selectedAutonomous = 3;
					image = 3;
					opacity = 4;
					
				}
			});
			auto4.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					selectedAutonomous = 4;
					image = 4;
					opacity = 4;
					
				}
			});
			auto5.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					selectedAutonomous = 5;
					image = 5;
					opacity = 4;
					
				}
			});
			double gyro = 0.0;
			double objectX = 0.0;
			double objectY = 0.0;
			double objectRotation = 0.0;
			int objectType = 0;
			boolean objectExist = false;
			double robotSpeed = 0.0;
			double armAngle = 0.0;
			double armExtend = 0.0;
			double amperage = 0.0;
			int setAutonomous = 0;
			boolean isDisabled = false;
			while(true) {
				if (opacity > 0) {
					opacity = opacity - 0.05;
				} else {
					opacity = 0.0;
				}
				dash.putNumber("selectedAutonomous", selectedAutonomous);
				motorStatus[0] = dash.getBoolean("frontLeftError", motorStatus[0]);
				motorStatus[1] = dash.getBoolean("rearLeftError", motorStatus[1]);
				motorStatus[2] = dash.getBoolean("frontRightError", motorStatus[2]);
				motorStatus[3] = dash.getBoolean("rearRightError", motorStatus[3]);
				motorStatus[4] = dash.getBoolean("frontLeftCurrent", motorStatus[4]);
				motorStatus[5] = dash.getBoolean("rearLeftCurrent", motorStatus[5]);
				motorStatus[6] = dash.getBoolean("frontRightCurrent", motorStatus[6]);
				motorStatus[7] = dash.getBoolean("rearRightCurrent", motorStatus[7]);
				motorStatus[8] = dash.getBoolean("totesCurrent", motorStatus[8]);
				motorStatus[9] = dash.getBoolean("armCurrent", motorStatus[9]);
				motorStatus[10] = dash.getBoolean("armExtensionCurrent", motorStatus[10]);
				motorStatus[11] = dash.getBoolean("gyroError", motorStatus[11]);
				gyro = dash.getNumber("gyro", gyro);
				objectX = dash.getNumber("objectX", objectX);
				objectY = dash.getNumber("objectY", objectY);
				objectRotation = dash.getNumber("objectRotation", objectRotation);
				objectType = (int) dash.getNumber("objectType", objectType);
				objectExist = false;//dash.getBoolean("objectExist", objectExist);
				robotSpeed = dash.getNumber("robotSpeed",robotSpeed);
				armAngle = dash.getNumber("armAngle", armAngle);
				armExtend = dash.getNumber("armExtend", armExtend);
				amperage = dash.getNumber("amperage", amperage);
				setAutonomous = (int) dash.getNumber("setAutonomous", setAutonomous);
				isDisabled = dash.getBoolean("isDisabled", isDisabled);
				window.getContentPane().removeAll();
				window.getContentPane().add(new DSWindow(motorStatus,gyro, objectX, objectY, objectRotation, objectType, objectExist, robotSpeed,armAngle,armExtend, amperage, setAutonomous, isDisabled, (float) opacity, image));
				window.setVisible(true);
				Thread.sleep(100);
			}
		}

}
