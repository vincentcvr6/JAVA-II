
package package1;
import package2.Culturalfiesta;
import package2.Festival;
import package2.Musicfiesta;
import package3.SportCompetition;
import package4.Fair;
public class driver1 {
	 /** 
		 * This program is to help the metropolitan events planning office manage the year around events.
		 * This driver program utilizes all of the classes in 4 packages and creates various objects from 6 classes. 
		 * Display information and test equals() methods.
		 * Create an array of 10 Event objects and search the least/most number of cities, 
		 * as well as events that happen in the same year
		 */	
		public static void main(String[] args) {
			
			// display a welcome message on the screen
			System.out.println("------------------------------\n"+
								"  Welcome to the Program\n"+
								"------------------------------\n");
					
			//Create various objects from the 6 classes
			Event e1 = new Event(2017, 9, 10), e2 = new Event(e1);
			Festival f1 = new Festival(2017, 10, 8, "Arts", 30.0, 10), f2 = new Festival(f1);
			Culturalfiesta c1 = new Culturalfiesta(2017, 3, 5, "film", 10.0, 20, 5);
			Musicfiesta m1 = new Musicfiesta(2018, 7, 3, "beer", 20.0, 5, 50), m2 = new Musicfiesta(2018, 6, 2, "folk", 15.0, 6, 10);
			SportCompetition s1 = new SportCompetition(2018, 6, 3, 5, "SUMMER"), s2 = null;
			Fair fr1 = new Fair(2017, 4, 6, 30, "CAREER"), fr2 = new Fair(2017, 4, 6, 30, "CAREER"); 
		
			//display all objects' information using the toString() method
			System.out.println(e1);
			System.out.println(e2);
			System.out.println(f1);
			System.out.println(f2);
			System.out.println(c1);
			System.out.println(m1);
			System.out.println(m2);
			System.out.println(s1);
			System.out.println(s2);
			System.out.println(fr1);
			System.out.println(fr2);
			
			//Test the equality of some to the created objects using the equals() method
			System.out.println("\nComparing some of the events");
			System.out.println("==============================");
			
			if(e1.equals(e2))
				System.out.println("Event e1 & event e2 are equal");
			else
				System.out.println("Event e1 & event e2 are not equal");
			
			if(c1.equals(m2))
				System.out.println("Culturalfiesta c1 & musicfiesta m1 are equal");
			else
				System.out.println("Culturalfiesta c1 & musicfiesta m1 are not equal");
			
			if(s1.equals(s2))
				System.out.println("SportCompetition s1 & SportCompetition s2 are equal");
			else
				System.out.println("SportCompetition s1 & SportCompetition s2 are not equal");
			
			if(fr1.equals(fr2))
				System.out.println("Fair fr1 & fair fr2 are equal");
			else
				System.out.println("Fair fr1 & fair fr2 are not equal");
			
			System.out.println("\nCreate an array of 10 Event objects");
			System.out.println("=====================================");
			
			//Create an array of 10 Event objects
			Event [] eventArray = new Event [10]; 
			eventArray [0] = e1;
			eventArray [1] = e2;
			eventArray [2] = f1;
			eventArray [3] = f2;
			eventArray [4] = c1;
			eventArray [5] = m1;
			eventArray [6] = m2;
			eventArray [7] = s1;
			eventArray [8] = fr1;
			eventArray [9] = fr2;
			
			//Trace(search) that array to find the object that has the least/most number of cities
			//or are happening on the same year(2018)
			int indexOfLeast = 0,indexOfMost = 0;			
			boolean [] flag = new boolean [10]; 			// the variable to indicate whether has the same year
			for(int i=1; i < eventArray.length; i++)
			{
				if(eventArray[i].getNumberOfCities() < eventArray[indexOfLeast].getNumberOfCities())
					indexOfLeast = i;
				if(eventArray[i].getNumberOfCities() > eventArray[indexOfMost].getNumberOfCities())
					indexOfMost = i;
				if(eventArray[i].getYear() == 2018)
					flag[i] = true;
			}
			
			//display all information of the objects along with their location (index) in the array
			System.out.println("Having the least number of cities:\n"+eventArray[indexOfLeast]
								+"\nwith the location "+indexOfLeast+" in the array.");
			System.out.println("Having the most number of cities:\n"+eventArray[indexOfMost]
								+"\nwith the location "+indexOfMost+" in the array.");
			System.out.println("\nHappening on the same year: 2018");
			for(int i=0; i < eventArray.length-1; i++)
			{	
				if(flag[i])
				System.out.println(eventArray[i]+"\nwith the location "+i+" in the array.");
			}
			
			//display a closing message
			System.out.println("\n--------------------------------------");
			System.out.println("Thank you for using this program! ");
		}
	}
