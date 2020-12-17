/*기존의 JSP가 담당하고 있던 컨트롤러로서의 업무를 현재 클래스로 분리 시킨것(Model2)
 * 그래야 JSP는 순수 디자인 영역만 남기때문에 유지보수시 교체 가능(MVC패턴)
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
		//파라미터 받기
		String blood=request.getParameter("blood");
		//혈액형에 대한 판단 메시지
		BloodAdvisor advisor = new BloodAdvisor();
		String msg = advisor.getAdvice(blood);
		
		//결과에 대한 출력은 디자인인 View가 담당하므로, 이 서블릿에서 처리하면 안된다.
		//서버의 메모리에 임시적 저장 , 세션에 저장
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);		
	}
	
	@Override
	public String getViewPage() {		
		return "/movie/movie_result.jsp";
	}
}