package image;

//Java Program to illustrate Reading from FileReader
//using BufferedReader Class

//Importing input output classes
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

//Main class
public class GFG {

	// main driver method
	public static void main(String[] args) throws Exception
	{

		// File path is passed as parameter
		File file = new File(
			"C:\\Users\\Jonathan\\eclipse-workspace\\AssignmentJava\\src\\file.db");

		// Note: Double backquote is to avoid compiler
		// interpret words
		// like \test as \t (ie. as a escape sequence)

		// Creating an object of BufferedReader class
		BufferedReader br
			= new BufferedReader(new FileReader(file));
		List<String> words = new ArrayList<>();
		
		//kEYBOARD INPUT
		
		Scanner keybord = new Scanner(System.in);

		// Declaring a string variable
		String st;
		// Condition holds true till
		// there is character in a string
		while ((st = br.readLine()) != null)
		{
			words.add(br.readLine());
		}
		
		Random rand = new Random();
		st = words.get(rand.nextInt(words.size()));
		System.out.println(st);
		
		List<Character> userGuess = new ArrayList<>();
		
		
		while(true) {
			printWordState(st, userGuess);
			getPlayerGuess(keybord, st, userGuess);
			if(printWordState(st, userGuess))
			{
				System.out.println("You won!");
				break;
			}
			System.out.println("Please print your guess for the world");
			if(keybord.next().equals(st))
			{
				System.out.println("You won!");
				break;
			}
			else {
				System.out.println("Nope Try Again!!!");
			}
		}
		

			
	}

	private static void getPlayerGuess(Scanner keybord, String st, List<Character> userGuess) {
		System.out.println("Please Enter A LETTER: ");
		String letterGuess = keybord.next();
		userGuess.add(letterGuess.charAt(0));
		
	}

	private static boolean printWordState(String st, List<Character> userGuess) {
		int correctCount = 0;
		for(int i = 0; i< st.length(); i++)
		{
			if(userGuess.contains(st.charAt(i))) {
				System.out.print(st.charAt(i));
				correctCount++;
			}
			else {
				System.out.print("_");
			}
		}
		System.out.println();
		return (st.length() == correctCount);
	}
}

