package package3;
import package1.Event;

//SportCompetition Class - This is a derived class from the Event Class
public class SportCompetition extends Event{
	
	// Attributes
	// In its parent class Event, attributes are private, 		
	// so in this derived class, in order to have the most restrictive access rights,
	// 3 private variables are not changed.
	// For the new variables, use private modifier.
	private int numberOfActivitie;					
	enum SeasonName {SUMMER, FALL, WINTER, SPRING};
	private SeasonName season;
	
	// default constructor
	public SportCompetition()  
	{
		numberOfActivitie = 0;
		season = null;
	}
	
	// parameterized constructor
	public SportCompetition(int yr, int mt, int num, int numac, String sea)  
	{
		super(yr, mt, num);
		numberOfActivitie = numac;
		season = SeasonName.valueOf(sea);
	}
	
	// copy constructor
	public SportCompetition(SportCompetition p)  
	{
		setYear(p.getYear());
		setMonth(p.getMonth());
		setNumberOfCities(p.getNumberOfCities());
		numberOfActivitie = p.numberOfActivitie;
		season = p.season;
	}
	
	public int getNumberOfActivitie()
	{
		return numberOfActivitie;
	}
	
	public void setNumberOfActivitie(int numac)
	{
		numberOfActivitie = numac;
	}
	
	public SeasonName getSeason()
	{
		return season;
	}
	
	public void setSeason(String sea)
	{
		season = SeasonName.valueOf(sea);
	}
	
	public String toString()
	{
		return "This " + season + " SportCompetition will be held in " + getYear() + ", "+ getMonth() 
				+ " in " + getNumberOfCities() + " cities, and has " + numberOfActivitie + " activities."; 
	}
	
	public boolean equals(Object x)
	{	
		// when the passed object is null or a different type from the class SportCompetition, return false 
		if (x == null || this.getClass() != x.getClass())
			return false;
		else
		{
			SportCompetition f = (SportCompetition)x;				// cast the passed object to a SportCompetition object
			return (this.getYear() == f.getYear() &&
				this.getMonth() == f.getMonth() &&
				this.getNumberOfCities() == f.getNumberOfCities() &&
				this.numberOfActivitie == f.numberOfActivitie &&
				this.season.equals(f.season) );
		}
	}
}	
