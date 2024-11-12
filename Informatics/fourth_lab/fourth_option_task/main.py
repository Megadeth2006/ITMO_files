import time
import json
import yaml
import os


def main_task():
    input_file = open("Informatics/fourth_lab/fourth_option_task/input.json", "r", encoding="utf-8")
    output_file = open("Informatics/fourth_lab/fourth_option_task/output1.yaml", "w", encoding="utf-8")
    yaml_data = input_file.read()
        # Заменяем метасимволы для приведения json к yaml формату
    yaml_data = yaml_data.replace('",', "").replace('},', "").replace('"', "")
    yaml_data = yaml_data.replace("{", "").replace("}", "")
    yaml_data = yaml_data.replace("[", "").replace("]", "")
    

    lines = yaml_data.split("\n") # список строк, с которым будем работать
    yaml_lines = [] # результирующий список строк
    first = -1
    for line in lines:
        if line.strip() == "":
            continue
        elif first == -1:
            first = line.find(line.strip()) 
        indent_level = line.find(line.strip())  # находим уровень отступа путем нахождения индекса первой встречи ключа в строке line
        yaml_lines.append("  " * (indent_level-first) + line.strip().strip("\t"))
        
  
    output_file.write("\n".join(yaml_lines))
    output_file.close()
                

def first_option():
    input_file = open("Informatics/fourth_lab/fourth_option_task/input.json", "r", encoding="utf-8")
    output_file = open("Informatics/fourth_lab/fourth_option_task/output2.yaml", "w", encoding="utf-8")
    input_file = input_file.read()
    json_data = json.loads(input_file) # парсим json из строки
    yaml_data = yaml.dump(json_data, default_flow_style=False, allow_unicode="True") # конвертируем json в yaml
    output_file.write(yaml_data)
    output_file.close()        
    

    




if __name__ == "__main__":
    main_file_path = "Informatics/fourth_lab/fourth_option_task/output1.yaml"
    if os.path.exists(main_file_path):
        try:
            os.remove(main_file_path)
        except Exception as e:
            print(e)
    first_option_path = "Informatics/fourth_lab/fourth_option_task/output2.yaml"
    if os.path.exists(first_option_path):
        try:
            os.remove(first_option_path)
        except Exception as e:
            print(e)
            
    start_time = time.time()
    main_task()
    end_time = time.time()
    print(f"Время выполнения:{end_time-start_time:.6f} секунд")
    
    start_time = time.time()
    first_option()
    end_time = time.time()
    print(f"Время выполнения:{end_time-start_time:.6f} секунд")
    
    
    
   