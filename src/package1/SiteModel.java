package package1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.swing.table.AbstractTableModel;
/*****************************************************************
The generic model for the Jtable to utilize to display information in
the GUI.

@author Zach Hopman
@version March 2015
*****************************************************************/
public class SiteModel extends AbstractTableModel {

	/**arraylist of all the campers */
	private ArrayList<Site> listSites;
	/**array of strings with all the coulumn names for the table */
	private String[] columnNames = { "Name Reserving", "Checked in",
			"Days Staying", "Site #", "Tent/RV info" };
	/*****************************************************************
	*the defualt constructor for the siteModel
	 ****************************************************************/
	public SiteModel() {
		super();
		listSites = new ArrayList<Site>();
	}
	/*****************************************************************
	*checks in the camper and calls the checkIn method from the site
	*@return the amount owed
	 ****************************************************************/
	public double CheckIn() {
		return getSites(listSites.size() - 1).CheckIn();
	}
	/*****************************************************************
	*sorts the JTable based on the name 
	 ****************************************************************/
	public ArrayList<Site> Sort(){
		Collections.sort(listSites, new Comparator<Site>(){

			@Override
			public int compare(Site site1, Site site2) {

				return site1.getNameReserving().compareTo
						(site2.getNameReserving());
			}
		});
			 
		return listSites;
	}
	/*****************************************************************
	*checks if the site is empty
	*@return true if empty otherwise false
	 ****************************************************************/
	public boolean isEmpty(int site) {
		for (int i = 0; i < listSites.size(); i++) {
			if (getSites(i).getSiteNumber() == site)
				return false;
		}
		return true;
	}
	/*****************************************************************
	*removes the site
	*@param i the site number
	 ****************************************************************/
	public void removeSite(int i) {
		listSites.remove(i);
		fireTableRowsDeleted(0, listSites.size());
	}
	/*****************************************************************
	*adds the site
	*@param the site number
	 ****************************************************************/
	public void addSite(Site a) {
		listSites.add(a);
		fireTableRowsInserted(0, listSites.size());
	}
	/*****************************************************************
	*returns the site 
	*@param the site number
	*@return the site
	 ****************************************************************/
	public Site getSites(int i) {
		return listSites.get(i);
	}
	/*****************************************************************
	*returns the amount of campers
	*@return the size of the arraylist
	 ****************************************************************/
	public int getSize() {
		return listSites.size();
	}
	/*****************************************************************
	*returns the coulumn name for the JTable
	*@param col the coloumn number you want
	*@return the name of the coloumn
	 ****************************************************************/
	public String getColumnName(int col) {
		return columnNames[col];
	}
	/*****************************************************************
	*returns the number of coulumns
	*@return the length of column array
	 ****************************************************************/
	public int getColumnCount() {
		return columnNames.length;
	}
	/*****************************************************************
	*returns the number of rows
	*@return the size of the arraylist of sites
	 ****************************************************************/
	public int getRowCount() {
		return listSites.size();
	}
	/*****************************************************************
	*returns each cell of the JTable based on the paramaters
	*@param row the row number
	*@param col the column number
	*@return the site at the specified cell
	 ****************************************************************/
	public Object getValueAt(int row, int col) {

		switch (col) {
		case 0:
			return (listSites.get(row).getNameReserving());

		case 1:
			return (DateFormat.getDateInstance(DateFormat.SHORT)
					.format(listSites.get(row).getCheckIn().getTime()));

		case 2:
			return (listSites.get(row).getDaysStaying());

		case 3:
			return (listSites.get(row).getSiteNumber());

		case 4:
			if (listSites.get(row) instanceof Tent)
				return ((Tent) listSites.get(row)).getNumOfTenters();
			else
				return ((RV) listSites.get(row)).getPower();

		default:
			return null;
		}
	}
	/*****************************************************************
	*saves JTable as a serizable file
	*@param filename the name of the file
	 ****************************************************************/
	public void saveDatabase(String filename) {
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(listSites);
			os.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	/*****************************************************************
	*loads the serizable file into the JTable
	*@param filename the name of the file
	 ****************************************************************/
	public void loadDatabase(String filename) {
		try {
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream is = new ObjectInputStream(fis);

			listSites = (ArrayList<Site>) is.readObject();
			fireTableRowsInserted(0, listSites.size() - 1);
			is.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	/*****************************************************************
	*saves the JTable as a text file
	*@param filename the name of the file
	 ****************************************************************/
	public boolean saveAsText(String filename) {
		if (filename.equals("")) {
			return false;
		}

		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(filename)));
			out.println(listSites.size());
			for (int i = 0; i < listSites.size(); i++) {
				Site site = listSites.get(i);
				out.println(site.getNameReserving());
				out.println(DateFormat.getDateInstance(DateFormat.SHORT)
						.format(site.getCheckIn().getTime()));
				out.println(site.getDaysStaying());
				out.println(site.getSiteNumber());
				if (site instanceof Tent)
					out.println(((Tent) site).getNumOfTenters());
				else
					out.println(((RV) site).getPower());

			}
			out.close();
			return true;
		} catch (IOException ex) {
			return false;
		}
	}
	/*****************************************************************
	*loads the JTable from a text file
	*@param filename the name of the file
	 ****************************************************************/
	public void loadFromText(String filename) {
		listSites.clear();
		fireTableRowsDeleted(0, listSites.size());

		try {
			Scanner scanner = new Scanner(new File(filename));
			// should clear the arrayList and screen....
			int count = Integer.parseInt(scanner.nextLine().trim());
			for (int i = 0; i < count; i++) {
				// gets name
				String nameReserving = scanner.nextLine().trim();
				// gets checkIn
				GregorianCalendar checkIn = null;
				try {
					DateFormat formatter = new SimpleDateFormat
							("MM/dd/yyyy");
					Date date = formatter.parse(scanner.nextLine().
							trim());
					checkIn = new GregorianCalendar();
					checkIn.setTime(date);
				} catch (ParseException ex) {
					ex.printStackTrace();
				}
				// gets daysStaying
				int daysStaying = Integer.parseInt(scanner.nextLine().
						trim());
				// gets siteNumber
				int siteNumber = Integer.parseInt(scanner.nextLine().
						trim());
				// gets Tenters
				int numOfTenters = Integer.parseInt(scanner.nextLine().
						trim());
				if (numOfTenters < 20) {
					Tent t;
					t = new Tent(nameReserving, checkIn, daysStaying,
							siteNumber, numOfTenters);
					listSites.add(t);
					fireTableRowsInserted(listSites.size() - 1,
							listSites.size() - 1);

				} else {
					RV r;
					int power = numOfTenters;
					r = new RV(nameReserving, checkIn, daysStaying,
							siteNumber,
							power);
					listSites.add(r);
					fireTableRowsInserted(listSites.size() - 1,
							listSites.size() - 1);
				}
			}
			scanner.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
