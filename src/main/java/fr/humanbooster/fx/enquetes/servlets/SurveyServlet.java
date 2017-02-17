package fr.humanbooster.fx.enquetes.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.humanbooster.fx.enquetes.business.Survey;
import fr.humanbooster.fx.enquetes.business.SurveyInternet;
import fr.humanbooster.fx.enquetes.business.SurveyPhone;
import fr.humanbooster.fx.enquetes.service.SurveyService;
import fr.humanbooster.fx.enquetes.service.impl.SurveyServiceImpl;

/**
 * Servlet implementation class SurveyServlet
 */
public class SurveyServlet extends HttpServlet {

	private SurveyService surveyService;
//	private PartnerSiteService partnerSiteService;
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
        surveyService = new SurveyServiceImpl();
        surveyPhone = new SurveyPhone();
        surveyInternet = new SurveyInternet();
        
//        partnerSiteService = new PartnerSiteServiceImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typeSurvey = "";	// type de l'enquête :    "surveyPhone"  | "surveyInternet"
		String typeAction = "";	// type de l'action :	"create" | "update"
		int idSurvey = -1;
		
		// suivant si on se trouve dans le cas de :
		switch (request.getParameter("typeAction")) {
		// création d'une nouvelle enquête
		case "create":
			request.setAttribute("typeAction", "create");
			break;
		// modification d'une enquête existante
		case "update":
			request.setAttribute("typeAction", "update");
			// si l'id de l'enquête est fourni
			if (request.getParameter("idSurvey") != null) {
				
				try {
					idSurvey = Integer.parseInt(request.getParameter("idSurvey"));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				
				// on récupère l'enquête
				typeSurvey = request.getParameter("typeSurvey");
				switch (typeSurvey) {
				case "surveyPhone":
					surveyPhone = surveyService.findById(idSurvey);
					break;
				case "surveyInternet":
					surveyInternet = surveyService.findById(idSurvey);
					break;
				default:	
					// on n'a pas un type d'enquête cohérent fourni dans l'url
					// A GERER
					break;
				}

			} else {	// if (request.getParameter("idSurvey") != null)
			// on n'a pas l'id de l'enquête à modifier
			// cas à gérer !
				
			}
			break;
		default:	
			// on n'a pas un type d'action parmi les 2 existants fourni dans l'url
			// A GERER
			break;
		}

		// on passe les bons attributs (survey et typeSurvey) à la vue 
		switch (typeSurvey) {
		case "surveyPhone": 			
			request.setAttribute("typeSurvey", "surveyPhone");
			request.setAttribute("survey", surveyPhone);
			break;
			
		case "surveyInternet":
			request.setAttribute("typeSurvey", "surveyInternet");
			request.setAttribute("survey", surveyInternet);
			break;	
		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		doGet(request, response);
	}

}
