package package2;

//Culturalfiesta Class - This is a derived class from the Festival Class
public class Culturalfiesta extends Festival{
	
	// Attributes
	// In its parent class Festival, attributes are protected, 		
	// so in this derived class, in order to have the most restrictive access rights,
	// 6 protected variables from Festival are not changed.
	// For the new variable, use private modifier.
	private int numberOfLanguages;	//the maximum languages spoken in this kind of festival
	
	// default constructor
	public Culturalfiesta()  
	{
		numberOfLanguages = 0;
	}
	
	// parameterized constructor 
	public Culturalfiesta(int yr, int mt, int num, String na, double tp, int dur, int numlan) 
	{
		super(yr, mt, num, na, tp, dur);
		numberOfLanguages = numlan;
	}
	
	// copy constructor
	public Culturalfiesta(Culturalfiesta c)  
	{
		year = c.year;
		month = c.month;
		numberOfCities = c.numberOfCities;
		name = c.name;
		ticketPrice = c.ticketPrice;
		duration = c.duration;
		numberOfLanguages = c.numberOfLanguages;
	}
	
	public int getNumberOfLanguages()
	{
		return numberOfLanguages;
	}
	
	public void setNumberOfLanguages(int numlan)
	{
		numberOfLanguages = numlan;
	}
	
	public String toString()
	{
		return "This " + getName() + " Culturalfiesta will be held in " + getYear() + ", "+ getMonth() 
				+ " in " + getNumberOfCities() + " cities, for " + getDuration() 
				+ " days, the ticket will cost " + getTicketPrice() + "$, and has " 
				+ numberOfLanguages + " spoken language."; 
	}
	
	public boolean equals(Object x)
	{	
		// when the passed object is null or a different type from the class Culturalfiesta, return false 
		if (x == null || this.getClass() != x.getClass())
			return false;
		else
		{
			Culturalfiesta c = (Culturalfiesta)x;			// cast the passed object to a Culturalfiesta object
			return (this.getYear() == c.getYear() &&
				this.getMonth() == c.getMonth() &&
				this.getNumberOfCities() == c.getNumberOfCities() &&
				this.name.equals(c.name)  &&
				this.ticketPrice == c.ticketPrice &&
				this.duration == c.duration &&
				this.numberOfLanguages == c.numberOfLanguages);
		}
	}
}
