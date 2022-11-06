#include <iostream>
#include <vector>

using namespace std;

//求出数组中最大数的位数
int MaxBit(vector<string> input) {
    //默认最大数为第一个数字
    string max_num = input[0];
    //找出数组中的最大数
    for (auto &i: input) {
        if (i > max_num) {
            max_num = i;
        }
    }
    int p = 0;
    while (!max_num.empty()) {
        p++;
        //每次删除最后一位
        max_num.erase(max_num.length() - 1, 1);
    }
    return p;
}

//取出所给数字的第d位数字, d从1开始
int GetNum(string num, int d) {
    if(d>num.length()){
        return 0;
    }
    char* ch = const_cast<char *>(num.c_str());
    if(ch[num.length()- d] == '-'){
        return 0;
    }
    return ch[num.length()- d]-'0';
}

vector<string> radixSort(string* stringInput, int length) {
    vector<string> input;
    for(int i = 0; i<length;i++){
        input.push_back(*stringInput++);
    }
    //创建临时存放排序过程中的数据
    vector<string> bucket(length);
    vector<int> count(10);

    for (int d = 1; d <= MaxBit(input); d++) {
        // 计数器清0
        for (int i = 0; i < 10; i++) {
            count[i] = 0;
        }

        // 统计各个桶中的个数
        for (int i = 0; i < length; i++) {
            count[GetNum(input[i], d)]++;
        }

        //得到每个数应该放入bucket中的位置
        //即越大的数字对应的值就会包含所有比他低的数字对应的值
        //这样便于临时排序
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        //采用倒序进行排序是为了不打乱已经排好的顺序
        for (int i = length - 1; i >= 0; i--) {
            int k = GetNum(input[i], d);
            bucket[count[k] - 1] = input[i];
            count[k]--;
        }

        // 临时数组复制到 input 中
        for (int j = 0; j < length; j++) {
            input[j] = bucket[j];
        }
    }
    return input;
}
