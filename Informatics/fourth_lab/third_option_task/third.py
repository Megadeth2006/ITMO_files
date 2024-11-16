# JSON-парсер, реализованный вручную с помощью формальной грамматики
class JSONParser:
    def __init__(self, text):
        self.text = text
        self.index = 0

    def parse(self):
        self.skip_whitespace()
        value = self.parse_value()
        self.skip_whitespace()
        return value

    def parse_value(self):
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

    def parse_object(self):
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

    def parse_array(self):
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

    def parse_string(self):
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

    def parse_number(self):
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

    def current_char(self):
        if self.index < len(self.text):
            return self.text[self.index]
        return ''

    def skip_whitespace(self):
        while self.index < len(self.text) and self.text[self.index].isspace():
            self.index += 1
            
# # Конвертация JSON-данных в YAML
# def json_to_yaml(data, indent=0):
#     yaml = ""
#     if isinstance(data, dict):
#         for key, value in data.items():
#             yaml += " " * indent + f"{key}:\n" + json_to_yaml(value, indent + 2)
            
#     elif isinstance(data, list):
#         for item in data:
#             yaml += " " * indent + "- " + json_to_yaml(item, indent + 2).lstrip()
#     elif isinstance(data, str):
#         yaml += f"\"{data}\"\n"
#     elif data is None:
#         yaml += "null\n"
#     else:
#         yaml += f"{data}\n"
#     return yaml
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
    

# Чтение JSON-файла, разбор и преобразование в YAML
def main():
    with open("Informatics/fourth_lab/third_option_task/example1.json", "r", encoding="UTF-8") as file:
        json_text = file.read()
    parser = JSONParser(json_text)
    parsed_data = parser.parse()
    print(parsed_data)
    yaml_output = json_to_yaml(parsed_data)
    with open("C:/Users/danie/OneDrive/Desktop/main folder/ITMO_files/Informatics/fourth_lab/third_option_task/output.yaml", "w", encoding = "UTF-8") as file:
        file.write(yaml_output)
    print("JSON успешно конвертирован в YAML и сохранён в 'output.yaml'.")


if __name__ == "__main__":
    main()