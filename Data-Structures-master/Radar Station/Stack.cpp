#include "Stack.h"

stack::stack()
{
	root = new nodeStack();
}

stack::~stack()
{

}

FlyingObject stack::pop() 
{
	FlyingObject element;
	if (isEmptyStack())
	{
		throw;
	}
	else 
	{
		positionStack temp = root;
		root = root->next;
		element = temp->object;

	}
	return element;
}

void stack::push(FlyingObject element) 
{
	positionStack pos = new nodeStack();
	pos->object = element;
	pos->next = NULL;
	positionStack temp = root;
	root = pos;
	root->next = temp;
}

FlyingObject stack::top() 
{
	return root->object;
}

void stack::makenullStack()
{
	while (root != NULL)
	{
		pop();
	}
}

bool stack::isEmptyStack()
{
	return root->next == NULL && root->object.getPlate() == "";
}

FlyingObject stack::getObject() 
{
	return root->object;
}
