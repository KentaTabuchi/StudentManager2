package com.kenta.tabuchi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {
	@Autowired
	StudentRepository repository;
	
	@GetMapping
	public ModelAndView index_get(ModelAndView view) {
		view.addObject("recordSet", repository.findAll());
		view.setViewName("index");
		return view;
	}
	
	@GetMapping("add_record")
	public ModelAndView add_record_get(@ModelAttribute("formModel")Student student, ModelAndView view) {
		view.setViewName("add_record");
		return view;
	}
	
	@GetMapping("delete_record/{id}")
	public ModelAndView delete_record_get
	(@PathVariable Long id, ModelAndView view) {
		view.addObject("record",repository.findById(id));
		view.setViewName("delete_record");
		return view;
	}
	@GetMapping("edit_record/{id}")
	public ModelAndView edit_record_get
	(@ModelAttribute("formModel")Student student,@PathVariable Long id,ModelAndView view) {
		
		view.addObject("record",repository.findById(id));
		view.setViewName("edit_record");
		return view;
	}
	
	@PostMapping("/add_record")
	public ModelAndView add_record_post
	(@ModelAttribute("formModel") @Validated Student student,BindingResult result,ModelAndView view) {
		
		if(!result.hasErrors()) {
			repository.saveAndFlush(student);
			view = new ModelAndView("redirect:/");
		}else {
			view.setViewName("add_record");
		}
		return view;
	}
//	@RequestMapping(value="/add_record",method=RequestMethod.POST)
//	public ModelAndView form(
//			@ModelAttribute("formModel") @Validated Student student,
//			BindingResult result,
//			ModelAndView mav)
//	{
//		ModelAndView adoptedMav = null;
//		if(!result.hasErrors()) {
//			M_StudentDao dao = new M_StudentDao(jdbc);
//			dao.insert(student);
//			try {
//				Thread.sleep(WAIT_RESPONSE_TIME);//if here is not this,caused MySQL error.communication link failer.
//			}catch(InterruptedException e) {e.printStackTrace();}
//			adoptedMav = new ModelAndView("redirect:/");
//		}else {
//			mav.setViewName("add_record");
//			adoptedMav = mav;
//		}
//		return adoptedMav;
//	}

}
