import java.util.Scanner; 
import java.util.Random;

public class Main{
	
	static Scanner  console = new Scanner(System.in);
	
	public static void main(String args[]){
		
		createArray();
		
	}
	
	
	public static void createArray(){
		
		int firstInputerInt;
		int secondInputerInt;
			
		while(true){
			
			try{
				
				System.out.print("Enter length of first array: ");
				firstInputerInt = Integer.parseInt(console.next());
				System.out.print("Enter length of second array: ");
				secondInputerInt = Integer.parseInt(console.next());
				
				if((firstInputerInt > 0) && (secondInputerInt > 0)){
					
					break;
					
				}									
				
			} catch(NumberFormatException e) {
				
				System.out.println("You entered an invalid value");
				
			}
					
		}	
	
		String[][] list = fillArray(firstInputerInt, secondInputerInt); //calls the fillArray method to enter random characters to the array and passes the dimensions entered by the user
		selectOption(list);
		
		
	}
	
	
	
	public static String[][] fillArray(int a, int b){ //creates the array with the dimensions set by the user, also fills the array with 3 random characters
		String[][] testArray = new String[a][b];
		Random rand = new Random();
		
		for(int row = 0; row < testArray.length; row++){
			
			for(int column = 0; column < testArray[0].length; column++){
				
				String randomizer = "";
				int max = 0;
				
				while(max < 3){
					
					randomizer += String.valueOf((char)(rand.nextInt(95)+32));
					max++;
					
				}
				
				testArray[row][column] = randomizer;
				
			}
		}	
		
		return testArray;
	}
	
	
	public static void replay(){
		
		main(new String[0]); //reruns the main method. calls the main method again. 
		
		
	}
	
	public static void search(String[][] testArray){
		String userInput = "";
		System.out.print("Enter character to be searched(maximum of 3 characters): ");
		userInput = console.next();
		int strLength = userInput.length();
		int counter = 0;
		int checkCounter = 0;
		if(strLength > 3){
			System.out.println("The input only accepts 3 characters. Try Again.");			
			//System.out.println(Arrays.deepToString(testArray).replace("], ", "]\n")); test lang kung may laman pa yung array pag ibinalik sa method pag mas marami yung input
			search(testArray);
		
		}else{
			if(strLength == 3){ //directly compares the inputted string and the element of the array. hmmm make another method for each if?...
				
				for(int row = 0; row < testArray.length; row++){
					
					for(int column = 0; column < testArray[0].length; column++){
						String str2 = testArray[row][column];
						
						if(userInput.equals(str2)){
							
							checkCounter++;
							counter++;
							System.out.print(userInput + " has " + counter + " instance(s) on " + row + " " + column );
							counter = 0;							
							System.out.println();
							
						}
						
					}					
					
				}
				
					if(checkCounter == 0){
						
						System.out.println(userInput + " not found ");
						
					}
				
			}else if (strLength == 2){ // makes a substring first with the number of characters depending on how many characters the user put in the search field. if length is 2 it makes a substring of 2 length from the original string of the array
				
				String charTemp = "";
				int maxChar = 3;				
				String subChar = "";				
				int ctr = 0;
				int check = 0;
				
				for(int row = 0; row < testArray.length; row++){
					
					for(int column = 0; column < testArray[0].length; column++){
						
						for(int minChar = 0; minChar <= 1; minChar++){	
						
							charTemp = testArray[row][column];
							subChar = charTemp.substring(minChar, maxChar);
							String str3 = subChar;
							boolean isFound = charTemp.contains(userInput);
							
							if(isFound){	
								
								ctr++;
								
							}else if(userInput.contains(str3)){
								
								ctr++;
								
							}
							
						}
						if(ctr >= 1){
							
							System.out.println(userInput + " has " + ctr + " instance(s) on " + row + " " + column);
							
						}
						
						ctr = 0;
						
					}
					
				}
				
				
			}else{
				
				String temp = "";
				String subTemp = "";
				int ctr2 = 0;
				char userChar = userInput.charAt(0);
				for(int row = 0; row < testArray.length; row++){
					
					for(int column = 0; column < testArray[0].length; column++){
						
						for(int minStr = 0; minStr <= 2; minStr++){
							
							temp = testArray[row][column];
							char tempCha = temp.charAt(minStr);
							if(tempCha == userChar){		
							
								ctr2++;								

							}
							
						}
						
						if(ctr2 >= 1){
							
							System.out.println(userInput + " has " + ctr2 + " instance(s) on " + row + " " + column);	
							
						}
						
						ctr2 = 0;
						
					}
					
				}
				
			}
			
			selectOption(testArray);		
				
		}			
		
	}
	
	public static void editArray(String[][] testArray){	
		
		String userChar = "";
		int userCharLength;
		while(true){
			
			try{
				System.out.print("Enter the row index: ");
				int row = Integer.parseInt(console.next());
				System.out.print("Enter the column index: ");
				int column = Integer.parseInt(console.next());
				int test = countRowLimit(testArray, row);
				int testTwo = countColumnLimit(testArray);				
				
				if((row < 0) || (column < 0) || (row > test) || (column > testTwo)){
					
					System.out.println("Invalid row and column");
					editArray(testArray);
					
				}else{
					
					System.out.print("Enter character you want to place(maximum of 3 only): ");
					userChar = console.next();
					userCharLength = userChar.length();
					
					if(userCharLength > 3){
						
						System.out.println("Maximum of 3 characters only");
						editArray(testArray);
						
					}else{
						
						testArray[row][column] = userChar;
						break;
						
					}
					
				}
				
			}catch(NumberFormatException e){
				
				System.out.println("Invalid Input");
				
			}catch(ArrayIndexOutOfBoundsException e){
				
				System.out.println("Index must be Dimension - 1. Try Again.");
				
			}
			
		}
		
		selectOption(testArray);
		
	}
	
	public static void selectOption(String[][] list){
		
		int firstInputerInt;
		
		while(true){
			
			try{ //prints the try block and re-ask the user to input a valid integer from 1-5
			
					System.out.println("Select One of the Options");
					System.out.println("1. Search");
					System.out.println("2. Edit");
					System.out.println("3. Print");
					System.out.println("4. Reset");
					System.out.println("5. Exit");
					System.out.print("Enter Desired Action: ");
					firstInputerInt = Integer.parseInt(console.next());
					
					if((firstInputerInt > 0) && (firstInputerInt <= 5)) {
						
						break; // breaks the loop and exits try catch if condition is satisified
						
					}
					
			} catch(NumberFormatException e){
				
					System.out.println("You have chosen an invalid option");// prints the statement if the input from the user is a char and not an integer 
			
			}
						
		}
		
		if(firstInputerInt == 1){
			
			search(list);
			
		}else if(firstInputerInt == 2){
			
			editArray(list);
			
		}else if(firstInputerInt == 3){
			
			printArray(list);
			
		}else if(firstInputerInt == 4){
			
			replay();
			
		}else{
			
		 System.exit(1);
		 
		}
	}
	
	
	public static void printArray(String[][] testArray){
		
		for(int row = 0; row < testArray.length; row++){
			
			for(int column = 0; column < testArray[0].length; column++){
				
				System.out.print(testArray[row][column] + "       ");
				
			}
			
			System.out.println();
			
		}
		
		selectOption(testArray);
		
	}
	
	public static int countRowLimit(String[][] testArray, int a){	//to validate the index input for editing the contents of array
		
		int ctrRow = 0;		
		
		for(int row = 0; row < testArray.length; row++){			
			
			ctrRow++;
			
		}
		
		return ctrRow;
		
	}
	
	public static int countColumnLimit(String [][] testArray){
		
		int ctrColumn = 0;
		
		for(int column = 0; column < testArray[0].length; column++){
			
			ctrColumn++;	
			
		}
		
		return ctrColumn;
		
	}
	
}


//System.out.println(Arrays.deepToString(list).replace("], ", "]\n"));	 just something

