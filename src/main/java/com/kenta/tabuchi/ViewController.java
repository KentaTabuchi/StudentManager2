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
//	@RequestMapping(value="/add_record",method=RequestMethod.GET)
//	public ModelAndView addRecord(@ModelAttribute("formModel")Student student,ModelAndView mav) {
//		mav.setViewName("add_record");
//		return mav;
//	}
}
