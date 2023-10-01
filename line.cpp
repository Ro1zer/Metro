#include "station.h"
int Data::line_size() { return static_cast<int>(Line::Count); }
Data::Line Data::next(Line& obj) {
	int temp = static_cast<int>(obj);
	if (temp == line_size())
	{
		throw std::out_of_range("\nMax limit of txt files!\n");
	}
	return obj = static_cast<Line>(++temp);
}
