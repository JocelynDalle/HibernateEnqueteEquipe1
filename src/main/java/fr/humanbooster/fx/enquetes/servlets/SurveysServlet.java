package fr.humanbooster.fx.enquetes.servlets;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.humanbooster.fx.enquetes.business.Question;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int type = Integer.valueOf(request.getParameter("type"));
		Set<Survey> surveys = ss.findAllSurvey();
		if(type == 0) {
			request.setAttribute("type", 0);
			request.getRequestDispatcher("survey.jsp").forward(request, response);
		} else {
			request.setAttribute("type", 1);
			request.getRequestDispatcher("survey.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
