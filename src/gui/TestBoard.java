package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class TestBoard extends JFrame{
	
	private JButton b1=new JButton("1"); 
	private JButton b2=new JButton("2"); 
	private JButton b3=new JButton("3"); 
	private JButton b4=new JButton("4"); 
	private JButton b5=new JButton("5"); 
	private JButton b6=new JButton("6"); 
	private JButton b7=new JButton("7"); 
	
  public TestBoard() throws IOException {  
	  
    super("4-connect");  
    setSize(553, 530);  
    JLayeredPane pane = getLayeredPane();  
    
    JPanel buttonPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    
    buttonPanel.add(b1);
    buttonPanel.add(b2);
    buttonPanel.add(b3);
    buttonPanel.add(b3);
    buttonPanel.add(b4);
    buttonPanel.add(b5);
    buttonPanel.add(b6);
    buttonPanel.add(b7);
    
    add(buttonPanel,BorderLayout.NORTH);
    buttonPanel.setLayout(new GridLayout(1,7));
    
    ImageIcon image =  new ImageIcon("C:\\Users\\Hp\\Downloads\\4-connectBoard (1)\\4-connectBoard\\src\\image\\board.png");
    JLabel label = new JLabel(image);
    boardPanel.add(label);
    
    add(boardPanel);  
      
  }

}
