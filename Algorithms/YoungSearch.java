//杨氏矩阵查找算法实现
//杨氏矩阵：在一个m行n列二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
public class YoungSearch {
	
	public static void main(String[] args) {
		int[][] arr = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
		int height = 4;
		int width = 4;
		int search = 55;//待查找的数
		boolean have = youngSearch(arr, height, width, search);
		if(have) {
			System.out.println("have");
		}else {
			System.out.println("do not have");
		}
	}

	/*
		a[][]:杨氏矩阵
		height:矩阵的行数
		width:矩阵的列数
		search:待查找的数
	*/
	public static boolean youngSearch(int a[][], final int height, final int width, final int search) {
        int i = 0;
        int j = width - 1;
        int var = a[i][width - 1];
		
        while (true) {
			if (var == search) {
                return true;
            } else if (var < search && i < height - 1) {
                var = a[++i][j];
            } else if (var > search && j > 0) {
                var = a[i][--j];
            } else {
                return false;
            }
		}
		
	}
}