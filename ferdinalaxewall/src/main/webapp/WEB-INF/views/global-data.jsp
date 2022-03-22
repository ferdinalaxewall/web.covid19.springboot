<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="style.css">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Poppins&family=Quicksand:wght@700&display=swap" rel="stylesheet">
    <link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css' rel='stylesheet'>
    
    <link rel="icon" href="img/3625655-no-background.png" sizes="16x16">

    <title>Global Covid-19 Data - Covid-19 Web Information</title>
</head>
<body>
	
	<div class="pre-loader">
        <div class="circle"></div>
    </div>

    <nav class="navbar">
        <div class="container">
            <a href="#home" class="navbar-brand">Covid-19 Web Information</a>
            <ul class="navbar-list">
                <li><a href="./#home" class="navbar-link">Home</a></li>
                <li><a href="./#data" class="navbar-link">Data</a></li>
                <li><a href="./#indonesia" class="navbar-link">Indonesia Covid-19 Data</a></li>
                <li><a href="./#protocols" class="navbar-link">Protocols</a></li>
                <li><a href="./#information" class="navbar-link">Information</a></li>
            </ul>
        </div>
    </nav>

    <section class="content" id="data" style="padding-bottom:50px;">
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
        </div>
    </section>
    
    <footer class="footer">
        <h3 class="footer-title">Covid-19 Web Information</h3>
        <p class="footer-description">Copyright &copy; 2020, Created by Ferdinalaxewall</p>
    </footer>

    <script type="text/javascript" src="webjars/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript">
    $(window).on("load", function(){
    	$(".pre-loader").fadeOut(2000);
    });
    
    $(document).ready(function(){
        $("#data .content-title").addClass("scroll");
        $("#data .card").each(function(i){
            setTimeout(function(){
                $("#data .card").eq(i).addClass("scroll");
            }, 100 * (i+1));
        });
    });

    $(window).scroll(function(){
        var wScroll = $(this).scrollTop();

        if (wScroll > 10) {
            $(".navbar").addClass("scroll");
        }

        if (wScroll < 10) {
            $(".navbar").removeClass("scroll");
        }
    });
    </script>
</body>
</html>