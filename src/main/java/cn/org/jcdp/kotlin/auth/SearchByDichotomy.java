package cn.org.jcdp.kotlin.auth;

import java.util.Arrays;

/**
 * SearchByDichotomy
 *
 * @author venson
 * @version 20180709
 */
public class SearchByDichotomy {
    private final static int[] arr=new int[]{5,24,35,66,68,69,85,655,755,888};
    private static int  sum=0;

    public static void main(String[] args){
        System.out.println(bSearch(arr,0,arr.length-1,5));
        System.out.println(sum);
    }

    public static int search(int[] arr,int start,int end,int val){
        sum++;
        if(arr==null){
            return -1;
        }
        if(end>arr.length){
            return -1;
        }
        if(start<=end){
            int mid=(end+start)/2;
            int result=arr[mid];
            System.out.println("start:"+start+",end:"+end+",mid："+mid+",result:"+result);
            if(result==val){
                return mid;
            }else if(result>val){
                return search(arr,start,mid-1,val);
            }else{
                return search(arr,mid+1,end,val);
            }
        }else{
            // 交叉了-即找不到情况,说明start在0或者尾部或者在交叉时的前一位,那么得到应该插入位置;
            if (start > end) {
                // 找到了前面一个start,并确定在此即可;
                System.out.println("no find, will insert in :" + start);
            }
            return -1;
        }
    }

    public static int bSearch(int[] arr,int start,int end,int val){
        if(arr==null){
            return -1;
        }
        if(end>arr.length){
            return -1;
        }
        for (;start<=end;){
            sum++;
            int mid=(start+end)/2;
            int result=arr[mid];
            System.out.println("start:"+start+",end:"+end+",mid："+mid+",result:"+result);
            if(result==val){
                return mid;
            }else if(val>result){
                start=mid+1;
            }else{
                end=mid-1;
            }
        }
        // 交叉了-即找不到情况,说明start在0或者尾部或者在交叉时的前一位,那么得到应该插入位置;
        if (start > end) {
            // 找到了前面一个start,并确定在此即可;
            System.out.println("no find, will insert in :" + start);
        }
        return -1;
    }
}
