def parse_input_file():
    file = open("Informatics/fourth_lab/main_task/input.json", "r", encoding="utf-8")
    yaml_data = file.read()
        # Заменяем метасимволы для приведения json к yaml формату
    yaml_data = yaml_data.replace('",', "").replace('},', "").replace('"', "")
    yaml_data = yaml_data.replace("{", "").replace("}", "")
    yaml_data = yaml_data.replace("[", "").replace("]", "")
    

    lines = yaml_data.split("\n") # список строк, с которым будем работать
    print(lines)
    yaml_lines = [] # результирующий список строк
    first = -1
    for line in lines:
        if line.strip() == "":
            continue
        elif first == -1:
            first = line.find(line.strip()) 
        indent_level = line.find(line.strip())  # находим уровень отступа путем нахождения индекса первой встречи ключа в строке line
        yaml_lines.append("  " * (indent_level-first) + line.strip().strip("\t"))
        
    # for i in yaml_lines:
    #     print(i)
    output_file = open("Informatics/fourth_lab/main_task/output.yaml", "w", encoding="utf-8")
    output_file.write("\n".join(yaml_lines))
    output_file.close()
                
        
    


def main():
    parse_input_file()
if __name__ == "__main__":
    main()