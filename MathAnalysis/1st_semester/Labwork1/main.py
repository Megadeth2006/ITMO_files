from decimal import Decimal # импорт библиотеки для работы с большими числами

import math # импорт библиотеки для использования математических функций
def sign(x): # определение знака числа: -1 - отрицательное, 1 - положительное или 0
    return int(math.copysign(1, x))

def f(x): # исходная функция
  return Decimal(math.tan(x)) - Decimal(1)/Decimal(x)  

def bisect(a, b, eps): # метод бисекций
   
    k = 0
    x0 = 0
    an = Decimal(a)
    bn = b
    r = f(a)
    while True:
        x0 = Decimal(0.5)*Decimal(an + bn)
        y = Decimal(f(x0))
        if ((y == 0) or (Decimal(bn - an) < Decimal(2*eps))):
            return [Decimal(x0), k]
        k = k+1
    
    
        if (sign(y) != sign(r)):
            bn = Decimal(x0)
        else:
            an = Decimal(x0)
            r = Decimal(y)   

if __name__ == "__main__":
    answer, count = bisect(Decimal(-math.pi/3), Decimal(-math.pi/6), Decimal(5**(-17)))
    
    answer = Decimal(answer).quantize(Decimal('.00000000000000001'))
    print("Корень уравнения tan(x) - 1/x найден по методу бисекций и равен:", Decimal(answer), "\nКоличество итераций равно", count)
    
            
        
        