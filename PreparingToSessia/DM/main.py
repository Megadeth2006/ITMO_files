import re
text = "65/32/2006"
print(re.sub(r'(\d\d)/(\d\d)/(\d{4})', r'\2.\1.\3', text))
print("dsf")