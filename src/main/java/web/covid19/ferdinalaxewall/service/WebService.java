package web.covid19.ferdinalaxewall.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface WebService {

//	ResponseEntity<String> allData();
	List<Map<String, String>> globalData();
	List<Map<String, String>> limitGlobalData();
	List<Map<String, String>> indonesiaData();
	List<Map<String, String>> limitIndonesiaProvinceData();
	List<Map<String, String>> indonesiaProvinceData();
	List<Map<String, String>> listMapFromFile();
	List<Map<String, String>> searchRequestandResponse(String name);
	
	String allCountryDataResponse(String name);
	List<Map<String, String>> XMLData();
	
}
