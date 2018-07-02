import java.util.Arrays;

/** 接口参数校验
 * @author:	fh qq313596790[青苔]
 * 修改日期：2015/11/2
 */
public class BubbleSort  {
	
	public static void bubbleSort(int[] arr) {
        int temp;//定义一个临时变量
        for(int i=0;i<arr.length-1;i++){//冒泡趟数
            for(int j=0;j<arr.length-i-1;j++){
                if(arr[j+1]<arr[j]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
    public static void main(String[] args) {
        int arr[] = new int[]{1,6,2,2,5};
        BubbleSort.bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
