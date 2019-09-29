#include "./max.h"
#include <algorithm>

Max::Max()
{
}

int Max::maxTable(int arr[], int n)
{
int maxValue;
maxValue = arr[0];

for(int i = 1; i<n; i++)
{
    maxValue = std::max(maxValue, arr[i]);
}

return maxValue;
}
