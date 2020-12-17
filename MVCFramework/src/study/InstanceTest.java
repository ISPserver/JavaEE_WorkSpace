//자바에서 클래스의 인스턴스를 생성하는 방법에는 new 연산자만 있는건 아니다.
package study;

import java.lang.reflect.Method;

public class InstanceTest {
	public static void main(String[] args) {
		//Dog클래스를 new 연산자를 쓰지 않고 올리기
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
