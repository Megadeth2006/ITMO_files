# def parse_input_file():
#     file = open("Informatics/fourth_lab/third_option_task/example2.json", "r", encoding="utf-8")
#     yaml_data = file.read()
#         # Заменяем метасимволы для приведения json к yaml формату
#     nums = "1234567890"
#     for i in nums:
#         yaml_data = yaml_data.replace(str(i)+",", str(i))
#     yaml_data = yaml_data.replace('",', "").replace('},', "").replace('"', "").replace("],", "").replace("true,", "true").replace("false,", "false").replace("    ", "\t").replace("  ", "\t")
#     yaml_data = yaml_data.replace("{", "").replace("}", "")
#     yaml_data = yaml_data.replace("[\n", "", 1).replace("\n]", "", 1).replace("]", "").replace("[", "")
    
    
#     lines = yaml_data.split("\n") # строка, с которой будем работать
#     print(lines)
#     yaml_lines = [] # результирующий список строк
#     first = -1
#     for line in lines:
#         if line.strip() == "":
#             continue
#         elif first == -1:
#             first =  line.find(line.strip())
#         indent_level = line.find(line.strip())  # находим уровень отступа путем нахождения индекса первой встречи ключа в строке line
#         yaml_lines.append("\t" * (indent_level - first) + line.strip().strip("\t"))
#     output_file = open("Informatics/fourth_lab/third_option_task/output.yaml", "w", encoding="utf-8")
#     output_file.write("\n".join(yaml_lines))
#     output_file.close()
                
        
    


# def main():
#     parse_input_file()
# if __name__ == "__main__":
#     main()