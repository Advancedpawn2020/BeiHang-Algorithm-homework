#include<stdio.h>

void shellSort(std::string arr[], int n);

void shellSort(std::string arr[], int n) {
    int gap = n / 2;
    while (gap > 0) {
        for (int i = gap; i < n; i++) {
            std::string current = arr[i];
            int preIndex = i - gap;
            while (preIndex >= 0 && arr[preIndex] > current) {
                arr[preIndex + gap] = arr[preIndex];
                preIndex = preIndex - gap;
            }
            arr[preIndex + gap] = current;
        }
        gap = gap / 2;
    }
}
