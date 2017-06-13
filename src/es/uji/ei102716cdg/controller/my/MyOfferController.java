package es.uji.ei102716cdg.controller.my;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.uji.ei102716cdg.dao.OfferDao;
import es.uji.ei102716cdg.domain.collaboration.Offer;
import es.uji.ei102716cdg.domain.skill.Skill;
import es.uji.ei102716cdg.domain.user.Student;
import es.uji.ei102716cdg.domain.user.User;
import es.uji.ei102716cdg.service.PostServiceInterface;
import es.uji.ei102716cdg.util.CustomSqlDateEditor;
import es.uji.ei102716cdg.validator.OfferValidator;


@Controller
@RequestMapping("my/offers")
public class MyOfferController {
	@Autowired
	private OfferDao offerDao;
	private PostServiceInterface postService;
	
	@Autowired
	public void setPostService(PostServiceInterface postService){
		this.postService = postService;
	}
	
	@Autowired
	public void setOfferDao(OfferDao offerDao){
		this.offerDao=offerDao;
	}
	
	@InitBinder("offer")
    public void customizeBinding(WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        dateFormatter.setLenient(false);
        binder.registerCustomEditor(Date.class, 
                                    new CustomSqlDateEditor(dateFormatter, true,10));
    }
	
	@RequestMapping("/add")
	public String addOffer(Model model){
		model.addAttribute("offer",new Offer());
		model.addAttribute("skill_list", postService.getActiveSkills());
		return "my/offer/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addOffer(HttpSession session, @ModelAttribute("offer") Offer offer,
			@RequestParam("nombre") String nombre, Model model,
			BindingResult bindingResult){
		
		OfferValidator offerValidator = new OfferValidator();
		offerValidator.validate(offer, bindingResult);
		if(bindingResult.hasErrors()){
			if (nombre != null && !nombre.trim().equals("")){
				model.addAttribute("nombre", nombre);
			}
			return "my/offer/add";
		}
		User user = (User) session.getAttribute("user");
		offer.setStudent_nick(user.getNick());
		int offerId = offerDao.addOfferAndGetId(offer);
		return "redirect:" + offerId + ".html";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String listOffers(Model model, HttpSession session){
		User user = (User) session.getAttribute("user");
		List<Offer> offersByNick = postService.getMyOffers(user.getNick());
		model.addAttribute("offers", offersByNick);
		model.addAttribute("skills", postService.getSkillsByPost(offersByNick));
		model.addAttribute("rating", postService.getRating(user.getNick()));
		return "my/offer/list";
	}
	
	@RequestMapping("/{id}")
	public String showOffer(Model model,  @PathVariable int id){
		Offer offer = offerDao.getOffer(id);
		Student student = postService.getStudentByNick(offer.getStudent_nick());
		model.addAttribute("offer", offer);
		model.addAttribute("skill", postService.getSkillById(offer.getSkill_Id()));
		model.addAttribute("student", student);
		model.addAttribute("rating", postService.getRating(student.getNick()));
		return "my/offer/info";
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String editOffer(Model model, @PathVariable int id){
		Offer offer = offerDao.getOffer(id);
		model.addAttribute("offer", offerDao.getOffer(id));
		Skill skill = postService.getSkillById(offer.getSkill_Id());
		model.addAttribute("skill", skill);
		model.addAttribute("nombre", skill.getName());
		return "my/offer/update";
	}
	
	@RequestMapping(value="/update/{id}", method = RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable String id,
								@ModelAttribute("offer") Offer offer,
								BindingResult bindingResult){
		OfferValidator offerValidator = new OfferValidator();
		offerValidator.validate(offer, bindingResult);
		if (bindingResult.hasErrors())
			return "my/offer/update";
		offerDao.updateOffer(offer);
		return "redirect:../" + id + ".html";
	}
	
	@RequestMapping(value="/eliminate/{id}")
	public String processEliminate(@PathVariable int id){
		Offer offer = offerDao.getOffer(id);
		offer.setActive(false);
		
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		Date yesterday = new Date(cal.getTime().getTime());
		offer.setEndDate(yesterday);
		
		offerDao.updateOffer(offer);
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String processDelete(@PathVariable int id){
		offerDao.deleteOffer(id);
		return "redirect:list.html";
	}
	
}
