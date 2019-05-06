package package4;
import package1.Event;

//Fair Class - This is a derived class from the Event Class
public class Fair extends Event {
	
	// Attributes
	// Use private modifier to have the most restrictive access rights.
	// These private variables can only be accessed by the public accessor and mutator methods.
	private int numberOfExhibitors;
	enum FairType {CAREER, SCIENCE, TRADE, TRAVEL};
	private FairType fairtp;
	
	// default constructor
	public Fair()  
	{
		numberOfExhibitors = 0;
		fairtp = null;
	}
	
	// parameterized constructor
	public Fair(int yr, int mt, int num, int numex, String ftp)  
	{
		super(yr, mt, num);
		numberOfExhibitors = numex;
		fairtp = FairType.valueOf(ftp);
	}
	
	// copy constructor
	public Fair(Fair p)  
	{
		setYear(p.getYear());
		setMonth(p.getMonth());
		setNumberOfCities(p.getNumberOfCities());
		numberOfExhibitors = p.numberOfExhibitors;
		fairtp = p.fairtp;
	}
	
	public int getNumberOfExibitors()
	{
		return numberOfExhibitors;
	}
	
	public void setNumberOfExibitors(int numex)
	{
		numberOfExhibitors = numex;
	}
	
	public FairType getSeason()
	{
		return fairtp;
	}
	
	public void setSeason(String ftp)
	{
		fairtp = FairType.valueOf(ftp);
	}
	
	public String toString()
	{
		return "This " + fairtp + " Fair will be held in " + getYear() + ", "+ getMonth() 
				+ " in " + getNumberOfCities() + " cities, and has " + numberOfExhibitors + " exibitors."; 
	}
	
	public boolean equals(Object x)
	{	
		// when the passed object is null or a different type from the class Fair, return false 
		if (x == null || this.getClass() != x.getClass())
			return false;
		else
		{
			Fair f = (Fair)x;				// cast the passed object to a Fair object
			return (this.getYear() == f.getYear() &&
				this.getMonth() == f.getMonth() &&
				this.getNumberOfCities() == f.getNumberOfCities() &&
				this.numberOfExhibitors == f.numberOfExhibitors &&
				this.fairtp.equals(f.fairtp) );
		}
	}
}
