package alanse.displaywindow;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.ColorUIResource;

public class GlueDisplayWindowFrame extends JFrame implements ActionListener,DocumentListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar menu;
	private JMenuItem copy, paste, cut;
	public boolean changed = false;
	private File file;

	private EditorWindow editorWindow;
	private RegisterWindow regWindow;
	private MemoryWindow memWindow;
	
	private GridBagConstraints gbc;
	public GlueDisplayWindowFrame(String title)
	{
		gbc=new GridBagConstraints();
		editorWindow=new EditorWindow();
		regWindow=new RegisterWindow();
		memWindow=new MemoryWindow();
		menu = new JMenuBar();
		
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout());
		setJMenuBar(menu);
		buildMenu();
		this.setBounds(new Rectangle(100,100,600,600));
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		setupDisplay(memWindow,0,0,0,0);
		setupDisplay(editorWindow,1,0,1,1);
		setupDisplay(regWindow,2,0,0,0);
		super.setVisible(true);
	
	}
	private void setupDisplay(Component window,int gridX,int gridY,int weightX,int weightY)
	{
		gbc.gridx=gridX;
		gbc.gridy=gridY;
		gbc.weightx=weightX;
		gbc.weighty=weightY;
		add(window);
	}
	private void buildMenu() {
		buildFileMenu();
		buildEditMenu();
	}

	private void buildFileMenu() {
		JMenu file = new JMenu("File");
		file.setMnemonic('F');
		menu.add(file);
		JMenuItem n = new JMenuItem("New");
		n.setMnemonic('N');
		n.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		n.addActionListener(this);
		file.add(n);
		JMenuItem open = new JMenuItem("Open");
		file.add(open);
		open.addActionListener(this);
		open.setMnemonic('O');
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		JMenuItem save = new JMenuItem("Save");
		file.add(save);
		save.setMnemonic('S');
		save.addActionListener(this);
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		JMenuItem saveas = new JMenuItem("Save as...");
		saveas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		file.add(saveas);
		saveas.addActionListener(this);
		JMenuItem quit = new JMenuItem("Quit");
		file.add(quit);
		quit.addActionListener(this);
		quit.setMnemonic('Q');
		quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
	}

	private void buildEditMenu() {
		JMenu edit = new JMenu("Edit");
		menu.add(edit);
		edit.setMnemonic('E');
		// cut
		cut = new JMenuItem("Cut");
		cut.addActionListener(this);
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
		cut.setMnemonic('T');
		edit.add(cut);
		// copy
		copy = new JMenuItem("Copy");
		copy.addActionListener(this);
		copy.setMnemonic('C');
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
		edit.add(copy);
		// paste
		paste = new JMenuItem("Paste");
		paste.setMnemonic('P');
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
		edit.add(paste);
		paste.addActionListener(this);
		// find
		JMenuItem find = new JMenuItem("Find");
		find.setMnemonic('F');
		find.addActionListener(this);
		edit.add(find);
		find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
		// select all
		JMenuItem sall = new JMenuItem("Select All");
		sall.setMnemonic('A');
		sall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
		sall.addActionListener(this);
		edit.add(sall);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String action = arg0.getActionCommand();
		if (action.equals("Quit")) {
			System.exit(0);
		} else if (action.equals("Open")) {
//			splitLines();
			loadFile();
		} else if (action.equals("Save")) {
			saveFile();
		} else if (action.equals("New")) {
			newFile();
		} else if (action.equals("Save as...")) {
			saveAs("Save as...");
		} else if (action.equals("Select All")) {
			editorWindow.textPane.selectAll();
		} else if (action.equals("Copy")) {
			editorWindow.textPane.copy();
		} else if (action.equals("Cut")) {
			editorWindow.textPane.cut();
		} else if (action.equals("Paste")) {
			editorWindow.textPane.paste();
		}
		/*
		 else { 
			FindDialog find = new FindDialog(this, true);
			find.showDialog();
		}
		*/
	}
	private void newFile() {
		if (changed)
			saveFile();
		file = null;
		editorWindow.textPane.setText("");
		changed = false;
		//setTitle("Editor");
	}
	private void loadFile() {
		JFileChooser dialog = new JFileChooser(System.getProperty("user.home"));
		dialog.setMultiSelectionEnabled(false);
		try {
			int result = dialog.showOpenDialog(this);
			if (result == JFileChooser.CANCEL_OPTION)
				return;
			if (result == JFileChooser.APPROVE_OPTION) {
				if (changed)
					saveFile();
				file = dialog.getSelectedFile();
				editorWindow.textPane.setText(readFile(file));
				changed = false;
				//setTitle("Editor - " + file.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private String readFile(File file) {
		StringBuilder result = new StringBuilder();
		try (	FileReader fr = new FileReader(file);		
				BufferedReader reader = new BufferedReader(fr);) {
			String line;
			while ((line = reader.readLine()) != null) {
				result.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Cannot read file !", "Error !", JOptionPane.ERROR_MESSAGE);
		}
		return result.toString();
	}

	private void saveFile() {
		if (changed) {
			int ans = JOptionPane.showConfirmDialog(null, "The file has changed. You want to save it?", "Save file",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (ans == JOptionPane.NO_OPTION)
				return;
		}
		if (file == null) {
			saveAs("Save");
			return;
		}
		String text = editorWindow. textPane.getText();
		try (PrintWriter writer = new PrintWriter(file);){
			if (!file.canWrite())
				throw new Exception("Cannot write file!");
			writer.write(text);
			changed = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void saveAs(String dialogTitle) {
		JFileChooser dialog = new JFileChooser(System.getProperty("user.home"));
		dialog.setDialogTitle(dialogTitle);
		int result = dialog.showSaveDialog(this);
		if (result != JFileChooser.APPROVE_OPTION)
			return;
		file = dialog.getSelectedFile();
		try (PrintWriter writer = new PrintWriter(file);){
			writer.write(editorWindow.textPane.getText());
			changed = false;
			//setTitle("Editor - " + file.getName());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		changed = true;
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		changed = true;
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		changed = true;
	}
	

}
