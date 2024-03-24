#include "nodeStack.h"

nodeStack::nodeStack()
{
	object = *new FlyingObject();
	next = NULL;
}

nodeStack::~nodeStack()
{
}