#include "station.h"
void Output::route() {
	try
	{
		if (Data::start == Data::stop) {
			std::cout << "You are already at this station!" << std::endl;
		}
		else if (Data::start.line() == Data::stop.line())
		{
			auto line = Calculate::find_line(Data::metro, Data::start.line());
			int start_index = Calculate::station_index(line, Data::start);
			int stop_index = Calculate::station_index(line, Data::stop);
			if (start_index > stop_index) {
				std::cout << "|----------|" << std::endl;
				std::cout << "|YOUR ROUTE|" << std::endl;
				std::cout << "|----------|" << std::endl;
				for (int i = start_index; i >= stop_index; --i) {
					line[i].print_all();
				}
			}
			else {
				std::cout << "|----------|" << std::endl;
				std::cout << "|YOUR ROUTE|" << std::endl;
				std::cout << "|----------|" << std::endl;
				for (int i = start_index; i <= stop_index; ++i) {
					line[i].print_all();
				}
			}
			std::cout << "|--------|" << std::endl;
			std::cout << "|Distanse|" << std::endl;
			std::cout << "|--------|" << std::endl;
			std::cout << "Total distance: " << Calculate::distance(start_index, stop_index) << " stations" << std::endl;
		}
		else if (Data::start.line() != Data::stop.line()) {
			auto start_line = Calculate::find_line(Data::metro, Data::start.line());
			auto stop_line = Calculate::find_line(Data::metro, Data::stop.line());
			int start_index = Calculate::station_index(start_line, Data::start);
			int start_transit_index = Calculate::station_transit_index(start_line, Data::stop.line());
			int stop_index = Calculate::station_index(stop_line, Data::stop);
			int stop_transit_index = Calculate::station_transit_index(stop_line, Data::start.line());

			if (start_index > start_transit_index) {
				std::cout << "|----------|" << std::endl;
				std::cout << "|YOUR ROUTE|" << std::endl;
				std::cout << "|----------|" << std::endl;
				for (int i = start_index; i >= start_transit_index; --i) {
					start_line[i].print_all();
				}
			}
			else {
				std::cout << "|----------|" << std::endl;
				std::cout << "|YOUR ROUTE|" << std::endl;
				std::cout << "|----------|" << std::endl;
				for (int i = start_index; i <= start_transit_index; ++i) {
					start_line[i].print_all();
				}
			}
			std::cout << "|---------------|\n";
			std::cout << "|Make a transfer|\n";
			std::cout << "|---------------|\n";
			std::cout << std::endl;
			if (stop_index > stop_transit_index) {
				for (int i = stop_transit_index; i <= stop_index; ++i) {
					stop_line[i].print_all();
				}
			}
			else {
				for (int i = stop_transit_index; i >= stop_index; --i) {
					stop_line[i].print_all();
				}
			}
			std::cout << "|--------|" << std::endl;
			std::cout << "|Distanse|" << std::endl;
			std::cout << "|--------|" << std::endl;
			std::cout << "Total distance: " << Calculate::distance(start_index, start_transit_index, stop_index, stop_transit_index) << " stations" << std::endl;
		}
	}
	catch (const std::exception& ex)
	{
		std::cerr<<ex.what();
		Data::clear();
		std::exit(1);
	}
}

