def parse_input_file():
    file = open("Informatics/fourth_lab/main_task/input.json", "r", encoding="utf-8")
    yaml_data = file.read()
        # Заменяем ключи на YAML-формат (отступы и двоеточия)
    yaml_data = yaml_data.replace("{", "").replace("}", "")
    yaml_data = yaml_data.replace("[", "").replace("]", "")
    yaml_data = yaml_data.replace(",", "")
    yaml_data = yaml_data.replace(":", ":\n  ")
    yaml_data = yaml_data.replace("\"", "")  # Убираем кавычки
    yaml_data = yaml_data.replace("\n\n", "\n")  # Убираем лишние пустые строки
    
    lines = yaml_data.split("\n")
    # print(lines)
    yaml_lines = []
    for line in lines:
        if line.strip() == "":
            continue
        indent_level = line.find(line.strip())  # находим уровень отступа
        yaml_lines.append("  " * indent_level + line.strip())
    final_var = ""
    reserved_words = ["date", "first_day", "second_day", "subject", "lesson", "lecture", "time", "room", "place", "schedule"]
    print(yaml_lines)
    for line in yaml_lines:
        
        exist_flag = False
        for i in reserved_words:
            if i in line:
                final_var += "\n" + line
                exist_flag = True
                break
        if exist_flag == False:
            final_var += " " + line.strip()
                
    # print(final_var)
    # print(yaml_lines)
    
   
    
            
         
    output_file = open("Informatics/fourth_lab/main_task/output.yaml", "w", encoding="utf-8")
    output_file.write(final_var)
    output_file.close()
                
        
    


def main():
    parse_input_file()
if __name__ == "__main__":
    main()