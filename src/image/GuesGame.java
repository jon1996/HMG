package image;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class GuesGame implements Runnable,ActionListener {
    private JFrame frame;
    JMenuBar mb;
	JMenu File,Options;
	JMenuItem neew,save,load,exit,option;
	ImageIcon newIcon;
	ImageIcon saveIcon;
	ImageIcon loadIcon;
	ImageIcon exitIcon;
	ImageIcon optionIcon;

    public void run() {
        showGui();
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new GridLayout(3, 1));
        bottomPanel.add(createKeyboardPanel());
        bottomPanel.add(createSubmitPanel());
        return bottomPanel;
    }

    private JPanel createKeyboardPanel() {
        JPanel keyboardPanel = new JPanel(new GridLayout(2, 13));
        for (char c = 'a'; c <= 'z'; c++) {
            JButton button = new JButton(String.valueOf(c));
            keyboardPanel.add(button);
        }
        return keyboardPanel;
    }

    private JPanel createSubmitPanel() {
        JPanel submitPanel = new JPanel();
        JTextField txtFld = new JTextField(30);
        submitPanel.add(txtFld);
        JButton submitButton = new JButton("Guess");
        submitButton.setPreferredSize(new Dimension(300, 20));
        submitPanel.add(submitButton);
        return submitPanel;
    }

    private void showGui() {
        frame = new JFrame("Hangman Game");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(new Display(), BorderLayout.CENTER);
        frame.add(createBottomPanel(), BorderLayout.PAGE_END);
        frame.pack();
        newIcon = new ImageIcon("C:\\Users\\Jonathan\\eclipse-workspace\\AssignmentJava\\src\\image\\copy_edit.png");
		saveIcon = new ImageIcon("C:\\Users\\Jonathan\\eclipse-workspace\\AssignmentJava\\src\\image\\save_edit.png");
		loadIcon = new ImageIcon("C:\\Users\\Jonathan\\eclipse-workspace\\AssignmentJava\\src\\image\\paste_edit.png");
		exitIcon = new ImageIcon("C:\\Users\\Jonathan\\eclipse-workspace\\AssignmentJava\\src\\image\\delete_edit.png");
		optionIcon = new ImageIcon("C:\\Users\\Jonathan\\eclipse-workspace\\AssignmentJava\\src\\image\\cut_edit.png");
		//Menu Item
		neew = new JMenuItem("New Game",newIcon);
		save = new JMenuItem("Save Game",saveIcon);
		load = new JMenuItem("Load Game",loadIcon);
		exit = new JMenuItem("Exit",exitIcon);
		option = new JMenuItem("Options",optionIcon);
		
		neew.addActionListener(this);
		save.addActionListener(this);
		load.addActionListener(this);
		exit.addActionListener(this);
		option.addActionListener(this);
		mb = new JMenuBar();
		File = new JMenu("File");
		Options = new JMenu("Options");
		File.add(neew); File.add(save);File.add(load);File.add(exit);
		Options.add(option);
		mb.add(File);mb.add(Options);
		frame.add(mb);
		frame.add(createKeyboardPanel(), BorderLayout.PAGE_END);
		frame.setJMenuBar(mb);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new GuesGame());
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

class Display extends JPanel {
    private String message;

    Display() {
        message = "Starting game";
        setPreferredSize(new Dimension(620, 420));
        setBackground(new Color(250, 230, 180));
        setFont(new Font("Serif", Font.BOLD, 20));
    }

   
}