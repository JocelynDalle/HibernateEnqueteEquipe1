package fr.humanbooster.fx.enquetes.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.humanbooster.fx.enquetes.business.Survey;
import fr.humanbooster.fx.enquetes.business.SurveyInternet;
import fr.humanbooster.fx.enquetes.business.SurveyPhone;
import fr.humanbooster.fx.enquetes.service.SurveyService;

@Controller // Cette classe Controller va traiter les requ�tes HTTP
// Elle remplace toutes les servlets pr�c�demment �crites
public class EnqueteController {

	@Autowired // On d�clare un objet en pr�cisant le nom d'une interface
	private SurveyService ss;

	@RequestMapping(value = { "/", "/index", "/accueil" }, method = RequestMethod.GET)
	// On d�finit la ou les url prises en charge par la m�thode accueil
	// Cette m�thode est invoqu�e uniquement face a une requ�te HTTP de type GET
	public ModelAndView accueil() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		Set<Survey> surveys = ss.findAllSurvey();
		mav.getModel().put("surveys", surveys);
		return mav; // le return redirige vers la page index.jsp
		// car un viewResolver a �t� d�fini ds le fichier spring-servlet.
	}

	@RequestMapping(value = { "/", "/index", "/accueil" }, method = RequestMethod.POST)
	public ModelAndView surveysPost(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		String typeAction = (String) map.get("typeAction");
		String typeSurvey = (String) map.get("typeSurvey");
		int idSurvey = -1;
		if (map.get("idSurvey") != null) {
			idSurvey = Integer.valueOf((String)map.get("idSurvey"));
		}
		if (typeAction != null && typeSurvey != null) {
			if (typeAction.equals("update")) {
				mav.getModel().put("typeAction", typeAction);
				mav.getModel().put("typeSurvey", typeSurvey);
				mav.getModel().put("survey", ss.findById(idSurvey));
				mav.setViewName("survey");
			} else if (typeAction.equals("delete")) {
				boolean deleted = ss.deleteSurvey(idSurvey);
				System.out.println("l'enqu�te " + idSurvey + " a �t� supprim�e");
				mav.setViewName("index");
			} else {
				System.out.println("probl�me de d'attribut update et delete dans l'index");
				mav.setViewName("index");
			}
		}

		return mav;
	}

	@RequestMapping(value = "/addSurvey", method = RequestMethod.GET)
	public ModelAndView addSurveyGet(@RequestParam Map<String, Object> map, @RequestParam String typeSurvey,
			@RequestParam String typeAction) {

		// String typeSurvey = ""; // type de l'enquête : "surveyPhone" |
		// "surveyInternet"
		// String typeAction = ""; // type de l'action : "create" | "update"
		Object idSurveyObj = map.get("idSurvey");
		int idSurvey = -1;
		if (idSurveyObj != null) {
			idSurvey = (Integer) idSurveyObj;
		}
		ModelAndView mav = new ModelAndView();
		Survey survey = null;
		// suivant si on se trouve dans le cas de création d'une nouvelle
		// enquête
		// ou de modification d'une enquête existante
		switch (typeAction) {
		// création d'une nouvelle enquête
		case "create":
			mav.getModel().put("typeAction", "create");
			break;
		// modification d'une enquête existante
		case "update":
			mav.getModel().put("typeAction", "update");
			// si l'id de l'enquête est fourni
			if (idSurvey != -1) {
				// on récupère l'enquête
				switch (typeSurvey) {
				case "surveyPhone":
					survey = new SurveyPhone();
					survey = ss.findById(idSurvey);
					break;
				case "surveyInternet":
					survey = new SurveyInternet();
					survey = ss.findById(idSurvey);
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
			mav.getModel().put("typeSurvey", typeSurvey);
			mav.getModel().put("survey", survey);

			mav.setViewName("survey");
		}

		return mav;
	}

	@RequestMapping(value = "/addSurvey", method = RequestMethod.POST)
	public ModelAndView addSurveyPost(@RequestParam Map<String, Object> map,
			@RequestParam String name, @RequestParam String price) {
		ModelAndView mav = new ModelAndView();
		Survey survey = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		// on détermine si on est dans le cas d'une création ou d'une
		// modification d'une enquête
		String typeAction = "";
		if (map.get("create") != null) {
			typeAction = "create";
		} else if (map.get("update") != null) {
			typeAction = "update";
		}

		// on détermine s'il s'agit d'une surveyPhone ou d'une surveyInternet
		String typeSurvey = "";
		if (map.get("script") != null) {
			typeSurvey = "surveyPhone";
		} else if (map.get("idsPartnerSite") != null) {
			typeSurvey = "surveyInternet";
		}

		Date date = null;
		try {
			date = sdf.parse((String)map.get("date"));
		} catch (ParseException e) {
			System.out.println("date non parsable : " + (String)map.get("date"));
			sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			try {
				date = sdf.parse((String)map.get("date"));
			} catch (ParseException e1) {
				System.out.println("date non parsable : " + (String)map.get("date"));
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		// si on retrouve bien un type d'action connu : create | update
		if (typeAction.equals("create")) {
			// on enregistre la nouvelle enquête
			// cas 1 : cas d'une enquête téléphonique (surveyPhone)
			if (typeSurvey.equals("surveyPhone")) {
				ss.createSurveyPhone(new SurveyPhone(name, Float.parseFloat(price), String.valueOf(map.get("script")), date));
				// cas 2 : cas d'une enquête internet (surveyInternet)
			} else if (typeSurvey.equals("surveyInternet")) {
				ss.createSurveyInternet(new SurveyInternet(name, Float.parseFloat(price)));
			}

		} else if (typeAction.equals("update")) {
			// on modifie l'enquête existante dont on récupère d'abord l'id
			int id = Integer.valueOf((String)map.get("id"));
			// on vérifie que l'enquête identifiée par cet id existe
			// effectivement
			if (ss.findById(id) != null) {

				// cas 1 : cas d'une enquête téléphonique (surveyPhone)
				if (typeSurvey.equals("surveyPhone")) {
					ss.modifySurvey(
							new SurveyPhone(id, name, Float.parseFloat(price), String.valueOf(map.get("script")), date));
					// cas 2 : cas d'une enquête internet (surveyInternet)
				} else if (typeSurvey.equals("surveyInternet")) {
					ss.modifySurvey(new SurveyInternet(id, name, Float.parseFloat(price)));
				} // end if (typeSurvey.equals("surveyPhone"))
			} // end if (surveyService.findById(id) != null)
		} // end if (typeAction.equals("create"))

		Set<Survey> surveys = ss.findAllSurvey();
		mav.getModel().put("surveys", surveys);
		mav.setViewName("index");
		return mav;

	}

}
