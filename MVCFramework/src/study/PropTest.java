/*
 * �ڹ��� �÷��������ӟp�� ��ü �� �����Ͱ� key-value ������ �Ǿ��ִ� ������ �����Ͽ�
 * ó���� �� �ִ� ��ü�� Properties�� �Ѵ�. 
 * �� ������ ���� ������ ������ ������ ���� key-value�� �� �ָ��� �����Ѵ�.
 * */
package study;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropTest {
	//�������� �ڹ��ڵ忡�� Ư�� ���丮�� ����ִ� ���������� ���� �����ؾ���
	FileInputStream fis = null;
	Properties props = new Properties();
	
	public PropTest() {
		try {
			fis = new FileInputStream("C:/javaEE_workspace/MVCFramework/WebContent/WEB-INF/mapping/mapping.properties");
			props.load(fis); //������Ƽ�� ��ü�� ��Ʈ�� ����
			
			//���ݺ��ʹ� key ������ �˻� ����
			String value = props.getProperty("zerg");
			System.out.println(value);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new PropTest();
	}
}
