package com.example.sort;

/**
 * @author : Jemmy
 * @date : 2019/8/13 23:10
 * @description : 冒泡排序，时间复杂度是O[Nlog2N]
 *
 * 通过一趟排序将要排序的数据分割成独立的两部分：分割点左边都是比它小的数，右边都是比它大的数。
 *
 * 然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
 */
public class QuickSort {

    public static  void getQuickSort(int[] arr,int low,int high){
        //如果左边等于右边，说明排序完成跳出结束程序
        if(low >= high){
            return;
        }
        int i,j,t,temp=arr[low] ;

        i=low;j=high;
        while (i < j){
            // 顺序很重要，先从右边开始往左找，直到找到比base值小的数
            while (arr[j]>=temp && i<j ){
                j--;
            }
            // 再从左往右边找，直到找到比base值大的数
            while (arr[i]<=temp && i<j ){
                i++;
            }
            // 上面的循环结束表示找到了位置或者(i>=j)了，交换两个数在数组中的位置
            if(i<j){
                t =arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }

        }
        // 将基准数放到中间的位置（基准数归位）
        arr[low] =arr[i];
        arr[i] = temp;

        getQuickSort(arr,low,i-1);
        getQuickSort(arr,i+1,high);
    }

    public static void main(String[] args) {


        int[] arr ={77,8,44,6,41,22,11,22,77};
        QuickSort.getQuickSort(arr,0,arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+",");
        }

    }

}
