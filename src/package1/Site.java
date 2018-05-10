package package1;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Site implements Serializable {

	private static final long serialVersionUID = 1L;

	/** The name of the person who is occupying the Site */
	protected String nameReserving;

	/** The date the Site was checked-in (occupied) */
	protected GregorianCalendar checkIn;

	/** The estimated number of days the person is reserving */
	/** This is just an estimate when the camper is */
	/** is checking in */
	protected int daysStaying;

	/** The date the Site was checked out */
	/** This is the exact day they checked-out */
	protected GregorianCalendar checkOutOn;

	/** The Site number */
	protected int siteNumber;
	
	
	/*****************************************************************
	 * Constructor that sets all varibles equal to paramaters
	 * @param nameReserving the name reserving campground site
	 * @param checkin the date of checkin
	 * @param daysStaying the number of days staying
	 * @param checkOutOn the date checking out
	 * @param siteNumber the number of the site
	 ****************************************************************/
	public Site(String nameReserving, GregorianCalendar checkIn,
			int daysStaying, GregorianCalendar checkOutOn,
			int siteNumber) {
		super();
		this.nameReserving = nameReserving;
		this.checkIn = checkIn;
		this.daysStaying = daysStaying;
		this.checkOutOn = checkOutOn;
		this.siteNumber = siteNumber;
	}
	/*****************************************************************
	 * Constructor that sets all varibles equal to paramaters
	 * @param nameReserving the name reserving campground site
	 * @param checkin the date of checkin
	 * @param daysStaying the number of days staying
	 * @param siteNumber the number of the site
	 ****************************************************************/
	// constructor with no checkOut
	public Site(String nameReserving, GregorianCalendar checkIn,
			int daysStaying, int siteNumber) {
		super();
		this.nameReserving = nameReserving;
		this.checkIn = checkIn;
		this.daysStaying = daysStaying;
		this.siteNumber = siteNumber;
	}
	/*****************************************************************
	*Constructor that creates a default site
	 ****************************************************************/
	public Site() {
		super();
	}
	/*****************************************************************
	*used to recieve the name of person reserving site
	*@return nameReserving
	 ****************************************************************/
	public String getNameReserving() {
		return nameReserving;
	}
	/*****************************************************************
	*used to set the Name of the person Reserving the site
	*@param nameReserving the name of person reserving
	 ****************************************************************/
	public void setNameReserving(String nameReserving) {
		this.nameReserving = nameReserving;
	}
	/*****************************************************************
	*returns the date checked in
	*@return checkIn
	 ****************************************************************/
	public GregorianCalendar getCheckIn() {
		return checkIn;
	}
	/*****************************************************************
	*sets the CheckIn value
	*@param checkIn the date checked in
	 ****************************************************************/
	public void setCheckIn(GregorianCalendar checkIn) {
		this.checkIn = checkIn;
	}
	/*****************************************************************
	*returns the number of days staying
	*@return daysStaying
	 ****************************************************************/
	public int getDaysStaying() {
		return daysStaying;
	}
	/*****************************************************************
	*sets the number of days staying
	*@param daysStaying 
	 ****************************************************************/
	public void setDaysStaying(int daysStaying) {
		this.daysStaying = daysStaying;
	}
	/*****************************************************************
	*returns the check out date
	*@return checkOutOn
	 ****************************************************************/
	public GregorianCalendar getCheckOutOn() {
		return checkOutOn;
	}
	/*****************************************************************
	*sets the check out date
	*@param checkOutOn
	 ****************************************************************/
	public void setCheckOutOn(GregorianCalendar checkOutOn) {
		this.checkOutOn = checkOutOn;
	}
	/*****************************************************************
	*returns the site number
	*@reutnr siteNumber
	 ****************************************************************/
	public int getSiteNumber() {
		return siteNumber;
	}
	/*****************************************************************
	*sets the site number
	*@param siteNumber
	 ****************************************************************/
	public void setSiteNumber(int siteNumber) {
		this.siteNumber = siteNumber;
	}
	/*****************************************************************
	*returns the amount of money the camper is expected to owe
	*@return daysStaying * 30;
	 ****************************************************************/
	public double CheckIn() {
		return daysStaying * 30;
	}
	/*****************************************************************
	*calculates the total amount of owed after the camper checks out
	*@param checkout the date checking out
	*@return the number of days times 30
	 ****************************************************************/
	public double CheckOut(String checkout) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String checkin = sdf.format(checkIn.getTime());
		final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;
		int diffInDays = (int) ((sdf.parse(checkout).getTime()
				- sdf.parse(
				checkin).getTime()) / DAY_IN_MILLIS);
		return diffInDays * 30;
	}

}
