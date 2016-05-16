package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;

import actions.ImportAction;
import actions.NewKeystoreAction;
import actions.Akcija2;
import actions.Akcija3;
import actions.OpenExportFormAction;


public class MainFrame extends JFrame {

	private static MainFrame frame = null;
	private JMenuBar menuBar;
	private MyToolBar toolbar;

	private MainFrame() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setTitle("Bezbednost");
		int width = (int) (screen.getWidth() * 0.5);
		int height = (int) (screen.getHeight() * 0.5);
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		Menu();
		this.setJMenuBar(menuBar);
		toolbar = new MyToolBar(null);
		add(toolbar,BorderLayout.NORTH);
		initTable();

	}

	public static MainFrame getInstance() {
		if(frame == null) {
			frame = new MainFrame();
		}
		return frame;
	}

	private void Menu(){
		menuBar = new JMenuBar();

		JMenu file = new JMenu("File");
		JMenu tools = new JMenu("Tools");
		JMenu ex = new JMenu("Examine");
		JMenu help = new JMenu("Help");

		JMenuItem ks = new JMenuItem(new NewKeystoreAction(null));
		file.add(ks);
		JMenuItem imp = new JMenuItem(new ImportAction(null));
		file.add(imp);
		JMenuItem exp = new JMenuItem(new OpenExportFormAction());
		file.add(exp);
		JMenuItem t = new JMenuItem(new Akcija2(null));
		tools.add(t);
		JMenuItem e = new JMenuItem(new Akcija3(null));
		ex.add(e);


		menuBar.add(file);
		menuBar.add(tools);
		menuBar.add(ex);
		menuBar.add(help);


	}

	public void initTable(){
		
		
		Vector rowData = new Vector();
		
		Calendar today = Calendar.getInstance();
		today.setTime(new Date());
		Date date = today.getTime();
		
		System.out.print(date);
		
//		if(alias!=null)
	    for (int i = 0; i < 1; i++) {
	      Vector colData = new Vector(Arrays.asList("qq","qq"));
	      rowData.add(colData);
	    }
	    
	    String[] columnNames = {"Alias name","Last Modified"};
	    
	    Vector columnNamesV = new Vector(Arrays.asList(columnNames));

	    JTable table = new JTable(rowData, columnNamesV);
	    
	  //Dozvoljeno selektovanje redova
	    table.setRowSelectionAllowed(true);
	  //Ali ne i selektovanje kolona 
	    table.setColumnSelectionAllowed(false);
	    
	    JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);


	}
}
