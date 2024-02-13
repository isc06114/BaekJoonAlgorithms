import sys
X=int(sys.stdin.readline())
a=X%5
b=int(X/5)
while a%3!=0:
    a=a+5
    b=b-1
    if b<0:
        break;

if b<0:
    print("-1")
else:
    print(int(a/3)+b)
    
