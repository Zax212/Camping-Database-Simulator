package package1;

import java.awt.HeadlessException;
import java.awt.event.*;
import java.text.ParseException;
import java.util.*;

import javax.swing.*;
/*****************************************************************
The graphical representation of the current and future residents of the
campground.

@author Zach Hopman
@version March 2015
*****************************************************************/
public class GUICampingReg extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	/**the menu bar for the GUI */
	private JMenuBar menus;
	/**file drop down menu */
	private JMenu fileMenu;
	/**check in drop down menu */
	private JMenu CheckInMenu;
	/**check out drop down menu */
	private JMenu CheckOutMenu;

	// fileMenu
	/**opens serizable save */
	private JMenuItem openSerItem;
	/**exits the program */
	private JMenuItem exitItem;
	/**saves current log as serizable file */
	private JMenuItem saveSerItem;
	/**opens text file save */
	private JMenuItem openTextItem;
	/**saves current log as text file */
	private JMenuItem saveTextItem;
	/**opens new window displaying info on current residents */
	private JMenuItem statusItem;
	/**sorts the campers by name*/
	private JMenuItem sort;

	// CheckInMenu
	/**opens window to check in a tent */
	private JMenuItem CheckInTent;
	/**opens a window to check in an rv */
	private JMenuItem CheckInRV;

	// CheckOutMenu
	/**checks out the currenttly selected camper */
	private JMenuItem CheckOut;
	/**the table to hold all information on campers */
	private JTable JTableArea;
	/**the generic model for a camper */
	private SiteModel model;
	/**the scroll pane for the JTable */
	private JScrollPane ScrollPane;

	/*****************************************************************
	*the Constructor used to create the GUI window. sets up the menubar
	*and the JTable.
	 ****************************************************************/
	private GUICampingReg() {
		// file menu
		fileMenu = new JMenu("File");
		openSerItem = new JMenuItem("Open Serial");
		saveSerItem = new JMenuItem("Save Serial");
		exitItem = new JMenuItem("Exit");
		openTextItem = new JMenuItem("Open Text");
		saveTextItem = new JMenuItem("Save Text");
		statusItem = new JMenuItem("Status");
		sort = new JMenuItem("Sort");

		fileMenu.add(openSerItem);
		fileMenu.add(saveSerItem);
		fileMenu.add(exitItem);
		fileMenu.add(openTextItem);
		fileMenu.add(saveTextItem);
		fileMenu.add(statusItem);
		fileMenu.add(sort);

		openSerItem.addActionListener(this);
		exitItem.addActionListener(this);
		saveSerItem.addActionListener(this);
		openTextItem.addActionListener(this);
		saveTextItem.addActionListener(this);
		statusItem.addActionListener(this);
		sort.addActionListener(this);
		
		// check in menu
		CheckInMenu = new JMenu("Check In");

		CheckInTent = new JMenuItem("Check in Tent");
		CheckInRV = new JMenuItem("Check in RV");

		CheckInMenu.add(CheckInTent);
		CheckInMenu.add(CheckInRV);

		// check out menu
		CheckOutMenu = new JMenu("Check Out");
		CheckOut = new JMenuItem("Check out");
		CheckOutMenu.add(CheckOut);

		CheckInTent.addActionListener(this);
		CheckInRV.addActionListener(this);
		CheckOut.addActionListener(this);

		menus = new JMenuBar();

		menus.add(fileMenu);
		menus.add(CheckInMenu);
		menus.add(CheckOutMenu);

		setJMenuBar(menus);

		// table
		model = new SiteModel();
		JTableArea = new JTable(model);
		ScrollPane = new JScrollPane(JTableArea);
		add(ScrollPane);

		setVisible(true);
		setSize(600, 400);

	}
	/*****************************************************************
	*the main method to be ran
	 ****************************************************************/
	public static void main(String[] args) {
		new GUICampingReg();
	}
	/*****************************************************************
	*the method for all actions. ie buttons and menuItems.
	*@param e the action
	 ****************************************************************/
	public void actionPerformed(ActionEvent e) {
		
		if (CheckOut == e.getSource()) {
			int index = JTableArea.getSelectedRow();
			
			
			if (index == -1)
				JOptionPane.showMessageDialog(this, "Empty");
			else{
				Site s = model.getSites(JTableArea.getSelectedRow());
				String CheckOutDate = JOptionPane.showInputDialog
						(this," mm/dd/yyyy","Enter the Date",1);
			
				
				try {
					JOptionPane.showMessageDialog
					(JTableArea,"You owe $"+ s.CheckOut(CheckOutDate));
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				model.removeSite(index);
			}

		}
		if (openSerItem == e.getSource()) {
			model.loadDatabase("testDB");
		}

		if (saveSerItem == e.getSource()) {
			model.saveDatabase("testDB");
		}
		if (openTextItem == e.getSource()) {
			model.loadFromText("testDBtext");
		}
		if (saveTextItem == e.getSource()) {
			model.saveAsText("testDBtext");
		}

		if (exitItem == e.getSource()) {
			System.exit(0);
		}
		if (CheckInTent == e.getSource()) {
			Tent t = new Tent();
			DialogCheckInTent x = new DialogCheckInTent(this, t);
			if (x.getCloseStatus() == x.OK) {
				if (model.isEmpty(t.getSiteNumber())) {
					model.addSite(t);
					JOptionPane.showMessageDialog(JTableArea,
							"You will owe: $"
							+ model.CheckIn());
				}
				else
					JOptionPane.showMessageDialog(JTableArea,"Site "
				+t.getSiteNumber()
							+" is taken.");
			}

		}

		if (CheckInRV == e.getSource()) {
			RV r = new RV();
			DialogCheckInRV x = new DialogCheckInRV(this, r);
			if (x.getCloseStatus() == x.OK) {
				if (model.isEmpty(r.getSiteNumber())) {
					model.addSite(r);
					JOptionPane.showMessageDialog(JTableArea,
							"You will owe: $"
							+ model.CheckIn());
				}
				else
					JOptionPane.showMessageDialog(JTableArea,"Site "+
				r.getSiteNumber()
							+" is taken.");

			}

			
		}
		//status
		if (statusItem == e.getSource()){
			
			String CheckOutDate = JOptionPane.showInputDialog
					(this," mm/dd/yyyy","Enter the Date",1);
			
			try {
				DialogGetDateStatus x = new DialogGetDateStatus
						(this, CheckOutDate, model);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			
		}
		if (sort == e.getSource()){
			model.Sort();
			repaint();
		}

	}

}
