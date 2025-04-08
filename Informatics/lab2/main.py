first = str(input()) 

s1 = (first[0]+first[2]+first[4]+first[6]).count("1")%2 #вычисляем синдромы последовательности
s2 = (first[1]+first[2]+first[5]+first[6]).count("1")%2 #если четное число единиц, то синдром = 0
s3 = (first[3]+first[4]+first[5]+first[6]).count("1")%2

wrong_bit_ind = int(str(s3) + str(s2) + str(s1), 2) # вычисляем с помощью синдромов ошибочный бит

if wrong_bit_ind == 0: # если значение переменной равно 0, то ошибки нет, печатаем информационные биты как есть
    print(first[2]+first[4]+first[5]+first[6])
else: # иначе инверсируем бит под индексом значения переменной wrong_bit_ind (посчитали с помощью синдромов)
    answer = ""
    wrong_bit_ind -= 1
    for i in [2, 4, 5, 6]:
        if wrong_bit_ind == i:
            answer += str(int(first[i])^1)
        else:
            answer += first[i]
    print(answer, wrong_bit_ind) # вывод корректных информационных битов и номер бита, в котором допущена ошибка
