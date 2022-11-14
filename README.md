# beihang-algorithm-homework

[![standard-readme compliant](https://img.shields.io/badge/JDK-11-green)](https://img.shields.io/badge/JDK-11-green)
[![standard-readme compliant](https://img.shields.io/badge/language-java-blue)](https://img.shields.io/badge/language-java-blue)
[![standard-readme compliant](https://img.shields.io/badge/language-c%2B%2B-blue)](https://img.shields.io/badge/language-c%2B%2B-blue)

## 标题

北航《程序设计与算法》大作业，内容如下

* 选择排序，归并排序，快速排序，希尔排序，基数排序的实现
* 范围在[-10^100, 10^100]的大数数组排序（C或C++）
* 用多线程实现大规模数据的分布式排序，输入超过100万为最低大规模要求


## 内容链接

- [项目目录](#项目目录)
- [实现](#实现)
- [测试结果](#测试结果)
- [License](#License)

## 项目目录

```
├── README.md
├── homework2 C++大数排序  
├── pom.xml
├── src
    └── main
        └── java
            ├── analysis 排序执行与统计
            ├── data 排序数据源
            ├── sort Java排序算法
            └── task 多线程排序任务
```

## 实现

### 大数排序

根据数据长度，生成排序数字字符串。负数字符串增加`-`前缀。并且数字字符的ASCII比较，和普通数字比较结果相同。

### 多线程排序

* 使用ForkJoinPool进行多线程任务处理，核心思想是将大的任务拆分成多个小任务（即[fork](https://github.com/haichaoshi/java-knowledge/blob/main/img/fokjoin.png)），然后在将多个小任务处理汇总到一个结果上（即join）
* ForkJoinPool已经分配了与线程数相等的队列，当有任务加入线程池时，会被平均分配到对应的队列上，各线程进行正常工作，当有线程提前完成时，会从队列的末端“[窃取](https://github.com/haichaoshi/java-knowledge/blob/main/img/work-stealing.png)”其他线程未执行完的任务
* fork的每一个job可以设置排序算法，join统一使用归并算法

## 测试结果

### 一般排序

| 算法     | 平均时间复杂度 | 最坏时间复杂度 | 最好时间复杂度 | 耗时(数组长度：10000) | 耗时(数组长度：100000) |
|----------|----------------|----------------|----------------|-----------------------|------------------------|
| 选择排序 | O(n^2)         | O(n^2)         | O(n)           | 42ms                  | 3824ms                 |
| 归并排序 | O(nlogn)       | O(nlogn)       | O(nlogn)       | 85ms                  | 25ms                 |
| 快速排序 | O(nlogn)       | O(n^2)         | O(nlogn)       | 2ms                   | 16ms                   |
| 希尔排序 | O(nlogn)       | O(ns)          | O(n)           | 3ms                   | 17ms                   |
| 基数排序 | O(N\*M)        | O(N\*M)        | O(N\*M)        | 15ms                  | 756ms                  |


### 大数排序

| 算法     | 耗时(数组长度：10000) | 耗时(数组长度：100000) |
|----------|-----------------------|------------------------|
| 选择排序 | 59.6254ms             | 3824ms                 |
| 归并排序 | 3.28178ms             | 590.846ms              |
| 快速排序 | 1.37231ms             | 230.84ms               |
| 希尔排序 | 3.60548ms             | 17ms                   |
| 基数排序 | 894.686ms             | 756ms                  |


### 多线程排序

| 算法     | 耗时(数组长度：1000000) |
|----------|-----------------------|
| 归并排序 | 110ms             |

## License

MIT © 2022 AdvancedPawn
