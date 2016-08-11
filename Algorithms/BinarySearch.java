//二分查找：在有序数组中查找某一特定元素的搜索算法
public class BinarySearch {
	public static void main(String[] args) {
		int[] data = {1, 4, 6, 14, 56, 233, 1234};
		int target = 233;
		int index = binarySearch2(data, 0, data.length - 1, target);
		if (index > -1) {
			System.out.println("found");
		}else {
			System.out.println("not found");
		}
	}

	/** 
 	* 递归方法实现二分查找
 	* @param data   已排序数组(这里假设是从小到大排序) 
 	* @param from   起始位置 
 	* @param to     终止位置 
 	* @param target 要查找的值
 	* @return -1：表示没找到； >-1：表示找到了，且返回值即找到的数字在data数组中的下标
 	*/  
	private static int binarySearch1(int[] data, int from, int to, int target) {
		if (from <= to) {
			int mid = (from + to) / 2;//中间位置
			if (data[mid] == target) {//找到了
				return mid;
			}else if (data[mid] < target) {//中间的值比目标值小，则在左半边继续查找
				return binarySearch1(data, mid + 1, to, target);
			}else {//中间的值比目标值大，则在右半边继续查找
				return binarySearch1(data, from, mid - 1, target);	
			}
		}
		return -1;
	}

	/** 
 	* 非递归方法实现二分查找
 	* @param data   已排序数组(这里假设是从小到大排序) 
 	* @param from   起始位置 
 	* @param to     终止位置 
 	* @param target 要查找的值
 	* @return -1：表示没找到； >-1：表示找到了，且返回值即找到的数字在data数组中的下标
 	*/  
	private static int binarySearch2(int[] data, int from, int to, int target) {
		while(from <= to) {
			int mid = (from + to) / 2;
			if (data[mid] == target) {
				return mid;
			}else if (data[mid] < target) {
				from = mid + 1;				
			}else {
				to = mid - 1;
			}
		}
		return -1;
	}
}