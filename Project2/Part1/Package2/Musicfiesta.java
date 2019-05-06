package package2;

//Musicfiesta Class - This is a derived class from the Festival Class
public class Musicfiesta extends Festival{
	
	// Attributes
	// In its parent class Festival, attributes are protected, 		
	// so in this derived class, in order to have the most restrictive access rights,
	// 6 protected variables from Festival are not changed.
	// For the new variable, use private modifier.
	private int numberOfBands;

	// default constructor 
	public Musicfiesta() 
	{
		numberOfBands = 0;
	}
	
	// parameterized constructor
	public Musicfiesta(int yr, int mt, int num, String na, double tp, int dur, int numban)  
	{
		super(yr, mt, num, na, tp, dur);
		numberOfBands = numban;
	}
	
	// copy constructor
	public Musicfiesta(Musicfiesta m)  
	{
		year = m.year;
		month = m.month;
		numberOfCities = m.numberOfCities;
		name = m.name;
		ticketPrice = m.ticketPrice;
		duration = m.duration;
		numberOfBands = m.numberOfBands;
	}
	
	public int getNumberOfBands()
	{
		return numberOfBands;
	}
	
	public void setNumberOfBands(int numban)
	{
		numberOfBands = numban;
	}
	
	public String toString()
	{
		return "This " + getName() + " Musicfiesta will be held in " + getYear() + ", "+ getMonth() 
				+ " in " + getNumberOfCities() + " cities, for " + getDuration() 
				+ " days, the ticket will cost " + getTicketPrice() + "$, and has " 
				+ numberOfBands + " bands."; 
	}
	
	public boolean equals(Object x)
	{	
		// when the passed object is null or a different type from the class Musicfiesta, return false 
		if (x == null || this.getClass() != x.getClass())
			return false;
		else
		{
			Musicfiesta m = (Musicfiesta)x;			// cast the passed object to a Musicfiesta object
			return (this.getYear() == m.getYear() &&
				this.getMonth() == m.getMonth() &&
				this.getNumberOfCities() == m.getNumberOfCities() &&
				this.name.equals(m.name)  &&
				this.ticketPrice == m.ticketPrice &&
				this.duration == m.duration &&
				this.numberOfBands == m.numberOfBands);
		}
	}
}
