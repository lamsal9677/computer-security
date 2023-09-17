/**
 * @file FreqDist.cpp
 * @brief This file contains the main function and all functions for the Lab1 program called Frequency Distribution
 * @author Sanskar Lamsal
 * @Subject Computer Security
 * @Assignment Lab 1
 * @Date 09/17/2023
 *
 * This program is a program to look at Frequency Distribution of characters in a given file
 *
*/

//including the required to run the program
#include <iostream>
#include <fstream>
#include <string>

//using the standard namespace
using namespace std;

//declaring the functions
void printNumberScale();
void printASCIIScale();
void printHorizonalLine();


int main(int argc, char* argv[]) {
	
		//declaring the variables from the command line
		int scale = 20;
		int nFlag = 0;
		int aFlag = 0;
		int rFlag = 0;
		int lFlag = 0;
		string fileName = "";

		for (int i = 1; i < argc; i++) {
			string arg = argv[i];

			if (arg.substr(0, 1) == "-") {
				if (arg == "-a")
					aFlag = 1;
				else if (arg == "-n")
					nFlag = 1;
				else if (arg == "-l")
					lFlag = 1;
				else if (arg.length() > 2 && arg.substr(0, 2) == "-r") {
					scale = stoi(arg.substr(2));
					if (scale == 0) {
						cerr << "Scale is zero" << endl;
						return 1;
					}
				}
				else {
					cerr << "Invalid Argument" << endl;
					return 1;
				}
			}
			else {
				fileName = arg;
				if (fileName.compare("") == 0) {
					cerr << "Filename can't be Empty";
					return 1;
				}
			}
				
		}

	unsigned int count[256] = {0}; // Array to store the count of each character
	unsigned int c2[256] = {0}; // Array to store the scaled down count of each character
	char ch; // Variable to store the character read from the file

	/*
	Read the file and store the count of each character in the array
	*/
	ifstream projFile(fileName, ios::binary); // Open the file in binary mode
	if (!projFile.is_open()) { // Check if the file is open
		cerr << "Input File not Found" << endl; // If not, print error message and exit
		return 1; // Return 1 to indicate error
	}
	while (projFile.get(ch)) // Read the file character by character
		count[(unsigned char)ch]++; // Cast to unsigned char to ensure we don't access negative indexes.
	projFile.close(); // Close the file

	if (lFlag == 1) {
		for (int i = 0; i <= 255; i++) {
			count[i] = log(count[i]);
		}
	}

	/* 
	Find the maximum Repeated in the array and scale down the graph
	*/
	unsigned int maxRepeated = 0; // Variable to store the maximum repeated
	unsigned int minRepeated = 4294967295; // Variable to store the minimum repeated
	unsigned int maxChar;
	unsigned long long sum = 0;
	double average = 0;

	for (int i = 0; i < 256; i++) { // Find the maximum repeated
		//cout << i << ": " << count[i] << endl;
		sum = sum + count[i];
		// If count[i] is greater than maxRepeated, set maxRepeated to count[i]
		if (count[i] > maxRepeated) {
			maxRepeated = count[i];
			maxChar = i;
		}

		if (count[i] > 0) {
			if (count[i] < minRepeated) {
				minRepeated = count[i];
			}
		}
	}
	//cout << "Sum: " << sum << endl;
	average = (double)sum / 256;


	double scaleFactor = (double)scale / maxRepeated; // Calculate the scale factor

	for (int i = 0; i <= 255; i++) // Scale down the count of each character
		c2[i] = static_cast<int>(ceil(count[i] * scaleFactor)); // Round up the scaled down count and store in c2

	printHorizonalLine(); // Print the horizontal line

	/* 
	Display the bar graph
	*/
	for (int i = scale; i > 0; i--) { // Loop from the top of the graph to the bottom
		std::cout << "|";
		for (int j = 0; j < 256; j++) {
			if (c2[j] >= i)
				std::cout << "*"; // Print * if the scaled down count is greater than or equal to the current row
			else
				std::cout << " "; // Print space otherwise
		}
		std::cout << "|";
		std::cout << endl;
	}

	printHorizonalLine(); // Print the horizontal line
	if(nFlag == 1) {
		printNumberScale(); // Print the number scale
	}
	if(aFlag == 1) {
		printASCIIScale();	// Print the ASCII scale
	}
	
	cout << endl;
	
	double variance = 0.0;
	double standDev = 0.0;
	unsigned long long countSumSq = 0;
	unsigned long long countSumSqTotal = 0;
	for (int i = 0; i < 256; i++) {
		countSumSq = countSumSq + (count[i] * count[i]);
	}

	countSumSqTotal = sum * sum;
	variance = static_cast<double>(countSumSq - (countSumSqTotal / 256)) / 256;
	standDev = sqrt(variance);
	//cout << "countSumSq: " << countSumSq << endl;
	//cout << "countSumSqTotal: " << countSumSqTotal << endl;
	//cout << "(countSumSqTotal / 256):" << (countSumSqTotal / 256) << endl;

	//cout << "countSumSq - (countSumSqTotal / 256):" << countSumSq - (countSumSqTotal / 256) << endl;
	cout << "MIN:" <<minRepeated<<" MAX  "<< "("<<maxChar<<"):" <<maxRepeated<< " AVG:" << average << " DEV:" << standDev;

	return 0;
}
/*
printNumberScale function takes in no input and prints the number scale
*/

void printNumberScale() {
	std::cout << "|";
	//Printing the number
	for (int i = 0; i <= 255; i++) {
		int hundreds = i / 100;
		//int tens = (i / 10) % 10;
		//int ones = i % 10;

		if (hundreds == 0) {
			std::cout << " ";
			continue;
		}

		// Display all the hundreds in one row
		std::cout << hundreds;
	}
	std::cout << "|";
	std::cout << endl;
	std::cout << "|";
	for (int i = 0; i <= 255; i++) {
		//int hundreds = i / 100;
		int tens = (i / 10) % 10;
		//int ones = i % 10;

		if (i < 10 && tens == 0) {
			std::cout << " ";
			continue;
		}

		// Display all the tens in one row
		std::cout << tens;
	}
	std::cout << "|";
	std::cout << endl;
	std::cout << "|";
	for (int i = 0; i <= 255; i++) {
		//int hundreds = i / 100;
		//int tens = (i / 10) % 10;
		int ones = i % 10;

		// Display all the ones in one row
		std::cout << ones;
	}
	std::cout << "|";
	std::cout << endl;
}

/*
printASCIIScale function takes in no input and prints the ASCII scale
*/
void printASCIIScale() {////Need to figure out if this is actully correct>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	std::cout << "|";
	for (int i = 0; i <= 255; i++) {
		if (i < 33 || i > 126)
			std::cout << " ";
		else
			std::cout << static_cast<char>(i);
	}
	std::cout << "|";
	std::cout << endl;
}

/*
printHorizonalLine function takes in no input and prints a horizontal line
*/
void printHorizonalLine() {
	for (int i = 0; i <= 257; i++) {
		if (i == 0 || i == 257)
			std::cout << "+";
		else
			std::cout << "-";
	}
	std::cout << endl;
}