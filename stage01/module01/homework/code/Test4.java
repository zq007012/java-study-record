import java.math.BigDecimal;
import java.util.Scanner;
class Test4 {
	public static void main(String[] args) {
		/*
		编程题 自定义数组扩容规则，当已存储元素数量达到总容量的 80%时，扩容 1.5 倍。 例如，总容量是 10，当输入第 8 个元素时，数组进行扩容，容量从 10 变 15
		思路: 
			1. 先新建一个长度为2的数组
			2. 提醒用户输入要保存的元素,获得用户输入的元素,将元素保存到数组中,
			3. 当用户输入的元素个数超过数组长度的80%时,就新建一个长度是原数组1.5倍的新数组,把旧数组里的存的用户输入的元素转移到新数组中,然后把新数组的值赋值给旧数组
		*/
		int[] arr = new int[2];
		
		int index = 0;
		Scanner scanner = new Scanner(System.in);
		//可以重复地获取用户输入的元素
		while (true) {
			System.out.println("+++++++++++++++++++++++++++++++++++++++");
			System.out.println("请输入你要保存的元素: ");
			int x = scanner.nextInt();
			arr[index] = x;
			//判断用户输入的元素个数是否达到或超过了数组长度的80%	
			if (new BigDecimal(arr.length).multiply(new BigDecimal(0.8)).compareTo(new BigDecimal(index + 1)) <= 0) {
				//如果达到或超过了80%
				//就新建一个数组, 把旧数组里用户输入的元素转移到新数组中
				int[] arrTemp = new int[new BigDecimal(arr.length).multiply(new BigDecimal(1.5)).intValue()];
				//把旧数组中的元素复制到新数组中
				
				//代码优化
				/*for (int i = 0; i <= index; i++) {
					arrTemp[i] = arr[i];
				}*/
				System.copyarray(arr , 0 , arrTemp , 0 , index + 1);
				//把新数组的值赋值给旧数组,这样就实现了数组的扩容
				arr = arrTemp;
				System.out.println("你输入的是第" + (index+1) + "个元素" + ",这已经到了或超过了数组的80%,数组已经进行扩容");
				System.out.println("现在数组的长度是: " + arr.length + " , 是原来的1.5倍");
			}

			String s = "{";
			for (int j = 0; j < index; j++) {
				s = s + arr[j] + ", ";
			}
			s = s + arr[index] + "}";
			System.out.println("数组的长度是: " + arr.length + " , 您已经保存了" + (index + 1) + "个元素");
			System.out.println("您保存的元素有: " + s);
			index = index + 1;
		}
		
	}
}
