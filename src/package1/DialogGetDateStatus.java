package package1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/*****************************************************************
The window that opens when checking status of campers.

@author Zach Hopman
@version March 2015
*****************************************************************/
public class DialogGetDateStatus extends JDialog {

	/**textfield for the name of camper */
	private JTextField name;
	/**textfield for the date checked in */
	private JTextField OccupyedOn;
	/**textfield for the number of days staying */
	private JTextField staying;
	/**textfield for the sitenumber */
	private JTextField siteNumber;

	/*****************************************************************
	*Constructor for the status menu. displays all campers in a list
	*and calculates the days remaining.
	*@param paOccupy the Jframe to add to.
	*@param date the checkOut date entered
	*@param model the JTable representation of the camper
	 ****************************************************************/
	public DialogGetDateStatus(JFrame paOccupy, String date,
			SiteModel model) throws ParseException{
		
		setTitle("Status");
		
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(8, 1));
		
		for(int i = 0; i<model.getRowCount(); i++){
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			String checkin = sdf.format((model.getSites(i).getCheckIn().
					getTime()));
			final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;
			int diffInDays = (int)((sdf.parse(date).getTime()-sdf.parse(
					checkin).getTime()) / DAY_IN_MILLIS);
			
			int dayremain = (int) model.getValueAt(i, 2) - diffInDays;
			
			textPanel.add(new JLabel(model.getValueAt(i, 0)+
					"  Checked in: "+model.getValueAt(i, 1)+
					"  Site #: "+model.getValueAt(i,3)+
					"  Estimated days: "+model.getValueAt(i, 2)+
					"  Days remaining: "+dayremain));
		}
		getContentPane().add(textPanel, BorderLayout.CENTER);
		
		setSize(500, 300);
		setVisible(true);
		
	}

		
		
		
	}
		



