#include "nodeList.h"

nodeList::nodeList()
{
	sn = *new Session();
	next = NULL;
	previous = NULL;
}

nodeList::~nodeList()
{
}