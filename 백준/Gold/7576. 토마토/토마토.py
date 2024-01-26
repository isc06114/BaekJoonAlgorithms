import sys
from collections import deque

M,N=map(int,sys.stdin.readline().split())

date=0
queue=deque()
box=[]
raw=M*N
direction=[(1,0),(0,1),(-1,0),(0,-1)]
for i in range(N):
    box.append(list(map(int,sys.stdin.readline().split())))


for i in range(N):
    for j in range(M):
        if box[i][j]==1:
            queue.append((i,j,date))
            raw-=1
        elif box[i][j]==-1:
            raw-=1
while queue:
    Y,X,date=queue.popleft()
    for d in direction:
        aX=X+d[0]
        aY=Y+d[1]
        if 0<=aX<M and 0<=aY<N:
            if box[aY][aX]==0 :
                box[aY][aX]=1
                queue.append((aY,aX,date+1))
                raw-=1
if date==0 and raw==0:
    print('0')
else:
    if raw!=0:
        print("-1")
    else:
        print(date)
