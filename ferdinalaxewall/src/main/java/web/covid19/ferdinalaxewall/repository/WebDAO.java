package web.covid19.ferdinalaxewall.repository;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface WebDAO {

	List<Map<String, String>> getAllGlobalData();
	List<Map<String, String>> getLimitGlobalData();
	List<Map<String, String>> getAllIndonesiaData();
	List<Map<String, String>> getAllIndonesiaProvinceData();
	List<Map<String, String>> getLimitIndonesiaProvinceData();
	List<Map<String, String>> getAllDataFromJsonFIle();
	List<Map<String, String>> searchByCountryNameRequest(String name);
	
	List<Map<String, String>> readXMLData();
	
}
