# def parse_xml(xml_string):
#     """Парсит XML строку и возвращает словарь."""
#     result = {}
#     stack = []
#     current = result
#     tags = []
#     buffer = ""
#     tag_start = False

#     for char in xml_string:
#         if char == '<':
#             if buffer.strip():
#                 if 'text' not in current:
#                     current['text'] = buffer.strip()
#                 else:
#                     current['text'] += ' ' + buffer.strip()
#                 buffer = ""
#             tag_start = True
#         elif char == '>':
#             tag = tags.pop() if tags else ''
#             if tag.startswith('/'):  # Закрывающий тег
#                 if stack:
#                     current = stack.pop()
#             else:  # Открывающий тег
#                 new_elem = {}
#                 if tag in current:
#                     # Если элемент повторяется, создаем список
#                     if isinstance(current[tag], list):
#                         current[tag].append(new_elem)
#                     else:
#                         current[tag] = [current[tag], new_elem]
#                 else:
#                     current[tag] = new_elem
#                 stack.append(current)
#                 current = new_elem
#             tag_start = False
#         else:
#             if tag_start:
#                 tags.append(buffer)
#                 buffer = ""
#             else:
#                 buffer += char

#     return result

# def dict_to_yaml(d, indent=0):
#     """Конвертирует словарь в строку формата YAML."""
#     yaml_str = ''
#     space = ' '  # Два пробела для отступов
    
#     for key, value in d.items():
#         if isinstance(value, list):
#             for item in value:
#                 yaml_str += f"{space * indent}{key}:\n"
#                 yaml_str += dict_to_yaml(item, indent + 1)
#         elif isinstance(value, dict):
#             yaml_str += f"{space * indent}{key}:\n"
#             yaml_str += dict_to_yaml(value, indent + 1)
#         elif 'text' in value:
#             yaml_str += f"{space * indent}{key}: {value['text']}\n"
#         else:
#             yaml_str += f"{space * indent}{key}: {value}\n"

#     return yaml_str

# def convert_xml_to_yaml(xml_file, yaml_file):
#     """Конвертирует XML файл в YAML файл."""
#     with open(xml_file, 'r', encoding='utf-8') as f:
#         xml_string = f.read()

#     xml_dict = parse_xml(xml_string)
#     yaml_output = dict_to_yaml(xml_dict)

#     with open(yaml_file, 'w', encoding='utf-8') as yaml_out:
#         yaml_out.write(yaml_output)

# if __name__ == "__main__":
#     xml_file = 'Informatics/fourth_lab/input.xml'  # Укажите путь к вашему XML файлу
#     yaml_file = 'Informatics/fourth_lab/output.yaml'  # Укажите путь к выходному YAML файлу

#     convert_xml_to_yaml(xml_file, yaml_file)
#     print(f'Конвертация завершена: {yaml_file}')

def work_with_topic(string):
    buff_key = ""
    buff_val = ""
    equal_sign = False
    dictionary = {}
    string = string[3:]
    print(string)
    # for char in string:
    #     if equal_sign == False:
    return 1, 2
            
            
        
            
            
            
def parse_xml(xml_string):
    buffer = ""
    encoding = ""
    version = "1.0"
    topic = True
    topic_buffer = ""
    
    for char in xml_string:
        if topic == True:
            if char not in "<? ":
                topic_buffer += char
            elif char == ">":
                topic == False
                encoding, version = work_with_topic(topic_buffer)



if __name__ == "__main__":
    
    with open("Informatics/fourth_lab/input.xml", 'r', encoding='utf-8') as f:
        xml_data = f.read()

    json_data = parse_xml(xml_data)
    print(json_data)



# def dict_to_json(data):
#     # Преобразуем словарь в строку JSON вручную
#     if isinstance(data, dict):
#         items = [f'"{key}": {dict_to_json(value)}' for key, value in data.items()]
#         return '{' + ', '.join(items) + '}'
#     elif isinstance(data, list):
#         items = [dict_to_json(item) for item in data]
#         return '[' + ', '.join(items) + ']'
#     else:
#         return f'"{data}"'

# def xml_to_json(xml_string):
#     # Парсим XML в словарь
#     parsed_dict = parse_xml(xml_string)
#     # Конвертируем словарь в JSON
#     return dict_to_json(parsed_dict)

# Пример использования