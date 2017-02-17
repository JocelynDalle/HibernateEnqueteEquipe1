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
import fr.humanbooster.fx.enquetes.service.SurveyService;
import fr.humanbooster.fx.enquetes.service.impl.SurveyServiceImpl;

/**
 * Servlet implementation class SurveyServlet
 */
@WebServlet("/SurveyServlet")
public class SurveyServlet extends HttpServlet {

	private static final long serialVersionUID = -942489127115599803L;

	private SurveyService surveyService;
//	private PartnerSiteService partnerSiteService;
	private Survey survey = null;
	//private Survey surveyInternet;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public SurveyServlet() {
        super();
        surveyService = new SurveyServiceImpl();
   
        //surveyPhone = new SurveyPhone();
        //surveyInternet = new SurveyInternet();
        
//        partnerSiteService = new PartnerSiteServiceImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String typeSurvey = "";	// type de l'enquête :    "surveyPhone"  | "surveyInternet"
		String typeAction = "";	// type de l'action :	"create" | "update"
		int idSurvey = -1;		
		
		typeSurvey = request.getParameter("typeSurvey");
		typeAction = request.getParameter("typeAction");
		
		// suivant si on se trouve dans le cas de création d'une nouvelle enquête
		// ou de modification d'une enquête existante
		switch (typeAction) {
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
				switch (typeSurvey) {
				case "surveyPhone":
					survey = new SurveyPhone();
					survey = surveyService.findById(idSurvey);
					break;
				case "surveyInternet":
					survey = new SurveyInternet();
					survey = surveyService.findById(idSurvey);
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

		// si typeSurvey n'est ni "surveyPhone", ni "surveyInternet"
		// ce n'est pas normal
		if (!typeSurvey.equals("surveyPhone") && !typeSurvey.equals("surveyInternet")) {
			// cas à traiter
		} else {
			// sinon, si typeSurvey a une valeur cohérente
			// on passe les bons attributs (survey et typeSurvey) à la vue 
			request.setAttribute("typeSurvey", typeSurvey);
			request.setAttribute("survey", survey);
			
			request.getRequestDispatcher("survey.jsp").forward(request, response);			
		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean create = request.getParameter("create") != null;
		System.out.println("create " + create);
		boolean update = request.getParameter("update") != null;
		System.out.println("update :" + update);
		System.out.println("id :" + request.getParameter("id"));
//		System.out.println("type d'enquête : " + typeSurvey);
//		String typeAction = (String) request.getAttribute("typeAction");
//		System.out.println("type d'action : " + typeAction);
//		Survey survey
		
		
//		request.getRequestDispatcher("SurveysServlet").forward(request, response);
	}

}
