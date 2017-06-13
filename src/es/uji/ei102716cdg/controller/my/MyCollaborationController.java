package es.uji.ei102716cdg.controller.my;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.uji.ei102716cdg.dao.CollaborationDao;
import es.uji.ei102716cdg.dao.OfferDao;
import es.uji.ei102716cdg.dao.RequestDao;
import es.uji.ei102716cdg.domain.collaboration.Collaboration;
import es.uji.ei102716cdg.domain.collaboration.Offer;
import es.uji.ei102716cdg.domain.collaboration.Request;
import es.uji.ei102716cdg.domain.skill.Skill;
import es.uji.ei102716cdg.domain.user.Student;
import es.uji.ei102716cdg.domain.user.User;
import es.uji.ei102716cdg.service.EmailService;
import es.uji.ei102716cdg.service.PostServiceInterface;


@Controller
@RequestMapping("my/collaborations")
public class MyCollaborationController {
	
	@Autowired
	private CollaborationDao collaborationDao;
	
	@Autowired
	private OfferDao offerDao;
	
	@Autowired
	private RequestDao requestDao;
	
	@Autowired
	private PostServiceInterface postService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	public void setCollaborationDao(CollaborationDao collaborationDao){
		this.collaborationDao = collaborationDao;
	}
	
	@Autowired
	public void setOfferDao(OfferDao offerDao){
		this.offerDao = offerDao;
	}
	
	@Autowired
	public void setRequestDao(RequestDao requestDao){
		this.requestDao = requestDao;
	}
	
	@Autowired
	public void setPostService(PostServiceInterface postService){
		this.postService = postService;
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String listCollabs(	@RequestParam(value="filter", required = false) String filter,
			Model model, HttpSession session){
		
		String nick= ((User) session.getAttribute("user")).getNick();
		List<Collaboration> collabs = null;
		
		if ( filter != null){
			switch (filter) {
			case "active":
				collabs = postService.getActiveCollaborations(nick);
				model.addAttribute("collabEndDates", postService.getCollabEndDates(collabs));
				break;
			
			case "eval":
				collabs = postService.getEvalCollaborations(nick);
				model.addAttribute("evalBtn", true);
				break;
				
			case "old":
				collabs = postService.getOldCollaborations(nick);
				break;
			default:
				collabs = postService.getCollaborations(nick);
				break;
			}
			
		} else { // mostrar todas
			collabs = postService.getCollaborations(nick);
		}
		
		model.addAttribute("collabs", collabs);
		model.addAttribute("skills", postService.getSkillsByCollabs(collabs));
		model.addAttribute("studentsOf", postService.getStudentsByCollabsOf(collabs));
		model.addAttribute("studentsRq", postService.getStudentsByCollabsRq(collabs));
		return "my/collaboration/list";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addCollab(	@RequestParam(value="confirm", required = false) String confirm,
								@RequestParam(value="offerId", required = false) Integer offerId,
								@RequestParam(value="requestId", required = false) Integer requestId,
								@RequestParam(value="skillId", required = false) Integer skillId,
								@RequestParam(value="fromOffer", required = false) String fromOffer,
								@RequestParam(value="fromRequest", required = false) String fromRequest,
			Model model, HttpSession session){
		
		User user = (User) session.getAttribute("user");
		Offer offer = null;
		Request request = null;
		
		if (confirm != null){ // 2nda parte: confirmar con 0/1 oferta/demanda (0 = debe crearse una off/req)
			
			if (offerId == null || requestId == null){ // se debe crear una contra-off/req
				if (offerId != null){ // collab con una auto-demanda
					offer = postService.getOffer(offerId);
					request = new Request(user.getNick(), skillId, offer.getStartDate(), offer.getEndDate(), offer.getDescription() ,false);
					offer.setActive(false);
					offerDao.updateOffer(offer);
					requestId = postService.addRequestAndGetId(request);
					emailService.sendEmailOfferAccepted(offer, offer.getStudent_nick());
				} else { // collab con una auto-oferta
					request = postService.getRequest(requestId);
					offer = new Offer(user.getNick(), skillId, request.getStartDate(), request.getEndDate(), request.getDescription() ,false);
					request.setActive(false);
					requestDao.updateRequest(request);
					offerId = postService.addOfferAndGetId(offer);
					emailService.sendEmailRequestAccepted(request, request.getStudent_nick());
				}
			} else {
				offer = postService.getOffer(offerId);
				request = postService.getRequest(requestId);
				offer.setActive(false);
				offerDao.updateOffer(offer);
				request.setActive(false);
				requestDao.updateRequest(request);
				
				if (fromOffer != null){
					emailService.sendEmailOfferAccepted(offer, offer.getStudent_nick());
				} else {
					emailService.sendEmailRequestAccepted(request, request.getStudent_nick());
				}
			}
			
			int collabId = collaborationDao.addCollaborationAndGetId(new Collaboration(offerId, requestId));
			
			
			return "redirect:/my/collaborations/" + collabId + ".html";
		}
		
		if (offerId != null){ // Viene desde una oferta
			List<Request> requests = postService.getRequestsBySkillId(user.getNick(), skillId);
			model.addAttribute("requests", requests);
		} else { // viene desde una demanda
			List<Offer> offers = postService.getOffersBySkillId(user.getNick(), skillId);
			model.addAttribute("offers", offers);
		}
		
		
		return "my/collaboration/add";
	}
	
	@RequestMapping(value="/edit/{collaboration_id}", method=RequestMethod.GET)
	public String editCollaboration(Model model, @PathVariable int collaboration_id, HttpSession session){
		User user = (User) session.getAttribute("user");
		Collaboration collab = collaborationDao.getCollaboration(collaboration_id);
		model.addAttribute("collaboration", collab);
		Offer offer = postService.getOffer(collab.getOffer_id());
		Request request = postService.getRequest(collab.getRequest_id());
		String collabStartDate  = offer.getStartDate().after(request.getStartDate()) 
								? offer.getStartDate().toString() 
								: request.getStartDate().toString();
				
		String collabEndDate 	= offer.getEndDate().before(request.getEndDate()) 
								? offer.getEndDate().toString() 
								: request.getEndDate().toString();
								
		
		return "my/collaboration/edit";
	}
	
	@RequestMapping(value="/edit/{collaboration_id}", method = RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable int collaboration_id,
								@ModelAttribute("collaboration") Collaboration collaboration,
								BindingResult bindingResult){
		if (bindingResult.hasErrors())
			return "my/collaboration/edit";
		collaborationDao.updateCollaboration(collaboration);
		return "redirect:/my/collaborations/list.html?success=update";
	}
	
	@RequestMapping("/{id}")
	public String showCollaboration(Model model,  @PathVariable int id, HttpSession session){
		User user = (User) session.getAttribute("user");
		Collaboration collab = collaborationDao.getCollaboration(id);
		Offer offer = postService.getOffer(collab.getOffer_id());
		Request request = postService.getRequest(collab.getRequest_id());
		Skill skill = postService.getSkillById(offer.getSkill_Id());
		Student studentOf = postService.getStudentByNick(offer.getStudent_nick());
		Student studentRq = postService.getStudentByNick(request.getStudent_nick());
		String collabStartDate  = offer.getStartDate().after(request.getStartDate()) 
								? offer.getStartDate().toString() 
								: request.getStartDate().toString();
								
		String collabEndDate 	= offer.getEndDate().before(request.getEndDate()) 
								? offer.getEndDate().toString() 
								: request.getEndDate().toString();
		model.addAttribute("collaboration", collab);
		model.addAttribute("studentOf", studentOf);
		model.addAttribute("studentRq", studentRq);
		model.addAttribute("ratingOf", postService.getRating(studentOf.getNick()));
		model.addAttribute("ratingRq", postService.getRating(studentRq.getNick()));
		model.addAttribute("collabStatus", collab.getStatus(offer.getEndDate(), request.getEndDate()));
		model.addAttribute("collabStartDate", collabStartDate);
		model.addAttribute("collabEndDate", collabEndDate);
		model.addAttribute("skill", skill);
		model.addAttribute("evalBtn", request.getStudent_nick().equals(user.getNick()));
		return "my/collaboration/info";
	}
	
}
