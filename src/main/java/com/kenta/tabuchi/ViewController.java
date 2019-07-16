package com.kenta.tabuchi;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {
	@Autowired
	StudentRepository repository;
	private static final Logger logger = LoggerFactory.getLogger(ViewController.class);
	//|---------------------------------------------------------------------------------------
	//|  GET method
	//|---------------------------------------------------------------------------------------
		
	@GetMapping(value= {"/","/{params}"})
	public ModelAndView index_get
	(@RequestParam(name="select_id",required=false)Integer select_id,
	 @RequestParam(name="find_select",required=false)Integer find_select,
	 @RequestParam(name="find_text",required=false)String find_text,
			ModelAndView view) 
	{
		if(find_text!=null) {
		List<Student> list = null;
		String filter = "";
		switch(find_select) {
		case 0: list = repository.findAllById(Long.valueOf(find_text));filter="ID:"+find_text; break;
		case 1: list = repository.findAllByNameLike("%"+find_text+"%");filter="名前:"+find_text;break;
		case 2: list = repository.findAllByRomaLike("%"+find_text+"%");filter="ローマ字:"+find_text;break;
		case 3: list = repository.findAllByBirthday(find_text);filter="誕生日:"+find_text;break;
		case 4: list = repository.findAllByPhone(find_text);filter="電話番号:"+find_text;break;
		case 5: list = repository.findAllByEmailLike("%"+find_text+"%");filter="E-mail:"+find_text;break;
		case 6: list = repository.findAllByAddressLike("%"+find_text+"%");filter="住所:"+find_text;break;
		case 7: list = repository.findAllByGraduation(find_text);filter="卒業年度"+find_text;break;
		
		}
			view.addObject("filter_text","絞り込み条件："+filter);
			view.addObject("recordSet",list);
			view.setViewName("index");
			return view;
		}else {
		
		String key = "id";
		try {
		switch(select_id){
		case 0: key = "id";						//|case 0 is not available selection. |
		case 1: 	 		   			break;  //|so case 0 needs redirect to case 1.|
		case 2: key = "roma"; 			break;
		case 3: key = "birthday";		break;
		case 4: key = "phone";			break;
		case 5: key = "graduation";		break;
		}
		
		
		view.addObject("select_id",select_id);
		view.addObject("recordSet",repository.findAll(new Sort(Sort.Direction.ASC,key)));
		view.setViewName("index");
		return view;
		}catch(NullPointerException e){

			return new ModelAndView("redirect:?select_id=1");
		}
		}
	}
	
	@GetMapping("add_record")
	public ModelAndView add_record_get(@ModelAttribute("formModel")Student student, ModelAndView view)
	{
		view.setViewName("add_record");
		return view;
	}
	
	@GetMapping("delete_record/{id}")
	public ModelAndView delete_record_get
	(@PathVariable Long id, ModelAndView view)
	{
		view.addObject("record",repository.findById(id).get());
		view.setViewName("delete_record");
		return view;
	}
	@GetMapping("edit_record/{id}")
	public ModelAndView edit_record_get
	(@ModelAttribute("formModel")Student student,@PathVariable Long id,ModelAndView view) 
	{	
		view.addObject("record",repository.findById(id).get());
		view.setViewName("edit_record");
		return view;
	}
    
    @GetMapping("export_csv")
    public void export_csv(HttpServletResponse response) {
    	
    	new CsvReader().exportCSV(response,repository);
    }
    
    
	//|---------------------------------------------------------------------------------------
	//|  POST method
	//|---------------------------------------------------------------------------------------
		
	@PostMapping("/add_record")
	public ModelAndView add_record_post
	(@ModelAttribute("formModel") @Validated Student student,BindingResult result,ModelAndView view) 
	{	
		if(!result.hasErrors()) {
			repository.saveAndFlush(student);
			return new ModelAndView("redirect:");
		}else {
			view.setViewName("add_record");
			return view;
		}	
	}
	@PostMapping("/edit_record")
	public ModelAndView edit_record_post
	(@ModelAttribute("formModel")@Validated Student student,BindingResult result,ModelAndView view)
	{
		if(!result.hasErrors()) {
		repository.saveAndFlush(student);
		return new ModelAndView("redirect:");
		}else {
			view.setViewName("edit_record");
			return view;
		}
		
	}
	@PostMapping("/delete_record")
	public ModelAndView delete_record_post
	(@RequestParam("id")Long id) 
	{
		repository.deleteById(id);
		return new ModelAndView("redirect:");
	}
	
    @PostMapping("import_csv")
    public ModelAndView import_csv
    (@RequestParam("upload_file")MultipartFile uploadFile) {
    	logger.info("export_csv invoke.");
    	new CsvReader().importCsv(uploadFile, repository);
    	return new ModelAndView("redirect:");
    }

	
}
