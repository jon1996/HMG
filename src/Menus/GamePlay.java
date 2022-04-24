//Created by Terry Dorsey//
package Menus;

import gameLogic.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class GamePlay extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L; //recommended by eclipse
	
	char[] currentWordArray;
	char[] toBeBlankArray;
	
	int chance;
	String currentWord = GameLogic.getRandomWord();

	
	int failCounter = currentWord.length();
	int some = failCounter/2;
	JPanel entireGameBoard;
	JLabel wordToGuess;
	JLabel hangmanHolder;
	JButton butA, butB, butC, butD, butE, butF, butG, butH, butI, butJ,
	butK, butL, butM, butN, butO, butP, butQ, butR, butS, butT, butU, butV,
	butW, butX, butY, butZ, GuessB;
	static JTextField guessText;
	static JLabel remChance;
	//MENUBAR
	 
	JMenuBar mb;    
	JMenu file,option;    
	JMenuItem neew, save,load,exit,options;
	
	
	//FileChoser
	JFileChooser j = new JFileChooser();
	//I made these button definitions print out with a small program 
	//see buttonPrinter
	
	public GamePlay(int xLocation, int yLocation)
	{
		//setting up Listener
		ListenForKeyboard keyboardListener = new ListenForKeyboard();
		butA = new JButton("A");
		butB = new JButton("B");
		butC = new JButton("C");
		butD = new JButton("D");
		butE = new JButton("E");
		butF = new JButton("F");
		butG = new JButton("G");
		butH = new JButton("H");
		butI = new JButton("I");
		butJ = new JButton("J");
		butK = new JButton("K");
		butL = new JButton("L");
		butM = new JButton("M");
		butN = new JButton("N");
		butO = new JButton("O");
		butP = new JButton("P");
		butQ = new JButton("Q");
		butR = new JButton("R");
		butS = new JButton("S");
		butT = new JButton("T");
		butU = new JButton("U");
		butV = new JButton("V");
		butW = new JButton("W");
		butX = new JButton("X");
		butY = new JButton("Y");
		butZ = new JButton("Z");
		
		//Menu Bar
		
		ImageIcon newIcon = new ImageIcon("C:\\Users\\Jonathan\\eclipse-workspace\\AssignmentJava\\src\\image\\copy_edit.png");
		ImageIcon saveIcon = new ImageIcon("C:\\Users\\Jonathan\\eclipse-workspace\\AssignmentJava\\src\\image\\save_edit.png");
		ImageIcon loadIcon = new ImageIcon("C:\\Users\\Jonathan\\eclipse-workspace\\AssignmentJava\\src\\image\\paste_edit.png");
		ImageIcon exitIcon = new ImageIcon("C:\\Users\\Jonathan\\eclipse-workspace\\AssignmentJava\\src\\image\\delete_edit.png");
		ImageIcon optionIcon = new ImageIcon("C:\\Users\\Jonathan\\eclipse-workspace\\AssignmentJava\\src\\image\\cut_edit.png");
		
		
		neew = new JMenuItem("New Game",newIcon);
		save = new JMenuItem("Save Game",saveIcon);
		load = new JMenuItem("Load Game",loadIcon);
		exit = new JMenuItem("Exit",exitIcon);
		options = new JMenuItem("Options",optionIcon);
		mb = new JMenuBar();
		file = new JMenu("File");
		option = new JMenu("Options");
		file.add(neew); file.add(save);file.add(load);file.add(exit);
		option.add(options);
		mb.add(file);mb.add(option);
		this.add(mb);
		setJMenuBar(mb);
		neew.addActionListener(this);
		save.addActionListener(this);
		load.addActionListener(this);
		exit.addActionListener(this);
		options.addActionListener(this);
		
		
		
		this.setVisible(true);//setting up the window
		this.setSize(700,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(xLocation + 75, yLocation - 100); //set equal to other window and offset. I'm sure there's a better way
		this.setResizable(false);
		this.setTitle("Hangman Game");
		//Guess Field
		
		
		Font fontForGuess = new Font("Algerian", Font.PLAIN, 30);
		String currentWord = this.currentWord;
		
		
		//defining new JPanels
		entireGameBoard = new JPanel(); //field so I can call it on listener action
		JPanel keyboardHolder = new JPanel();
		JPanel gridForRows = new JPanel();
		JPanel topRow  = new JPanel();
		JPanel medRow = new JPanel();
		JPanel bottomRow = new JPanel();
		JPanel guessRow = new JPanel();
		
		//JLabels
		hangmanHolder = new JLabel();
		wordToGuess = new JLabel();
		
		
			
		//sets up layouts for JPanels
		gridForRows.setLayout(new GridLayout(4, 0, 3, 3)); //(rows, cols(0 means auto), xpadding, ypadding)
		topRow.setLayout(new FlowLayout());
		medRow.setLayout(new FlowLayout());
		bottomRow.setLayout(new FlowLayout());
		
		guessRow.setLayout(new FlowLayout());
		entireGameBoard.setLayout(new FlowLayout());
				
		//make an array that contains blanks the length of the currentWord
		//perhaps move this to GameLogic 
		toBeBlankArray = currentWord.toCharArray();
		for(int x = 0; x < toBeBlankArray.length; x++)
		{
			if(toBeBlankArray[x] != ' ')//ensures that spaces don't become underscores
			{
				toBeBlankArray[x] = '_';
			}
		}
		
		String wordToDisplay = new String(toBeBlankArray);//toBeBlankArray.toString(); wasn't working, so String Constructor
		
		wordToGuess.setText(wordToDisplay);
		wordToGuess.setFont(fontForGuess);                             
		
		//sets up keyboard
		topRow.add(butA);
		topRow.add(butB);
		topRow.add(butC);
		topRow.add(butD);
		topRow.add(butE);
		topRow.add(butF);
		topRow.add(butG);
		topRow.add(butH);
		topRow.add(butI);
		topRow.add(butJ);
		topRow.add(butK);
		topRow.add(butL);
		topRow.add(butM);

		medRow.add(butN);
		medRow.add(butO);
		medRow.add(butP);
		medRow.add(butQ);
		medRow.add(butR);
		medRow.add(butS);
		medRow.add(butT);
		medRow.add(butU);
		medRow.add(butV);
		medRow.add(butW);
		medRow.add(butX);		
		medRow.add(butY);		
		medRow.add(butZ);	
		
		
		guessText = new JTextField(20);
		bottomRow.add(guessText);
		GuessB = new JButton("Guess");
		bottomRow.add(GuessB);
		
		remChance = new JLabel("Remaining Chance:" + some);
		guessRow.add(remChance);
		//adding all my layouts together
		gridForRows.add(topRow);
		gridForRows.add(medRow);
		gridForRows.add(bottomRow);
		gridForRows.add(guessRow);
		keyboardHolder.add(gridForRows);
		entireGameBoard.add(hangmanHolder);
		entireGameBoard.add(wordToGuess);
		entireGameBoard.add(keyboardHolder);
		
		this.add(entireGameBoard);//adding everything to current obj
		
		
		
		GuessB.addActionListener(keyboardListener);
		//wrote this using my ButtonPrinter
		butA.addActionListener(keyboardListener);
		butB.addActionListener(keyboardListener);
		butC.addActionListener(keyboardListener);
		butD.addActionListener(keyboardListener);
		butE.addActionListener(keyboardListener);
		butF.addActionListener(keyboardListener);
		butG.addActionListener(keyboardListener);
		butH.addActionListener(keyboardListener);
		butI.addActionListener(keyboardListener);
		butJ.addActionListener(keyboardListener);
		butK.addActionListener(keyboardListener);
		butL.addActionListener(keyboardListener);
		butM.addActionListener(keyboardListener);
		butN.addActionListener(keyboardListener);
		butO.addActionListener(keyboardListener);
		butP.addActionListener(keyboardListener);
		butQ.addActionListener(keyboardListener);
		butR.addActionListener(keyboardListener);
		butS.addActionListener(keyboardListener);
		butT.addActionListener(keyboardListener);
		butU.addActionListener(keyboardListener);
		butV.addActionListener(keyboardListener);
		butW.addActionListener(keyboardListener);
		butX.addActionListener(keyboardListener);
		butY.addActionListener(keyboardListener);
		butZ.addActionListener(keyboardListener);	
		
		
		
	}//end of constructor
	
	private class ListenForKeyboard implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) //every time a Letter is tried, this is fired
		{	
			String stringToConvert = ((JButton) e.getSource()).getText();
			char charToSend = stringToConvert.charAt(0);

			//consider containing this logic inside method?
			boolean[] isArrayRight = GameLogic.checkArrayForMatches(currentWord, charToSend);
			
			String upperCaseCurretWord = currentWord.toUpperCase();//could def make this more efficient|I'm repeating myself: 							
			currentWordArray = upperCaseCurretWord.toCharArray();//see GameLogic for almost exact same logic|will do later
			
			//takes the blanks and fills in the letter if guess was correct
			for(int x = 0; x < isArrayRight.length; x++)
			{
				if(isArrayRight[x] == true)
				{
					toBeBlankArray[x] = currentWordArray[x];
				}
			}
			
			String wordToShow = new String(toBeBlankArray);//assigns result of prev loop to wordToShow
			wordToGuess.setText(wordToShow); //refreshes the JPanel holding the word to guess
			
			
			
			if((GameLogic.doesArrayContainATrue(isArrayRight)) == true)//if user guesses correctly
			{
				if((GameLogic.doesArrayContainUnderscores(toBeBlankArray)) == false)//user has won
				{
					String winPhrase = "You Won! The word was \'" + currentWord + "\'.";
					int xLocation = GamePlay.super.getX();
					int yLocation = GamePlay.super.getY();
					new GameOver(xLocation, yLocation, winPhrase);

					GamePlay.super.dispose();
				}
			}
			
			else if((GameLogic.doesArrayContainATrue(isArrayRight)) == false) //fires if they guessed wrong
			{
				
				
				System.out.println(currentWord.length());
				System.out.println(some);
				
				some--;
				remChance.setText("Remaining Chance:" + some);
				System.out.println(some);
				
				
				if(some == 0)//player loses
				{
					String losePhrase = "You Lost! The word was \'" + currentWord + "\'.";
					int xLocation = GamePlay.super.getX();
					int yLocation = GamePlay.super.getY();
					new GameOver(xLocation, yLocation, losePhrase);
					GamePlay.super.dispose();
					//System.out.println("You loose");
				}
				
				
			}
			
			String emailValue = guessText.getText();
			System.out.println(currentWord);
			if(e.getSource() == GuessB)
			{
				if(emailValue.equals(currentWord))
				{
					System.out.println( emailValue);
					String winPhrase = "You Won! The word was \'" + currentWord + "\'.";
					int xLocation = GamePlay.super.getX();
					int yLocation = GamePlay.super.getY();
					new GameOver(xLocation, yLocation, winPhrase);

					GamePlay.super.dispose();
				}
				else {
					String losePhrase = "You Lost! The word was \'" + currentWord + "\'.";
					int xLocation = GamePlay.super.getX();
					int yLocation = GamePlay.super.getY();
					new GameOver(xLocation, yLocation, losePhrase);
					GamePlay.super.dispose();
					
				}
			}
		
			
			String holderForButton = ((JButton) e.getSource()).getText();//is holder redundant? I think I made stringToConvert this way
			String buttonCalled = "but" + holderForButton;//reconstruct into a button's name.
			switch(buttonCalled)//printed all this using button printer
			{//this make buttons gray out once they are clicked
			case "butA":
				butA.setEnabled(false);
				
				break;
			case "butB":
				butB.setEnabled(false);
			
				break;
			case "butC":
				butC.setEnabled(false);
				
				break;
			case "butD":
				butD.setEnabled(false);
				
				break;
			case "butE":
				butE.setEnabled(false);
				break;
			case "butF":
				butF.setEnabled(false);
				break;
			case "butG":
				butG.setEnabled(false);
				break;
			case "butH":
				butH.setEnabled(false);
				break;
			case "butI":
				butI.setEnabled(false);
				break;
			case "butJ":
				butJ.setEnabled(false);
				break;
			case "butK":
				butK.setEnabled(false);
				break;
			case "butL":
				butL.setEnabled(false);
				break;
			case "butM":
				butM.setEnabled(false);
				break;
			case "butN":
				butN.setEnabled(false);
				break;
			case "butO":
				butO.setEnabled(false);
				break;
			case "butP":
				butP.setEnabled(false);
				break;
			case "butQ":
				butQ.setEnabled(false);
				break;
			case "butR":
				butR.setEnabled(false);
				break;
			case "butS":
				butS.setEnabled(false);
				break;
			case "butT":
				butT.setEnabled(false);
				break;
			case "butU":
				butU.setEnabled(false);
				break;
			case "butV":
				butV.setEnabled(false);
				break;
			case "butW":
				butW.setEnabled(false);
				break;
			case "butX":
				butX.setEnabled(false);
				break;
			case "butY":
				butY.setEnabled(false);
				break;
			case "butZ":
				butZ.setEnabled(false);
				break;
			}	
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==neew)    
		{
			GamePlay.super.dispose();
			int xLocation = GamePlay.super.getX();
			int yLocation = GamePlay.super.getY();
			new GamePlay(xLocation, yLocation);
			
		}
		if(e.getSource()==exit)    
		{
			
			int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to Exit the Game",null, JOptionPane.YES_NO_OPTION);
			if(n == JOptionPane.YES_OPTION)
			{
				GamePlay.super.dispose();
			}
		}
		if(e.getSource()==options)    
		{
		
		}
		if(e.getSource()==save)    
		{
			j.setDialogTitle("Save");
			 // create an object of JFileChooser class
            JFileChooser j = new JFileChooser();
            j.setAcceptAllFileFilterUsed(false);
 
            // only allow files of .txt extension
            FileNameExtensionFilter restrict = new FileNameExtensionFilter(".txt", "txt");
            j.addChoosableFileFilter(restrict);
 
            // invoke the showsSaveDialog function to show the save dialog
            int r = j.showSaveDialog(null);
 
            // if the user selects a file
            if (r == JFileChooser.APPROVE_OPTION)
 
            {
            	
            		FileOutputStream fos;
					try {
						fos = new FileOutputStream("save.txt");
					
            		BufferedOutputStream bos = new BufferedOutputStream(fos);
            		ObjectOutputStream oos = new ObjectOutputStream(bos);
            		
            		dataStorage dstorage = new dataStorage();
            		dstorage.currentWord = currentWord;
            		dstorage.some = some;
            		oos.writeObject(dstorage);
            		System.out.print(fos);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            }
            // if the user cancelled the operation
            else {
            	System.out.println("file not approved");
            }
		}
		if(e.getSource()==load)    
		{
			j.showSaveDialog(null);
		}
	}
	
	
	//Class to SaveFile
	 class FileFilterF extends FileFilter
	{
		private String FileFormat = "sav";
		private char DotIndex = '.';

		public boolean accept(File f) {
			// TODO Auto-generated method stub
			
			if(f.isDirectory())
			{
				return true;
			}
			if(extension(f).equalsIgnoreCase(FileFormat))
			{
				return true;
			}
			return false;
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return ".sav";
		}
		private String extension(File f) {
			// TODO Auto-generated method stub
			String FileName = f.getName();
			int indexFile = FileName.lastIndexOf(DotIndex);
			if(indexFile > 0 && indexFile < FileName.length() -1)
			{
				return FileName.substring(indexFile+1);
			}
			else {
				return null;
			}
			
			
		}
		
		void optionMessage()
		{
			JButton Cancel = new JButton("Change");
			JTextField email = new JTextField(20);
		}

		
	}
}