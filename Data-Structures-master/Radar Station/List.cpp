#include "List.h"

list::list() 
{
	header = new nodeList();
}

list::~list()
{
}
void list::insert(Session ses, positionList pos)
{
	positionList temp = new nodeList();
	if (pos == NULL) 
	{
		throw;
	}
	else if (isEmptyList())
	{
		header->sn = ses;
	}
	else 
	{
		temp->sn = ses;
		temp->next = pos->next;
		temp->previous = pos;
		pos->next->previous = temp;
		pos->next = temp;
		pos->next = pos;
		temp = pos->next;
	}
}
void list::remove(positionList pos)
{
	if (pos == NULL)
	{
		throw;
	}
	if (pos->next != NULL)
	{
		pos->next->previous = pos->previous;
	}
	if (pos->previous != NULL)
	{
		pos->previous->next = pos->next;
	}
	delete(pos);
};

Session list::retrieve(positionList pos)
{
	if (pos == NULL)
	{
		throw;
	};
	return pos->sn;
}

positionList list::next(positionList pos)
{
	if (pos == NULL)
	{
		throw;
	};
	return pos->next;
}
positionList list::previous(positionList pos)
{
	if (pos == NULL)
	{
		throw;
	};
	return pos->previous;
}
void list::makenullList() 
{
	while (isEmptyList())
	{
		remove(header);
	}
}
bool list::isEmptyList()
{
	return header != NULL;
}
positionList list::getHeader()
{
	return header;
}
stack list::getStackObjects() 
{
	return header->sn.getStObject();
}