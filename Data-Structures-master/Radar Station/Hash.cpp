#include "Hash.h"
using namespace std;

hashStructure::hashStructure()
{
	for (int i = 0; i < 1000; i++)
	{
		hashTable[i] = new nodeHash();
	}
}

hashStructure::~hashStructure()
{

}

int hashStructure::hashFunction(string plate)
{
	int firstNumber = (int)plate.at(0) - 48;			//ASCII
	int secondNumber = (int)plate.at(1) - 48;			//ASCII
	int thirdNumber = (int)plate.at(2) - 48;			//ASCII

	int index = ( firstNumber * 100 ) + ( secondNumber * 10 ) + thirdNumber;

	return index;
}

void hashStructure::insertFlyingObject(FlyingObject givenObject, string plate)
{
	int index = hashFunction(plate);

	if (hashTable[index]->key == "")
	{
		hashTable[index]->key = plate;
		hashTable[index]->object = givenObject;
	}
	else
	{
		nodeHash *linkedElement = hashTable[index];
		nodeHash *auxPtr = new nodeHash();

		auxPtr->key = plate;
		auxPtr->object = givenObject;
		auxPtr->next = NULL;

		while (linkedElement->next != NULL)
		{
			linkedElement = linkedElement->next;
		}
		linkedElement->next = auxPtr;
	}
}

void hashStructure::printFlyingObject(string plate, string type, string size, string origin)
{
	int index = 0;
	nodeHash* linkedElement;

	if (plate == "-1")
	{
		for (int i = 0; i < 1000; i++)
		{
			linkedElement = hashTable[i];

			while (linkedElement != NULL)
			{
				if (type == linkedElement->object.getType() || type == "-1")
				{
					if (size == linkedElement->object.getSize() || size == "-1")
					{
						if (origin == linkedElement->object.getOrigin() || origin == "-1")
						{
							if (linkedElement->key != "") 
							{
								cout << "	" << linkedElement->object.getPlate() << "			" << linkedElement->object.getSize() << "			" << linkedElement->object.getOrigin() << "			" << linkedElement->object.getType() << endl;
							}
						}
					}
				}
				linkedElement = linkedElement->next;
			}
		}
	}
	else
	{
		index = hashFunction(plate);

		nodeHash* linkedElement = hashTable[index];

		while (linkedElement != NULL)
		{
			if (type == linkedElement->object.getType() || type == "-1")
			{
				if (size == linkedElement->object.getSize() || size == "-1")
				{
					if (origin == linkedElement->object.getOrigin() || origin == "-1")
					{
						if (plate == linkedElement->object.getPlate())
						{
							cout << "	" << linkedElement->object.getPlate() << "			" << linkedElement->object.getSize() << "			" << linkedElement->object.getOrigin() << "			" << linkedElement->object.getType() << endl;
						}
					}
				}
			}
			linkedElement = linkedElement->next;
		}
	}
}

int hashStructure::countFlyingObject(string type, string size, string origin)
{
	int index = 0;
	int countedElements = 0;
	nodeHash* linkedElement;

	for (int i = 0; i < 1000; i++)
	{
		linkedElement = hashTable[i];
		while (linkedElement != NULL)
		{
			if (type == linkedElement->object.getType() || type == "-1")
			{
				if (size == linkedElement->object.getSize() || size == "-1")
				{
					if (origin == linkedElement->object.getOrigin() || origin == "-1")
					{
						if (linkedElement->key != "")
						{
							countedElements++;
						}
					}
				}
			}
			linkedElement = linkedElement->next;
		}
	}
	return countedElements;
}

int hashStructure::searchFOTypeSize(string type, string size)
{
	int appearanceFO = 0;
	FlyingObject searchedFO = *new FlyingObject();
	
	nodeHash* linkedElement = new nodeHash();
	for (int i = 0; i < 1000; i++)
	{
		linkedElement = hashTable[i];

		while (linkedElement != NULL)
		{
			if (type == linkedElement->object.getType())
			{
				if (size == linkedElement->object.getSize())
				{
					appearanceFO++;
				}
			}
			linkedElement = linkedElement->next;
		}
	}
	return appearanceFO;
}

void  hashStructure::printPlatesByNumber(string numbers)
{
	int column = 0;
	int index = hashFunction(numbers);
	nodeHash* linkedElement = hashTable[index];

	cout << "Hash Table Data\n\n";

	while (linkedElement != NULL && linkedElement->key != "")
	{
			if (column < 6)
			{
				cout << "| " << linkedElement->key << " ";
				column++;
			}
			else
			{
				cout << "| " << linkedElement->key << " |" << endl;
				column = 0;
			}
			if (linkedElement->next == NULL)
			{
				cout << "|" << endl;
			}
		
		linkedElement = linkedElement->next;
	}
}

void  hashStructure::printAllFlyingObjects()
{
	int index = 0;
	nodeHash* linkedElement;

	for (int i = 0; i < 1000; i++)
	{
		linkedElement = hashTable[i];
		if (linkedElement->key != "")
		{
			cout << "	" << linkedElement->object.getPlate() << "			" << linkedElement->object.getSize() << "			" << linkedElement->object.getOrigin() << "			" << linkedElement->object.getType() << endl;
			linkedElement = linkedElement->next;
		}	
	}
}