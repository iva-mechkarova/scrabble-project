package ketspoon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;

/*This class is just a rough idea on how we plan on our classes working.
 * This is just for testing purposes.
 * From doing this we can see we will not need to check letters in the frame as 
 * a player will only be able to remove letters displayed
 * We plan on buttons being made variables in the future and the player and frame will not need to be passed
 * */

public class TestDisplay {
	int xAxis;
	int yAxis;
	
	Scanner input = new Scanner(System.in);
	
	public void endGame() {
		JFrame newFrame=new JFrame("End Game");
		JButton endGame=new JButton("End Game");
		
		endGame.setBounds(0,0,100,50);
	    endGame.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e) {
	    		System.exit(0);
	    	}  
	    });
	    
	    newFrame.setSize(50,100);  
	    newFrame.setLayout(null);  
	    newFrame.setVisible(true);
	    newFrame.add(endGame);
	}
	
	public void displayFrame (Frame testFrame,Player player,Board board) {
		
		ArrayList<Tile> tempRemove = new ArrayList<>();
		JFrame newFrame=new JFrame("Frame");  
	    JButton frameIndex0=new JButton(""+testFrame.getLettersInFrame().get(0).getTileLetter());
	    JButton frameIndex1=new JButton(""+testFrame.getLettersInFrame().get(1).getTileLetter()); 
	    JButton frameIndex2=new JButton(""+testFrame.getLettersInFrame().get(2).getTileLetter()); 
	    JButton frameIndex3=new JButton(""+testFrame.getLettersInFrame().get(3).getTileLetter()); 
	    JButton frameIndex4=new JButton(""+testFrame.getLettersInFrame().get(4).getTileLetter()); 
	    JButton frameIndex5=new JButton(""+testFrame.getLettersInFrame().get(5).getTileLetter()); 
	    JButton frameIndex6=new JButton(""+testFrame.getLettersInFrame().get(6).getTileLetter());
	    JButton endTurn=new JButton("End Turn");
	    
	    frameIndex0.setBounds(0,0,50,50);  
	    frameIndex1.setBounds(51,0,50,50); 
	    frameIndex2.setBounds(102,0,50,50); 
	    frameIndex3.setBounds(153,0,50,50); 
	    frameIndex4.setBounds(204,0,50,50); 
	    frameIndex5.setBounds(255,0,50,50); 
	    frameIndex6.setBounds(306,0,50,50); 
	    endTurn.setBounds(362,0,120,50);
	    
	    frameIndex0.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e) {
	    		Tile tileIndex0 = testFrame.getLettersInFrame().get(0);
	    		frameIndex0.setText("");
	    		frameIndex0.setEnabled(false);
	    		tempRemove.add(tileIndex0);
	    		System.out.print("Enter x:");xAxis=input.nextInt();
	    		System.out.print("Enter y:");yAxis=input.nextInt();
	    		board.addTileToSquare(xAxis, yAxis, tileIndex0 );
	    		board.displayBoard();
	    	}  
	    });
	    
	    frameIndex1.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e) {
	    		Tile tileIndex1 = testFrame.getLettersInFrame().get(1);
	    		frameIndex1.setText("");
	    		frameIndex1.setEnabled(false);
	    		tempRemove.add(tileIndex1);
	    		System.out.print("Enter x:");xAxis=input.nextInt();
	    		System.out.print("Enter y:");yAxis=input.nextInt();
	    		board.addTileToSquare(xAxis, yAxis, tileIndex1 );
	    		board.displayBoard();
	    	}  
	    });
	    
	    frameIndex2.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e) {
	    		Tile tileIndex2 = testFrame.getLettersInFrame().get(2);
	    		frameIndex2.setText("");
	    		frameIndex2.setEnabled(false);
	    		tempRemove.add(tileIndex2);
	    		System.out.print("Enter x:");xAxis=input.nextInt();
	    		System.out.print("Enter y:");yAxis=input.nextInt();
	    		board.addTileToSquare(xAxis, yAxis, tileIndex2 );
	    		board.displayBoard();
	    	}  
	    });
	    
	    frameIndex3.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e) {
	    		Tile tileIndex3 = testFrame.getLettersInFrame().get(3);
	    		frameIndex3.setText("");
	    		frameIndex3.setEnabled(false);
	    		tempRemove.add(tileIndex3);
	    		System.out.print("Enter x:");xAxis=input.nextInt();
	    		System.out.print("Enter y:");yAxis=input.nextInt();
	    		board.addTileToSquare(xAxis, yAxis, tileIndex3 );
	    		board.displayBoard();
	    	}  
	    });
	    
	    frameIndex4.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e) {
	    		Tile tileIndex4 = testFrame.getLettersInFrame().get(4);
	    		frameIndex4.setText("");
	    		frameIndex4.setEnabled(false);
	    		tempRemove.add(tileIndex4);
	    		System.out.print("Enter x:");xAxis=input.nextInt();
	    		System.out.print("Enter y:");yAxis=input.nextInt();
	    		board.addTileToSquare(xAxis, yAxis, tileIndex4 );
	    		board.displayBoard();
	    	}  
	    });
	    
	    frameIndex5.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e) {
	    		Tile tileIndex5 = testFrame.getLettersInFrame().get(5);
	    		frameIndex5.setText("");
	    		frameIndex5.setEnabled(false);
	    		tempRemove.add(tileIndex5);
	    		System.out.print("Enter x:");xAxis=input.nextInt();
	    		System.out.print("Enter y:");yAxis=input.nextInt();
	    		board.addTileToSquare(xAxis, yAxis, tileIndex5 );
	    		board.displayBoard();
	    	}
	    });
	    
	    frameIndex6.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e) {
	    		Tile tileIndex6 = testFrame.getLettersInFrame().get(6);
	    		frameIndex6.setText("");
	    		frameIndex6.setEnabled(false);
	    		tempRemove.add(tileIndex6);
	    		System.out.print("Enter x:");xAxis=input.nextInt();
	    		System.out.print("Enter y:");yAxis=input.nextInt();
	    		board.addTileToSquare(xAxis, yAxis, tileIndex6 );
	    		board.displayBoard();
	    	}  
	    });
	    
	    endTurn.addActionListener(new ActionListener(){ 
	    	int turnScore=0;
	    	public void actionPerformed(ActionEvent e) {
	    		for (int i = 0; i < tempRemove.size(); i++) {
	    			System.out.println("Removed:"+testFrame.removeTile(tempRemove.get(i)));
	    			turnScore+=tempRemove.get(i).getTileValue();
				}
	    		if(testFrame.frameIsEmpty()) {
	    			turnScore+=50;
	    		}
	    		player.calculateScore(turnScore);
	    		System.out.println(player.toString());
	    		newFrame.setVisible(false);
	    	}  
	    });
	    
	    newFrame.add(frameIndex0);
	    newFrame.add(frameIndex1);
	    newFrame.add(frameIndex2);
	    newFrame.add(frameIndex3);
	    newFrame.add(frameIndex4);
	    newFrame.add(frameIndex5);
	    newFrame.add(frameIndex6);
	    newFrame.add(endTurn);
	    newFrame.setSize(500,100);  
	    newFrame.setLayout(null);  
	    newFrame.setVisible(true);
	}
}
