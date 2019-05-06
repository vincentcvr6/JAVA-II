
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This program is called BibCreator. The main task of this tool is read and process a given .bib file 
 * (which has one or more articles) and create 3 different files with the correct reference formats for IEEE, ACM and NJ.
 * 
 */

public class BibCreator {

	// instant variables of the class
	private static int fileNum = 10;								//the number of files
	private static String [] fileName = new String[fileNum];		//the array of names of original files 
	private static String [] newFileName1 = new String[fileNum];	//the array of names of IEEE output files
	private static String [] newFileName2 = new String[fileNum];	//the array of names of ACM output files
	private static String [] newFileName3 = new String[fileNum];	//the array of names of NJ output files
	private static File [] aFileArray = new File [fileNum];		//the array of original files 
	private static File [] aFileArray1 = new File [fileNum];		//the array of IEEE output files
	private static File [] aFileArray2 = new File [fileNum];		//the array of ACM output files
	private static File [] aFileArray3 = new File [fileNum];		//the array of NJ output files
	private static BufferedReader br = null;						
	private static PrintWriter pw =null;
	private static int invalidNum=0;								//the number of invalid files
	
	// the method used to delete output files
	public static void deleteOutputFiles(int i) {
		
			if(aFileArray1[i].exists()) {
				aFileArray1[i].delete();
			}
			if(aFileArray2[i].exists()) {
				aFileArray2[i].delete();
			}
			if(aFileArray3[i].exists()) {
				aFileArray3[i].delete();
			}
	}
	
	// the method used to judge if a string is a number 
	public static boolean isNumeric(String str) {  
        for (int i = str.length(); --i >= 0;) {  
            int chr = str.charAt(i);  
            if (chr < 48 || chr > 57)  
                return false;  
        }  
        return true;  
    }  
	
	// The method processes each of these files to find out whether it is valid or not.
	// If a file is valid, then the method must create the proper records for each of the 3 formats.
	// If a file is invalid, then the method must stop the processing of this file only, throws an exception.
	// The method will then continue with the processing of the following file.
	public static void processFilesForValidation(File[] aFileArray, int fileIndex) 
		throws FileInvalidException, IOException{
		
		String next;								// the string read by bufferedReader
		int fieldValid=-1;						// the first index number to identify if a filed is valid
		int fieldEnd=0;							// the second index number to identify if a filed is valid
		String fieldName,fieldContent;			// the name of a field, the content of a field
		boolean oneArticle = false;				// identify a article
		int num=0;								// the number of article in a file
		int artNum=0;							// the index number of article in the process
		
		
		for(int i=fileIndex; i<fileNum; i++) {
			
			// first create the array of article in a file
			br = new BufferedReader(new FileReader(aFileArray[i]));	
			num=0;
			artNum=0;
			next=br.readLine();
			while(next!=null) {
				if(next.length()==0) {
					next=br.readLine();
					continue;
				}
				if(next.indexOf("@ARTICLE{")>=0)
					num++;
				next=br.readLine();
			}
			
			
			if(num==0) {
				invalidNum++;
				throw new FileInvalidException();
			}
			Article [] artArray = new Article[num];
			br.close();
			
			// begin read contents of fields
			br = new BufferedReader(new FileReader(aFileArray[i]));	
			next=br.readLine();
			while(next!=null) {
				if(next.length()==0) {
					next=br.readLine();
					continue;
				}
				if(next.indexOf("@ARTICLE{")>=0) {
					oneArticle = false;
					Article aArticle = new Article();
					while(!oneArticle) {
						next=br.readLine();
						if(next==null||next.length()==0)
							continue;
									
							
							else{
									fieldValid=next.indexOf("={");
									fieldEnd=next.indexOf("},");
								
										if(fieldValid>=0 && fieldEnd<0) {
											System.out.println("yun");
											while(!next.contains("},")) {
												String temp = br.readLine();
												next=next.concat(temp);
												System.out.println(temp);
											}
										}else
											
										if(fieldValid>=0 && fieldEnd >=0) {
											fieldName=next.substring(0, fieldValid);
											fieldContent=next.substring((fieldValid+2),fieldEnd);
											if(fieldContent.equals("")) {
												invalidNum++;
												throw new FileInvalidException(aFileArray, fieldName, i);
											}
											else
													switch(fieldName) {
													case "author": aArticle.setAuthor(fieldContent);break;
													case "journal": aArticle.setJournal(fieldContent);break;
													case "title": aArticle.setTitle(fieldContent);break;
													case "year": aArticle.setYear(fieldContent);break;
													case "volume": aArticle.setVolume(fieldContent);break;
													case "number": aArticle.setNumber(fieldContent);break;
													case "pages": aArticle.setPages(fieldContent);break;
													case "keywords": aArticle.setKeywords(fieldContent);break;
													case "doi": aArticle.setDoi(fieldContent);break;
													case "ISSN": aArticle.setIssn(fieldContent);break;
													case "month": aArticle.setMonth(fieldContent);break;
													default: 
														System.out.println(fieldName);
													}
										}
							}
						  	
						if(next.equals("}")) {
							if(aArticle.getAuthor()==null||aArticle.getJournal()==null||aArticle.getTitle()==null||
								aArticle.getYear()==null||aArticle.getVolume()==null||aArticle.getNumber()==null||
								aArticle.getPages()==null||aArticle.getKeywords()==null||aArticle.getDoi()==null||
								aArticle.getIssn()==null||aArticle.getMonth()==null) {
								invalidNum++;
								throw new FileInvalidException(aFileArray, i);
							}else {
								oneArticle=true;
								artNum++;
								artArray[artNum-1]=aArticle.clone();	
							}
									
						}
					}
				
				}
					
				next=br.readLine();	
			}
			br.close();
			
			// write contents of fields to the IEEE output files
			pw = new PrintWriter(new FileOutputStream(newFileName1[i]));
			for(int j=0; j<num;j++) {
				if(artArray[j]==null) {
					break;
				}
				else {
					pw.println(ieeeFormat(artArray[j])+". \""+artArray[j].getTitle()+"\", "+artArray[j].getJournal()+", vol. "+artArray[j].getVolume()+
							", no. "+artArray[j].getNumber()+", p. "+artArray[j].getPages()+", "+artArray[j].getMonth()+" "+artArray[j].getYear()+"\n");
				}
			}
			pw.close();	
			
			// write contents of fields to the ACM output files
			pw = new PrintWriter(new FileOutputStream(newFileName2[i]));
			for(int j=0; j<num;j++) {
				
				if(artArray[j]==null) {
					break;
				}
				else {
					pw.println("["+(j+1)+"]\t"+acmFormat(artArray[j])+". "+artArray[j].getYear()+". "+artArray[j].getTitle()+". "+artArray[j].getJournal()+". "+artArray[j].getVolume()+
							", "+artArray[j].getNumber()+"("+artArray[j].getYear()+"), "+artArray[j].getPages()+". DOI:https://doi.org/"+"\n");
				}
			}
			pw.close();	
			
			// write contents of fields to the NJ output files
			pw = new PrintWriter(new FileOutputStream(newFileName3[i]));
			for(int j=0; j<num;j++) {
			
				if(artArray[j]==null) {
					break;
				}
				else {
					pw.println(njFormat(artArray[j])+". "+artArray[j].getTitle()+". "+artArray[j].getJournal()+". "+artArray[j].getVolume()+
							", "+artArray[j].getPages()+"("+artArray[j].getYear()+").\n");
				}
			}
			pw.close();	
		}
}

	// the method to convert the format of author for IEEE 
	public static String ieeeFormat(Article a){
		if(a!=null) {
			String author = a.getAuthor();
			int tokenNum=0;
			StringTokenizer authorToken = new StringTokenizer(author);
			tokenNum=authorToken.countTokens();
			String [] tokenArray = new String [tokenNum];
			for(int i=0; i<tokenNum; i++) {
				tokenArray [i]= authorToken.nextToken();
			}	
			for(int i=0; i<tokenNum; i++) {
				if(tokenArray[i].contains(".")) 
				tokenArray[i]=tokenArray[i].concat(" ");	
			}
			String newAuthor = tokenArray[0];
			for(int i=1; i<tokenNum; i++) {
				
				if(tokenArray[i].equals("and")) {
					newAuthor = newAuthor.concat(", ");
				}else
					newAuthor = newAuthor.concat(tokenArray[i]);
			}
			return newAuthor;
			/*String newAuthor = authorToken.nextToken();
			while(authorToken.hasMoreTokens()) {
				System.out.println(authorToken.nextToken());
				if(authorToken.nextToken().equals("and")) {
					newAuthor = newAuthor.concat(", ");
				}else
					newAuthor=newAuthor.concat(authorToken.nextToken());
					a.setAuthor(newAuthor);
			}
			 */
		}else
			return null;
	}	
	
	// the method to convert the format of author for ACM 
	public static String acmFormat(Article a){
		if(a!=null) {
			String author = a.getAuthor();
			int indexOfAnd = author.indexOf(" and ");
			if((indexOfAnd)>0) {
				author = author.substring(0, indexOfAnd);
				author = author.concat(" et al");
			}
			return author;
		}
		return null;
	}	
	
	// the method to convert the format of author for NJ 
	public static String njFormat(Article a){
		if(a!=null) {
			String author = a.getAuthor();
			int tokenNum=0;
			StringTokenizer authorToken = new StringTokenizer(author);
			tokenNum=authorToken.countTokens();
			String [] tokenArray = new String [tokenNum];
			for(int i=0; i<tokenNum; i++) {
				tokenArray [i]= authorToken.nextToken();
			}	
			for(int i=0; i<tokenNum; i++) {
				if(tokenArray[i].contains(".")) 
				tokenArray[i]=tokenArray[i].concat(" ");	
			}
			String newAuthor = tokenArray[0];
			for(int i=1; i<tokenNum; i++) {
				
				if(tokenArray[i].equals("and")) {
					newAuthor = newAuthor.concat(" & ");
				}else
					newAuthor = newAuthor.concat(tokenArray[i]);
			}
			return newAuthor;

		}else
			return null;
	}	
	
	// the main method
	public static void main(String[] args) {
		
		// display a welcome message on the screen
		System.out.println("Welcome to BibCreator!\n");
	
		// process the file names
		
		//File path = new File(".");
		//File [] list = path.listFiles();
		for(int i=0; i<fileNum; i++) {
			fileName[i] = "Latex"+(i+1)+".bib";
			aFileArray[i] = new File(fileName[i]);
			//aFileArray[i] = new File(list[i]);
			//System.out.println(list[i].getName());
			
			Scanner inFile = null;
			try {
				inFile = new Scanner(new FileReader(aFileArray[i]));
				//br = new BufferedReader(new FileReader(aFileArray[i]));	
					inFile.close();
			}catch(FileNotFoundException e) {							   
				System.out.println("Could not open input "+fileName[i]+" for reading.\n\n"
							+ "Please check if file exists! Program will terminate after closing any opened files.");
				System.exit(0);	
			} 
		}
		
		// create output file names
		for(int i=0; i<fileNum; i++) {
			newFileName1[i]= "IEEE"+(i+1)+".json";
			newFileName2[i]= "ACM"+(i+1)+".json";
			newFileName3[i]= "NJ"+(i+1)+".json";
			aFileArray1[i] = new File(newFileName1[i]);
			aFileArray2[i] = new File(newFileName2[i]);
			aFileArray3[i] = new File(newFileName3[i]);	
		}
		
		// open/create IEEE output files
		for(int i=0; i<fileNum; i++) {
			try {
				pw = new PrintWriter(new FileOutputStream(newFileName1[i]));
				pw.close();
			}catch(FileNotFoundException e) {
				System.out.println(newFileName1[i]+"cannot be opened/created");
				System.out.println("All other created output files will be deleted");	
				for(int j=0; j<=i; j++) 
					deleteOutputFiles(i);
				System.out.println("Program will terminate.");
				System.exit(0);	
			}
		}
		
		// open/create ACM output files
		for(int i=0; i<fileNum; i++) {
			try {
				pw = new PrintWriter(new FileOutputStream(newFileName2[i]));
				pw.close();
			}catch(FileNotFoundException e) {
				System.out.println(newFileName2[i]+"cannot be opened/created");
				System.out.println("All other created output files will be deleted");	
				for(int j=0; j<=i; j++) 
					deleteOutputFiles(i);
				System.out.println("Program will terminate.");
				System.exit(0);	
			}
		}

		// open/create NJ output files
		for(int i=0; i<fileNum; i++) {
			try {
				pw = new PrintWriter(new FileOutputStream(newFileName3[i]));
				pw.close();
			}catch(FileNotFoundException e) {
				System.out.println(newFileName3[i]+"cannot be opened/created");
				System.out.println("All other created output files will be deleted");	
				for(int j=0; j<=i; j++) 
					deleteOutputFiles(i);
				System.out.println("Program will terminate.");
				System.exit(0);	
			}
		}
		
		
			try {
					processFilesForValidation(aFileArray, 0);
			}catch(FileInvalidException e) {
			
			}catch(IOException e) {
				System.out.println("Error: An error has occurred while closing file. ");
				System.out.println("Program will terminate.");
				System.exit(0);	
			}finally {
				System.out.println("A total of "+invalidNum+" files were invalid, and could not be processed. ALl other "+
									(fileNum-invalidNum)+" \"Valid\" files have been created.");
			}
	
		
			
			//ask the user to enter the name of one of the created output files to display
			System.out.print("\nPlease enter the name of one of the files that you need to review:");
			Scanner kb = new Scanner(System.in);
			String s = kb.nextLine();
			try {
				br = new BufferedReader(new FileReader(s));
				System.out.println("Here are the contents of the successfully created Json File:"+s);
				String next = br.readLine();
				while(next!=null) {
					System.out.println(next);
					next = br.readLine();
				}
				System.out.println("Goodbye! Hope you have enjoyed creating the needed files using Bibcreator.");
				
			} catch (FileNotFoundException e) {
				System.out.println("Could not open input file. File does not exist; possibly it could not be created!");
				System.out.println("\nHowever, you will be allowed another chance to enter another file name.");
				System.out.print("Please enter the name of one of the files that you need to review:");
				s = kb.nextLine();
				try {
					br = new BufferedReader(new FileReader(s));
					System.out.println("Here are the contents of the successfully created Json File:"+s);
					String next = br.readLine();
					while(next!=null) {
						System.out.println(next);
						next = br.readLine();
					}
				} catch (FileNotFoundException e1) {
					System.out.println("\nCould not open input file again! Either file does not exist or could not be created.");
					System.out.println("Sorry I am unable to display your desired files! Program will exit!");
					System.out.println("\n\n--------------------------------------");
					System.out.println("Thank you for using this program! ");
					System.exit(0);
					
				}catch(IOException e1) {
					System.out.println("Error: An error has occurred while closing file. ");
					System.out.println("Program will terminate.");
					System.exit(0);	
				}
			}catch(IOException e) {
				System.out.println("Error: An error has occurred while closing file. ");
				System.out.println("Program will terminate.");
				System.exit(0);	
			}finally{
				kb.close();
				System.out.println("\n\n--------------------------------------");
				System.out.println("Thank you for using this program! ");
			}
	}

}



//the exception class that can handle all the different errors for an invalid file
class FileInvalidException extends Exception{
	
	// Constructor 
	public FileInvalidException()
	{
		super("Error: Input file cannot be parsed due to missing information");
		System.out.println(this.getMessage());
		System.out.println("Program will terminate.");
		System.exit(0);
	}
	
	public FileInvalidException(String s)
	{
		super(s);
	}
	
	public FileInvalidException(File[] aFileArray ,int i)
	{
		super("Error: Input file cannot be parsed due to missing information in the file: "+ aFileArray[i]);
		System.out.println(this.getMessage());
		System.out.println("Program will terminate.");
		System.exit(0);
	}
	
	// the constructor used to handle invalid file  
	public FileInvalidException(File[] aFileArray, String fieldName, int i)
	{
		super("Error: Detected Empty Field!\n"+
				"============================\n\n"+
				"Problem detected with input file: "+aFileArray[i]+"\n"+
				"File is Invalid: Field \""+fieldName+"\" is Empty. Processing stoped at this point. Other empty fields may be present as well!\n");

		System.out.println(this.getMessage());
		BibCreator.deleteOutputFiles(i); 
		try {
			BibCreator.processFilesForValidation(aFileArray,(i+1));
		}catch(FileInvalidException e) {
		}catch(IOException e) {
			System.out.println("Error: An error has occurred while closing file. ");
			System.out.println("Program will terminate.");
			System.exit(0);	
		}	
	}
	
	public String getMessage()
	{
		return super.getMessage();
	}
	
}
