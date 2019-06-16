package Coding_Exam_A;

import java.awt.Color;


import javax.swing.JOptionPane;

import org.jointheleague.graphical.robot.Robot;

public class CodingExamA {
	public static void main(String[] args) {
		String s = JOptionPane.showInputDialog("Give me number of robots");
		String s2 =JOptionPane.showInputDialog("Give me a color red, blue, or green");
		String s3 = JOptionPane.showInputDialog("Give me how many sides each shape will have");
		/*
		 * Write a program that asks the user for three pieces of information.
		 * 1. How many robots
		 * 2. The color of the shapes
		 * 3. How many sides each shape will have
		 * 
		 * Once the information has been collected, the program will then make the requested number of robots
		 * each draw the requested shape in the requested color. The robots should execute at the same time so 
		 * Threads will need to be used. Arrange the robots so that the shapes do not overlap.
		 * For full credit, define the Thread functions using lambdas.
		 * 
		 * See the Coding_Exam_A_Demo.jar for an example of what the finished product should look like.
		 */
		Color c;
		s2 = s2.toLowerCase();
		if(s2.equals("red")) {
			c = new Color(255,0,0);
		} else if(s2.equals("blue")) {
			c = new Color(0,255,0);
		} else if(s2.equals("green")) {
			c = new Color(0,0,255);
		} else {
			c = Color.black;
		}
			
		int sides = Integer.parseInt(s3);
		int num = Integer.parseInt(s);
		Thread[] threadArr = new Thread[num];
		for(int i = 0;i<num;i++) {
			Robot rob = new Robot();
			rob.setSpeed(50);
			rob.penDown();
			rob.setPenColor(c);
			rob.setX(i*200);
			threadArr[i] = new Thread(()->{
				for(int n = 0;n<sides;n++) {
					rob.move(300/sides);
					rob.turn(360/sides);
				}
			});
		}
		for(Thread t:threadArr) {
			t.start();
		}
		for(Thread t:threadArr) {
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
