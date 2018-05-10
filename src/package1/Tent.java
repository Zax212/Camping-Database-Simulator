package package1;

import java.text.ParseException;
import java.util.GregorianCalendar;
/*****************************************************************
one of two options to be checked into the campsite, tent has a unique
variable of number of tenters.

@author Zach Hopman
@version March 2015
*****************************************************************/
public class Tent extends Site {

	private static final long serialVersionUID = 1L;
	/** Represents the number of tenters on this site */
	private int numOfTenters;

	/*****************************************************************
	*Constructor that intilizes all variable to the values given in
	*paramaters
	*@param nameReserving name of the person reserving
	*@param checkIn the date checked in
	*@param daysStaying the number of days staying
	*@param siteNumber the site number
	*@param numOfTenters the number of tenters
	 ****************************************************************/
	public Tent(String nameReserving, GregorianCalendar checkIn,
			int daysStaying, int siteNumber, int numOfTenters) {

		super(nameReserving, checkIn, daysStaying, siteNumber);
		this.numOfTenters = numOfTenters;
	}
	
	/*****************************************************************
	*Constructor that intilizes all variable to the values given in
	*paramaters
	*@param nameReserving name of the person reserving
	*@param checkIn the date checked in
	*@param checkOutOn the date checking out
	*@param daysStaying the number of days staying
	*@param siteNumber the site number
	*@param numOfTenters the number of tenters
	 ****************************************************************/
	public Tent(String nameReserving, GregorianCalendar checkIn,
			int daysStaying, GregorianCalendar checkOutOn,
			int siteNumber,
			int numOfTenters) {
		super(nameReserving, checkIn, daysStaying, checkOutOn,
				siteNumber);
		this.numOfTenters = numOfTenters;
	}
	/*****************************************************************
	*Default constructor that creates a tent with default values
	 ****************************************************************/
	public Tent() {
		super();
	}
	/*****************************************************************
	*returns number of tenters
	*@return the number of tenters
	 ****************************************************************/
	public int getNumOfTenters() {
		return numOfTenters;
	}
	/*****************************************************************
	*sets the number of tenters
	*@param numOfTenters the number of tenters
	 ****************************************************************/
	public void setNumOfTenters(int numOfTenters) {
		this.numOfTenters = numOfTenters;
	}
	/*****************************************************************
	*returns the amount expected to owe
	*@return the days staying times the number of tenters times 3
	 ****************************************************************/
	public double CheckIn() {
		return (super.CheckIn() / 10) * numOfTenters;
	}
	/*****************************************************************
	*calculates the amount owed after a tenter checks out
	*@return the site checkout value / 10 time number of tenters
	 ****************************************************************/
	public double CheckOut(String checkout) throws ParseException{
		return (super.CheckOut(checkout)/10) * numOfTenters;
	}

}
