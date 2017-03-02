package fr.humanbooster.fx.enquetes.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.humanbooster.fx.enquetes.business.PartnerSite;
import fr.humanbooster.fx.enquetes.service.PartenerSiteService;
import fr.humanbooster.fx.enquetes.service.impl.PartenerSiteServiceImpl;

/**
 * Servlet implementation class AddPartnerServlet
 */
@WebServlet("/AddPartnerServlet")
public class AddPartnerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PartenerSiteService pss = new PartenerSiteServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPartnerServlet() {
        super();
        pss = new PartenerSiteServiceImpl();
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
		String name = request.getParameter("name");
		String url = request.getParameter("url");
		String idSurveyStr = request.getParameter("idSurvey");
		int idSurvey = 0;
		PartnerSite partnerSite = pss.create(name, url);
		System.out.println("PartnerSite créé : " + partnerSite);
		if(idSurveyStr != null) {
			idSurvey = Integer.parseInt(idSurveyStr);
			request.setAttribute("idSurvey", idSurvey);
			request.getRequestDispatcher("modifieSitesPartenaires");
		} else {
			response.sendRedirect("index");
		}
	}

}
