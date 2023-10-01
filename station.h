#pragma once
#ifndef DEBUG
#define DEBUG
#include <iostream>
#include <string>
#include <vector>
/*Data - головні поля з котрими працює программа протягом усього існування*/
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
	//Після завершення ініціалізації гілки Data::metro,виконати ітерацію,до поки current != Count
	Line next(Line& obj);
	//Якщо сталась помилка - очистити всі данні збережені в векторі
	void clear();
	//metro.size() = кількість гілок,де кожна гілка - це вектор зі станціями.
	extern std::vector<std::vector<Data::Station>>metro;
	//Зберігає станції які є пересадочними між собою
	extern std::vector<std::vector<std::string>>transitions;
	extern Data::Station start;
	extern Data::Station stop;
}
/*Init - Ініціалізує головний вектор з Data (metro),все що тут є напряму модифікує Data::metro. Ця частина користувачу невідома*/
namespace Init {
	Data::Station& find(const std::string& transit_name);
	//З'єднує пересадочні станції між собою (використовуючі данні з Data::transitions)
	void connect_transition();
	Data::Station read_txt_file(std::string const& str, const char delim);
	void set_vector_with_stations(const std::string& path);
	//Кількість сканування текстових файлів обмежена класом Line,тут він нам потрібен для ініціалізації гілок всіх станцій Data::metro. Після сканування кожного файлу,ітерує current поки він не досягне значення Count
	extern Data::Line current;
}
/*Input - інтерфейс якти користується користувач для заповнення Data::start & Data::stop*/
namespace Input {
	bool find(const std::string& transit_name);
	void text();
	void gui();
	void print(const std::vector<Data::Station>& line)noexcept;
	extern bool trigger_set_start;
	extern bool trigger_set_stop;
}
/*Calculate - тут знахдяться функції для розрахунку відстані. Назви повнісьтю відповідають за сенс*/
namespace Calculate {
	int station_index(const std::vector<Data::Station>& line, const Data::Station& station)noexcept;
	int station_transit_index(const std::vector<Data::Station>& line, const Data::Line& color_line)noexcept;
	int distance(int start_indx, int stop_indx)noexcept;
	int distance(int start_indx, int transition_start_indx, int stop_indx, int transition_stop_indx)noexcept;
	std::vector<Data::Station> find_line(const std::vector<std::vector<Data::Station>>& global_vector, const Data::Line& color_line);
}
/*Output - найменш читабельний простір імен,який виводить користувачу маршрут,використовуючи функції з Calculate::*/
namespace Output {
	void route();
}
#endif // DEBUG


