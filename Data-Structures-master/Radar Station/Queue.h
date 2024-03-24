#ifndef QUEUE_H
#define QUEUE_H
#include <iostream>
#include "nodeQueue.h"

using namespace std;



typedef nodeQueue* positionQueue;

class queue 
{
	positionQueue fr;
	positionQueue rear;

	public:
		queue();
		~queue();
		string dequeue();
		void enqueue(string element);
		string front();
		string rearElement();
		void makenullQueue();
		bool isEmptyQueue();
};

#endif

