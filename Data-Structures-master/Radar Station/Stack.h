#ifndef STACK_H
#define STACK_H

#include "FlyingObject.h"
#include "nodeStack.h"

using namespace std;

typedef nodeStack* positionStack;

class stack 
{
	positionStack root;

	public:
		stack();
		~stack();
		FlyingObject pop();
		void push(FlyingObject element);
		FlyingObject top();
		void makenullStack();
		bool isEmptyStack();
		FlyingObject getObject(); 
};

#endif

