class ArrayOperation {
	public static void main(String[] args) {
		int[] arr = new int[5];
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		System.out.println("------------------------");
		/*arr[0] = 11;
		arr[1] = 22;
		arr[2] = 33;
		arr[3] = 44;*/
		for (int i = 0; i < arr.length -1; i++) {
			arr[i] = (i + 1) * 11;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		System.out.println("------------------------");
		for (int i = arr.length - 1; i > 0; i--) {
			arr[i] = arr[i-1];
		}
		arr[0] = 55;
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}

		System.out.println();
		System.out.println("------------------------");
		for (int i = 0; i < arr.length - 1; i++) {
			arr[i] = arr[i+1];
		}
		arr[arr.length - 1] = 0;
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}

		System.out.println();
		System.out.println("------------------------");
		for (int i = 0; i < arr.length -1; i++) {
			if (22 == arr[i]) {
				arr[i] = 220;
				break;
			}
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}

		System.out.println();
		System.out.println("------------------------");
	}
}
