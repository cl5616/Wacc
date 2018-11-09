#include <iostream>
#include "fileinfo.h"
#include "heap.h"
using namespace std;
int main(int argc, char const *argv[])
{
	if (argc != 2)
		return -1;
	FILE* file;
	fopen_s(&file, argv[1], "rb");
	if (file == NULL)
	{
		cerr << "error in opening file" << endl;
		return -2;
	}
	WaccProgram::loadFile(file);
	try
	{
		WaccProgram::getIns().execute();
	}
	catch (WaccException& e)
	{
		cerr << e.getErrorMsg() << endl;
	}
	fclose(file);
	return 0;
}