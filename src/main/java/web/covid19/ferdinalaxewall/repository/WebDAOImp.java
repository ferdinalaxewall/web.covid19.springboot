package web.covid19.ferdinalaxewall.repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@Repository
public class WebDAOImp implements WebDAO {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(WebDAOImp.class);
	
	@Override
	public List<Map<String, String>> getLimitGlobalData() {
		ResponseEntity<String> response = restTemplate.getForEntity("https://covid19.mathdro.id/api/deaths", String.class);
		List<Map<String, String>> listOfMap = new ArrayList<Map<String,String>>();
		JSONParser jsonParse = new JSONParser();
		log.info("Successfully Get All Covid-19 Data From Covid-19 Mathdro.id API");
		try {
			Object obj = jsonParse.parse(response.getBody());
			JSONArray jsonArray = (JSONArray) obj;
			for (int i = 0; i < 8; i++) {
				Map<String, String> map = new HashMap<String, String>();
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);

				String countryRegion = (String) jsonObject.get("countryRegion");
				Long confirmed = (Long) jsonObject.get("confirmed");
				Long deaths = (Long) jsonObject.get("deaths");

				String provinceState;
				if (jsonObject.get("provinceState") != null) {
					provinceState = (String) jsonObject.get("provinceState");
				} else {
					provinceState = (String) "Unknown Province";
				}
			
				Long cases28Days;
					if (jsonObject.get("cases28Days") != null) {						
						cases28Days = (Long) jsonObject.get("cases28Days");
					} else {
						cases28Days = 0L;
					}
					
				Long deaths28Days;
					if (jsonObject.get("deaths28Days") != null) {						
						deaths28Days = (Long) jsonObject.get("deaths28Days");
					} else {
						deaths28Days = 0L;
					}
				
				String castToString;
				Double iRate;
					if (jsonObject.get("incidentRate") != null) {						
						castToString = (String) jsonObject.get("incidentRate").toString();
						iRate = Double.parseDouble(castToString);
					} else {
						castToString = (String) "0";
						iRate = Double.parseDouble(castToString);
					}
				
				Long incidentRate = (Long) Math.round(iRate);
				
				NumberFormat numberFormat = NumberFormat.getNumberInstance();
				
				map.put("countryRegion", countryRegion);
				map.put("provinceState", provinceState);
				map.put("confirmed", numberFormat.format(confirmed).toString());
				map.put("deaths", numberFormat.format(deaths).toString());
				map.put("cases28Days", numberFormat.format(cases28Days).toString());
				map.put("deaths28Days", numberFormat.format(deaths28Days).toString());
				map.put("incidentRate", numberFormat.format(incidentRate).toString());
				
				listOfMap.add(map);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return listOfMap;
	}

	@Override
	public List<Map<String, String>> getAllGlobalData() {
		ResponseEntity<String> response = restTemplate.getForEntity("https://covid19.mathdro.id/api/deaths", String.class);
		List<Map<String, String>> listOfMap = new ArrayList<Map<String,String>>();
		JSONParser jsonParse = new JSONParser();
		log.info("Successfully Get All Covid-19 Data From Covid-19 Mathdro.id API");
		try {
			Object obj = jsonParse.parse(response.getBody());
			JSONArray jsonArray = (JSONArray) obj;
			for (int i = 0; i < jsonArray.size(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);

				String countryRegion = (String) jsonObject.get("countryRegion");
				Long confirmed = (Long) jsonObject.get("confirmed");
				Long deaths = (Long) jsonObject.get("deaths");

				String provinceState;
				if (jsonObject.get("provinceState") != null) {
					provinceState = (String) jsonObject.get("provinceState");
				} else {
					provinceState = (String) "Unknown Province";
				}
			
				Long cases28Days;
					if (jsonObject.get("cases28Days") != null) {						
						cases28Days = (Long) jsonObject.get("cases28Days");
					} else {
						cases28Days = 0L;
					}
					
				Long deaths28Days;
					if (jsonObject.get("deaths28Days") != null) {						
						deaths28Days = (Long) jsonObject.get("deaths28Days");
					} else {
						deaths28Days = 0L;
					}
				
				String castToString;
				Double iRate;
					if (jsonObject.get("incidentRate") != null) {						
						castToString = (String) jsonObject.get("incidentRate").toString();
						iRate = Double.parseDouble(castToString);
					} else {
						castToString = (String) "0";
						iRate = Double.parseDouble(castToString);
					}
				
				Long incidentRate = (Long) Math.round(iRate);
				
				NumberFormat numberFormat = NumberFormat.getNumberInstance();
				
				map.put("countryRegion", countryRegion);
				map.put("provinceState", provinceState);
				map.put("confirmed", numberFormat.format(confirmed).toString());
				map.put("deaths", numberFormat.format(deaths).toString());
				map.put("cases28Days", numberFormat.format(cases28Days).toString());
				map.put("deaths28Days", numberFormat.format(deaths28Days).toString());
				map.put("incidentRate", numberFormat.format(incidentRate).toString());
				
				listOfMap.add(map);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return listOfMap;
	}
	
	@Override
	public List<Map<String, String>> getAllDataFromJsonFIle() {
		List<Map<String, String>> listOfMap = new ArrayList<Map<String,String>>();
		try {
			FileReader dataReader = new FileReader("D:\\Project-java\\covid-api.json");
			JSONParser jsonParse = new JSONParser();
			try {
				Object obj = jsonParse.parse(dataReader);
				JSONArray jsonArray = (JSONArray) obj;
				for (int i = 0; i < jsonArray.size(); i++) {
					Map<String, String> map = new HashMap<String, String>();
					JSONObject jsonObject = (JSONObject) jsonArray.get(i);
					
					
					String countryRegion = (String) jsonObject.get("countryRegion");
					Long confirmed = (Long) jsonObject.get("confirmed");
					Long deaths = (Long) jsonObject.get("deaths");
					
					Long cases28Days;
						if (jsonObject.get("cases28Days") != null) {						
							cases28Days = (Long) jsonObject.get("cases28Days");
						} else {
							cases28Days = 0L;
						}
					
					Long deaths28Days;
						if (jsonObject.get("deaths28Days") != null) {						
							deaths28Days = (Long) jsonObject.get("deaths28Days");
						} else {
							deaths28Days = 0L;
						}
					
					String castToString;
					Double iRate;
						if (jsonObject.get("incidentRate") != null) {						
							castToString = (String) jsonObject.get("incidentRate").toString();
							iRate = Double.parseDouble(castToString);
						} else {
							castToString = (String) "0";
							iRate = Double.parseDouble(castToString);
						}
					
					Long incidentRate = (Long) Math.round(iRate);
					
					NumberFormat numberFormat = NumberFormat.getNumberInstance();
					
					map.put("countryRegion", countryRegion);
					map.put("confirmed", numberFormat.format(confirmed).toString());
					map.put("deaths", numberFormat.format(deaths).toString());
					map.put("cases28Days", numberFormat.format(cases28Days).toString());
					map.put("deaths28Days", numberFormat.format(deaths28Days).toString());
					map.put("incidentRate", numberFormat.format(incidentRate).toString());
					
					listOfMap.add(map);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listOfMap;
	}
	
	@Override
	public List<Map<String, String>> getAllIndonesiaData() {
		ResponseEntity<String> response = restTemplate.getForEntity("https://data.covid19.go.id/public/api/update.json", String.class);
		List<Map<String, String>> listTotalCovid = new ArrayList<Map<String, String>>();
		List<Long> listPositiveCases = new ArrayList<Long>();
		List<Long> listRecoveredCases = new ArrayList<Long>();
		List<Long> listDeathsCases = new ArrayList<Long>();
		List<Long> listHopistalizedCases = new ArrayList<Long>();
		JSONParser jsonParse = new JSONParser();
		try {
			Map<String, String> mapValue = new HashMap<String, String>();
			Object obj = jsonParse.parse(response.getBody());
			JSONObject jsonObject = (JSONObject) obj;
			JSONObject data = (JSONObject) jsonObject.get("data");
			JSONObject update = (JSONObject) jsonObject.get("update");
			JSONObject penambahan = (JSONObject) update.get("penambahan");
			JSONObject total = (JSONObject) update.get("total");
			JSONArray harian = (JSONArray) update.get("harian");
			
			for (int i = 0; i < harian.size(); i++) {
				JSONObject objHarian = (JSONObject) harian.get(i);
				JSONObject positiveValue = (JSONObject) objHarian.get("jumlah_positif");
				JSONObject recoveredValue = (JSONObject) objHarian.get("jumlah_sembuh");
				JSONObject deathsValue = (JSONObject) objHarian.get("jumlah_meninggal");
				JSONObject hospitalizedValue = (JSONObject) objHarian.get("jumlah_dirawat");
				
				Long totalPositive = (Long) positiveValue.get("value");
				Long totalRecovered = (Long) recoveredValue.get("value");
				Long totalDeaths = (Long) deathsValue.get("value");
				Long totalHospitalized = (Long) hospitalizedValue.get("value");
				
				listPositiveCases.add(totalPositive);
				listRecoveredCases.add(totalRecovered);
				listDeathsCases.add(totalDeaths);
				listHopistalizedCases.add(totalHospitalized);
			}
			
			
			Long spesimen = (Long) data.get("total_spesimen");
			Long spesimen_negatif = (Long) data.get("total_spesimen_negatif");
			Long jumlah_positif = (Long) total.get("jumlah_positif");
			Long jumlah_dirawat = (Long) total.get("jumlah_dirawat");
			Long jumlah_sembuh = (Long) total.get("jumlah_sembuh");
			Long jumlah_meninggal = (Long) total.get("jumlah_meninggal");
			Long positiveToday = (Long) penambahan.get("jumlah_positif");
			Long deathsToday = (Long) penambahan.get("jumlah_meninggal");
			Long recoveredToday = (Long) penambahan.get("jumlah_sembuh");
			Long hospitalizedToday = (Long) penambahan.get("jumlah_dirawat");
			Long maxPositiveValue = Collections.max(listPositiveCases);
			Long maxRecoveredValue = Collections.max(listRecoveredCases);
			Long maxDeathsValue = Collections.max(listDeathsCases);
			Long maxHospitalizedValue = Collections.max(listHopistalizedCases);
			String updatedAtToday = (String) penambahan.get("created");
			String nowDate = (String) penambahan.get("tanggal");
			

			NumberFormat numberFormat = NumberFormat.getNumberInstance();
			
			try {
				mapValue.put("jumlah_positif", numberFormat.format(jumlah_positif));
				mapValue.put("jumlah_dirawat", numberFormat.format(jumlah_dirawat));
				mapValue.put("jumlah_sembuh", numberFormat.format(jumlah_sembuh));
				mapValue.put("jumlah_meninggal", numberFormat.format(jumlah_meninggal));
				mapValue.put("total_spesimen", numberFormat.format(spesimen));
				mapValue.put("total_spesimen_negatif", numberFormat.format(spesimen_negatif));
				mapValue.put("positiveToday", numberFormat.format(positiveToday));
				mapValue.put("deathsToday", numberFormat.format(deathsToday));
				mapValue.put("recoveredToday", numberFormat.format(recoveredToday));
				mapValue.put("hospitalizedToday", numberFormat.format(hospitalizedToday));
				mapValue.put("updatedAtToday", updatedAtToday);
				mapValue.put("maxPositiveCase", numberFormat.format(maxPositiveValue));
				mapValue.put("maxRecoveredCase", numberFormat.format(maxRecoveredValue));
				mapValue.put("maxDeathsCase", numberFormat.format(maxDeathsValue));
				mapValue.put("maxHospitalizedCase", numberFormat.format(maxHospitalizedValue));
				mapValue.put("nowDate", nowDate);
			} catch (Exception e) {
				e.printStackTrace();
			}
			listTotalCovid.add(mapValue);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return listTotalCovid;
	}

	@Override
	public List<Map<String, String>> getLimitIndonesiaProvinceData() {
		ResponseEntity<String> response = restTemplate.getForEntity("https://data.covid19.go.id/public/api/prov.json", String.class);
		List<Map<String, String>> listTotalProvinceCovid = new ArrayList<Map<String, String>>();
		JSONParser jsonParse = new JSONParser();
		try {
			Object obj = jsonParse.parse(response.getBody());
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray list_data = (JSONArray) jsonObject.get("list_data");
			
			for (int i = 0; i < 8; i++) {
				Map<String, String> mapValue = new HashMap<String,String>();
				JSONObject objListData = (JSONObject) list_data.get(i);
				String provinceName = (String) objListData.get("key");
				String updatedAt = (String) jsonObject.get("last_date");
				Long totalCases = (Long) objListData.get("jumlah_kasus");
				Long deathsCases = (Long) objListData.get("jumlah_meninggal");
				Long recoveredCases = (Long) objListData.get("jumlah_sembuh");
				Long hospitalizedCases = (Long) objListData.get("jumlah_dirawat");
				
				NumberFormat numberFormat = NumberFormat.getNumberInstance();
				
				try {
					mapValue.put("provinceName", provinceName);
					mapValue.put("totalCases", numberFormat.format(totalCases));
					mapValue.put("deathsCases", numberFormat.format(deathsCases));
					mapValue.put("recoveredCases", numberFormat.format(recoveredCases));
					mapValue.put("hospitalizedCases", numberFormat.format(hospitalizedCases));
					mapValue.put("updatedAt", updatedAt);
				} catch (Exception e) {
					e.printStackTrace();
				}
				listTotalProvinceCovid.add(mapValue);
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return listTotalProvinceCovid;
	}
	
	@Override
	public List<Map<String, String>> getAllIndonesiaProvinceData() {
		ResponseEntity<String> response = restTemplate.getForEntity("https://data.covid19.go.id/public/api/prov.json", String.class);
		List<Map<String, String>> listTotalProvinceCovid = new ArrayList<Map<String, String>>();
		JSONParser jsonParse = new JSONParser();
		try {
			Object obj = jsonParse.parse(response.getBody());
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray list_data = (JSONArray) jsonObject.get("list_data");
			
			for (int i = 0; i < list_data.size(); i++) {
				Map<String, String> mapValue = new HashMap<String,String>();
				JSONObject objListData = (JSONObject) list_data.get(i);
				String provinceName = (String) objListData.get("key");
				String updatedAt = (String) jsonObject.get("last_date");
				Long totalCases = (Long) objListData.get("jumlah_kasus");
				Long deathsCases = (Long) objListData.get("jumlah_meninggal");
				Long recoveredCases = (Long) objListData.get("jumlah_sembuh");
				Long hospitalizedCases = (Long) objListData.get("jumlah_dirawat");
				
				NumberFormat numberFormat = NumberFormat.getNumberInstance();
				
				try {
					mapValue.put("provinceName", provinceName);
					mapValue.put("totalCases", numberFormat.format(totalCases));
					mapValue.put("deathsCases", numberFormat.format(deathsCases));
					mapValue.put("recoveredCases", numberFormat.format(recoveredCases));
					mapValue.put("hospitalizedCases", numberFormat.format(hospitalizedCases));
					mapValue.put("updatedAt", updatedAt);
				} catch (Exception e) {
					e.printStackTrace();
				}
				listTotalProvinceCovid.add(mapValue);
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return listTotalProvinceCovid;
	}

	@Override
	public List<Map<String, String>> searchByCountryNameRequest(String name) {
		ResponseEntity<String> response = restTemplate.getForEntity("https://covid19.mathdro.id/api/countries/"+name+"/deaths", String.class);
		List<Map<String, String>> listByCountryData = new ArrayList<Map<String,String>>();
		JSONParser jsonParse = new JSONParser();
		try {
			log.info("Successfully Request Covid-19 Data With Country Name : " + name);
			Object obj = jsonParse.parse(response.getBody());
			JSONArray jsonArray = (JSONArray) obj;
			if (jsonArray.isEmpty()) {
				log.info("Covid-19 Data Not Found with Country Name : " + name);
			}else {
				log.info("Successfully loaded Covid-19 Data  with Country Name : " + name);
				for (int i = 0; i < jsonArray.size(); i++) {
					Map<String, String> map = new HashMap<String, String>();
					JSONObject jsonObject = (JSONObject) jsonArray.get(i);

					String countryRegion = (String) jsonObject.get("countryRegion");
					Long confirmed = (Long) jsonObject.get("confirmed");
					Long deaths = (Long) jsonObject.get("deaths");

					String provinceState;
					if (jsonObject.get("provinceState") != null) {
						provinceState = (String) jsonObject.get("provinceState");
					} else {
						provinceState = (String) "Unknown Province";
					}
				
					Long cases28Days;
						if (jsonObject.get("cases28Days") != null) {						
							cases28Days = (Long) jsonObject.get("cases28Days");
						} else {
							cases28Days = 0L;
						}
						
					Long deaths28Days;
						if (jsonObject.get("deaths28Days") != null) {						
							deaths28Days = (Long) jsonObject.get("deaths28Days");
						} else {
							deaths28Days = 0L;
						}
					
					String castToString;
					Double iRate;
						if (jsonObject.get("incidentRate") != null) {						
							castToString = (String) jsonObject.get("incidentRate").toString();
							iRate = Double.parseDouble(castToString);
						} else {
							castToString = (String) "0";
							iRate = Double.parseDouble(castToString);
						}
					
					Long incidentRate = (Long) Math.round(iRate);
					
					NumberFormat numberFormat = NumberFormat.getNumberInstance();
					
					map.put("countryRegion", countryRegion);
					map.put("provinceState", provinceState);
					map.put("confirmed", numberFormat.format(confirmed).toString());
					map.put("deaths", numberFormat.format(deaths).toString());
					map.put("cases28Days", numberFormat.format(cases28Days).toString());
					map.put("deaths28Days", numberFormat.format(deaths28Days).toString());
					map.put("incidentRate", numberFormat.format(incidentRate).toString());
					
					listByCountryData.add(map);
					
				}
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
			log.error("Request Failed with Country Name : " +name);
		}
		
		return listByCountryData;
	}

	@Override
	public List<Map<String, String>> readXMLData() {
		List<Map<String, String>> listArticle = new ArrayList<Map<String, String>>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			try {
				Document doc = db.parse("D:\\Data Kerja\\ferdinalaxewall\\file-data\\article.xml");
				NodeList list = doc.getElementsByTagName("item");
				for (int i = 0; i < list.getLength(); i++) {
					Map<String, String> articleMap = new HashMap<String, String>();
					
					Node node = list.item(i);
					Element element = (Element) node;
					String articleTitle = element.getElementsByTagName("title").item(0).getTextContent();
					String sourceLink = element.getElementsByTagName("link").item(0).getTextContent();
					String pubDate = element.getElementsByTagName("pubDate").item(0).getTextContent();
					String publishedDate = pubDate.replace(" +0700", "");
					NamedNodeMap enclosureLink = element.getElementsByTagName("enclosure").item(0).getAttributes();
					String imageSource = enclosureLink.getNamedItem("url").getTextContent();
					
					articleMap.put("articleTitle", articleTitle);
					articleMap.put("sourceLink", sourceLink);
					articleMap.put("publishedDate", publishedDate);
					articleMap.put("imageSource", imageSource);
					
					listArticle.add(articleMap);
				}
			} catch (SAXException | IOException e) {
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		}
		
		return listArticle;
	}

}
