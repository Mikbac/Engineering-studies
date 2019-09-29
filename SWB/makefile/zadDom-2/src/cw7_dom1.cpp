#include <iostream>

#include "./sortowanie.h"
#include "../libs/libswbmath/min.h"
#include "../libs/libswbmath/max.h"
#include "../libs/libswbmath/avg.h"


using namespace std;

int main()
{
	int arr[] = {1, 3, 5, 1, 9, 120, 3, 7, 10, 4, 12, 23, 1, 82, 231, 5, 2, 4, 1, 2, 5, 3, 9, 88, 12, 342, 65, 40, 999, 45, 23, 12, 87};
	int n = sizeof(arr)/sizeof(arr[0]);
    int * newArr;

    Sortowanie sortowanie;
    Min minElement;
    Max maxElement;
    Avg avgElement;

    cout<<"Przed sortowaniem: "<<endl;
	for (int i = 0; i < n; i++) {
		cout << arr[i] << " ";
	}
	

    cout<<endl<<"Sortowanie: "<<endl;
	sortowanie.sortTable(arr, n);
	for (int i = 0; i < n; i++) {
		cout << arr[i] << " ";
	}


	cout<<endl<<"Min: "<<endl;
	cout<<minElement.minTable(arr, n);


	cout<<endl<<"Max: "<<endl;
	cout<<maxElement.maxTable(arr, n);


	cout<<endl<<"Avg: "<<endl;
    cout<<avgElement.avgTable(arr, n)<<endl;



	return 0;
}
