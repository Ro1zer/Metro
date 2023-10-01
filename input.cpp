#include "station.h"
bool Input::trigger_set_start = false;
bool Input::trigger_set_stop = false;
bool Input::find(const std::string& transit_name) {
	for (auto& line : Data::metro) {
		for (auto& station : line) {
			if (transit_name == station.name()) {
				return true;
			}
			else {
				continue;
			}
		}
	}
	return false;
}
void Input::text() {
	if (trigger_set_start == false) {
		std::cout << "|-------------------|" << std::endl;
		std::cout << "|Enter start station|" << std::endl;
		std::cout << "|-------------------|" << std::endl;
	}
	else if (trigger_set_stop == false) {
		std::cout << "|------------------|" << std::endl;
		std::cout << "|Enter stop station|" << std::endl;
		std::cout << "|------------------|" << std::endl;
	}
	std::string station_name;
	std::cout << "You: ";
	std::cin >> station_name;
	if ((find(station_name))&& (!trigger_set_start)) {
		std::cout << "Exactly!" << std::endl;
		Data::start = Init::find(station_name);
		trigger_set_start = true;
		return;
	}
	else if((find(station_name)) && (!trigger_set_stop)) {
		std::cout << "Exactly!" << std::endl;
		Data::stop = Init::find(station_name);
		trigger_set_stop = true;
		return;
	}
	else {
		throw std::exception("\nStation with this name has not find\n");
	}
}
void Input::gui() {
	if (trigger_set_start == false) {
		std::cout << "|-------------------|" << std::endl;
		std::cout << "|Enter start station|" << std::endl;
		std::cout << "|-------------------|" << std::endl;
	}
	else if (trigger_set_stop == false) {
		std::cout << "|------------------|" << std::endl;
		std::cout << "|Enter stop station|" << std::endl;
		std::cout << "|------------------|" << std::endl;
	}
	int answ;
	std::cout << "Choose line: "<<std::endl;
	for (int i = 0; i != Data::metro.size();++i) {
		std::cout << "(" << i << ")";
		Data::metro.at(i).at(i).print_line();
	}
	std::cout << "You: ";
	std::cin >> answ;
	if (answ < 0 || Data::metro.size() <= answ) {
		throw std::out_of_range("\nInvalid action!\n");
	}
	print(Data::metro.at(answ));
	int index;
	std::cout << "If you will exit with line,enter (-1)"<<std::endl;
	std::cout << "You: ";
	std::cin >> index;
	if (index == -1) {
		return;
	}
	else if (index<0 || Data::metro.at(answ).size() <=index) {
		throw std::out_of_range("\nStation with this index has not find in this range\n");
	}
	else {
		std::cout << "\nExacly!\nYour choise: " << Data::metro.at(answ).at(index).name()<<std::endl;
		if (trigger_set_start == false) {
			Data::start = Data::metro.at(answ).at(index);
			trigger_set_start = true;
		}
		else if(trigger_set_stop == false) {
			Data::stop = Data::metro.at(answ).at(index);
			trigger_set_stop = true;
		}
	}

}
void Input::print(const std::vector<Data::Station>& line)noexcept {
	std::cout << "Now you see the line: ";
	line.at(0).print_line();
	for (int counter = 0; counter != line.size(); ++counter) {
		std::cout << "Index:\t|" << counter << "|" << std::endl;
		line[counter].print_all();
	}
}