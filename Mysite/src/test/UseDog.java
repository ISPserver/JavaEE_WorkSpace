package test;

public class UseDog {
	public static void main(String[] args) {
		for(int i=0; i<10; i++) {
			//new���� �ʰ� ����
			Dog d = Dog.getInstance();
			System.out.println(d);
		}
	}
}
