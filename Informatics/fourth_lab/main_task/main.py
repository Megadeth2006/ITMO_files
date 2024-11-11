def parse_input_file():
    file = open("Informatics/fourth_lab/main_task/input.json", "r", encoding="utf-8")
    yaml_data = file.read()
        # Заменяем метасимволы для приведения json к yaml формату
    yaml_data = yaml_data.replace('",', "").replace('},', "").replace('"', "")
    yaml_data = yaml_data.replace("{", "").replace("}", "")
    yaml_data = yaml_data.replace("[", "").replace("]", "")
    

    lines = yaml_data.split("\n") # строка, с которой будем работать
    yaml_lines = [] # результирующий список строк
    for line in lines:
        if line.strip() == "":
            continue
        indent_level = line.find(line.strip())  # находим уровень отступа путем нахождения индекса первой встречи ключа в строке line
        yaml_lines.append("  " * (indent_level-1) + line.strip())
    output_file = open("Informatics/fourth_lab/main_task/output.yaml", "w", encoding="utf-8")
    output_file.write("\n".join(yaml_lines))
    output_file.close()
                
        
    


def main():
    parse_input_file()
if __name__ == "__main__":
    main()