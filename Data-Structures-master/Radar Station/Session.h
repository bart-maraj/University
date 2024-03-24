#ifndef SESSION_H
#define SESSION_H

#include "Stack.h"
#include "Hash.h"

class Session {
	stack stackObjects;
	int numberObjects;
	int numberSTS;
	double timeOp;
	double timeAverage;

	queue typeQueue;
	queue sizeQueue;
	queue originQueue;
	queue plate1Queue;
	queue plate2Queue;
	queue plate3Queue;
	queue plate4Queue;
	queue plate5Queue;

	hashStructure hashSt;

	public:
		Session();
		~Session();
		void createStack();
		FlyingObject randFO();
		queue CreateQueueFromArray(string a[], int size);
		queue getPlatesByNumber(string numbers);
		stack getStObject();
		int getNumberObjects();
		int getNumberSTS();
		double getTimeOp();
		double getTimeAverage();
		string stringQueue(queue givenQueue);
		int getSizeGivenQueue(queue givenQueue);
		void printPlatesByNumber(string numbers);
		void printFlyingObjects(string type, string size, string origin, string plate);
		int countFlyingObjects(string type, string size, string origin);
		void printAllFlyingObjects();
		queue getTypeQueue();
		queue getSizeQueue();
		queue getOriginQueue();
		queue getPlate1Queue();
		queue getPlate2Queue();
		queue getPlate3Queue();
		queue getPlate4Queue();
		queue getPlate5Queue();
		hashStructure getHashStructure();
};


#endif 

