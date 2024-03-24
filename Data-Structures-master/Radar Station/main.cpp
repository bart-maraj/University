#include "RadarStation.h"


int main()
{
	RadarStation radar = *new RadarStation();
	int exitValue = 0;									//When 0 it will exit the program

	do
	{
		exitValue = radar.runRadar();
	} while (exitValue != 0);

}