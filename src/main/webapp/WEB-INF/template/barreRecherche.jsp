<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="barreRecherche" class="col-xs-12">


	<c:if test="${request.getContextPath().response.locale!='en'}">
		<a href="home?lang=en"><img src="resources/Images/langue_en.png"
			id="iconesRecherche" alt="" /></a>
	</c:if>
	<c:if test="${request.getContextPath().response.locale=='en'}">
		<a href="home?lang=fr"><img src="resources/Images/langue_fr.png"
			id="iconesRecherche" alt="" /></a>
	</c:if>


	<div id="c_a9b96b916c279cb6330a6ca35b96170e" class="mini   meteoB">
		<div id="w_a9b96b916c279cb6330a6ca35b96170e" class="mini"
			style="height: 100%"></div>
	</div>
	<script type="text/javascript"
		src="http://www.meteorama.fr/widget/loader/a9b96b916c279cb6330a6ca35b96170e"></script>
</div>
