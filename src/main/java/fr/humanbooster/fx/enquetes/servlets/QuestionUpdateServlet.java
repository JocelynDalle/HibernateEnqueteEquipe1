package fr.humanbooster.fx.enquetes.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.humanbooster.fx.enquetes.business.Question;
import fr.humanbooster.fx.enquetes.service.QuestionService;
import fr.humanbooster.fx.enquetes.service.impl.QuestionServiceImpl;

/**
 * Servlet implementation class QuestionAddServlet
 */
@WebServlet("/QuestionUpdateServlet")
public class QuestionUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	QuestionService qs = new QuestionServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String idQuestion = request.getParameter("idQuestion");
		Question question = qs.findQuestionById(idQuestion);
		request.setAttribute("question", question);
		request.getRequestDispatcher("modifieQuestion.jsp").forward(request, response);;
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idQuestion = request.getParameter("idQuestion");
		System.out.println(idQuestion);
		String wording = request.getParameter("wording");
		qs.modifyQuestion(idQuestion, wording);
		request.getRequestDispatcher("index").forward(request, response);
	}

}
