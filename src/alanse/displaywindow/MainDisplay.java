package alanse.displaywindow;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
//this connects all other display, such as editor,memoryWindow, RegisterWindow
public class MainDisplay extends JPanel{
	
	public MemoryWindow memWindow;
	public RegisterWindow regWindow;
	public EditorWindow editorWindow;

	private GridBagConstraints gridLayoutManager;
	public MainDisplay()
	{
		gridLayoutManager=new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		memWindow=new MemoryWindow();
		editorWindow=new EditorWindow();
		regWindow=new RegisterWindow();

		
		this.addWindow(memWindow,0,0);
		this.addWindow(editorWindow,1,0);
		this.addWindow(regWindow, 2,0);
	}
	public void addWindow(Component win,int gridX,int gridY)
	{
		gridLayoutManager.gridheight=1;
		gridLayoutManager.gridwidth=1;
		gridLayoutManager.gridx=gridX;
		gridLayoutManager.gridy=gridY;
		this.add(win,gridLayoutManager);
	}
	public void addWindow(Component win,int gridX,int gridY,int gridWidth,int gridHeight)
	{
		gridLayoutManager.gridheight=gridHeight;
		gridLayoutManager.gridwidth=gridWidth;
		gridLayoutManager.gridx=gridX;
		gridLayoutManager.gridy=gridY;
		this.add(win,gridLayoutManager);
	}
}
