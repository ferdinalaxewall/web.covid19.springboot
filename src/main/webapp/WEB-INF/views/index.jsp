<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Poppins&family=Quicksand:wght@700&display=swap" rel="stylesheet">
    <link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css' rel='stylesheet'>
	<link href="izitoast/iziToast.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css"/>
    
    <link rel="stylesheet" href="style.css">
    <link rel="icon" href="img/3625655-no-background.png" sizes="16x16">

    <title>Covid-19 Web Information</title>
</head>
<body>
	
	<div class="pre-loader">
        <div class="circle"></div>
    </div>

    <nav class="navbar">
        <div class="container">
            <a href="#home" class="navbar-brand">Covid-19 Web Information</a>
            <ul class="navbar-list">
                <li><a href="#home" class="navbar-link">Home</a></li>
                <li><a href="#data" class="navbar-link">Data</a></li>
                <li><a href="#indonesia" class="navbar-link">Indonesia Covid-19 Data</a></li>
                <li><a href="#protocols" class="navbar-link">Protocols</a></li>
                <li><a href="#information" class="navbar-link">Information</a></li>
            </ul>
        </div>
    </nav>

    <section id="home">
        <div class="hero-text">
            <h3 class="hero-title">
                a Website that contains information about Covid-19
            </h3>
            <p class="hero-description">
                Stay Alert and Obey Health Protocols wherever you are
            </p>
        </div>
        <form class="input-group">
            <input type="text" name="countryName" id="input-countryName" placeholder="Search Covid-19 Data by Country Name...">
            <button id="search-button" type="submit">
                <i class='bx bx-search'></i>
            </button>
        </form>
    </section>

    <section class="content" id="data">
        <div class="container">
            <h3 class="content-title">
                <span class="capitalize" style="text-transform: capitalize;">Global</span> Covid-19 Cases Per Month
            </h3>

            <div class="group-case-card">
            
                <c:forEach items="${listOfData }" var="data">
	                <div class="card">
	                	<div class="country-group">
	                    	<h3 class="country-name">${data.get("countryRegion")}</h3>
	                    	<h5 class="province-name">${data.get("provinceState")}</h5>
	                	</div>
	                    <div class="total-case-group">
	                    	<h3 class="total-case">${data.get("confirmed")}</h3>
	                    	<p class="total-case-description">Total Case</p>
	                    </div>
	                    <div class="case-details">
	                        <p id="recovered">Incident Rate : ${data.get("incidentRate")} / Day</p>
	                        <p id="confirmed">Cases : ${data.get("cases28Days")} / Month</p>
	                        <p id="deaths">Deaths : ${data.get("deaths28Days")} / Month</p>
	                    </div>
	                </div>
            	</c:forEach>

            </div>
            <a href="global" class="more-button">See More</a>
        </div>
    </section>
    
    <section class="content" id="indonesia">
        <div class="container">
            <h3 class="content-title">
                Covid-19 Cases in Indonesia
            </h3>

            <div class="group-indonesia-data-card">

                <div class="group-main-card">
                    <div class="indonesia-card" id="yesterdays-cases-card">
	                    <c:forEach items="${covidIndonesia }" var="totalData">
	                        <h5 class="card-title">Yesterday's Cases</h5>
	                        <div class="card-body">
	                            <p id="positive">Positive Cases : ${totalData.get("positiveToday")}</p>
	                            <p id="recovered">Recovered : ${totalData.get("recoveredToday")}</p>
	                            <p id="hospitalized">Hospitalized : ${totalData.get("hospitalizedToday")}</p>
	                            <p id="deaths">Deaths : ${totalData.get("deathsToday")}</p>
	                        </div>
	                        <p class="card-footer">Updated at <br> ${totalData.get("updatedAtToday")}</p>
	                    </c:forEach>
                    </div>
                    
                    <div class="indonesia-bigger-card">
                        <div class="card-left-body main-card">
                            <h5 class="card-title">Indonesia</h5>
                            <div class="details-cases-container">
                                <p id="hospitalized">Hospitalized</p>
                                <p id="recovered">Recovered</p>
                                <p id="deaths">Deaths</p>
                                <p id="total-speciment">Total Speciment</p>
                                <p id="total-speciment-negatice">Total Speciment</p>
                            </div>
                        </div>
                        <div class="card-right-body main-card">
                        	<c:forEach items="${covidIndonesia }" var="totalData">
	                            <h5 class="card-title">${totalData.get("jumlah_positif")} Cases</h5>
	                            <div class="details-cases-container">
	                                <p id="hospitalized">${totalData.get("jumlah_dirawat")} People</p>
	                                <p id="recovered">${totalData.get("jumlah_sembuh")} People</p>
	                                <p id="deaths">${totalData.get("jumlah_meninggal")} People</p>
	                                <p id="total-speciment">${totalData.get("total_spesimen")} Speciments</p>
	                                <p id="total-speciment-negatice">${totalData.get("total_spesimen_negatif")} Speciments</p>
	                            </div>                        	
                        	</c:forEach>
                        </div>
                    </div>

                    <div class="indonesia-card" id="highest-cases-card">
                       	<c:forEach items="${covidIndonesia }" var="totalData">
	                        <h5 class="card-title">Highest Cases</h5>
	                        <div class="card-body">
	                            <p id="positive">Positive Cases : ${totalData.get("maxPositiveCase") }</p>
	                            <p id="recovered">Recovered : ${totalData.get("maxRecoveredCase") }</p>
	                            <p id="hospitalized">Hospitalized : ${totalData.get("maxHospitalizedCase") }</p>
	                            <p id="deaths">Deaths : ${totalData.get("maxDeathsCase") }</p>
	                        </div>
	                        <p class="card-footer">Since <br> 2020-03-02 - ${totalData.get("nowDate") }</p>
                        </c:forEach>
                    </div>
                </div>
                
                <div class="group-province-card">
                
                	<c:forEach items="${provinceData}" var="province">
	                    <div class="indonesia-card">
	                        <h5 class="card-title">${province.get("provinceName") }</h5>
	                        <div class="card-body">
	                            <p id="positive">Total Cases : ${province.get("totalCases") }</p>
	                            <p id="recovered">Recovered : ${province.get("recoveredCases") }</p>
	                            <p id="hospitalized">Hospitalized : ${province.get("hospitalizedCases") }</p>
	                            <p id="deaths">Deaths : ${province.get("deathsCases") }</p>
	                        </div>
	                        <p class="card-footer">Updated at <br> ${province.get("updatedAt") }</p>
	                    </div>
                	</c:forEach>
                    
                    
                </div>

            </div>
            
            <a href="indonesia" class="more-button">See More</a>
        </div>
    </section>
    

    <section class="content" id="protocols">
        <div class="container">
            <h3 class="content-title">
                Health Protocol During a Pandemic
            </h3>

            <div class="group-protocols-card">
                <div class="card">
                    <h5 class="protocol-title">Stay at Home</h5>
                    <p class="protocol-description">Stay at home unless for emergency matters</p>
                </div>
                <div class="card">
                    <h5 class="protocol-title">Wear a Mask</h5>
                    <p class="protocol-description">Wear a mask properly wherever you go</p>
                </div>
                <div class="card">
                    <h5 class="protocol-title">Wash your Hands</h5>
                    <p class="protocol-description">Wash with soap and running water regularly</p>
                </div>
                <div class="card">
                    <h5 class="protocol-title">Maintain a Distance</h5>
                    <p class="protocol-description">Maintain a safe distance of 1.5 to 2 meters</p>
                </div>
                <div class="card">
                    <h5 class="protocol-title">Avoid Crowds</h5>
                    <p class="protocol-description">Avoid crowds or any social gathering</p>
                </div>
                <div class="card">
                    <h5 class="protocol-title">Reduce Mobility</h5>
                    <p class="protocol-description">Limit mobility unless for emergency</p>
                </div>
            </div>

        </div>
    </section>


    <section class="content" id="information">
        <div class="container">
			<h3 class="content-title">
                Article and News about Covid-19 in Indonesia
            </h3>

            <div class="group-information-card">
            
                <c:forEach items="${articleData }" var="article">
	                <div class="card">
	                    <div class="card-img">
	                        <img src="${article.get('imageSource') }" alt="${article.get('title') }" class="image">
	                    </div>
	                    <div class="card-body">
	                        <h3 class="card-title">${article.get('articleTitle') }</h3>
	                        <div class="card-text-group">
	                            <p id="date-published">${article.get('publishedDate') }</p>
	                            <a href="${article.get('sourceLink') }" target="_blank" class="source-link">See more</a>
	                        </div>
	                    </div>
	                </div>
            	</c:forEach>
                
            </div>
        </div>
    </section>

    <footer class="footer">
        <h3 class="footer-title">Covid-19 Web Information</h3>
        <p class="footer-description">Copyright &copy; 2020, Created by Ferdinalaxewall</p>
    </footer>

    <script type="text/javascript" src="webjars/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
   	<script type="text/javascript" src="izitoast/iziToast.min.js"></script>
	<script type="text/javascript" src="script.js"></script>
</body>
</html>