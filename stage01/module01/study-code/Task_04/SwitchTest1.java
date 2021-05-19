class SwitchTest1 {
	public static void main(String[] args) {
		//char a = 'a';
		switch ('a') {
		case 10:
			System.out.println(10);
			break;
		case 97:
			System.out.println(97);
			break;
		//编译报错, case标签重复
		/*case 97:
			System.out.println('a');
			break;*/
		//编译报错, case标签重复
		/*case 'a':
			System.out.println('a');
			break;*/
		case 'b':
			System.out.println('b');
			break;
		default :
			System.out.println("default");
		}
	}
}
