import numpy as np

def f(x):
    return np.log(np.sin(x))

def f2(x):  
    return -1 / (np.sin(x)**2) - 1 / (np.tan(x)**2)
def f4(x):  
    sinx = np.sin(x)
    cosx = np.cos(x)
    return (
        6 * cosx**2 / sinx**4 +
        8 * cosx**2 / sinx**2 +
        2 / sinx**2 +
        2 / np.tan(x)**2
    )

a = np.pi / 4
b = np.pi / 2
eps = 1e-5


def simpson_method(f, f4, a, b, eps):
    n = 2
    while True:
        h = (b - a) / n
        x = np.linspace(a, b, n + 1)
        y = f(x)
        total = h/3 * (y[0] + 4 * np.sum(y[1:n:2]) + 2 * np.sum(y[2:n-1:2]) + y[n])

        x_vals = np.linspace(a + 1e-4, b - 1e-4, 1000)
        M4 = np.max(np.abs(f4(x_vals)))
        error = (b - a) / 180 * h**4 * M4

        if error < eps:
            return total, n, h, error
        n *= 2
I_simp, n_simp, h_simp, err_simp = simpson_method(f, f4, a, b, eps)

print(I_simp)
