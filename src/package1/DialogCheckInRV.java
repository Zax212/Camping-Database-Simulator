package package1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.*;
import java.util.*;

import javax.swing.*;
/*****************************************************************
The window that opens when checking in a RV

@author Zach Hopman
@version March 2015
*****************************************************************/
public class DialogCheckInRV extends JDialog implements ActionListener{

	/**textfield for the name of camper */
	private JTextField name;
	/**textfield for the date  */
	private JTextField OccupyedOn;
	/**textfield for expected number of days staying */
	private JTextField staying;
	/**textfield for the sitenumber */
	private JTextField siteNumber;
	/**textfield for the amount of power needed */
	private JTextField Power;
	/**button to add the camper */
	private JButton okButton;
	/**button to cancel the check in */
	private JButton cancelButton;
	/**int that holds either 1 or 0 to determine if window is open */
	private int closeStatus;
	/**the site being checked in */
	private Site unit;

	public static final int OK = 0;
	public static final int CANCEL = 1;

	/*****************************************************************
	*The constructor to set up and display the window for checking in
	*a RV
	*@param paOccupy the Jframe
	*@param d the site being added
	 ****************************************************************/
	public DialogCheckInRV(JFrame paOccupy, Site d) {
		super(paOccupy, true);

		setTitle("Check In RV");
		closeStatus = CANCEL;
		setSize(400, 200);

		unit = d;

		// prevent user from closing window
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		// instantiate and display text fields

		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(6, 2));

		textPanel.add(new JLabel("Name:"));
		name = new JTextField("John Doe", 30);
		textPanel.add(name);

		textPanel.add(new JLabel("Amount of Power: "));
		Power = new JTextField("30", 30);
		textPanel.add(Power);

		textPanel.add(new JLabel("Site Number: "));
		siteNumber = new JTextField("2", 30);
		textPanel.add(siteNumber);

		textPanel.add(new JLabel("Ocupied on Date: "));
		OccupyedOn = new JTextField("03/18/2015", 30);
		textPanel.add(OccupyedOn);

		textPanel.add(new JLabel("Days Planning to Stay: "));
		staying = new JTextField("1", 30);
		textPanel.add(staying);

		getContentPane().add(textPanel, BorderLayout.CENTER);

		// Instantiate and display two buttons
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);

		setSize(300, 300);
		setVisible(true);

	}
	/*****************************************************************
	*the method for all actions. ie buttons and menuItems.
	*@param e the action
	 ****************************************************************/
	public void actionPerformed(ActionEvent e) {

		JButton button = (JButton) e.getSource();

		// if OK clicked the fill the object
		if (button == okButton) {
			closeStatus = OK;

			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			Date d;
			try {
				d = df.parse(OccupyedOn.getText());

				GregorianCalendar opened = new GregorianCalendar();
				opened.setTime(d);

				int DayStay = Integer.parseInt(staying.getText());
				int pow = Integer.parseInt(Power.getText());
				int sitenum = Integer.parseInt(siteNumber.getText());

				unit.setNameReserving(name.getText());
				unit.setCheckIn(opened);
				unit.setDaysStaying(DayStay);
				unit.setSiteNumber(sitenum);
				((RV) unit).setPower(pow);

			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}

		// make the dialog disappear
		dispose();
	}

	public int getCloseStatus() {
		return closeStatus;
	}
}