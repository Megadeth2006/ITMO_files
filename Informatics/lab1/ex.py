from itertools import permutations
c = 1
for i in permutations('12345', 5):
    i = "".join(i)
    print(i, c)
    if c == 35:
        break
    c += 1