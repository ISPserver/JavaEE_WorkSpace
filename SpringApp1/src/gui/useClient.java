package gui;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class useClient {
	public static void main(String[] args) {
		//ChatClient�� has a ����� �����ϰ� �ִ� ��ü���� ���� new ���� ����,
		//�������� ApplicationContext�� �̿��� �ν��Ͻ����� ����(=injection)�ϱ�
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/config/gui-context.xml");
		
		ChatClient chatClient = (ChatClient)context.getBean("chatClient");
		chatClient.init();
	}
}
