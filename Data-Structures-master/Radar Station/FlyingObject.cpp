#include "FlyingObject.h"
#include <random>

FlyingObject::FlyingObject()
{
	type = "";
	size = "";
	origin = "";
	plate = "";
}
FlyingObject::~FlyingObject()
{
}

FlyingObject FlyingObject::createFlyingObject(string tp, string sz, string og, string plt) 
{
	type = tp;
	size = sz;
	origin = og;
	plate = plt;
	return *this;
	
}

string FlyingObject::getType() 
{
	return type;
}

string FlyingObject::getSize()
{
	return size;
}

string FlyingObject::getOrigin()
{
	return origin;
}

string FlyingObject::getPlate() 
{
	return plate;
}