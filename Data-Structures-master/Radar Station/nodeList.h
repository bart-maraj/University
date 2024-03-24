#ifndef NODELIST_H
#define NODELIST_H
#include <iostream>
#include "Session.h"

class nodeList
{
	public:
		Session sn;
		nodeList* next;
		nodeList* previous;
	public:
		nodeList();
		~nodeList();
};


#endif
