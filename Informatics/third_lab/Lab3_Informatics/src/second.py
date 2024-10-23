import re

test0 = "20 + 22 = 42"
test1 = "25782asdoifhsad + 23asdfasdf + 845asdfas = sdfasd26650sadfasd"
test2 = "asdfoiasdyg235478621sjhgias - iohasogasg243275ssdfasiudfg + isgdfyhsa452750shdjfas +  wrwqer789245asdf76sdf12s3 - 98252 = 21537862157"
test3 = "25sduhfg9924185usd92sdhu82 + 683491shuo489ashd +skaosihda9872536 + 38hjfd38sdfhjk82374 = 932hisd295sfiudh8293743"
test4 = "74986fhsd + 3748 + 82hsd0f + 2857sdjsdjk02sf003 - 0237rwedfs34837 + 8927sdfis  = aw8023457sdjh828247"
test5 = "7477473chjk + 8027sshjd23807 + 825798sdjfo8927sdh + asdo92875sjdf83 - 9823rhsdf329 = 0827hsdf9823hsdf80234"

def replacer(x):
    return str(4*int(x.group())**2-7)

def main():
    c = 0
    tests = [test0, test1, test2, test3, test4, test5]
    for test in tests: 
        print("Тест номер " + str(c) +":", end = " ")
        test = re.sub("[^+-=1234567890 ]", "", test)
        print(re.sub("[+-]?\d+", replacer, test, count=0))
        c += 1
        
if __name__ == "__main__":
    main()