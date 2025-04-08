import json
import yaml # используем pyyaml

input_file = open("Informatics/fourth_lab/first_option_task/input.json", "r", encoding="utf-8").read() 

json_data = json.loads(input_file) # парсим json из строки
yaml_data = yaml.dump(json_data, default_flow_style=False, allow_unicode="True") # конвертируем json в yaml

output_file = open("Informatics/fourth_lab/first_option_task/output.yaml", "w", encoding="utf-8")
output_file.write(yaml_data)
output_file.close()