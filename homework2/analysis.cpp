#include<stdio.h>
#include <string>
#include <iostream>
#include "mergeSort.cpp"
#include "quickSort.cpp"
#include "radixSort.cpp"
#include "selectSort.cpp"
#include "shellSort.cpp"
#include <math.h>

template<class T>
int getLength(T &array) {
    return (sizeof(array) / sizeof(array[0]));
}


using namespace std;
/*
 * mergeSort:
finish, 0.471791ms
quickSort:
finish, 0.175542ms
radixSort:
finish, 115.694ms
selectSort:
finish, 0.446375ms
shellSort:
finish, 0.1875ms
 */
int main() {
    int len = 100;
    int bound = 1000;

    string unHandleArr[1000] = {};
    for (int i = 0; i < len; i++) {
        string s;
        if (rand() % 2 != 0) {
            s.push_back('-');
        }
        // 千位规模
        for (int j = 0; j < bound; j++) {
            char temp = (rand() % 10 + '0');
            s.push_back(temp);
        }
        unHandleArr[i] = s;
    }


    string arr[1000] = {};
    for (int i = 0; i < len; i++) {
        arr[i] = unHandleArr[i];
    }

    using namespace std::chrono;

    steady_clock::time_point startTime = steady_clock::now();
    // 归并排序

    mergeSort(arr, 0, len - 1);
    cout << "mergeSort: ";
//    for (int i = 0; i < len; i++) {
//        cout << arr[i] << " ";
//    }
    cout << endl;
    steady_clock::time_point finishTime = steady_clock::now();

    cout << "finish, " << std::chrono::duration<double, std::milli>(finishTime - startTime).count() << "ms" << endl;


    // 快速排序
    for (int i = 0; i < len; i++) {
        arr[i] = unHandleArr[i];
    }
    startTime = steady_clock::now();
    quickSort(arr, 0, len - 1);
    cout << "quickSort: ";
//    for (int i = 0; i < len; i++) {
//        cout << arr[i] << " ";
//    }
    cout << endl;
    finishTime = steady_clock::now();
    cout << "finish, " << std::chrono::duration<double, std::milli>(finishTime - startTime).count() << "ms" << endl;

    // 基数排序
    startTime = steady_clock::now();
    vector<string> radixVector = radixSort(unHandleArr, len);
    cout << "radixSort: ";
//    for (int i = 0; i < len; i++) {
//        cout << arr[i] << " ";
//    }
    cout << endl;
    finishTime = steady_clock::now();
    cout << "finish, " << std::chrono::duration<double, std::milli>(finishTime - startTime).count() << "ms" << endl;


    //选择排序
    for (int i = 0; i < len; i++) {
        arr[i] = unHandleArr[i];
    }
    startTime = steady_clock::now();
    selectSort(arr, len);
    cout << "selectSort: ";
//    for (int i = 0; i < len; i++) {
//        cout << arr[i] << " ";
//    }
    cout << endl;
    finishTime = steady_clock::now();
    cout << "finish, " << std::chrono::duration<double, std::milli>(finishTime - startTime).count() << "ms" << endl;


    // 希尔排序
    for (int i = 0; i < len; i++) {
        arr[i] = unHandleArr[i];
    }
    startTime = steady_clock::now();
    shellSort(arr, len);
    cout << "shellSort: ";
//    for (int i = 0; i < len; i++) {
//        cout << arr[i] << " ";
//    }
    cout << endl;
    finishTime = steady_clock::now();
    cout << "finish, " << std::chrono::duration<double, std::milli>(finishTime - startTime).count() << "ms" << endl;
    return 0;
}