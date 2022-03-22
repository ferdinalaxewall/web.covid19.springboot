<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>Test Page</title>
</head>
<body>

<c:forEach items="${covidIndonesia }" var="covidData">
	<p>${covidData.get("jumlah_positif")} Kasus Positif</p>
	<p>${covidData.get("jumlah_sembuh") } Kasus Sembuh</p>
	<p>${covidData.get("jumlah_dirawat") } Kasus Dirawat</p>
	<p>${covidData.get("jumlah_meninggal") } Kasus Meninggal</p>
</c:forEach>

</body>
</html>