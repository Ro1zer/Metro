#include "station.h"
bool Data::Station::operator==(Station& obj)
{
	if ((this->line() == obj.line()) && (this->name() == obj.name()) && (this->transition() == obj.transition())) {
		return true;
	}
	else {
		return false;
	}
}
void Data::Station::print_line()const {
	switch (line())
	{
	case Data::Line::Red:
		std::cout << "Red" << std::endl;
		break;
	case Data::Line::Blue:
		std::cout << "Blue" << std::endl;
		break;
	case Data::Line::Green:
		std::cout << "Green" << std::endl;
		break;
	default:
		std::cerr << "Indef line!" << std::endl;
		break;
	}
}
void Data::Station::print_all()const {
	std::cout << "Name: " << name() << std::endl;
	std::cout << "Line: ";
	switch (line())
	{
	case Data::Line::Red:
		std::cout << "Red" << std::endl;
		break;
	case Data::Line::Blue:
		std::cout << "Blue" << std::endl;
		break;
	case Data::Line::Green:
		std::cout << "Green" << std::endl;
		break;
	default:
		std::cerr << "Indef line!" << std::endl;
		break;
	}
	if (transition()) {
		std::cout << "Transit: " << transition()->name() << std::endl;
		std::cout << "Transit_line: ";
		switch (transition()->line())
		{
		case Data::Line::Red:
			std::cout << "Red" << std::endl;
			break;
		case Data::Line::Blue:
			std::cout << "Blue" << std::endl;
			break;
		case Data::Line::Green:
			std::cout << "Green" << std::endl;
			break;
		default:
			std::cerr << "Indef line!" << std::endl;
			break;
		}
	}
	std::cout << std::endl;
}

