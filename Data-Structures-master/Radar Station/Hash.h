#ifndef HASH_H
#define HASH_H

#include "nodeHash.h"
#include "FlyingObject.h"

using namespace std;

class hashStructure
{
public:
	nodeHash *hashTable[1000];

public:
	hashStructure();
	~hashStructure();
	int hashFunction(string plate);
	void insertFlyingObject(FlyingObject givenObject, string plate);
	void printFlyingObject(string plate, string type, string size, string origin);
	int countFlyingObject(string type, string size, string origin);
	int searchFOTypeSize(string type, string size);
	void printPlatesByNumber(string numbers);
	void printAllFlyingObjects();
};

#endif