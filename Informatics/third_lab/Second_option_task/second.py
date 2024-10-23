import re
# сделать тестирование 


def replacer(x):
    return str(4*int(x.group())**2-7)

def func(current_test): 
    test = current_test  
    test = re.sub("[^+-=1234567890 ]", "", test)
    test = re.sub(r"[+-]?\d+", replacer, test, count=0)
    return test
        


