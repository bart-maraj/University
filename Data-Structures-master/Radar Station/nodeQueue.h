#ifndef NODEQUEUE_H
#define NODEQUEUE_H

#include <iostream>

using namespace std;

class nodeQueue
{
public:
	string element;
	nodeQueue* next;
public:
	nodeQueue();
	~nodeQueue();

};

#endif // NODEQUEUE_H