# -*- coding: utf-8 -*-
def calculate():
    a = int(input()) #  �������� �����
    b = -10 #   ������� ��
    c = 10 #    �������� ��
    m = ""
    while True:
        if (a > 0): # ���� ������� ������������, � �������� -10 (�� �������)
            chastnoe = (abs(a)//abs(b)) # ������ ���������� �� ����, ����� ����������� ���������� ������� �����. �� �����. � ��������
            m = str(a + chastnoe*b) + m
            a = -chastnoe # ������ ��� ��-�� ����, ��� chastnoe - ��� ��������� ������� ������� ���� ����� � ������ ���� ������������� ��� 0
            
        elif (a < 0): # ���� ������� ������������, � �������� -10 (�� �������)
            chastnoe = (abs(a)//abs(b))+1 # ���������� 1, ����� ���� ��������� ������� �������������� �� ������������� � ��������
            m = str(a - chastnoe*b) + m
            a = chastnoe #������� �� ������� ����� ������ ������ ������������ ��� 0
        
        if abs(a) < abs(b) and a > 0: # ����� �� �����, ���� ������� ������������ � ������ ��������
            m = str(abs(a)) + m 
            break 
    print(m)
       
if __name__ == "__main__":
    calculate()
    
    

