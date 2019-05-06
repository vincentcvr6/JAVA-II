import java.util.Scanner;
public class Book {
  /** 
	 * This program defines a class named Book to store information about books
	 * and a software application that helps the store staff in keeping track of 
	 * the books at the store
	 */
	//Instant variables of the class
	private String title;        		//The book title
	private String name;				//The name of author
	private long ISBN_number;			//The ISBN number
	private double price;				//The price tag
	private static int bookNumber=0;		//The number of books have been stored
	private static int optionNumber;		//The number of option for main menu
	private static int bookUpdateInput ;	//The number of option for update menu
	
	//The default constructor
	public Book() {
		this.title=null;
	    this.name=null;
	    this.ISBN_number=0;
	    this.price=0.0;
	    bookNumber++;
	}
	
	//The constructor with parameters
	public Book(String title, String name, long ISBN_number, double price){
    this.title=title;
    this.name=name;
    this.ISBN_number=ISBN_number;
    this.price=price;
    bookNumber++;
	}
	
	//Copy constructor
	public Book(Book abook) {
		this.title=abook.title;
	    this.name=abook.name;
	    this.ISBN_number=abook.ISBN_number;
	    this.price=abook.price;
	}

	//Returns value of title
	public String getTitle() {
		return title;
	}

	//Sets new value of title
	public void setTitle(String title) {
		this.title = title;
	}

	//Returns value of name
	public String getName() {
		return name;
	}

	//Sets new value of name
	public void setName(String name) {
		this.name = name;
	}

	//Returns value of ISBN_number
	public long getISBN_number() {
		return ISBN_number;
	}

	//Sets new value of ISBN_number
	public void setISBN_number(long ISBN_number) {
		this.ISBN_number = ISBN_number;
	}

	//Returns value of price
	public double getPrice() {
		return price;
	}

	//Sets new value of price
	public void setPrice(double price) {
		this.price = price;
	}
	
	//Create string representation of Book for printing
	public String toString() {
		return ("Author: "+name+"\n"+
				"Title: "+title+"\n"+
				"ISBN: "+ISBN_number+" #"+"\n"+
				"Price: $"+price+"\n");
	}

	//Returns the number of created books objects
	public static int findNumberOfCreatedBooks(){
		return bookNumber;
	}

	//Compares two Book objects 
	public boolean equals(Book otherBook){
		return((ISBN_number==otherBook.ISBN_number) && (price==otherBook.price));
	}
	
	
	
	public static int mainMenu() {		//Main menu for returning the option number
		Scanner keyIn = new Scanner(System.in);
		System.out.print("What do you want to do?\n"+
				"   1. Enter new books (password required)\n"+
				"   2. Change information of a book (password required)\n"+
				"   3. Display all books by a specific author\n"+
				"   4. Display all books under a certain a price.\n"+
				"   5. Quit\n"+
				"Please enter your choice >");
		boolean validNum=false;			//The status of whether the input number is between 1 and 5 inclusive
		while(!validNum){
			validNum=true;
			String optionNumberStr = keyIn.next();
			if(optionNumberStr.length()>1)		
				validNum=false;			
			char chr = optionNumberStr.charAt(0);
			if(chr<49||chr>53) 			//Makes sure the number is 1 and 5 inclusive
				validNum=false;
			if(validNum) {
				optionNumber = (int) chr-48;
				break;
			}
			else	
				System.out.println("Please enter a number between 1 and 5.");
		}
		return optionNumber;			//Returns the option number
	}
	
	public static int updateMenu() {			//Update menu for returning the option number
		Scanner keyIn = new Scanner(System.in);
		System.out.println("What information would you like to change?\n"+
				   "   1. author\n" + 
				   "   2. title\n"+
				   "   3. ISBN\n"+ 
				   "   4. price\n"+
				   "   5. Quit\n"+ 
				   "Enter your choice >");
		boolean validNum=false;			//The status of whether the input number is between 1 and 5 inclusive
		while(!validNum){
			validNum=true;
			String bookUpdateInputStr = keyIn.next();
			if(bookUpdateInputStr.length()>1)
				validNum=false;
			char chr = bookUpdateInputStr.charAt(0);
			if(chr<49||chr>53) 			//Makes sure the number is 1 and 5 inclusive
				validNum=false;
			if(validNum) {
				bookUpdateInput = (int) chr-48;
				break;
			}
			else	
				System.out.println("Please enter a number between 1 and 5.");
		}
		return bookUpdateInput;			//Returns the option number
	}
	
	//Compares the author name for option 3
	public static boolean findBooksby(Book inventoryWithIndex, String author) {
		return (inventoryWithIndex.name.equals(author));
	}	

	//Compares the price for option 4
	public static boolean findCheaperThan(Book inventoryWithIndex, double price) {
		return (inventoryWithIndex.price<price);
	}

	//The driver program for Part II
	public static void main(String[] args) {
	
		// display a welcome message on the screen
		System.out.println("------------------------------\n"+
							"  Welcome to Yanqi's Program\n"+
							"------------------------------\n");
		
		Scanner keyIn = new Scanner(System.in);	//Keyboard input variable
		int maxBooks = 0;						//The maximum number of books the bookstore can contain 
		int bookNumInput = 0;					//The number of books user inputs in option 1
		final String password = "password"; 		//The constant password for option 1 and 2
		boolean passwordCorrect = false;			//The status of correct password
		String title;							//The book title
		String name;								//The name of author
		long ISBN_number;						//The ISBN number
		double price;							//The price tag
		boolean mainMenuBreaker=false;			//Controls the break of the main Menu
		
		// prompt the user for inputting the maximum number of books
		System.out.println("Please enter the maximum number of books:");
		maxBooks = keyIn.nextInt();
		keyIn.nextLine();
		Book [] inventory  = new Book [maxBooks];//The empty array that will keep track of books
		
		
		Book.mainMenu();						//Displays the main menu, return a number for choosing
		while(!mainMenuBreaker) {			//Keeps running in this main menu
			if(optionNumber==1) {			//When choosing option 1, checks the password
				passwordCorrect = false;
				for(int i=0; i<4; i++) {	
					for(int j=0; j<3; j++) {
						System.out.println("Please enter the password:");
						String passwordIn = keyIn.nextLine();
						if(passwordIn.equals(password)) {
							passwordCorrect=true;
							break;
						}
					}
					if(passwordCorrect)
						break;
					if(i==3) {				//When total failed attempts is 12, the program exits
						System.out.println("Program detected suspicous activities and will terminate immediately!");
						optionNumber=5;
						break;
					}						
					if(Book.mainMenu()==1)	//After the 3rd wrong attempt entry, the main menu is re-displayed again
						continue;
					else
						break;
				}
			}
			
			if(optionNumber==2) {			//When choosing option 2, checks the password
				passwordCorrect = false;
				for(int j=0; j<3; j++) {
					System.out.println("Please enter the password: ");
					String passwordIn = keyIn.nextLine();
					if(passwordIn.equals(password)) {
						passwordCorrect=true;
						break;
					}
				}
				if(!passwordCorrect) {
					Book.mainMenu();			//After the 3rd wrong attempt entry, the main menu is re-displayed 
					continue;
				}
			
			}
			
			
			//Core part of main menu
			switch(optionNumber) {
				case 1:						//Option 1 for entering new books
					if(passwordCorrect) {
						//Ask the user how many books he/she wants to enter
						do {
							System.out.println("Please enter the number of books you want to enter: ");
							bookNumInput = keyIn.nextInt();
							keyIn.nextLine();
							if((bookNumInput+bookNumber)<=maxBooks)		//Check if there is enough space in the inventory 
								break;
							else
								System.out.println("Sorry, you can only add "+(maxBooks-bookNumber)+" books, please enter again: ");
						}while((bookNumInput+bookNumber)>maxBooks);
						
						//Add information of books to the inventory
						int bookNumberUp=bookNumber+bookNumInput;			//Records the total number
						for(int k=bookNumber; k<bookNumberUp; k++) {
							System.out.print("Please enter the information of book "+(k+1)+"\n"+
												"name of Author: ");
							name = keyIn.nextLine();
							System.out.print("title of book: ");
							title = keyIn.nextLine();
							System.out.print("ISBN: ");
							ISBN_number = keyIn.nextLong();
							System.out.print("$ price: ");
							price = keyIn.nextDouble();
							keyIn.nextLine();
							inventory [k] = new Book(title, name, ISBN_number, price);
						}
					}
					Book.mainMenu();			//Displays the main menu
					break;
				case 2:						//Option 2 for changing information of a book
					if(passwordCorrect) {
						boolean option2Breaker = false;
						while(!option2Breaker) {
							System.out.println("Please enter the index of book that you want to update: ");
							int bookIndexInput = keyIn.nextInt();
							
							if(bookIndexInput>maxBooks||inventory[bookIndexInput-1]==null) { //If there is no Book object at the specified index location
								System.out.println("There is no book, please choose: \n"+
													"1. re-enter another book\n"+
													"2. quit\n"+
													"3. go back to the main menu");
								int bookIndexChoice = keyIn.nextInt();
								if(bookIndexChoice==2) {				//Quits this operation
									optionNumber=5;
									option2Breaker=true;
									continue;
								}
								else
									if(bookIndexChoice==3) {			//Goes back to the main menu
										Book.mainMenu();
										keyIn.nextLine();
										option2Breaker=true;
										break;
									}
							}
							else {									//When the entered index has a valid book
								System.out.println("Book: #"+bookIndexInput+"\n"+inventory[bookIndexInput-1]);//Displays the information of that book
								updateMenu();						//Displays the update menu
								keyIn.nextLine();
								boolean updateMenuBreaker=false;		//Used to control the loop
								do{
									switch(bookUpdateInput) {
										case 1:
											System.out.print("Please enter the name of author: ");
											String nameUpdate = keyIn.nextLine();
											inventory[bookIndexInput-1].name = nameUpdate;
											System.out.println("Book: #"+bookIndexInput+"\n"+inventory[bookIndexInput-1]);
											updateMenu();
											break;
										case 2:
											System.out.print("Please enter the title of book: ");
											String titleUpdate = keyIn.nextLine();
											inventory[bookIndexInput-1].title = titleUpdate;
											System.out.println("Book: #"+bookIndexInput+"\n"+inventory[bookIndexInput-1]);
											updateMenu();
											break;
										case 3:
											System.out.print("Please enter the ISBN: ");
											long ISBN_numberUpdate = keyIn.nextLong();
											keyIn.nextLine();
											inventory[bookIndexInput-1].ISBN_number = ISBN_numberUpdate;
											System.out.println("Book: #"+bookIndexInput+"\n"+inventory[bookIndexInput-1]);
											updateMenu();
											break;
										case 4:
											System.out.print("Please enter the price: ");
											double priceUpdate = keyIn.nextDouble();
											keyIn.nextLine();
											inventory[bookIndexInput-1].price = priceUpdate;
											System.out.println("Book: #"+bookIndexInput+"\n"+inventory[bookIndexInput-1]);
											updateMenu();
											break;
										case 5:
											Book.mainMenu();;			//Keeps prompting the user for additional changes until the user enters 5
											option2Breaker=true;
											updateMenuBreaker=true;
											break;
									}
								}while(!updateMenuBreaker);
							}	
						}
					}	
					break;
				case 3:			//Option 3 for displaying all books by a specific author
					System.out.println("Please enter the name of author: ");
					String nameOfAuthorSearch = keyIn.nextLine();
					boolean bookFind3=false;
					for(int i=0; i<bookNumber; i++) {
						if(findBooksby(inventory[i], nameOfAuthorSearch)) {
							System.out.println("Book: #"+(i+1)+"\n"+inventory[i]);
							bookFind3=true;
						}
					}
					if(!bookFind3)
						System.out.println("No such books found.");
					Book.mainMenu();
					break;
				case 4:			//Option 4 for displaying all books under a certain a price
					System.out.println("Please enter the price: ");
					double priceSearch = keyIn.nextDouble();
					boolean bookFind4=false;
					for(int i=0; i<bookNumber; i++) {
						if(findCheaperThan(inventory[i], priceSearch)) {
							System.out.println("Book: #"+(i+1)+"\n"+inventory[i]);
							bookFind4=true;
						}
					if(!bookFind4)
						System.out.println("No such books found.\n");
					}
					Book.mainMenu();
					break;
				case 5:			//Option 5 for displaying a closing message and end the driver
					keyIn.close();      // close the scanner object
					System.out.println("\n\n--------------------------------------");
					System.out.println("Thank you for using Yanqi's program! ");
					System.exit(0);
			}
		}
	}
}
