class JSONParser: # парсер json формата из строки: строка -> json
    def __init__(self, text):
        self.text = text
        self.index = 0

    def parse(self): #старт парсинга: пропускаем пробелы -> обрабатываем не пробельный символ -> пропускаем пробелы -> возвращаем json
        self.skip_whitespace()
        value = self.parse_value()
        self.skip_whitespace()
        return value

    def parse_value(self): # обрабатываем не пробельный символ (если пробел, то пропускаем и работаем с не пробельным символом)
        self.skip_whitespace()
        char = self.current_char()
        if char == '"':
            return self.parse_string()
        elif char == '{':
            return self.parse_object()
        elif char == '[':
            return self.parse_array()
        elif char.isdigit() or char == '-':
            return self.parse_number()
        elif self.text[self.index:self.index+4].lower() == "true":
            self.index += 4
            return True
        elif self.text[self.index:self.index+5].lower() == "false":
            self.index += 5
            return False
        elif self.text[self.index:self.index+4].lower() == "null":
            self.index += 4
            return None
        else:
            print("Че ты сюда вписал вообще, кроме цифр/чисел/строк/словарей/списков/true/false/none ничего не принимается")

    def parse_object(self): # работаем с объектом (словарь)
        obj = {}
        self.index += 1  # Skip '{'
        self.skip_whitespace()
        while self.current_char() != '}':
            key = self.parse_string()
            self.skip_whitespace()
            if self.current_char() != ':':
                print("Переделывай свой json: ожидался символ : после ключа словаря")
            self.index += 1  # Skip ':'
            self.skip_whitespace()
            value = self.parse_value()
            obj[key] = value
            self.skip_whitespace()
            if self.current_char() == ',':
                self.index += 1  # Skip ','
                self.skip_whitespace()
            elif self.current_char() != '}':
                print("Переделывай свой json: ожидались символы , или }")
        self.index += 1  # Skip '}'
        return obj

    def parse_array(self): # работаем со списком
        arr = []
        self.index += 1  # Skip '['
        self.skip_whitespace()
        while self.current_char() != ']':
            value = self.parse_value()
            arr.append(value)
            self.skip_whitespace()
            if self.current_char() == ',':
                self.index += 1  # Skip ','
                self.skip_whitespace()
            elif self.current_char() != ']':
                print("Переделывай свой json, он выполнен не по формату: нужны , или ]")
                
        self.index += 1  # Скипаем ']'
        return arr

    def parse_string(self): # работаем со строкой
        self.index += 1  # Скипаем открытие строки '"'
        start = self.index
        while self.current_char() != '"':
            if self.current_char() == '\\':
                self.index += 2  # Скипаем символ \
            else:
                self.index += 1
        result = self.text[start:self.index]
        self.index += 1  # Скипаем закрытие строки '"'
        return result

    def parse_number(self): # работаем с числами
        start = self.index
        if self.current_char() == '-':
            self.index += 1
        while self.current_char().isdigit():
            self.index += 1
        if self.current_char() == '.':
            self.index += 1
            while self.current_char().isdigit():
                self.index += 1
        if self.current_char() in 'eE':
            self.index += 1
            if self.current_char() in '+-':
                self.index += 1
            while self.current_char().isdigit():
                self.index += 1
        return float(self.text[start:self.index])

    def current_char(self): # текущий символ 
        if self.index < len(self.text):
            return self.text[self.index]
        return ''

    def skip_whitespace(self): # скипаем пробелы (прибавляем к рассматриваемому индексу единицу)
        while self.index < len(self.text) and self.text[self.index].isspace():
            self.index += 1
            
def json_to_csv(schedule_dict, indent = 0, tire = 0): # конвертер json в csv
    schedule = schedule_dict.get('schedule', {})
    

    csv_lines = []

    headers = ['Date', 'Time', 'Subject', 'Lecturer', 'Room', 'Place']
    csv_lines.append(','.join(headers))
    
    # Перебираем дни в расписании
    for day, day_info in schedule.items():
        date = day_info.get('date', 'Unknown Date')  # Извлекаем дату
        lessons = day_info.get('lessons', {})       # Извлекаем список занятий
        
        # Перебираем занятия
        for lesson_key, lesson_info in lessons.items():
            # Извлекаем данные о каждом занятии
            time = lesson_info.get('time', '')
            subject = lesson_info.get('subject', '')
            lecturer = lesson_info.get('lecturer', '')
            room = lesson_info.get('room', '')
            place = lesson_info.get('place', '')
            
            # Формируем строку для CSV
            row = [date, time, subject, lecturer, room, place]
            csv_lines.append(','.join(row))
    return '\n'.join(csv_lines)
           
            
           
    
    
    

def main():
    input_file = open("ITMO_files/Informatics/fourth_lab/fifth_option_task/input.json", "r", encoding="UTF-8")
    json_text = input_file.read()
    parser = JSONParser(json_text)
    parsed_data = parser.parse()
    csv_output = json_to_csv(parsed_data)
    csv_output = csv_output.lstrip("\n")
    output_file = open("ITMO_files/Informatics/fourth_lab/fifth_option_task/output.csv", "w", encoding = "UTF-8")
    output_file.write(csv_output)
    output_file.close()
   


if __name__ == "__main__":
    main()
    

