

#include "./min.h"
#include <algorithm>

Min::Min()
{
}

int Min::minTable(int arr[], int n)
{
int minValue;
minValue = arr[0];

for(int i = 1; i<n; i++)
{
    minValue = std::min(minValue, arr[i]);
}

return minValue;
}

