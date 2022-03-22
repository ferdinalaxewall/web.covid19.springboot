package web.covid19.ferdinalaxewall.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import web.covid19.ferdinalaxewall.service.WebService;

@Controller
@RequestMapping("")
public class WebController {

	@Autowired
	private WebService service;
	
	@GetMapping("/")
	public String indexPage(Model model) {
		model.addAttribute("listOfData", service.limitGlobalData());
		model.addAttribute("covidIndonesia", service.indonesiaData());
		model.addAttribute("provinceData", service.limitIndonesiaProvinceData());
		model.addAttribute("articleData", service.XMLData());
		return "index";
	}
	
	@GetMapping("/test")
	public String testPage(Model model) {
		model.addAttribute("lists", service.limitGlobalData());
		model.addAttribute("covidIndonesia", service.indonesiaData());
		return "test";
	}
	
	@GetMapping("/global")
	public String globalDataPage(Model model) {
		model.addAttribute("listOfData", service.globalData());
		return "global-data";
	}
	
	@GetMapping("/indonesia")
	public String indonesiaDataPage(Model model) {
		model.addAttribute("provinceData", service.indonesiaProvinceData());
		return "indonesia-data";
	}
	
	@PostMapping("/searchByCountryName")
	public @ResponseBody String searchByCountyNameData(@RequestBody Map<String, String> bindingMap) {
		return service.allCountryDataResponse(bindingMap.get("countryName"));
	}
	
}
