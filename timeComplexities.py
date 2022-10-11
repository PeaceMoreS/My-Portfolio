#CREATED BY MUHAMMAD WAJID LATIF, 201601923
from multiprocessing import Process
import random
import time
import itertools
from itertools import combinations
#I didnt not write the functions for powerset, bubblesort, mergesort and replace
#O(2^n)
def powerset(s):
  sTime = time.perf_counter()
  power_set=[[]]
  for elem in s:
    for sub_set in power_set:
      power_set=power_set+[list(sub_set)+[elem]]
  fTime=time.perf_counter()-sTime
  return fTime
#O(1)
def constantTime(i,j):
    sTime = time.perf_counter()
    k=i+j
    return time.perf_counter()-sTime
#O(n^2)
def bubbleSort(arr):
    swap = False
    sTime=time.perf_counter()
    n = len(arr)
    for i in range(n):
        for j in range(0, n - i - 1):
            if arr[j] > arr[j + 1]:
                swap=True
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
    if(swap==False):
            return 0
    fTime=time.perf_counter()-sTime
    return fTime
#O(nlgn)
def merge_sort(unsorted_list):
    if len(unsorted_list) <= 1:
        return unsorted_list
    middle = len(unsorted_list) // 2
    left_list = unsorted_list[:middle]
    right_list = unsorted_list[middle:]
    left_list = merge_sort(left_list)
    right_list = merge_sort(right_list)
    return list(merge(left_list, right_list))
def merge(left_half,right_half):
    res = []
    while len(left_half) != 0 and len(right_half) != 0:
        if left_half[0] < right_half[0]:
            res.append(left_half[0])
            left_half.remove(left_half[0])
        else:
            res.append(right_half[0])
            right_half.remove(right_half[0])
    if len(left_half) == 0:
        res = res + right_half
    else:
        res = res + left_half
    return res
#O(lgn)
def binary_search(list, key):
    first = 0
    final = len(list) - 1
    sTime = time.perf_counter()
    while (first <= final):
        mid = (final + first) // 2  # // FOR ROUNDING
        if (list[mid] == key):
            fTime= time.perf_counter()-sTime
            return fTime
        else:
            if (key < list[mid]):
                final = mid - 1
            else:
                first = mid + 1
    return False
#O(n)
def replace(list, key):
    sTime=time.perf_counter()
    for i in range(len(list)-1):
        list[i] = key
    fTime = time.perf_counter()-sTime
    return fTime
def constant(k):
    time = constantTime(k,100)
    print("Constant time Algorithm added",k,"& 100 in ", str(round(time, 7)), "Seconds")
def logarithmic(k):
    list2 = []
    for i in range(k):
        find_random = random.randrange(0, 1000000)
        list2.append(find_random)
    list2.sort()
    timeT=binary_search(list2, find_random)
    if(not time):
        sTime = time.perf_counter()
        binary_search(list2, find_random)
        fTime = time.perf_counter()-sTime
        print("Binary Search completed, but the key was not found!.... Took", str(round(fTime, 7)) ,
              "Seconds to search")
    print("Logarithmic time Algorithm O(lgn) [Binary Search] Found Key"
          " in list using binary Search in ", str(round(timeT, 7)) ,"Seconds")
def linear(k):
    list3 = []
    for i in range(k):
        find_random = random.randrange(0, 1000000)
        list3.append(find_random)
    time = replace(list3, 10)
    print("Linear time Algorithm O(n) [Replace] Replaced all integers in list in ~", str(round(time, 7)), "Seconds")
def quasilinear(k):
    list4 = []
    for i in range(k):
        find_random = random.randrange(0, 1000000)
        list4.append(find_random)
    sTime=time.perf_counter()
    merge_sort(list4)
    fTime=time.perf_counter()-sTime
    print("Quasilinear Algorithm O(nlgn) [Merge Sort] Sorted list in ~", str(round(fTime, 7)), "Seconds")
def quadratic(k):
    list5 = []
    for i in range(k):
        find_random = random.randrange(0, 1000000)
        list5.append(find_random)
    time = bubbleSort(list5)
    print("Quadratic Algorithm O(n^2) [Bubble Sort] Sorted list in ", str(round(time, 7)) , "Seconds")
def exponential(k):
    list6 = []
    for i in range(k):
        find_random = random.randrange(0, 1000000)
        list6.append(find_random)
    time = powerset(list6)
    print("Exponential Algorithm O(2^n) [Generating Powerset] "
          "generated Powerset in ", str(round(time, 7)) , "Seconds")
def stressCPU1(k):
    list7 = []
    for i in range(k):
        find_random = random.randrange(0, 1000000)
        list7.append(find_random)
def stressCPU2(k):
    list7 = []
    for i in range(k):
        find_random = random.randrange(0, 1000000)
        list7.append(find_random)
def main():
    print("Enter size of lists:")
    try:
        i = int(input())
        print("Choose list size for the exponential function")
        expo = int(input())
    except(Exception):
        print("Not an integer! Rerun")
        return

    print("Every list will generate random integers between 1 and 1000000")
    print("Exponential function will generate list of size:",2**expo)
    print("----------------------------------------------------------------------------------------------")
    Constant = Process(target=constant, args=(i,))         #SIMPLE ADDITION, O(1)
    Log = Process(target=logarithmic, args=(i,))           #BINARY SEARCH, O(lgn)
    Linear = Process(target=linear, args=(i,))             #REPLACE, O(n)
    quaLinear = Process(target=quasilinear, args=(i,))     #MERGE SORT, O(nlgn)
    Quadratic = Process(target=quadratic, args=(i,))       #BUBBLE SORT, O(n^2)
    Exponential = Process(target=exponential, args=(expo,))   #POWERSET, O(2^n)
  #  stress1 = Process(target=stressCPU1, args=(i,))        #Just to Stress CPU/Memory
  #  stress2 = Process(target=stressCPU2, args=(i,))        #Just to Stress CPU/Memory
    Constant.start()
    Log.start()
    Linear.start()
    quaLinear.start()
    Quadratic.start()
    Exponential.start()
    Constant.join()
    Log.join()
    Linear.join()
    quaLinear.join()
    Exponential.join()
    Quadratic.join()
    print("---------------------Thank you! :)----------------------------------")
  #  stress1.start()
  #  stress2.start()
# def convertSeconds(seconds):
#     h = seconds//(60*60)
#     m = (seconds-h*60*60)//60
#     s = seconds-(h*60*60)-(m*60)
#     converted= str(round(h))+":"+str(round(m))+":"+str(round(s))
#     return converted
if __name__ == '__main__':
    main()