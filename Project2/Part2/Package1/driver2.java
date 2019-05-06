package package1;
import package2.Culturalfiesta;
import package2.Festival;
import package2.Musicfiesta;
import package3.SportCompetition;
import package4.Fair;

public class driver2 {
	 /** 
		 * This program is to help the metropolitan events planning office manage the year around events.
		 * This driver program uses the most restrictive access rights to their attributes
		 * and calls copyFestival() method to copy an array of Event.
		 * By creating an array of 12 objects, test and analyze the feature of copy constructors. 
		 */
		public static void main(String[] args) {
			
			// display a welcome message on the screen
			System.out.println("------------------------------\n"+
								"  Welcome to the Program\n"+
								"------------------------------\n");
			
			//Create 12 various objects from the 6 classes		
			Event e1 = new Event(2017, 9, 10), 
					e2 = new Event(2018, 6, 12);
			Festival f1 = new Festival(2017, 10, 8, "Arts", 30.0, 10), 
						f2 = new Festival(f1);
			Culturalfiesta c1 = new Culturalfiesta(2018, 3, 5, "film", 10.0, 20, 5),
						c2 = new Culturalfiesta(2017, 4, 6, "Comedy", 20.0, 25, 6);
			Musicfiesta m1 = new Musicfiesta(2018, 7, 3, "beer", 20.0, 5, 50), 
						m2 = new Musicfiesta(2018, 6, 2, "folk", 15.0, 6, 10);
			SportCompetition s1 = new SportCompetition(2018, 6, 3, 5, "SUMMER"), 
						s2 = new SportCompetition(2017, 5, 2, 4, "FALL");
			Fair fr1 = new Fair(2018, 4, 6, 30, "CAREER"), 
					fr2 = new Fair(2017, 3, 5, 20, "TRAVEL"); 
			
			// create an array of 12 Events 
			System.out.println("\nCreate an array of 12 Event objects");
			System.out.println("=====================================");
			Event [] eventArray = new Event [12]; 
			eventArray [0] = e1;
			eventArray [1] = e2;
			eventArray [2] = f1;
			eventArray [3] = f2;
			eventArray [4] = c1;
			eventArray [5] = c2;
			eventArray [6] = m1;
			eventArray [7] = m2;
			eventArray [8] = s1;
			eventArray [9] = s2;
			eventArray [10] = fr1;
			eventArray [11] = fr2;
			
			Event[] eventArrayCopy = copyFestival(eventArray);
			
			// display the contents of both arrays
			for(int i=0; i < eventArray.length; i++)
			{
				System.out.println(eventArray[i]);
				// if the objects are Events(not its derived class), the copy is correct
				// but if the objects are other class(Festival, Fair, etc), the copy is wrong.
				// because using copy constructor can not meet the condition of triggering polymorphism.
				System.out.println(eventArrayCopy[i]+"\n");		
			}
			
			//display a closing message
			System.out.println("\n--------------------------------------");
			System.out.println("Thank you for using the program! ");

		}
		
		
		
		// A method that takes an array of Events and return a copy of that array
		public static Event[] copyFestival(Event [] e) 
		{
			Event[] evarr = new Event[e.length];		// create a new array with the same length
			for(int i=0; i < e.length; i++)
			{
				evarr[i] = new Event(e[i]);		
			}
			return evarr;
		}
		
		/* Another way to copy
		public static Event[] copyFestival(Event [] e) 
		{
			Event[] evarr = new Event[e.length];
			
			for(int i=0; i < e.length; i++)
			{
				switch(e[i].getClass().toString().substring(15))
				{
					case "Event": evarr[i] = new Event(e[i]);
						break;
					case "Festival": evarr[i] = new Festival((Festival)e[i]);
						break;
					case "Culturalfiesta": evarr[i] = new Culturalfiesta((Culturalfiesta)e[i]);
						break;
					case "Musicfiesta": evarr[i] = new Musicfiesta((Musicfiesta)e[i]);
						break;
					case "SportCompetition": evarr[i] = new SportCompetition((SportCompetition)e[i]);
						break;
					case "Fair": evarr[i] = new Fair((Fair)e[i]);
						break;
					default:
						break;
				}
			}
			return evarr;
		}
		*/
	}

