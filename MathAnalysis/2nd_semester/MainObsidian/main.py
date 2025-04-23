def f_upper(x):
    return 10 / (x**2 + 4)

def f_lower(x):
    return (x**2 + 5*x + 4) / (x**2 + 4)

a, b = -6, 1
n = int(input())


dx = (b - a) / n
sum_integral = 0
for i in range(1, n+1):
    xi = a + (b - a) * i / n
    sum_integral += (f_upper(xi) - f_lower(xi)) * dx
print(str(n) + ": " + str(sum_integral))
print(sum_integral-6.76207)