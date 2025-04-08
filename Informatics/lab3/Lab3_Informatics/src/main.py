import re
test1 = "[<{|12113[<{|ewfg8iou  2wg349-8t50u2t43rfjwedlkjgwqruiop[<{|oqiwyutpiuwqegfhawe9gf8uoqytwegqw[<{|iwearhptuq3tighqwreio[<{|jwiqrhg9-7eqw8rtghqweriu[<{|" #6
test2 = "[<{|[<{|[<{|[<{|[<{|joASHDFPUIQWEFBNKASDLIGUQW BGVI [<{|IOHWERUOIGNQWB KJ;FG UI[<{|SDGFIUYWAGHBIJQWBEFJHKLGWg[<{|UIRWEG UAWER[<{|JIWSHDGUIWQRBGJISADHGJILSAQGF[<{|JKHAWPIGFUQWGFHBQWJKEBFGJKHAWDGF78QW3EHBGFIHUGWSD67F8QWEGFIHBWEIOY[<{|JSADBNGIFJNQR EW,M IHUIFG KBNJG UIQERHGT IQWGETIU [<{|HDWIU GYQWFGQWE UIFG YQWIUFGIUQWENGFKJASDFNGKJWEA[<{|" #13
test3 = "[<{|[<{|[<{|[<{|[<{|[<{|[<{|[<{|uiasdhfgoiuqwehbfjkbscvnj [<{|" #9
test4 = "[<{|wutyp  2fksdhjag2t73i5u2k[<{|fweuyr923t5`23tr8weytr15t6gyrwehrg4t7rygwihsdhfi[<{|fsbhdkjfglbahkfsd[<{|howueqyrthuq34tbwebfiweqgi[<{|ouwehrui23brbweifyg[<{|huefhweirguwei[<{|wo'eihrwq[t[<{|iohwe[fpqwhu[<{|jsfpaweuh[<{|]]" #10
test5 = "[<{|[<{|[<{|[<{|[<{|[<{|[<{|[<{|[<{|[<{|[<{|[<{|[<{|[<{|[<{|[<{|[<{|[<{|" #18
def main():
    c  = 1
    tests = [test1, test2, test3, test4, test5]
    for test in tests:
        print("Тест номер " + str(c) + ":", end = " ")
        print(len(re.findall('\[\<\{\|', test)))
        c += 1
    
    
if __name__ == "__main__":
    main()

