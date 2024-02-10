import sys
import itertools

L,C=map(int,sys.stdin.readline().split())
listN=list(input().split())
sortedlist=sorted(listN)
alist=[]
blist=[]
output=[]
for i in sortedlist:
    if i=='a' or i=='e'or i=='i'or i=='o'or i=='u':
        alist.append(i)
    else:
        blist.append(i)
alen=len(alist)
blen=len(blist)
if blen>=L-1:
    k=L-1

else:
    k=blen

    
for i in range(2,k+1):
    acom=list(itertools.combinations(alist,L-i))
    bcom=list(itertools.combinations(blist,i))
    for acase in acom:
        for bcase in bcom:
            result=sorted(acase+bcase)
            output.append(''.join(result))

output.sort()
              
    
for i in output:
    print(i)
    
