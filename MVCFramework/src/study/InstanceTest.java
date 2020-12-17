//�ڹٿ��� Ŭ������ �ν��Ͻ��� �����ϴ� ������� new �����ڸ� �ִ°� �ƴϴ�.
package study;

import java.lang.reflect.Method;

public class InstanceTest {
	public static void main(String[] args) {
		//DogŬ������ new �����ڸ� ���� �ʰ� �ø���
		try {
			Class dogClass = Class.forName("study.Dog");
			Method[] methods = dogClass.getMethods();
			for(Method m: methods) {
				System.out.println(m.getName());
			}
			Dog dog = (Dog)dogClass.newInstance();
			System.out.println(dog.getName());
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
