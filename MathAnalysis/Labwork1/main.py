import math
def sign(x): # определение знака числа: -1 - отрицательное, 1 - положительное или 0
    return int(math.copysign(1, x))

def f(x): # исходная функция
  return math.tan(x) - 1/x  

def bisect(a, b, eps): # метод бисекций
   
    k = 0
    x0 = 0
    an = a
    bn = b
    r = f(a)
    while True:
        x0 = 0.5*(an + bn)
        y = f(x0)
        if ((y == 0) or ((bn - an) <= (2*eps))):
            return [x0, k]
   
        k = k+1
    
    
        if (sign(y) != sign(r)):
            bn = x0
        else:
            an = x0
            r = y   

if __name__ == "__main__":
   
    answer = bisect(-1, -1/2, 5e-3)
    print("Корень уравнения tan(x) - 1/x найден по методу бисекций и равен:", answer[0], "\nКоличество итераций равно", answer[1])
    
 
    
    
            
        
        