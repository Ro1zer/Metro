#include "station.h"
std::vector<std::vector<Data::Station>>Data::metro;
std::vector<std::vector<std::string>>Data::transitions;
Data::Station Data::start;
Data::Station Data::stop;
void Data::clear() {
	for (auto&line :metro) {
		for (auto& station : line)station.~Station();
		line.clear();
		line.~vector();
	}
	metro.clear();
	metro.~vector();
}