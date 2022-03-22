package web.covid19.ferdinalaxewall.service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import web.covid19.ferdinalaxewall.repository.WebDAO;

@Service
public class WebServiceImp implements WebService {

	@Autowired
	private WebDAO dao;

	@Override
	public List<Map<String, String>> globalData() {
		return dao.getAllGlobalData();
	}

	@Override
	public List<Map<String, String>> limitGlobalData() {
		return dao.getLimitGlobalData();
	}

	@Override
	public List<Map<String, String>> indonesiaData() {
		return dao.getAllIndonesiaData();
	}
	
	@Override
	public List<Map<String, String>> indonesiaProvinceData() {
		return dao.getAllIndonesiaProvinceData();
	}

	@Override
	public List<Map<String, String>> limitIndonesiaProvinceData() {
		return dao.getLimitIndonesiaProvinceData();
	}
	
	@Override
	public List<Map<String, String>> listMapFromFile() {
		return dao.getAllDataFromJsonFIle();
	}

	@Override
	public String allCountryDataResponse(String name) {
		dao.searchByCountryNameRequest(name);
		List<Map<String, String>> listService = dao.searchByCountryNameRequest(name);
		List<String> listResponse = new ArrayList<String>();
		String delim = "";
		if (listService.isEmpty()) {
			listResponse.add("<p>Sorry, data from that country is not available, Please enter the name of another country.</p>");
		}else {
			for (int i = 0; i < listService.size(); i++) {
				Map<String, String> mapService = listService.get(i);
				String provinceState = mapService.get("provinceState");
				String countryName = mapService.get("countryRegion");
				String incidentRate = mapService.get("incidentRate");
				String confirmed = mapService.get("confirmed");
//				String deaths = mapService.get("deaths");
				String cases28Days = mapService.get("cases28Days");
				String deaths28Days = mapService.get("deaths28Days");
				
				String responseText = "<div class='card'>\r\n" + 
						"	                	<div class='country-group'>\r\n" + 
						"	                    	<h3 class='country-name'>"+provinceState+"</h3>\r\n" + 
						"	                    	<h5 class='province-name'>"+countryName+"</h5>\r\n" + 
						"	                	</div>\r\n" + 
						"	                    <div class='total-case-group'>\r\n" + 
						"	                    	<h3 class='total-case'>"+confirmed+"</h3>\r\n" + 
						"	                    	<p class='total-case-description'>Total Case</p>\r\n" + 
						"	                    </div>\r\n" + 
						"	                    <div class='case-details'>\r\n" + 
						"	                        <p id='recovered'>Incident Rate : "+incidentRate+" / Day</p>\r\n" + 
						"	                        <p id='confirmed'>Cases : "+cases28Days+" / Month</p>\r\n" + 
						"	                        <p id='deaths'>Deaths : "+deaths28Days+" / Month</p>\r\n" + 
						"	                    </div>\r\n" + 
						"	                </div>";
				listResponse.add(responseText);
			}
		}
		String response = String.join(delim, listResponse);
		return response;
	}

	
	@Override
	public List<Map<String, String>> searchRequestandResponse(String name) {
		return dao.searchByCountryNameRequest(name);
	}

	@Override
	public List<Map<String, String>> XMLData() {
		// TODO Auto-generated method stub
		return dao.readXMLData();
	}

}
