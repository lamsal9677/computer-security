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

using namespace std;

void printNumberScale();
void printASCIIScale();
void printHorizonalLine();


int main() {
	int scale = 1;
	int logScale = false;
	string fileName = "Shakespeare.txt";

	unsigned int count[256] = {0};
	unsigned int c2[256] = {0};
	char ch;

	//open file in read only binary
	ifstream projFile(fileName, ios::binary);
	if (!projFile.is_open()) {
		cerr << "Input File not Found" << endl;
		return 1;
	}
	while (projFile.get(ch))
		count[(unsigned char)ch]++; // Cast to unsigned char to ensure we don't access negative indexes.
	projFile.close();


	// Find the maximum Repeated in the array and scale down the graph
	unsigned int maxRepeated = 0;
	for (int i = 0; i < 256; i++)
		maxRepeated = (count[i] > maxRepeated) ? count[i] : maxRepeated;
	double scaleFactor = (double)scale / maxRepeated;
	for (int i = 0; i <= 255; i++)
		c2[i] = static_cast<int>(ceil(count[i] * scaleFactor));

	printHorizonalLine();

	// Display the bar graph
	for (int i = scale; i > 0; i--) {
		std::cout << "|";
		for (int j = 0; j < 256; j++) {
			if (c2[j] >= i)
				std::cout << "*";
			else
				std::cout << " ";
		}
		std::cout << "|";
		std::cout << endl;
	}

	printHorizonalLine();
	printNumberScale();
	printASCIIScale();
	return 0;
}
void printNumberScale() {
	std::cout << "|";
	//Printing the number
	for (int i = 0; i <= 255; i++) {
		int hundreds = i / 100;
		int tens = (i / 10) % 10;
		int ones = i % 10;

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
		int hundreds = i / 100;
		int tens = (i / 10) % 10;
		int ones = i % 10;

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
		int hundreds = i / 100;
		int tens = (i / 10) % 10;
		int ones = i % 10;

		// Display all the ones in one row
		std::cout << ones;
	}
	std::cout << "|";
	std::cout << endl;
}
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
void printHorizonalLine() {
	for (int i = 0; i <= 257; i++) {
		if (i == 0 || i == 257)
			std::cout << "+";
		else
			std::cout << "-";
	}
	std::cout << endl;
}