/*
 * Brandon Forster
 * CIS 3360 � Security in Computing Fall 2012
 * Programming Assignment 2
 * 7 November 2012
 */

package crc;

import java.util.*;
import java.io.*;


public class CRCConverter {
	
	private static File userFile;

	public static void main(String[] args) {

		//set up the keyboard scanner
		Scanner stdin = new Scanner(System.in);

		String pathname;
		//make the user input filenames until we find one we like
		while (true)
		{
			System.out.println("Please enter path/ name for file.");
			pathname= stdin.next();
			File tempFile= new File(pathname);

			//check to make sure that the file the user entered exists
			if (tempFile.exists() == false)
			{
				System.out.println("Invalid pathname for file. Try again.");
				continue;
			}
			else
			{
				//if it does, copy the object to the file of higher scope.
				userFile= tempFile;
				
				//verifyFile checks that the input is only hex characters.
				//if it's not, it returns false and input loop begins again.
				if (verifyFile(userFile) == true)
					break;
				else
				{
					System.out.println("Input is not in hexadecimal format.");
					continue;
				}
			}
		}

		//an infinite loop that has program functionality in it.
		//will run until user tells it to exit.
		int userChoice= 0;
		while (userChoice != 3){

			//read in user input
			System.out.println("-------------Menu-------------");
			System.out.println("1: Calculate CRC");
			System.out.println("2: Verify CRC");
			System.out.println("3: Exit");

			//make sure the user inputs an integer, like they're supposed to
			try
			{
				userChoice = stdin.nextInt();
			}
			catch (InputMismatchException e)
			{
				//tell the user they dun goofed
				System.out.println("Please enter a valid choice.\n");
				//eat the input from the scanner so we don't get stuck
				stdin.next();
				continue;
			}

			switch (userChoice){

			//the user wants to calculate CRC, go do that
			case 1:
				calculateCRC();

				//go verify CRC
			case 2:
				verifyCRC();

				//exit the program normally
			case 3:
				System.exit(0);

				//tell the user they entered something weird
			default:
			{
				System.out.println("Please enter a valid choice.\n");
				continue;
			}
			}

		}
	}
	
	public static void calculateCRC()
	{
		//print out the input file
		System.out.println(getInputAsString());
		
		//print out a binary representation of the input hex
		printBinary(hexToBinary(getInputAsString()));
	}

	public static void verifyCRC()
	{

	}
	
	public static String getInputAsString()
	{
		String inputString= "";
		try {
			Scanner scn = new Scanner(userFile);
			
			//while the scanner can find strings in the input.
			while (scn.hasNext())
				inputString = inputString + scn.next();
			
			//this should never run ever.
		}	catch (FileNotFoundException e) {
			System.out.println("Something went wrong...");
		}
		
		return inputString;
	}
	
	public static boolean verifyFile(File input)
	{
		try {
			Scanner hexScanner = new Scanner(input);
			
			//changes the delimiter to the empty string so that .next()
			//returns one character at a time.
			hexScanner.useDelimiter("");
			
			while (hexScanner.hasNext() == true)
			{
				//converts the read in string to a char, then checks if it is
				//in valid ascii ranges
				char check = hexScanner.next().toUpperCase().toCharArray()[0];
				if (check < '0')
					return false;
				else if (check > 'F')
					return false;
				else if (check > '9' && check < 'A')
					return false;
			}
			//if it passed above checks, it must be okay.
			return true;
			
			//this should never run ever.
		}	catch (FileNotFoundException e) {
			System.out.println("Something went wrong...");
			return false;
		}
	}
	
	public static String hexToBinary(String hexNumber)
	{
	    int temp = Integer.parseInt(hexNumber, 16);
	    String binaryNumber = Integer.toBinaryString(temp);
	    return binaryNumber;
	}
	
	public static void printBinary(String binaryNumber)
	{
		//uses regexes that I looked up how to do online to perform
		//required ops.
		binaryNumber= binaryNumber.replaceAll(".{32}", "$0\n");
		binaryNumber= binaryNumber.replaceAll(".{4}", "$0 ");
		
		System.out.print(binaryNumber);
	}
	
	public static String binaryToHex (String binaryNumber)
	{
		int temp = Integer.parseInt(binaryNumber, 2);
	    String hexNumber = Integer.toHexString(temp);
		return hexNumber;
	}
}