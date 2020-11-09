# -*- coding : utf-8 -*-
# @FileName  : qsort.py
# @Author    : Ruixiang JIANG (Songrise)
# @Time      : 2020-11-08
# @Github    ：https://github.com/songrise
# @Descriptions: to review qsort

  
def qsort(arr: list, lo: int, hi: int):
    """inplace quick sort,"""
    if lo >= hi:
        return
    pivot = arr[hi]
    low, high = lo, hi
    b = arr[lo:hi+1]
    for i in range(len(b)):
        if b[i] <= pivot:
            arr[low] = b[i]
            low += 1
        else:
            arr[high] = b[i]
            high -= 1
    print(arr)

    qsort(arr, lo, high-1)
    qsort(arr, high+1, hi)


def LomutoPartition(arr: list, lo: int, hi: int) -> int:
    """return the pivot index"""
    pivot = arr[hi]
    j = lo
    for i in range(lo, hi+1):
        if(arr[i] <= pivot):
            arr[i], arr[j] = arr[j], arr[i]
            j += 1
    return j-1


def qsortLomuto(arr: list, lo: int, hi: int):
    if lo >= hi:
        return
    pivotIndex = LomutoPartition(arr, lo, hi)
    print(arr)
    qsortLomuto(arr, lo, pivotIndex-1)
    qsortLomuto(arr, pivotIndex+1, hi)


def sort(arr: list):
    # qsort(arr, 0, len(arr)-1)
    qsortLomuto(arr, 0, len(arr)-1)


a = [14, 3, 2, 9, 7, 6, 8, 5]

b = list(reversed([_ for _ in range(9)]))
sort(a)
print(a)

# print(b)
# sort(b)
