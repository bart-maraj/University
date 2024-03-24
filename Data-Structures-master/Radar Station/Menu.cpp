#include "Menu.h"
#include <iostream>
using namespace std;

int Menu::menu() 
{
	int exitValue;
	do
	{
		cout << "0 - Exit" << endl;
		cout << "1 - Run a simulation session" << endl;
		cout << "2 - Print the content of queues Q1 to Q5(maintaining the content immutable) and the number of elements of every queue." << endl;
		cout << "3 - Print the content of queues Type and Size (maintaining the content immutable)." << endl;
		cout << "4 - Search for the plates with the given number (the first three digits of the plate) equals to a given number(using keyboard)." << endl;
		cout << "5 - Statistics." << endl;
		cout << "6 - Verify." << endl;
		cout << "7 - List of selected type." << endl;
		cout << "\nEnter the option: ";
		cin >> exitValue;
		cout << endl;
	} while (exitValue < 0 || exitValue > 7);
	
	return exitValue;
}