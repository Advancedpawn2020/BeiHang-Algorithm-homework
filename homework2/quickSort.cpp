#include<stdio.h>

void quickSort(std::string arr[], int start, int end);

void quickSort(std::string arr[], int start, int end){
	if(start>=end){
		return;
	}
	std::string privot = arr[start];
	int low = start;
	int high = end;
	while(low < high){
		while(low < high && arr[high] >=privot){
			high--;
		}
		arr[low] = arr[high];

		while(low < high && arr[low] < privot){
			low++;
		}
		arr[high] = arr[low];
	}
	arr[low] = privot;

	quickSort(arr, start, low-1);
	quickSort(arr, low+1, end);
} 
