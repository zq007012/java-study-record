import java.util.Random;
class Test3 {
	/*
	3. 编程题 实现双色球抽奖游戏中奖号码的生成，中奖号码由 6 个红球号码和 1 个蓝球号码组成。 其中红球号码要求随机生成 6 个 1~33 之间不重复的随机号码。 其中蓝球号码要求随机生成 1 个 1~16 之间的随机号码。
	思路:
		a.获取随机数
			A.获取随机数可以使用java.util.Random中的nextInt(int bound)方法
			B.获取不重复的随机号码
				1.可以使用数组, 号码是数组的元素, 随机数是数组的角标
				2.获得下一个号码时,新建一个比上一个数组长度减1的数组,数组的元素是上一个数组除被选到的元素外的其它元素,接着获取随机数,直到六个红球选完
		b.打印随机数
	*/
	public static void main(String[] args) {
		//Random类用来获取随机数
		Random r = new Random();
		//声明一个数组来装生成的随机数,前六个用来装红球,最后一个是篮球
		int[] ballNumbers = new int[7];

		//获取红球的随机数,六次
		//声明一个redNumbers数组,把红球可以生成的数从小到大放到这个数组里
		//这个redNumbers数组用来装每个红球可以生成的所有数字

		//这个变量用来保存redNumbers数组的长度
		int length = 33;
		int[] redNumbers = new int[length];
		//对redNumbers的元素赋值,把红球可以生成的数从小到大放到这个数组里
		for (int a = 0; a < length; a++) {
			redNumbers[a] =  a + 1;
		}
		
		
		for (int b = 0; b < 6; b++ ) {
			//使用r对象获得33以内的随机数,用这个随机数作为redNumbers的角标,获得红球的随机数
			int randomIndex = r.nextInt(length);
			ballNumbers[b] = redNumbers[randomIndex];

			//获得一个随机数后,就需要把这个随机数从redNumbers数组里剔除
			/*
			把这个随机数从redNumbers数组里剔除的思路
			1.我们可以声明一个新的数组来装redNumbers数组的其它元素
			2.然后把引用redNumbers指向新的数组
			*/
			//我们可以声明一个新的数组,新的数组的长度比redNumbers的长度小1
			length = redNumbers.length;
			int[] redNumbers2 = new int[length - 1];
			//新数组装redNumbers数组的其它元素
			/**/
			for (int i = 0; i < length; i++) {
				if (i < randomIndex) {
					redNumbers2[i] = redNumbers[i];
				}else if (i > randomIndex) {
					redNumbers2[i-1] = redNumbers[i];
				}
			}
			redNumbers = redNumbers2;
		}

		//获得篮球的随机数,把这个随机数装到随机数数组里
		int blueNumber = r.nextInt(16) + 1;
		ballNumbers[6] = blueNumber;
		System.out.println("生成的随即号码为: ");

		//打印生成的随机数
		for (int c = 0; c < 7; c++) {
			if (c < 6) {
				System.out.println("红球" + c + ": " + ballNumbers[c]);
			}else{
				System.out.println("蓝球: " + ballNumbers[c]);
			}
		}
	}
}
