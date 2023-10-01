#include "station.h"
#include <cmath>
int Calculate::station_index(const std::vector<Data::Station>& line, const Data::Station& station)noexcept {
	for (int indx = 0; indx != line.size(); ++indx) {
		if (line[indx].name() == station.name()) {
			return indx;
		}
		else { continue; }
	}
	return -1;
}
int Calculate::station_transit_index(const std::vector<Data::Station>& line,const Data::Line&color_line)noexcept {
	for (int indx = 0; indx != line.size(); ++indx) {
		if ((line[indx].transition()) && (line[indx].transition()->line() == color_line)) {
			return indx;
		}
		else {
			continue;
		}
	}
	return -1;
}
int Calculate::distance(int start_indx, int stop_indx)noexcept {
	int rezult = std::abs(start_indx - stop_indx);
	return rezult;
}
int Calculate::distance(int start_indx, int transition_start_indx, int stop_indx, int transition_stop_indx)noexcept {
	int rezult = std::abs(start_indx - transition_start_indx)+std::abs(stop_indx - transition_stop_indx);
	return rezult;
}
std::vector<Data::Station> Calculate::find_line(const std::vector<std::vector<Data::Station>>& metro, const Data::Line& color_line) {
	for (int i = 0; i != metro.size(); ++i) {
		if (metro.at(i).at(i).line() == color_line) {
			return metro.at(i);
		}
	}
	throw std::exception("\nLine has not find\n");
}
