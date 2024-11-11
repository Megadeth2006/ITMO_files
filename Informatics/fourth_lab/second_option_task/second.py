import re
def parse_input_file():
    file = open("Informatics/fourth_lab/second_option_task/input.json", "r", encoding="utf-8")
    yaml_data = file.read()
    yaml_data = re.sub(r'["}],', "", yaml_data)
    yaml_data = re.sub(r'[{}"]', "", yaml_data)
    
    
    lines = yaml_data.split("\n")
    # print(lines)
    yaml_lines = []
    for line in lines:
        if line.strip() == "":
            continue
        indent_level = line.find(line.strip())  # находим уровень отступа
        yaml_lines.append("  " * (indent_level-1) + line.strip())
    

    output_file = open("Informatics/fourth_lab/second_option_task/output.yaml", "w", encoding="utf-8")
    output_file.write("\n".join(yaml_lines))
    output_file.close()
                
        
    


def main():
    parse_input_file()
if __name__ == "__main__":
    main()