#include<stdio.h>
#define _CRT_SECURE_NO_WARNINGS
using namespace std;
int main(int argc, char** argv)
{
	int T, M, N;
	scanf(" %d", &T);
	for (int test_case = 1; test_case <= T; test_case++) {
		scanf(" %d %d", &N,&M);
		if ((M & (1 << N) - 1) == ((1 << N) - 1)) {
			printf("#%d ON\n", test_case);
		}
		else {
			printf("#%d OFF\n", test_case);
		}
	}

	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}

