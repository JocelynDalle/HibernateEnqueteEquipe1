package fr.humanbooster.fx.enquetes.servlets;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.humanbooster.fx.enquetes.business.Survey;
import fr.humanbooster.fx.enquetes.service.QuestionService;
import fr.humanbooster.fx.enquetes.service.SurveyService;
import fr.humanbooster.fx.enquetes.service.impl.QuestionServiceImpl;
import fr.humanbooster.fx.enquetes.service.impl.SurveyServiceImpl;

/**
 * Servlet implementation class SurveysServlet
 */
@WebServlet("/SurveysServlet")
public class SurveysServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SurveyService ss = new SurveyServiceImpl();
	private QuestionService qs = new QuestionServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SurveysServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Object objQ = session.getAttribute("newQ");
		Object objS = session.getAttribute("newS");
		int newQ = -1;
		int newS = -1;
		if(objQ != null) {
			newQ = (Integer)objQ;
			if(newQ == 1) {
				session.setAttribute("newQ", 0);
			}
		}
		if(objS != null) {
			newQ = (Integer)objS;
			if(newS == 1) {
				session.setAttribute("newS", 0);
			}
		}
		response.setCharacterEncoding("UTF-8");
		Set<Survey> surveys = ss.findAllSurvey();
		request.setAttribute("surveys", surveys);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String typeAction = request.getParameter("typeAction");
		String typeSurvey = request.getParameter("typeSurvey");
		int idSurvey = Integer.valueOf(request.getParameter("idSurvey"));
		if (typeAction != null && typeSurvey != null) {
			if (typeAction.equals("update")) {
				request.setAttribute("typeAction", typeAction);
				request.setAttribute("typeSurvey", typeSurvey);
				request.setAttribute("survey", ss.findById(idSurvey));
				request.getRequestDispatcher("survey.jsp").forward(request, response);
			} else if (typeAction.equals("delete")) {
				boolean deleted = ss.deleteSurvey(idSurvey);
				System.out.println("l'enqu�te " + idSurvey + " a �t� supprim�e");
				response.sendRedirect("index");
			} else {
				System.out.println("probl�me de d'attribut update et delete dans l'index");
				request.getRequestDispatcher("index").forward(request, response);
			}
		}

	}

}
