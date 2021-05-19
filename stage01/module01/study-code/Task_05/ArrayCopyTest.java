class ArrayCopyTest {
	public static void main(String[] args) {
		int[] arr = {11 , 22 , 33 , 44 , 55};
		System.out.println("第一个数组中的元素有: ");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		System.out.println("--------------");
		int[] brr = new int[3];
		/*for (int i = 0; i < brr.length; i++) {
			brr[i] = arr[i + 1];
		}*/
		System.arraycopy(arr , 1, brr , 0 , 3);
		for (int i = 0; i < brr.length; i++) {
			System.out.print(brr[i] + " ");
		}
		System.out.println();
		System.out.println("--------------");
	}
}
