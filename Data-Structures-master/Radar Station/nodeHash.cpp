#include "nodeHash.h"

nodeHash::nodeHash() 
{
	key = "";
	next = NULL;
	object = *new FlyingObject();
}

nodeHash::~nodeHash()
{

}