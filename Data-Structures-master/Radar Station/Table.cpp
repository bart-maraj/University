#include <iostream>
#include <time.h>
#include <stdlib.h>

#include "Table.h"

using namespace std;

table::table()
{
	for (int i = 0; i < 5; i++)
	{
		for (int j = 0; j < 7; j++)
		{
			tableFO[i][j] = 0;
		}
	}
}

table::~table()
{
}

void table::printTable(bool hash, Session givenSession)
{
	unsigned t0, t1;
	int x = 1;
	

	if (hash)
	{
		t0 = clock();
		hashStructure hashTable = givenSession.getHashStructure();

		cout << "--------------------------------------------------------------------------------------------------------------------" << endl;
		cout << "--------------------------------------------------------------------------------------------------------------------" << endl;
		cout << "Statistics - Hash Table Data \n " << endl;
		cout << "			Drone		Plane		Bird		Superlopez	UFO		Ghost		Flying Rock" << endl;
		cout << "Huge			" << hashTable.searchFOTypeSize("Drone", "Huge") << "		" << hashTable.searchFOTypeSize("Plane", "Huge") << "		" << hashTable.searchFOTypeSize("Bird", "Huge") << "		" << hashTable.searchFOTypeSize("Superlopez", "Huge") << "		" << hashTable.searchFOTypeSize("UFO", "Huge") << "		" << hashTable.searchFOTypeSize("Ghost", "Huge") << "		" << hashTable.searchFOTypeSize("FlyingRock", "Huge") << endl;
		cout << "Big			" << hashTable.searchFOTypeSize("Drone", "Big") << "		" << hashTable.searchFOTypeSize("Plane", "Big") << "		" << hashTable.searchFOTypeSize("Bird", "Big") << "		" << hashTable.searchFOTypeSize("Superlopez", "Big") << "		" << hashTable.searchFOTypeSize("UFO", "Big") << "		" << hashTable.searchFOTypeSize("Ghost", "Big") << "		" << hashTable.searchFOTypeSize("FlyingRock", "Big") << endl;
		cout << "Medium			" << hashTable.searchFOTypeSize("Drone", "Medium") << "		" << hashTable.searchFOTypeSize("Plane", "Medium") << "		" << hashTable.searchFOTypeSize("Bird", "Medium") << "		" << hashTable.searchFOTypeSize("Superlopez", "Medium") << "		" << hashTable.searchFOTypeSize("UFO", "Medium") << "		" << hashTable.searchFOTypeSize("Ghost", "Medium") << "		" << hashTable.searchFOTypeSize("FlyingRock", "Medium") << endl;
		cout << "Small			" << hashTable.searchFOTypeSize("Drone", "Small") << "		" << hashTable.searchFOTypeSize("Plane", "Small") << "		" << hashTable.searchFOTypeSize("Bird", "Small") << "		" << hashTable.searchFOTypeSize("Superlopez", "Small") << "		" << hashTable.searchFOTypeSize("UFO", "Small") << "		" << hashTable.searchFOTypeSize("Ghost", "Small") << "		" << hashTable.searchFOTypeSize("FlyingRock", "Small") << endl;
		cout << "Tiny			" << hashTable.searchFOTypeSize("Drone", "Tiny") << "		" << hashTable.searchFOTypeSize("Plane", "Tiny") << "		" << hashTable.searchFOTypeSize("Bird", "Tiny") << "		" << hashTable.searchFOTypeSize("Superlopez", "Tiny") << "		" << hashTable.searchFOTypeSize("UFO", "Tiny") << "		" << hashTable.searchFOTypeSize("Ghost", "Tiny") << "		" << hashTable.searchFOTypeSize("FlyingRock", "Tiny") << endl;
		
		t1 = clock();

		cout << "\nTime:" << (double(t1 - t0) / CLOCKS_PER_SEC) << endl;
	}
	else
	{
		t0 = clock();
		getValuesTable(givenSession);

		cout << "\nStatistics - Stack Data \n " << endl;
		cout << "			Drone		Plane		Bird		Superlopez	UFO		Ghost		Flying Rock" << endl;
		cout << "Huge			" << tableFO[0][0] << "		" << tableFO[0][1] << "		" << tableFO[0][2] << "		" << tableFO[0][3] << "		" << tableFO[0][4] << "		" << tableFO[0][5] << "		" << tableFO[0][6] << endl;
		cout << "Big			" << tableFO[1][0] << "		" << tableFO[1][1] << "		" << tableFO[1][2] << "		" << tableFO[1][3] << "		" << tableFO[1][4] << "		" << tableFO[1][5] << "		" << tableFO[1][6] << endl;
		cout << "Medium			" << tableFO[2][0] << "		" << tableFO[2][1] << "		" << tableFO[2][2] << "		" << tableFO[2][3] << "		" << tableFO[2][4] << "		" << tableFO[2][5] << "		" << tableFO[2][6] << endl;
		cout << "Small			" << tableFO[3][0] << "		" << tableFO[3][1] << "		" << tableFO[3][2] << "		" << tableFO[3][3] << "		" << tableFO[3][4] << "		" << tableFO[3][5] << "		" << tableFO[3][6] << endl;
		cout << "Tiny			" << tableFO[4][0] << "		" << tableFO[4][1] << "		" << tableFO[4][2] << "		" << tableFO[4][3] << "		" << tableFO[4][4] << "		" << tableFO[4][5] << "		" << tableFO[4][6] << endl;
		
		t1 = clock();

		cout << "\nTime:" << (double(t1 - t0) / CLOCKS_PER_SEC) << endl;
		cout << "--------------------------------------------------------------------------------------------------------------------" << endl;
		cout << "--------------------------------------------------------------------------------------------------------------------" << endl << endl;

	}
}

void table::getValuesTable(Session givenSession)
{
	int row, column;
	string size, type;
	stack auxStackOfObjects = *new stack();
	FlyingObject currentFlyingObject = *new FlyingObject();

	auxStackOfObjects = givenSession.getStObject();

	while (!auxStackOfObjects.isEmptyStack())
	{
		currentFlyingObject = auxStackOfObjects.pop();

		size = currentFlyingObject.getSize();
		type = currentFlyingObject.getType();

		row = getRowTable(size);
		column = getColumnTable(type);

		tableFO[row][column]++;
	}
}

int table::getColumnTable(string type)
{
	int column = 0;
	if (type == "Drone")
	{
		column = 0;
	}
	else if (type == "Plane")
	{
		column = 1;
	}
	else if (type == "Bird")
	{
		column = 2;
	}
	else if (type == "Superlopez")
	{
		column = 3;
	}
	else if (type == "UFO")
	{
		column = 4;
	}
	else if (type == "Ghost")
	{
		column = 5;
	}
	else
	{
		column = 6;
	}
	return column;
}

int table::getRowTable(string size)
{
	int row = 0;
	if (size == "Huge")
	{
		row = 0;
	}
	else if (size == "Big")
	{
		row = 1;
	}
	else if (size == "Medium")
	{
		row = 2;
	}
	else if (size == "Small")
	{
		row = 3;
	}
	else
	{
		row = 4;
	}
	return row;
}
