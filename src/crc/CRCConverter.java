/*
 * Brandon Forster
 * CIS 3360 – Security in Computing Fall 2012
 * Programming Assignment 2
 * 7 November 2012
 */

package crc;

import java.util.*;
import java.io.*;


public class CRCConverter {

	public static void main(String[] args) {

		//set up the keyboard scanner
		Scanner stdin = new Scanner(System.in);
		
		String pathname;
		File userFile;
		//make the user input filenames until we find one we like
		while (true)
		{
			System.out.println("Please enter path/ name for file.");
			pathname= stdin.next();
			File tempFile= new File(pathname);
			
			if (tempFile.exists() == false)
			{
				System.out.println("Error: invalid pathname for file. Try again.");
				continue;
			}
			else
			{
				userFile= tempFile;
				break;
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
				//calculateCRC();

				//go verify CRC
			case 2:
				//verifyCRC();

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

}
