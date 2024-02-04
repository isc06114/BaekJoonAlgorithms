n = int(input())
INIT = 0
def count_func():
    dp = [[INIT]*(1<<10) for _ in range(10)]
    for i in range(1,10):
        dp[i][1 << i] = 1
    for _ in range(2, n+1):
        second_dp = [[INIT]*(1<<10) for _ in range(10)]
        for i in range(10):
            for j in range(1<<10):
                if i>0:
                    second_dp[i][j|(1<<i)] +=dp[i-1][j]
                if i<9:
                    second_dp[i][j|(1<<i)] +=dp[i+1][j]
        dp = second_dp
    return sum(dp[i][(1<<10)-1] for i in range(10))% 1000000000
print(count_func())