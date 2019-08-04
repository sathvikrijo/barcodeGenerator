import random
import string

randomBarNo = []
hashMap = {}

file = open("barcodes.txt","w")

for times in range(1000):
    code = (''.join(random.choice(string.ascii_uppercase + string.digits) for _ in range(6)))
    if code not in hashMap.keys():
        hashMap[code] = 1
        file.write(code)
        file.write("\n")

file.close()

#file.writelines(randomBarNo)
