package com.example.sort;


/**
 * @author : jemmy
 * @date : 2019/8/13 10:41
 * @description :冒泡排序，时间复杂度是O[N^2]
 * 冒泡排序只会操作相邻的两个数据。每次冒泡操作都会对相邻的两个元素进行比较，看是否满足大小关系要求。
 * 如果不满足就让它俩互换。一次冒泡会让至少一个元素移动到它应该在的位置，重复n-1 次，
 * 就完成了 n 个数据的排序工作。
 */
public class BubbleSort {

    public  static Integer[] getBubbleSort(Integer[] arr){

        int temp;
        for (int i=0;i<arr.length-1;i++){

            for (int j = 0;j<arr.length-i-1;j++){

                if(arr[j]>arr[j+1]){

                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;


                }
            }
        }

        return arr;
    }

    public static void main(String[] args) {
        Integer[] arr ={77,8,44,6,41,22,11};
        Integer[] bubbleSort = BubbleSort.getBubbleSort(arr);

        System.out.println(bubbleSort);
    }
}
