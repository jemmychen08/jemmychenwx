package com.example.sort;

/**
 * @Auther: Jemmy
 * @Date: 2019/8/13 23:10
 * @Description:
 */
public class QuickSort {

    public static  void getQuickSort(Integer[] arr,int low,int high){
        int temp = arr[low];
        int t ;
        while (low < high){
            while (arr[high]>=temp){
                high--;
            }
            while (arr[low]<=temp){
                low++;
            }

            t =arr[low];
            arr[low] = arr[high];
            arr[high] = t;
        }
        arr[low] = temp;
        getQuickSort(arr,low,low-1);
        getQuickSort(arr,high+1,high);
    }

}
