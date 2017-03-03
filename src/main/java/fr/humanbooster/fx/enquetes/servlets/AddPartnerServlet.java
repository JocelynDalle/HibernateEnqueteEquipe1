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
 * Servlet implementation class AddPartnerServlet
 */
@WebServlet("/AddPartnerServlet")
public class AddPartnerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SurveyService ss = new SurveyServiceImpl();
	private PartenerSiteService pss = new PartenerSiteServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddPartnerServlet() {
		super();
		ss = new SurveyServiceImpl();
		pss = new PartenerSiteServiceImpl();
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
				System.out.println("IdSurvey doit être un entier");
				response.sendRedirect("index");
				e.printStackTrace();
				return;
			}
			Set<PartnerSite> partners = pss.findAll();
			System.out.println("idSurvey : " + idSurvey);
			SurveyInternet survey = (SurveyInternet) ss.findById(idSurvey);
			Set<PartnerSite> surveyPartners = survey.getLsPartnerSite();
			partners.removeAll(surveyPartners);
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
		String name = request.getParameter("name");
		String url = request.getParameter("url");
		String idSurveyStr = request.getParameter("idSurvey");
		String idPartnerStr = request.getParameter("idPartner");
		int idSurvey = 0;
		int idPartner = 0;
		PartnerSite partnerSite = null;
		if (idSurveyStr != null) {
			if (name != null && url != null) {
				partnerSite = pss.create(name, url);
				System.out.println("PartnerSite créé : " + partnerSite);
			}
			idSurvey = Integer.parseInt(idSurveyStr);
			if (idPartnerStr != null) {
				try {
					idPartner = Integer.parseInt(idPartnerStr);
					System.out.println(ss.addPartnerToSurvey(idPartner, idSurvey));
				} catch (NumberFormatException e) {
					System.out.println(
							"Erreur de parsing : idPartner = '" + idPartnerStr + ", idSurvey = '" + idSurvey + "'");
					e.printStackTrace();
					response.sendRedirect("index");
					return;
				}
			}
			request.setAttribute("idSurvey", idSurvey);
			doGet(request, response);
		} else {
			response.sendRedirect("index");
		}
	}

}
