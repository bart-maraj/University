#ifndef NODEHASH_H
#define NODEHASH_H
#include <iostream>
#include "FlyingObject.h"

using namespace std;

class nodeHash
{
public:
	string key;
	FlyingObject object;
	nodeHash* next;
public:
	nodeHash();
	~nodeHash();
};


#endif
