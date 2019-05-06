
package package1;

//the base class
public class Event {
	
	// Attributes
	// Use private modifier to have the most restrictive access rights.
	// These private variables can only be accessed by the public accessor and mutator methods.
	private int year;       					
	private int month;				
	private int numberOfCities;		

	// default constructor
	public Event()  
	{
		year = 0;
		month = 0;
		numberOfCities = 0;
	}
	
	// parameterized constructor
	public Event(int yr, int mt, int num)  
	{
		year = yr;
		month = mt;
		numberOfCities = num;
	}
	
	// copy constructor 
	public Event(Event e) 
	{
		year = e.year;
		month = e.month;
		numberOfCities = e.numberOfCities;
	}
	
	public int getYear()
	{
		return year;
	}
	
	public void setYear(int yr)
	{
		year = yr;
	}
	
	public int getMonth()
	{
		return month;
	}
	
	public void setMonth(int mt)
	{
		month = mt;
	}
	
	public int getNumberOfCities()
	{
		return numberOfCities;
	}
	
	public void setNumberOfCities(int num)
	{
		numberOfCities = num;
	}
	
	public String toString()
	{
		return "This event will be held in " + getYear() + ", "+ getMonth() 
				+ " in " + getNumberOfCities() + " cities." ;
	}
	
	public boolean equals(Object x)
	{	
		// when the passed object is null or a different type from the class Event, return false 
		if (x == null || this.getClass() != x.getClass())
			return false;
		else
		{
			Event e = (Event)x;				// cast the passed object to a Event object
			return (this.year == e.year &&
				this.month == e.month &&
				this.numberOfCities == e.numberOfCities);
		}
	}
}
