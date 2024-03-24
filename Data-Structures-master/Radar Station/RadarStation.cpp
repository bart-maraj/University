#include "RadarStation.h"

RadarStation::RadarStation()
{
	listOfSessions = *new list();
	lastSession = *new Session();
}

RadarStation::~RadarStation()
{
}

int RadarStation::runRadar()
{
	Menu menuInterface;
	int chosenOption;

	chosenOption = menuInterface.menu();
	optionSwitch(chosenOption);

	return chosenOption;
}

void RadarStation::optionSwitch(int chosenOption) 
{
	switch (chosenOption)
	{
		case 1:
			try
			{
				option1();
			}
			catch (...)
			{
				cout << "Exception occured \n";
			}
			break;
		case 2:
			try
			{
				option2();
			}
			catch (...)
			{
				cout << "Exception occured \n";
			}
			break;
		case 3:
			try
			{
				option3();
			}
		catch (...)
			{
				cout << "Exception occured \n";
			}
			break;
		case 4:
			try
			{
				option4();
			}
			catch (...)
			{
				cout << "Exception occured \n";
			}
			break;
		case 5:
			try
			{
				option5();
			}
			catch (...)
			{
				cout << "Exception occured \n";
			}
			break;
		case 6:
			try
			{
				option6();
			}
			catch (...)
			{
				cout << "Exception occured \n";
			}
			break;
		case 7:
			try
			{
				option7();
			}
			catch (...)
			{
				cout << "Exception occured \n";
			}
			break;
		default:
			break;
	}
}

void RadarStation::option1()																// Run a simulation session
{	
	lastSession = *new Session();															// Initializes a new sesion.

	printOption1(true);

	lastSession.createStack();																// Creates a new stack of objects.
	listOfSessions.insert(lastSession, listOfSessions.getHeader());							// Sesion inserted in list.

	printOption1(false);
}

void RadarStation::option2()																					// Print the content of queues Q1 to Q5
{							
	cout << "--------------------------------------------------------------------------------------------------------------------" << endl;
	cout << "--------------------------------------------------------------------------------------------------------------------" << endl;

	cout << "Plate 1:\n	Content:" << lastSession.stringQueue(lastSession.getPlate1Queue());								// Prints the queue	
	cout << "	Number of elements: " << lastSession.getSizeGivenQueue(lastSession.getPlate1Queue()) << endl << endl;		// Prints the size of the queue

	cout << "Plate 2:\n	Content:" << lastSession.stringQueue(lastSession.getPlate2Queue());
	cout << "	Number of elements: " << lastSession.getSizeGivenQueue(lastSession.getPlate2Queue()) << endl << endl;

	cout << "Plate 3:\n	Content:" << lastSession.stringQueue(lastSession.getPlate3Queue());
	cout << "	Number of elements: " << lastSession.getSizeGivenQueue(lastSession.getPlate3Queue()) << endl << endl;

	cout << "Plate 4:\n	Content:" << lastSession.stringQueue(lastSession.getPlate4Queue());
	cout << "	Number of elements: " << lastSession.getSizeGivenQueue(lastSession.getPlate4Queue()) << endl << endl;

	cout << "Plate 5:\n	Content:" << lastSession.stringQueue(lastSession.getPlate5Queue());
	cout << "	Number of elements: " << lastSession.getSizeGivenQueue(lastSession.getPlate5Queue()) << endl;

	cout << "--------------------------------------------------------------------------------------------------------------------" << endl;
	cout << "--------------------------------------------------------------------------------------------------------------------" << endl << endl;
}

void RadarStation::option3()																// Print the content of queues Type and Size
{				
	cout << "--------------------------------------------------------------------------------------------------------------------" << endl;
	cout << "--------------------------------------------------------------------------------------------------------------------" << endl;

	cout << "Type queue: " << lastSession.stringQueue(lastSession.getTypeQueue()) << endl;
	cout << "Size queue: " << lastSession.stringQueue(lastSession.getSizeQueue());

	cout << "--------------------------------------------------------------------------------------------------------------------" << endl;
	cout << "--------------------------------------------------------------------------------------------------------------------" << endl << endl;
}

void RadarStation::option4()
{																									//Search for the plates with the given number
	string plateNumbers;

	cout << "Enter the number to be searched ( xxx where x = 0-9 ): ";
	cin >> plateNumbers;																			// Reads the "xxx" string (x = 0-9)
	cout << endl;
																									// Gets a queue with the plates that start with the given numbers "xxx??"
	printOption4(true, plateNumbers);
	printOption4(false, plateNumbers);
}

void RadarStation::option5()																
{
	table tableFO = *new table();

	tableFO.printTable(true, lastSession);
	tableFO.printTable(false, lastSession);
}

void RadarStation::option6()																
{
	printOption6(true);
	printOption6(false);
}

void RadarStation::option7()																
{
	string type, size, origin, plate;
	stack searchedFlyingObjectsStack = *new stack();
	FlyingObject printedFlyingObject = *new FlyingObject();
	
	cout << "Introduce the specific values of the Flying Object: \n";
	cout << "Type(-1 to not specify the type): ";
	cin >> type;
	cout << "Size(-1 to not specify the size): ";
	cin >> size;
	cout << "Origin(-1 to not specify the origin): ";
	cin >> origin;
	cout << "Plate(-1 to not specify the plate): ";
	cin >> plate;

	printOption7(true, type, size, origin, plate);

	printOption7(false, type, size, origin, plate);
}

void RadarStation::printPlateQueues()
{
	cout << "	Number of elements plate queue 1: " << lastSession.getSizeGivenQueue(lastSession.getPlate1Queue()) << endl;
	cout << "	Number of elements plate queue 2: " << lastSession.getSizeGivenQueue(lastSession.getPlate2Queue()) << endl;
	cout << "	Number of elements plate queue 3: " << lastSession.getSizeGivenQueue(lastSession.getPlate3Queue()) << endl;
	cout << "	Number of elements plate queue 4: " << lastSession.getSizeGivenQueue(lastSession.getPlate4Queue()) << endl;
	cout << "	Number of elements plate queue 5: " << lastSession.getSizeGivenQueue(lastSession.getPlate5Queue()) << endl;
}

void RadarStation::printOption1(bool before)
{
	
	if (before)
	{
		cout << "--------------------------------------------------------------------------------------------------------------------" << endl;
		cout << "--------------------------------------------------------------------------------------------------------------------" << endl;

		cout << "Before the session: \n" << endl;
		printPlateQueues();
	}
	else
	{
		cout << endl << endl;
		cout << "After the session: \n" << endl;
		printPlateQueues();

		cout << "\nNumber of elements generated " << lastSession.getNumberObjects() << endl;
		cout << "Number of Tiny-Superlopez-from Surprise: " << lastSession.getNumberSTS() << endl;
		cout << "Time for creation and insertion: " << lastSession.getTimeAverage() << endl;
		cout << "Time for whole operation: " << lastSession.getTimeOp() << endl;

		cout << "--------------------------------------------------------------------------------------------------------------------" << endl;
		cout << "--------------------------------------------------------------------------------------------------------------------" << endl << endl;


	}
}

void RadarStation::printOption4(bool hash, string plateNumbers)
{
	unsigned t0, t1;
	if (hash)
	{
		cout << "--------------------------------------------------------------------------------------------------------------------" << endl;
		cout << "--------------------------------------------------------------------------------------------------------------------" << endl;
		t0 = clock();
		lastSession.getHashStructure().printPlatesByNumber(plateNumbers);
		t1 = clock();
		cout << "\nTime:" << (double(t1 - t0) / CLOCKS_PER_SEC) << endl << endl;
	}
	else
	{
		t0 = clock();
		lastSession.printPlatesByNumber(plateNumbers);
		t1 = clock();
		cout << "\nTime:" << (double(t1 - t0) / CLOCKS_PER_SEC) << endl;
		cout << "--------------------------------------------------------------------------------------------------------------------" << endl;
		cout << "--------------------------------------------------------------------------------------------------------------------" << endl << endl;
	}
}

void RadarStation::printOption6(bool hash)
{
	unsigned t0, t1, t2, t3, totalTime;

	if (hash)
	{
		cout << "--------------------------------------------------------------------------------------------------------------------" << endl;
		cout << "--------------------------------------------------------------------------------------------------------------------" << endl;
		cout << "Flying Objects - Hash Table Data\n\n";
		cout << "	Plate			Size			Origin			Type		\n\n";
		
		t0 = clock();
		lastSession.getHashStructure().printAllFlyingObjects();
		t1 = clock();

		cout << "\nTime to print - Hash Table: " << (double(t1 - t0) / CLOCKS_PER_SEC) << endl;
	}
	else
	{
		cout << "\nFlying Objects -Stack Data\n\n";
		cout << "	Plate			Size			Origin			Type		\n\n";

		t2 = clock();
		lastSession.printAllFlyingObjects();
		t3 = clock();

		cout << "\nTime to print - Stack: " << (double(t3 - t2) / CLOCKS_PER_SEC) << endl;

		cout << "\nNumber of Tiny-Superlopez-from Surprise: " << lastSession.getNumberSTS() << endl;
		t0 = clock();
		cout << "Number of Tiny-Superlopez-from Surprise - Hash Table: " << lastSession.getHashStructure().countFlyingObject("Superlopez", "Tiny", "Surprise") << endl;
		t1 = clock();
		cout << "Number of Tiny-Superlopez-from Surprise - Stack: " << lastSession.countFlyingObjects("Superlopez", "Tiny", "Surprise") << endl;
		t2 = clock();

		cout << "\nTime to count Tiny-Superlopez-from Surprise elements - Hash Table: " << (double(t1 - t0) / CLOCKS_PER_SEC) << endl;
		cout << "Time to count Tiny-Superlopez-from Surprise element - Stack: " << (double(t2 - t1) / CLOCKS_PER_SEC) << endl;

		cout << "\nNumber of elements generated: " << lastSession.getNumberObjects() << endl;
		t0 = clock();
		cout << "Number of elements generated - Hash Table: " << lastSession.getHashStructure().countFlyingObject("-1", "-1", "-1") << endl;
		t1 = clock();
		cout << "Number of elements generated - Stack: " << lastSession.countFlyingObjects("-1", "-1", "-1") << endl;
		t2 = clock();

		cout << "\nTime to count all elements - Hash Table: " << (double(t1 - t0) / CLOCKS_PER_SEC) << endl;
		cout << "Time to count all elements - Stack: " << (double(t2 - t1) / CLOCKS_PER_SEC) << endl;


		cout << "--------------------------------------------------------------------------------------------------------------------" << endl;
		cout << "--------------------------------------------------------------------------------------------------------------------" << endl << endl;
	}
}

void RadarStation::printOption7(bool hash, string type, string size, string origin, string plate)
{
	stack searchedFlyingObjectsStack = *new stack();
	FlyingObject printedFlyingObject = *new FlyingObject();


	if (hash)
	{
		cout << "--------------------------------------------------------------------------------------------------------------------" << endl;
		cout << "--------------------------------------------------------------------------------------------------------------------" << endl;
		cout << "Flying Objects - Hash Table Data\n\n";
		cout << "	Plate			Size			Origin			Type		\n\n";

		lastSession.getHashStructure().printFlyingObject(plate,type, size, origin);
	}
	else
	{
		cout << "\nFlying Objects -Stack Data\n\n";
		cout << "	Plate			Size			Origin			Type		\n\n";

		lastSession.printFlyingObjects(type, size, origin, plate);

		cout << "--------------------------------------------------------------------------------------------------------------------" << endl;
		cout << "--------------------------------------------------------------------------------------------------------------------" << endl << endl;
	}
}

