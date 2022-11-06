#include<stdio.h>

void selectSort(std::string arr[], int n);

void selectSort(std::string arr[], int n){

	for(int i=0; i<n-1; i++){
		int min = i;
		for(int j=i+1; j<n; j++){
			if(arr[min]>arr[j]){
				min=j;
			}
		}
		if(i!=min){
			std::string temp = arr[min];
			arr[min] = arr[i];
			arr[i] = temp;
		}
	}
}
