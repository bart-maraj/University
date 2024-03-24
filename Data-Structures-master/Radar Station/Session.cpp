#include "Session.h"
#include <time.h>
#include <stdlib.h>

Session::Session()
{
	string arrayType[7] = { "Drone", "Plane", "Bird", "Superlopez", "UFO", "Ghost", "FlyingRock" };
	string arraySize[5] = { "Huge", "Big", "Medium", "Small", "Tiny" };
	string arrayOrigin[11] = { "N", "S", "W", "E", "NE", "NW", "SE", "SW", "UP", "DOWN", "Surprise" };
	string arrayPlate1[11] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };
	string arrayPlate2[17] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "1", "3", "5", "7", "9", "2", "4" };
	string arrayPlate3[23] = { "1", "1", "2", "2", "3", "3", "4", "4", "5", "5", "6", "6", "7", "7", "8", "8", "9", "9", "0", "0", "2", "3", "2" };
	string arrayPlate4[26] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
	string arrayPlate5[32] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "e", "g", "f", "s", "t", "y" };

	stackObjects = *new stack();
	numberObjects = 0;
	numberSTS = 0;
	timeOp = 0;
	timeAverage = 0;
	typeQueue = CreateQueueFromArray(arrayType, 7);
	sizeQueue = CreateQueueFromArray(arraySize, 5);
	originQueue = CreateQueueFromArray(arrayOrigin, 11);
	plate1Queue = CreateQueueFromArray(arrayPlate1, 11);
	plate2Queue = CreateQueueFromArray(arrayPlate2, 17);
	plate3Queue = CreateQueueFromArray(arrayPlate3, 23);
	plate4Queue = CreateQueueFromArray(arrayPlate4, 26);
	plate5Queue = CreateQueueFromArray(arrayPlate5, 32);

	hashSt = *new hashStructure();
}

Session::~Session() 
{
}

void Session::createStack() 
{
	FlyingObject FO = *new FlyingObject();																			// Initializes the Flying Object.
	unsigned t0, t1, t2;																							// t0,t1,t2 used to get the time

	t0 = clock();																									// t0 = start of the operation

	srand(time(0));
	numberObjects = 1000 + rand()%(10000 - 1000);																	// numberObjects = is a random number between 1000 and 10000.

	FO = randFO();																									// Creates a random Flying Object
	
	hashSt.insertFlyingObject(FO, FO.getPlate());
	
	stackObjects.push(FO);																							// Pushes the Flying Object into the stack


	t1 = clock();																									// t1 = creation and insertion of 1 object
	timeAverage = (double(t1 - t0) / CLOCKS_PER_SEC);
	
	if (FO.getType() == "Superlopez" && FO.getSize() == "Tiny" && FO.getOrigin() == "Surprise") numberSTS++;		// NumberSTS increases when  size = Tiny, type = Superlopez, origin = Surprise


	for (int i = 1; i < numberObjects; i++) {
		FO = randFO();																								// Creates a random Flying Object
		
		hashSt.insertFlyingObject(FO, FO.getPlate());

		stackObjects.push(FO);																						// Pushes the Flying Object into the stack
		

		if (FO.getType() == "Superlopez" && FO.getSize() == "Tiny" && FO.getOrigin() == "Surprise") numberSTS++;	// NumberSTS increases when  size = Tiny, type = Superlopez, origin = Surprise
	}

	t2 = clock();																									// t2 = end of the operation

	timeOp = (double(t2 - t0) / CLOCKS_PER_SEC);
}

FlyingObject Session::randFO() 
{
	FlyingObject FO = *new FlyingObject();	// Initializes the Flying Object.
	string tp, sz, og, plt;

	int random = rand() % 100;						// prob = random number between 0 and 100
	
	if (random < 70)
	{											// The first type will be dequeued copied to the FO and enqueued again (70% of the cases)
		tp = typeQueue.dequeue();
		typeQueue.enqueue(tp);
	}
	else 
	{											// Dequeued one type (T1), dequeued a second type (T2), copied T2 to the FO, and then T2 enqueuedand T1 enqueued(30 % of the cases)
		string auxType1, auxType2;
		auxType1 = typeQueue.dequeue();
		auxType2 = typeQueue.dequeue();
		tp = auxType2;
		typeQueue.enqueue(auxType2);
		typeQueue.enqueue(auxType1);
	}

	if (random < 40)
	{											// The Size will be dequeued copied to the FO and enqueued again (40% of the cases)
		sz = sizeQueue.dequeue();
		sizeQueue.enqueue(sz);
	}
	else 
	{											// Dequeued one Size (S1), dequeued a second Size (S2), copied S2 to the FO, and then S2 enqueuedand S1 enqueued(60 % of the cases).
		string auxSize1, auxSize2;
		auxSize1 = sizeQueue.dequeue();
		auxSize2 = sizeQueue.dequeue();
		sz = auxSize2;
		sizeQueue.enqueue(auxSize2);
		sizeQueue.enqueue(auxSize1);
	}

												// the Origin will be dequeued copied to the FO and enqueued again
	og = originQueue.dequeue();
	originQueue.enqueue(og);


	string plateChar1 = plate1Queue.dequeue();	// Using the queues, for every FO, a new plate will be created dequeuing an element from every queue. The elements will be enqueued again in its original queue. 
	plt = plt + plateChar1;
	plate1Queue.enqueue(plateChar1);

	string plateChar2 = plate2Queue.dequeue();
	plt = plt + plateChar2;
	plate2Queue.enqueue(plateChar2);

	string plateChar3 = plate3Queue.dequeue();
	plt = plt + plateChar3;
	plate3Queue.enqueue(plateChar3);

	string plateChar4 = plate4Queue.dequeue();
	plt = plt + plateChar4;
	plate4Queue.enqueue(plateChar4);

	string plateChar5 = plate5Queue.dequeue();
	plt = plt + plateChar5;
	plate5Queue.enqueue(plateChar5);

	if (random < 20)
	{										// With a probability of 20 %, an element will be enqueued twice, making its queue grow.
		plate1Queue.enqueue(plateChar1);
		plate2Queue.enqueue(plateChar2);
		plate3Queue.enqueue(plateChar3);
		plate4Queue.enqueue(plateChar4);
		plate5Queue.enqueue(plateChar5);
	}

	FO = FO.createFlyingObject(tp, sz, og, plt);

	return FO;
}

queue Session::CreateQueueFromArray(string array[], int sizeOfArray) 
{					
											// Creates a queue given the array of string values
	queue auxQueue = *new queue();

	for (int i = 0; i < sizeOfArray; i++)
	{
		auxQueue.enqueue(array[i]);
	}
	return auxQueue;
}

queue Session::getPlatesByNumber(string numbers) 
{
	queue searchedPlatesQueue = *new queue();																							// The queue that will be returned.
	stack auxStack = stackObjects;																						// Auxiliary stack that is a copy of stackObjects, we will pop it until its empty to get the values.
	string auxPlate;

	while (!auxStack.isEmptyStack()) 
	{
		auxPlate = auxStack.pop().getPlate();	

		if (auxPlate.at(0) == numbers.at(0) && auxPlate.at(1) == numbers.at(1) && auxPlate.at(2) == numbers.at(2))		// If the the numbers of the plate are the same that the given one from the user the plate is enqueued in the one that will be returned.
		{	
			searchedPlatesQueue.enqueue(auxPlate);
		}
	}
	return searchedPlatesQueue;
}

string Session::stringQueue(queue givenQueue) 
{
	queue auxQueue = givenQueue;
	string contentOfQueue = "";
	
	while (!auxQueue.isEmptyQueue())
	{
		contentOfQueue = contentOfQueue + " " + auxQueue.dequeue();
	}
	contentOfQueue = contentOfQueue + "\n";
	return contentOfQueue;
}

int Session::getSizeGivenQueue(queue givenQueue)
{
	queue auxQueue = givenQueue;
	int sizeOfQueue = 0;
	while (!auxQueue.isEmptyQueue())
	{
		sizeOfQueue++;
		auxQueue.dequeue();
	}
	return sizeOfQueue;
}

stack Session::getStObject() 
{
	return stackObjects;
}
int Session::getNumberObjects() 
{
	return numberObjects;
}
int Session::getNumberSTS() 
{
	return numberSTS;
}
double Session::getTimeOp() 
{
	return timeOp;
}
double Session::getTimeAverage()
{
	return timeAverage;
}
queue Session::getTypeQueue() 
{
	return typeQueue;
}
queue Session::getSizeQueue() 
{
	return sizeQueue;
}
queue Session::getOriginQueue() 
{
	return originQueue;
}
queue Session::getPlate1Queue() 
{
	return plate2Queue;
}
queue Session::getPlate2Queue() 
{
	return plate2Queue;
}
queue Session::getPlate3Queue() 
{
	return plate3Queue;
}
queue Session::getPlate4Queue() 
{
	return plate4Queue;
}
queue Session::getPlate5Queue() 
{
	return plate5Queue;
}
void Session::printPlatesByNumber(string numbers)
{
	int column = 0;	
	queue queueToPrint = *new queue();

	queueToPrint = getPlatesByNumber(numbers);

	cout << "\nStack Data\n\n";

	while (!queueToPrint.isEmptyQueue())														// Prints the plates in columns of 7.
	{
		if (column < 6)
		{
			cout << "| " << queueToPrint.dequeue() << " ";
			column++;
		}
		else
		{
			cout << "| " << queueToPrint.dequeue() << " |" << endl;
			column = 0;
		}
		if (queueToPrint.isEmptyQueue())
		{
			cout << "|" << endl;
		}
	}
}
hashStructure Session::getHashStructure()
{
	return hashSt;
}

void Session::printFlyingObjects(string type, string size, string origin, string plate)
{
	stack auxStackOfObjects = *new stack();
	stack searchedFlyingObjectsStack = *new stack();
	FlyingObject currentFlyingObject = *new FlyingObject();

	auxStackOfObjects = stackObjects;

	while (!auxStackOfObjects.isEmptyStack())
	{
		currentFlyingObject = auxStackOfObjects.pop();

		if (type == currentFlyingObject.getType() || type == "-1")
		{
			if (size == currentFlyingObject.getSize() || size == "-1")
			{
				if (origin == currentFlyingObject.getOrigin() || origin == "-1")
				{
					if (plate == currentFlyingObject.getPlate() || plate == "-1")
					{
						cout << "	" << currentFlyingObject.getPlate() << "			" << currentFlyingObject.getSize() << "			" << currentFlyingObject.getOrigin() << "			" << currentFlyingObject.getType() << endl;
					}
				}
			}
		}
	}
}

int Session::countFlyingObjects(string type, string size, string origin)
{
	stack auxStackOfObjects = *new stack();
	stack searchedFlyingObjectsStack = *new stack();
	FlyingObject currentFlyingObject = *new FlyingObject();
	int countedElements = 0;

	auxStackOfObjects = stackObjects;

	while (!auxStackOfObjects.isEmptyStack())
	{
		currentFlyingObject = auxStackOfObjects.pop();

		if (type == currentFlyingObject.getType() || type == "-1")
		{
			if (size == currentFlyingObject.getSize() || size == "-1")
			{
				if (origin == currentFlyingObject.getOrigin() || origin == "-1")
				{	
					countedElements++;
				}
			}
		}
	}
	return countedElements;
}

void Session::printAllFlyingObjects()
{
	stack auxStackOfObjects = *new stack();
	stack searchedFlyingObjectsStack = *new stack();
	FlyingObject currentFlyingObject = *new FlyingObject();

	auxStackOfObjects = stackObjects;

	while (!auxStackOfObjects.isEmptyStack())
	{
		currentFlyingObject = auxStackOfObjects.pop();

		cout << "	" << currentFlyingObject.getPlate() << "			" << currentFlyingObject.getSize() << "			" << currentFlyingObject.getOrigin() << "			" << currentFlyingObject.getType() << endl;
	}

}