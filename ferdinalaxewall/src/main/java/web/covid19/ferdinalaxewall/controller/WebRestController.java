package web.covid19.ferdinalaxewall.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import web.covid19.ferdinalaxewall.service.WebService;

@RestController
@RequestMapping("/api")
public class WebRestController {
	
	@Autowired
	private WebService service;
	
	@GetMapping("/global")
	public List<Map<String, String>> globalDataAPI(){
		return service.globalData();
	}
	
	@GetMapping("/global-limit")
	public List<Map<String, String>> limitGlobalDataAPI(){
		return service.limitGlobalData();
	}
	
	@GetMapping("/indonesia")
	public List<Map<String, String>> totalIndonesiaDataAPI(){
		return service.indonesiaData();
	}

	@GetMapping("/provinceID")
	public List<Map<String, String>> allIndonesiaProvinceDataAPI(){
		return service.indonesiaProvinceData();
	}

	@GetMapping("/provinceID-limit")
	public List<Map<String, String>> limitIndonesiaProvinceDataAPI(){
		return service.limitIndonesiaProvinceData();
	}
	
	@PostMapping("/searchDataByCountryName")
	public @ResponseBody List<Map<String, String>> searchByCountyNameData(@RequestBody Map<String, String> bindingMap) {
		return service.searchRequestandResponse(bindingMap.get("countryName"));
	}
	
	@GetMapping("/xml")
	public List<Map<String, String>> readXMLData() {
		return service.XMLData();
	}
	
//	
//	@GetMapping("/data")
//	public ResponseEntity<String> allData() {
//		return service.allData();
//	}
//	
//	@GetMapping("/list")
//	public List<Map<String, String>> showList(){
//		return service.limitGlobalData();
//	}
//
//	@GetMapping("/file")
//	public List<Map<String, String>> showListFromFile(){
//		return service.listMapFromFile();
//	}
//	
//	@GetMapping("/indonesia")
//	public ResponseEntity<String> indonesiaData(){
//		return service.allIndonesiaData();
//	}
//	
//	@GetMapping("/province")
//	public List<Map<String, String>> indonesiaProvinceData(){
//		return service.totalIndonesiaProvinceData();
//	}
//	
////	@PostMapping("/searchByCountryName")
////	public List<Map<String, String>> searchCountry(@RequestBody Map<String, String> bindingMap){
////		return service.allCountryDataResponse(bindingMap.get("countryName"));
////	}
////	
////	@GetMapping("/covid")
////	public List<Map<String, String>> requestParam(@RequestParam("countryName") String countryName) {
////		return service.allCountryDataResponse(countryName);
////	}
			
}
