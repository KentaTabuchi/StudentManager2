package com.kenta.tabuchi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@GetMapping("delete_record")
	public ModelAndView delete_record_get(@ModelAttribute("formModel")Student student,ModelAndView view) {
		view.setViewName("delete_record");
		return view;
	}
//	@RequestMapping(value="/delete_record",method=RequestMethod.GET)
//	public ModelAndView deleteRecord(
//			ModelAndView mav) {
//		mav.setViewName("delete_record");
//		M_StudentDao dao = new M_StudentDao(jdbc);
//		Iterable<Student> list = dao.findAll();
//		mav.addObject("recordSet", list);
//		return mav;
//	}

}
