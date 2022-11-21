#include<stdio.h>
#include <string>
#include <chrono>
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
    string ss;

    string unHandleArr[100] = {};
    for (int i = 0; i < len; i++) {
        string s;
        string s1;
        if (rand() % 2 != 0) {
            s.push_back('-');
        }
        // 千位规模
        for (int j = 0; j < bound; j++) {
            char temp = (rand() % 10 + '0');
            s.push_back(temp);
            s1.push_back('0');
        }
        unHandleArr[i] = s;
        ss = s1;

    }
    string arr[100] = {};
    for (int i = 0; i < len; i++) {
        arr[i] = unHandleArr[i];
    }

    using namespace std::chrono;

    steady_clock::time_point startTime = steady_clock::now();
    // 归并排序

    string arr1[100] = {};
    mergeSort(arr, 0, len - 1);
    for (int i = len -1 , j = 0; i >= 0; i--) {
        if (arr[i] < ss) {
            arr1[j++] = arr[i];
        }
        else{
            arr1[i] = arr[i];
        }
    }
    cout << "mergeSort: ";
//    for (int i = 0; i < len; i++) {
//        cout << arr1[i] << " ";
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
    for (int i = len -1 , j = 0; i >= 0; i--) {
        if (arr[i] < ss) {
            arr1[j++] = arr[i];
        }
        else{
            arr1[i] = arr[i];
        }
    }
//    for (int i = 0; i < len; i++) {
//        cout << arr1[i] << " ";
//    }
    cout << endl;
    finishTime = steady_clock::now();
    cout << "finish, " << std::chrono::duration<double, std::milli>(finishTime - startTime).count() << "ms" << endl;

    // 基数排序
    startTime = steady_clock::now();
    vector<string> radixVector = radixSort(unHandleArr, len);
    cout << "radixSort: ";
    for (int i = len -1 , j = 0; i >= 0; i--) {
        if (arr[i] < ss) {
            arr1[j++] = arr[i];
        }
        else{
            arr1[i] = arr[i];
        }
    }
//    for (int i = 0; i < len; i++) {
//        cout << arr1[i] << " ";
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
    for (int i = len -1 , j = 0; i >= 0; i--) {
        if (arr[i] < ss) {
            arr1[j++] = arr[i];
        }
        else{
            arr1[i] = arr[i];
        }
    }
//    for (int i = 0; i < len; i++) {
//        cout << arr1[i] << " ";
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
    for (int i = len -1 , j = 0; i >= 0; i--) {
        if (arr[i] < ss) {
            arr1[j++] = arr[i];
        }
        else{
            arr1[i] = arr[i];
        }
    }
//    for (int i = 0; i < len; i++) {
//        cout << arr1[i] << " ";
//    }
    cout << endl;
    finishTime = steady_clock::now();
    cout << "finish, " << std::chrono::duration<double, std::milli>(finishTime - startTime).count() << "ms" << endl;
    return 0;
}
