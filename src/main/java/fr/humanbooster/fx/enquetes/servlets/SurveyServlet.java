package fr.humanbooster.fx.enquetes.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.humanbooster.fx.enquetes.business.Survey;
import fr.humanbooster.fx.enquetes.business.SurveyInternet;
import fr.humanbooster.fx.enquetes.business.SurveyPhone;

/**
 * Servlet implementation class SurveyServlet
 */
public class SurveyServlet extends HttpServlet {

	private Survey surveyPhone;
	private Survey surveyInternet;
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -942489127115599803L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public SurveyServlet() {
        super();
        // initialisation SurveyService A FAIRE
        surveyPhone = new SurveyPhone();
        surveyInternet = new SurveyInternet();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int type = Integer.parseInt(request.getParameter("type"));
		if (request.getParameter("id") == null) {
			int idServlet = Integer.parseInt(request.getParameter("id"));
		} else {
			
		}
		
//		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
