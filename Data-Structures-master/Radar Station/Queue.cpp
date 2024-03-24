#include "Queue.h"

queue::queue()
{
	rear = new nodeQueue();
	fr = new nodeQueue();
}

queue::~queue()
{

}

string queue::dequeue()
{
	string element = "";

	if (isEmptyQueue())
	{
		throw;
	}
	else
	{
		positionQueue temp = fr;
		fr = fr->next;
		element = temp->element;

	}
	
	return element;
}

void queue::enqueue(string element) 
{
	nodeQueue* pos = new nodeQueue();
	pos->element = element;
	pos->next = NULL;

	if (isEmptyQueue())
	{
		fr = pos;
		rear = pos;
		fr->next = rear;
		rear->next = NULL;
	}
	else
	{
		positionQueue temp = rear;
		rear = pos;
		temp->next = rear;
	}
}

string queue::front() 
{
	return fr->element;
}

string queue::rearElement()
{
	return rear->element;
}

void queue::makenullQueue()
{
	while (fr != NULL) 
	{
		dequeue();
	}
}

bool queue::isEmptyQueue()
{
	return  fr == NULL || fr->next == NULL && fr->element == "";
}

