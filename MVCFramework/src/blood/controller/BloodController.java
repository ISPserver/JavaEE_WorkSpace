/*������ JSP�� ����ϰ� �ִ� ��Ʈ�ѷ��μ��� ������ ���� Ŭ������ �и� ��Ų��(Model2)
 * �׷��� JSP�� ���� ������ ������ ���⶧���� ���������� ��ü ����(MVC����)
 * */
package blood.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.Controller;

import blood.model.BloodAdvisor;

public class BloodController implements Controller{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�Ķ���� �ޱ�
		String blood=request.getParameter("blood");
		//�������� ���� �Ǵ� �޽���
		BloodAdvisor advisor = new BloodAdvisor();
		String msg = advisor.getAdvice(blood);
		
		//����� ���� ����� �������� View�� ����ϹǷ�, �� �������� ó���ϸ� �ȵȴ�.
		//������ �޸𸮿� �ӽ��� ���� , ���ǿ� ����
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);		
	}
	
	@Override
	public String getViewPage() {		
		return "/movie/movie_result.jsp";
	}
}