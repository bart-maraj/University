#ifndef INTERFACE_H
#define INTERFACE_H

#include <time.h>
#include <stdlib.h>

#include "Session.h"
#include "List.h"
#include "Menu.h"
#include "Table.h"


using namespace std;

class RadarStation {
	list listOfSessions;
	Session lastSession;

	public:
		RadarStation();
		~RadarStation();
		int runRadar();
		void optionSwitch(int chosenOption);
		void option1();
		void option2();
		void option3();
		void option4();
		void option5();
		void option6();
		void option7();
		void printPlateQueues();
		void printOption1(bool before);
		void printOption4(bool hash, string plateNumbers);
		void printOption6(bool hash);
		void printOption7(bool hash, string type, string size, string origin, string plate);
	};


#endif

