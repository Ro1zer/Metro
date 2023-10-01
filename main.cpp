#include "station.h"
#include <iostream>
/*Стартове меню звідки починається взаємодія користувача з можливостями программи*/
namespace Menu{
	int menu() {
		int answ;
		std::cout << "Choose more comfortable action: " << std::endl;
		std::cout << "(0) Text" << std::endl;
		std::cout << "(1) GUI" << std::endl;
		std::cout << "You: ";
		std::cin >> answ;
		if ((answ < 0) || (Data::metro.size() <= answ)) {
			throw std::exception("\nPlease,enter true action next time\n");
		}
		try
		{
			if (answ == 0) {
				while ((Input::trigger_set_start != true) || (Input::trigger_set_stop != true))
				{
					Input::text();
				}
			}
			else if (answ == 1) {
				while ((Input::trigger_set_start != true) || (Input::trigger_set_stop != true))
				{
					Input::gui();
				}

			}
		}
		catch (const std::out_of_range&ofr)
		{
			std::cerr << ofr.what();
			Data::clear();
			std::exit(1);
		}
		catch (const std::exception& ex)
		{
			std::cerr << ex.what();
			Data::clear();
			std::exit(1);
		}
		Output::route();
		Data::clear();
		return 0;
	}
	
}

int main() {
	Init::set_vector_with_stations("red.txt");
	Init::set_vector_with_stations("blue.txt");
	Init::set_vector_with_stations("green.txt");

	if (Data::metro.size() == 0) {
		std::cerr << "file wasn`t initialize!";
		Data::clear();
		std::exit(1);
	}
	Init::connect_transition();

	try
	{
		return Menu::menu();
	}
	catch (const std::exception& ex)
	{
		std::cerr << ex.what();
		Data::clear();
		std::exit(1);
	}
}
