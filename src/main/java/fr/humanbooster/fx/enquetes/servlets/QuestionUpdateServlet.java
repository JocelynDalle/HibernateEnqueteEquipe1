package fr.humanbooster.fx.enquetes.servlets;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.humanbooster.fx.enquetes.business.Question;
import fr.humanbooster.fx.enquetes.business.Survey;
import fr.humanbooster.fx.enquetes.service.QuestionService;
import fr.humanbooster.fx.enquetes.service.SurveyService;
import fr.humanbooster.fx.enquetes.service.impl.QuestionServiceImpl;
import fr.humanbooster.fx.enquetes.service.impl.SurveyServiceImpl;

/**
 * Servlet implementation class QuestionAddServlet
 */
@WebServlet("/QuestionUpdateServlet")
public class QuestionUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SurveyService ss = new SurveyServiceImpl();
	private QuestionService qs = new QuestionServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuestionUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String idQuestion = request.getParameter("idQuestion");
		Question question = qs.findQuestionById(idQuestion);
		String idSurvey = request.getParameter("idSurvey");
		int idSurveyInt = Integer.parseInt(idSurvey);
		Survey survey = ss.findById(idSurveyInt);
		request.setAttribute("survey", survey);
		request.setAttribute("question", question);
		request.getRequestDispatcher("modifieQuestion.jsp").forward(request, response);
		;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String idQuestion = request.getParameter("idQuestion");
		String idSurvey = request.getParameter("idSurvey");
		String type = request.getParameter("typeAction");
		if (type != null && idQuestion != null && idSurvey != null) {
			switch (type) {
			case "update":
				String wording = request.getParameter("wording");
				qs.modifyQuestion(idQuestion, wording);
				HttpSession session = request.getSession(true);
				session.setAttribute("newQ", 1);
				session.setAttribute("idNewQ", idQuestion);
				session.setAttribute("idSurvey", idSurvey);
				break;
			case "delete":
				qs.deleteQuestion(idQuestion);
				break;
			default:
				System.out.println("Problème : ni update ni delete");
				break;
			}
		}
		System.out.println(idQuestion);
		response.sendRedirect("index");
	}

}
