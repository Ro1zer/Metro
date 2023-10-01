#include "station.h"
#include <fstream>
#include <sstream>
Data::Line Init::current;
Data::Station& Init::find(const std::string& transit_name) {
	for (auto& line : Data::metro) {
		for (auto& station : line) {
			if (transit_name == station.name()) {
				return station;
			}
			else {
				continue;
			}
		}
	}
	throw std::exception("\nStations didn't find\n");
}
void Init::connect_transition() {
	for (auto& vec_str : Data::transitions) {
		
		try
		{
			Data::Station* ptr1{};
			Data::Station* ptr2{};
			for (int i = 0; i != vec_str.size(); ++i) {
				if (i == 0) {
					ptr1 = &find(vec_str[i]);
				}
				if (i == 1) {
					ptr2 = &find(vec_str[i]);
				}
			}
			ptr1->transition(ptr2);
			ptr2->transition(ptr1);
		}
		catch (const std::exception&ex)
		{
			std::cerr << ex.what();
			Data::transitions.clear();
			Data::transitions.~vector();
			Data::clear();
			std::exit(1);
		}
	}
	Data::transitions.clear();
	Data::transitions.~vector();
}
Data::Station Init::read_txt_file(std::string const& str, const char delim)
{
	std::stringstream ss(str);
	std::string s;
	Data::Station temp;
	bool name_set_trigger = false;
	while (std::getline(ss, s, delim)) {
		if (name_set_trigger == false) {
			temp.name(s);
			temp.line(current);
			name_set_trigger = true;
		}
		else {
			std::vector<std::string>t_transit_name;
			t_transit_name.push_back(temp.name());
			t_transit_name.push_back(s);
			Data::transitions.push_back(t_transit_name);
		}
	}
	return temp;
}
void Init::set_vector_with_stations(const std::string& path) {
	std::vector<Data::Station>temp;
	std::ifstream fin;
	fin.open(path);
	if (!fin.is_open())
	{
		std::cerr << "File doesn`t open" << std::endl;
		fin.close();
		return;
	}
	else {
		std::string str;
		const char del = ':';
		while (!fin.eof())
		{
			fin >> str;
			temp.push_back(read_txt_file(str, del));
		}
	}
	fin.close();
	try
	{
		current = Data::next(current);
		Data::metro.push_back(temp);
	}
	catch (std::out_of_range error)
	{
		std::cerr << error.what();
		Data::clear();
		std::exit(1);
	}
}
