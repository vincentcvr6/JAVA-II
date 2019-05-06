package package2;
import package1.Event;

//Festival Class - This is a derived class from the Event Class
public class Festival extends Event{
	
	// Attributes    
	// Use private modifier to have the most restrictive access rights.
	// These private variables can only be accessed by the public accessor and mutator methods.		
	private String name;				//such as Arts, Beer,Comedy, Film, Fire, Folk...etc
	private double ticketPrice;
	private int duration;			//# of_days
	
	// default constructor
	public Festival()  
	{
		name = null;
		ticketPrice = 0.0;
		duration = 0;
	}
	
	// parameterized constructor
	public Festival(int yr, int mt, int num, String na, double tp, int dur)  
	{
		super(yr, mt, num);
		name = na;
		ticketPrice = tp;
		duration = dur;
	}
	
	// copy constructor
	public Festival(Festival f)  
	{
		setYear(f.getYear());
		setMonth(f.getMonth());
		setNumberOfCities(f.getNumberOfCities());
		name = f.name;
		ticketPrice = f.ticketPrice;
		duration = f.duration;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String na)
	{
		name = na;
	}

	public double getTicketPrice()
	{
		return ticketPrice;
	}
	
	public void setTicketPrice(double tp)
	{
		ticketPrice = tp;
	}
	
	public int getDuration()
	{
		return duration;
	}
	
	public void setDuration(int dur)
	{
		duration = dur;
	}
	
	public String toString()
	{
		return "This festival will be held in " + getYear() + ", "+ getMonth() 
				+ " in " + getNumberOfCities() + " cities, for " + getDuration() 
				+ " days, the ticket will cost " + getTicketPrice() + "$."; 
	}
	
	public boolean equals(Object x)
	{	
		// when the passed object is null or a different type from the class Festival, return false 
		if (x == null || this.getClass() != x.getClass())
			return false;
		else
		{
			Festival f = (Festival)x;				// cast the passed object to a Festival object
			return (this.getYear() == f.getYear() &&
				this.getMonth() == f.getMonth() &&
				this.getNumberOfCities() == f.getNumberOfCities() &&
				this.name.equals(f.name) &&
				this.ticketPrice == f.ticketPrice &&
				this.duration == f.duration);
		}
	}
}