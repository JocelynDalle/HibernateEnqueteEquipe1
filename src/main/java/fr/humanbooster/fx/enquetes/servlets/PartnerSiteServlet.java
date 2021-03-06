package fr.humanbooster.fx.enquetes.servlets;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.humanbooster.fx.enquetes.business.PartnerSite;
import fr.humanbooster.fx.enquetes.business.SurveyInternet;
import fr.humanbooster.fx.enquetes.service.PartenerSiteService;
import fr.humanbooster.fx.enquetes.service.SurveyService;
import fr.humanbooster.fx.enquetes.service.impl.PartenerSiteServiceImpl;
import fr.humanbooster.fx.enquetes.service.impl.SurveyServiceImpl;

/**
 * Servlet implementation class PartnerSiteServlet
 */
@WebServlet("/PartnerSiteServlet")
public class PartnerSiteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PartenerSiteService pss = new PartenerSiteServiceImpl();
	private SurveyService ss = new SurveyServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PartnerSiteServlet() {
		super();
		pss = new PartenerSiteServiceImpl();
		ss = new SurveyServiceImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idSurveyStr = request.getParameter("idSurvey");
		int idSurvey = 0;
		if (idSurveyStr != null) {
			try {
				idSurvey = Integer.parseInt(idSurveyStr);
			} catch (NumberFormatException e) {
				System.out.println("IdSurvey doit �tre un entier");
				response.sendRedirect("index");
				e.printStackTrace();
				return;
			}
			Set<PartnerSite> partners = pss.findAll();
			System.out.println("idSurvey : " + idSurvey);
			SurveyInternet survey = (SurveyInternet) ss.findById(idSurvey);
			Set<PartnerSite> surveyPartners = survey.getLsPartnerSite();
			for(PartnerSite p : surveyPartners) {
				System.out.println("partner site :" + p);
			}
			if(!surveyPartners.isEmpty()) {
				partners.removeAll(surveyPartners);
			}
			request.setAttribute("partners", partners);
			request.setAttribute("survey", survey);
			request.getRequestDispatcher("modifieSitesPartenaires.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idSurveyStr = request.getParameter("idSurvey");
		String idPartnerStr = request.getParameter("idPartner");
		int idSurvey = 0;
		int idPartner = 0;
		
		if (idSurveyStr != null) {
			if(idPartnerStr != null) {
				try {
					idPartner = Integer.parseInt(idPartnerStr);
					idSurvey = Integer.parseInt(idSurveyStr);
					System.out.println(ss.addPartnerToSurvey(idPartner, idSurvey));
					request.setAttribute("idSurvey", idSurvey);
					doGet(request, response);
				} catch (NumberFormatException e) {
					System.out.println("Erreur de parsing : idPartner = '" + idPartnerStr + ", idSurvey = '" + idSurvey + "'");
					e.printStackTrace();
					response.sendRedirect("index");
				}
			}
			
		} else {
			response.sendRedirect("index");
		}
	}

}
