class ArrayArrayTest {
	public static void main(String[] args) {
			//二维数组存的是一维数组本身,还是只是一维数组的地址
			int[][] arr2 = new int[2][];
			int[] brr1 = new int[3];
			System.out.println("brr1 = " + brr1);
			int[] brr2 = new int[4];
			System.out.println("brr2 = " + brr2);
			
			arr2[0] = brr1;
			arr2[1] = brr2;
			System.out.println("arr2[0] = " + arr2[0]);
			System.out.println("arr2[1] = " + arr2[1]);
			/*
			brr1 =	  [I@6842775d
			brr2 =    [I@574caa3f
			arr2[0] = [I@6842775d
			arr2[1] = [I@574caa3f
			*/
		
	}
}
