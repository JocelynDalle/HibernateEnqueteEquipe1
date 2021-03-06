package fr.humanbooster.fx.enquetes.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.humanbooster.fx.enquetes.business.Survey;
import fr.humanbooster.fx.enquetes.service.SurveyService;
import fr.humanbooster.fx.enquetes.service.impl.SurveyServiceImpl;

/**
 * Servlet implementation class FilterServlet
 */
@WebServlet("/FilterServlet")
public class FilterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SurveyService ss = new SurveyServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilterServlet() {
        super();
        ss = new SurveyServiceImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nameFilter = request.getParameter("nameFilter");
		String startDateStr = request.getParameter("startDate");
		String endDateStr = request.getParameter("endDate");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//surround below line with try catch block as below code throws checked exception
		Date startDate = null;
		Date endDate = null;
		try {
			System.out.println(startDateStr);
			System.out.println(endDateStr);
			startDate = sdf.parse(startDateStr);
			request.setAttribute("startDate", DateFormat.getDateInstance( DateFormat.MEDIUM ).format(startDate));
		} catch (ParseException e) {
			System.out.println("Date de d�but non parsable");
//			e.printStackTrace();
		} catch (NullPointerException npe) {
			System.out.println("startDate est null");
		}
		try {
			endDate = sdf.parse(endDateStr);
			request.setAttribute("endDate", DateFormat.getDateInstance( DateFormat.MEDIUM ).format(endDate));
		} catch (ParseException e) {
			System.out.println("Date de fin non parsable");
//			e.printStackTrace();
		} catch (NullPointerException npe) {
			System.out.println("endDate est null");
		}
		System.out.println("date de d�but = " + startDate);
		System.out.println("name filter = " + nameFilter);
		Set<Survey> surveys = null;
		if(startDate != null && endDate != null) {
			surveys = ss.filterSurveys(nameFilter, startDate, endDate);
		} else if(startDate != null){
			surveys = ss.filterSurveys(nameFilter, startDate, null);
		} else if(endDate != null) {
			surveys = ss.filterSurveys(nameFilter, null, endDate);
		} else {
			surveys = ss.filterSurveys(nameFilter, null, null);
		}
		if (!nameFilter.equals("")) {
			request.setAttribute("nameFilter", nameFilter);
		}
		request.setAttribute("surveys", surveys);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
