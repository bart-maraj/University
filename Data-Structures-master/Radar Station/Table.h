#ifndef TABLE_H
#define TABLE_H

#include "Session.h"

class table
{
	public:
		int tableFO[5][7];
	public:
		table();
		~table();
		void printTable(bool hash, Session givenSession);
		void getValuesTable(Session givenSession);
		int getColumnTable(string type);
		int getRowTable(string size);
};

#endif
