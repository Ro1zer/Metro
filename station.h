#pragma once
#ifndef DEBUG
#define DEBUG
#include <iostream>
#include <string>
#include <vector>
/*Data - ������� ���� � ������� ������ ��������� �������� ������ ���������*/
namespace Data {
	enum class Line { Red, Blue, Green, Count };
	class Station {
	public:
		Station(const std::string& _name = std::string(), Data::Line _line = Data::Line::Count, Station* ptr = nullptr) :n(_name), l(_line), t(ptr) {}
		Station(const Station&) = default;
		Station(Station&&) = default;

		Station& operator=(const Station&) = default;
		Station& operator=(Station&&) = default;
		bool operator==(Station& obj);

		void name(const std::string& _name) { this->n = _name; }
		void line(Data::Line& _line) { this->l = _line; }
		void transition(Station* ptr) { this->t = ptr; }

		std::string name() const { return n; }
		Data::Line line() const { return l; }
		Station* transition() const { return t; }
		Data::Line& line() { return l; }

		void print_line()const;
		void print_all()const;

		~Station() = default;
	private:
		std::string n;
		Data::Line l;
		Station* t;
	};
	int line_size();
	//ϳ��� ���������� ������������ ���� Data::metro,�������� ��������,�� ���� current != Count
	Line next(Line& obj);
	//���� ������� ������� - �������� �� ����� ��������� � ������
	void clear();
	//metro.size() = ������� ����,�� ����� ���� - �� ������ � ���������.
	extern std::vector<std::vector<Data::Station>>metro;
	//������ ������� �� � ������������� �� �����
	extern std::vector<std::vector<std::string>>transitions;
	extern Data::Station start;
	extern Data::Station stop;
}
/*Init - ��������� �������� ������ � Data (metro),��� �� ��� � ������� �������� Data::metro. �� ������� ����������� �������*/
namespace Init {
	Data::Station& find(const std::string& transit_name);
	//�'���� ����������� ������� �� ����� (�������������� ����� � Data::transitions)
	void connect_transition();
	Data::Station read_txt_file(std::string const& str, const char delim);
	void set_vector_with_stations(const std::string& path);
	//ʳ������ ���������� ��������� ����� �������� ������ Line,��� �� ��� ������� ��� ������������ ���� ��� ������� Data::metro. ϳ��� ���������� ������� �����,����� current ���� �� �� ������� �������� Count
	extern Data::Line current;
}
/*Input - ��������� ���� ����������� ���������� ��� ���������� Data::start & Data::stop*/
namespace Input {
	bool find(const std::string& transit_name);
	void text();
	void gui();
	void print(const std::vector<Data::Station>& line)noexcept;
	extern bool trigger_set_start;
	extern bool trigger_set_stop;
}
/*Calculate - ��� ���������� ������� ��� ���������� �������. ����� ��������� ���������� �� ����*/
namespace Calculate {
	int station_index(const std::vector<Data::Station>& line, const Data::Station& station)noexcept;
	int station_transit_index(const std::vector<Data::Station>& line, const Data::Line& color_line)noexcept;
	int distance(int start_indx, int stop_indx)noexcept;
	int distance(int start_indx, int transition_start_indx, int stop_indx, int transition_stop_indx)noexcept;
	std::vector<Data::Station> find_line(const std::vector<std::vector<Data::Station>>& global_vector, const Data::Line& color_line);
}
/*Output - ������� ����������� ������ ����,���� �������� ����������� �������,�������������� ������� � Calculate::*/
namespace Output {
	void route();
}
#endif // DEBUG


