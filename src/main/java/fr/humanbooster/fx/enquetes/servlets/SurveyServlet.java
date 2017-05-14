package fr.humanbooster.fx.enquetes.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.humanbooster.fx.enquetes.business.PartnerSite;
import fr.humanbooster.fx.enquetes.business.Survey;
import fr.humanbooster.fx.enquetes.business.SurveyInternet;
import fr.humanbooster.fx.enquetes.business.SurveyPhone;
import fr.humanbooster.fx.enquetes.service.PartenerSiteService;
import fr.humanbooster.fx.enquetes.service.SurveyService;
import fr.humanbooster.fx.enquetes.service.impl.PartenerSiteServiceImpl;
import fr.humanbooster.fx.enquetes.service.impl.SurveyServiceImpl;

/**
 * Servlet implementation class SurveyServlet
 */
@WebServlet("/SurveyServlet")
public class SurveyServlet extends HttpServlet {

	private static final long serialVersionUID = -942489127115599803L;

	private SurveyService surveyService;
	// private PartnerSiteService partnerSiteService;
	private Survey survey = null;
	// private Survey surveyInternet;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SurveyServlet() {
		super();
		surveyService = new SurveyServiceImpl();

		// surveyPhone = new SurveyPhone();
		// surveyInternet = new SurveyInternet();

		// partnerSiteService = new PartnerSiteServiceImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String typeSurvey = ""; // type de l'enquête : "surveyPhone" |
								// "surveyInternet"
		String typeAction = ""; // type de l'action : "create" | "update"
		int idSurvey = -1;

		typeSurvey = request.getParameter("typeSurvey");
		typeAction = request.getParameter("typeAction");
		
		// suivant si on se trouve dans le cas de création d'une nouvelle
		// enquête
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

			} else { // if (request.getParameter("idSurvey") != null)
				// on n'a pas l'id de l'enquête à modifier
				// cas à gérer !

			}
			break;
		// on n'a pas un type d'action parmi les 2 existants fourni dans l'url
		default:
			// A GERER
			break;
		}

		// si typeSurvey n'est ni "surveyPhone", ni "surveyInternet"
		// ce n'est pas normal
		if (!typeSurvey.equals("surveyPhone") && !typeSurvey.equals("surveyInternet")) {
			// cas à traiter
			/*
			 * Set<Survey> surveys = surveyService.findAllSurvey();
			 * request.setAttribute("surveys", surveys);
			 * request.getRequestDispatcher("index.jsp").forward(request,
			 * response);
			 */
		} else {
			// sinon, si typeSurvey a une valeur cohérente
			// on passe les bons attributs (survey et typeSurvey) à la vue
			request.setAttribute("typeSurvey", typeSurvey);
			request.setAttribute("survey", survey);
			request.getRequestDispatcher("survey.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// on détermine si on est dans le cas d'une création ou d'une
		// modification d'une enquête
		String typeAction = "";
		if (request.getParameter("create") != null) {
			typeAction = "create";
		} else if (request.getParameter("update") != null) {
			typeAction = "update";
		}

		// on détermine s'il s'agit d'une surveyPhone ou d'une surveyInternet
		String typeSurvey = "";
		if (request.getParameter("script") != null) {
			typeSurvey = "surveyPhone";
		} else if (request.getParameter("idsPartnerSite") != null) {
			typeSurvey = "surveyInternet";
		}

		Date date = null;
		try {
			date = DateFormat.getDateInstance(DateFormat.MEDIUM).parse(request.getParameter("date"));
		} catch (ParseException e) {
			try {
				date = DateFormat.getDateInstance(DateFormat.SHORT).parse(request.getParameter("date"));
			} catch (ParseException e1) {
				try {
					date = DateFormat.getDateInstance(DateFormat.LONG).parse(request.getParameter("date"));
				} catch (ParseException e2) {
					e2.printStackTrace();
				}
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		// si on retrouve bien un type d'action connu : create | update
		if (typeAction.equals("create")) {
			// on enregistre la nouvelle enquête
			// cas 1 : cas d'une enquête téléphonique (surveyPhone)
			if (typeSurvey.equals("surveyPhone")) {
				SurveyPhone survey = new SurveyPhone(request.getParameter("name"),
						Float.parseFloat(request.getParameter("price")), request.getParameter("script"));
				if (date != null) {
					survey.setDate(date);
				}
				surveyService.createSurveyPhone(survey);
				// cas 2 : cas d'une enquête internet (surveyInternet)
			} else if (typeSurvey.equals("surveyInternet")) {
				SurveyInternet survey = new SurveyInternet(request.getParameter("name"),
						Float.parseFloat(request.getParameter("price")));
				if (date != null) {
					survey.setDate(date);
				}
				surveyService.createSurveyInternet(survey);
			}
			
		} else if (typeAction.equals("update")) {
			// on modifie l'enquête existante dont on récupère d'abord l'id
			int id = Integer.parseInt(request.getParameter("id"));
			// on vérifie que l'enquête identifiée par cet id existe
			// effectivement
			if (surveyService.findById(id) != null) {
				// cas 1 : cas d'une enquête téléphonique (surveyPhone)
				if (typeSurvey.equals("surveyPhone")) {
					SurveyPhone survey = (SurveyPhone) surveyService.findById(id);
					survey.setName(request.getParameter("name"));
					survey.setPrice(Float.parseFloat(request.getParameter("price")));
					survey.setScript(request.getParameter("script"));
					if (date != null) {
						survey.setDate(date);
					}
					surveyService.modifySurvey(survey);
					// cas 2 : cas d'une enquête internet (surveyInternet)
				} else if (typeSurvey.equals("surveyInternet")) {
					SurveyInternet survey = (SurveyInternet) surveyService.findById(id);
					survey.setName(request.getParameter("name"));
					survey.setPrice(Float.parseFloat(request.getParameter("price")));
					if (date != null) {
						survey.setDate(date);
					}
					surveyService.modifySurvey(survey);
				} // end if (typeSurvey.equals("surveyPhone"))
			} // end if (surveyService.findById(id) != null)
		} // end if (typeAction.equals("create"))

		Set<Survey> surveys = surveyService.findAllSurvey();
		request.setAttribute("surveys", surveys);
		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

}
