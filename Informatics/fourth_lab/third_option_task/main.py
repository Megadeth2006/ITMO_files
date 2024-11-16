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
        elif self.text[self.index:self.index+4].lower() == "none":
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
            
def json_to_yaml(data, indent = 0, tire = 0): # конвертер json в yaml
    yaml = ""
    
    if isinstance(data, dict):
        for key, value in data.items():
            
            if tire != 1 or key != list(data.keys())[0]:
                if isinstance(value, dict):
                    yaml += "\n"
                elif isinstance(value, list):
                    yaml += "\n"
                elif isinstance(value, str):
                    yaml += "\n"
                else:
                    yaml += "\n"
                yaml += indent * " " + key + ":" + json_to_yaml(value, indent+2,0)
                
            elif key == list(data.keys())[0]and tire == 1:
                
                yaml +=  key + ":" + json_to_yaml(value, indent+2,0)
           
            
           
    elif isinstance(data, list):
        for value in data:
            yaml += "\n" + (indent-2)  * " " + "- " + json_to_yaml(value, indent, 1)
            
    elif isinstance(data, str):
        yaml += (f' "{data.strip()}"')
    else:
        yaml += " " + str(data).strip()

    return yaml
    
    

def main():
    input_file = open("C:/Users/danie/OneDrive/Desktop/main folder/ITMO_files/Informatics/fourth_lab/third_option_task/example0.json", "r", encoding="UTF-8")
    json_text = input_file.read()
    parser = JSONParser(json_text)
    parsed_data = parser.parse()
    yaml_output = json_to_yaml(parsed_data)
    yaml_output = yaml_output.lstrip("\n")
    output_file = open("C:/Users/danie/OneDrive/Desktop/main folder/ITMO_files/Informatics/fourth_lab/third_option_task/output.yaml", "w", encoding = "UTF-8")
    output_file.write(yaml_output)
   


if __name__ == "__main__":
    main()