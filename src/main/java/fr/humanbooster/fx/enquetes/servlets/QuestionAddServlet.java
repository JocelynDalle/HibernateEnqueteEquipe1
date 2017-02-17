package fr.humanbooster.fx.enquetes.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.humanbooster.fx.enquetes.business.Survey;
import fr.humanbooster.fx.enquetes.service.QuestionService;
import fr.humanbooster.fx.enquetes.service.SurveyService;
import fr.humanbooster.fx.enquetes.service.impl.QuestionServiceImpl;
import fr.humanbooster.fx.enquetes.service.impl.SurveyServiceImpl;

/**
 * Servlet implementation class QuestionAddServlet
 */
@WebServlet("/QuestionAddServlet")
public class QuestionAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private SurveyService ss = new SurveyServiceImpl();
	private QuestionService qs = new QuestionServiceImpl();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idSurvey = request.getParameter("idSurvey");
		int idSurveyInt = Integer.parseInt(idSurvey);
		Survey survey = ss.findById(idSurveyInt);
		request.setAttribute("survey", survey);
		request.getRequestDispatcher("ajoutEnquete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idSurvey = request.getParameter("idSurvey");
		String wording = request.getParameter("wording");
		qs.addQuestionToSurvey(wording, idSurvey);

		doGet(request, response);
	}

}
