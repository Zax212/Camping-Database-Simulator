package package1;

import java.util.GregorianCalendar;
/*****************************************************************
one of two options to check into a campsite, RV has a unique varible 
of the power or amps needed.

@author Zach Hopman
@version March 2015
*****************************************************************/
public class RV extends Site {

	private static final long serialVersionUID = 1L;
	/** Represents the power supplied to the site */
	private int power; // 30, 40, 50 amps of service.
	/*****************************************************************
	*Constructor that intilizes all variable to the values given in
	*paramaters
	*@param nameReserving name of the person reserving
	*@param checkIn the date checked in
	*@param daysStaying the number of days staying
	*@param siteNumber the site number
	*@param power the amount of power needed
	 ****************************************************************/
	public RV(String nameReserving, GregorianCalendar checkIn,
			int daysStaying,
			int siteNumber, int power) {

		super(nameReserving, checkIn, daysStaying, siteNumber);
		this.power = power;
	}
	/*****************************************************************
	*Constructor that intilizes all variable to the values given in
	*paramaters
	*@param nameReserving name of the person reserving
	*@param checkIn the date checked in
	*@param daysStaying the number of days staying
	*@param checkOutOn the date checking out
	*@param siteNumber the site number
	*@param numOfTenters the number of tenters
	 ****************************************************************/
	public RV(String nameReserving, GregorianCalendar checkIn,
			int daysStaying,
			GregorianCalendar checkOutOn, int siteNumber, int power) {
		super(nameReserving, checkIn, daysStaying, checkOutOn,
				siteNumber);
		this.power = power;
	}
	/*****************************************************************
	*the default constructor for a RV
	 ****************************************************************/
	public RV() {
		super();
	}
	/*****************************************************************
	*returns the amount of power
	*@return power
	 ****************************************************************/
	public int getPower() {
		return power;
	}
	/*****************************************************************
	*sets the amount of power
	*@param power the amount of power
	 ****************************************************************/
	public void setPower(int power) {
		this.power = power;
	}

}
