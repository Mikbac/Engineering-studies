

#include "./avg.h"
#include <algorithm>

Avg::Avg()
{
}

float Avg::avgTable(int arr[], int n)
{
float avgValue = 0;
int sum = 0;

for(int i = 0; i<n; i++)
{
    sum = sum + arr[i];
}

avgValue = sum / n;

return avgValue;
}

