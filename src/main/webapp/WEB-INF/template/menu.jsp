<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:tiles="http://tiles.apache.org/tags-tiles"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">

	<div id="menuHomeIndiko">
		<div id="logo" >	<form name="rechercheForm" class="form-inline" role="form"
		action="recherche" method="post">
		<div class="form-group has-success has-feedback  visible-lg" id="rechercheMenu"
			style="float: right; vertical-align: middle;">
		<!--	<a href="panier"> <img src="resources/Images/bag.png"
				class="iconesHead" alt="" /> </a>  <label class="control-label"
				for="inputSuccess4"></label>--> <input type="text" class="form-control"
				id="inputSuccess4" name="terme" /> <span
				class="glyphicon glyphicon-search form-control-feedback btnRecherche"
				onclick="rechercheForm.submit()"></span>
		</div>
	</form></div>
<a href="." id="retourCarte">&#160;</a>

		<ul class="menuIndiko">
			<li class="item1"><a href="home?id=1">${Shopping}</a>
				<ul>
					<c:forEach items="${categorie}" var="item">
						<c:if test="${item.parent==0}">
							<li class="subitem1 aMenuModeIndiko" id="${item.id}">							
							<a href="shopping?id=${item.id}">
								<c:set var="myVar" value="/shopping?id=${item.id}" />										
									<c:if test="${not( (fn:endsWith(pageContext.request.requestURL, 'shopping.jsp')) and (param.id==item.id))}">	
										${item.nom}
									</c:if>
								<c:if test="${(fn:endsWith(pageContext.request.requestURL, 'shopping.jsp'))  and (param.id==item.id)}">	
									<span class="dejaVisite">	${item.nom}</span>
								</c:if>
							</a>						
							
							</li>
							<div id="m${item.id}" class="menuModeIndiko">
							<c:if test="${categorie.size() > 0}">
							
									<c:forEach items="${categorie}" var="ssitem">
										<c:if test="${ssitem.parent==item.id}">	
										<ul>	
										<li>							
											<a href="shopping?id=${ssitem.id}" >
										
											${ssitem.nom}
											
											</a></li></ul>
										</c:if>
										
									</c:forEach>
									
									</c:if>
							</div>
						</c:if>
					</c:forEach>					
				</ul></li>
			<li class="item2"><a href="home?id=2">${Culture} </a>
				<ul>
					<c:forEach items="${categorieHistoire}" var="item">
						<c:if test="${item.parent==0}">
							<li class="subitem2 aMenuModeIndikoH" id="${item.id}"><a href="home?id=${item.id}">
								<c:if test="${not( (fn:endsWith(pageContext.request.requestURL, 'home.jsp')) and (param.id==item.id))}">	
										${item.nom}
									</c:if>
								<c:if test="${(fn:endsWith(pageContext.request.requestURL, 'home.jsp'))  and (param.id==item.id)}">	
									<span class="dejaVisite">	${item.nom}</span>
								</c:if>
								</a></li>		
								<div id="mH${item.id}" class="menuModeIndiko">
							<c:if test="${categorieHistoire.size() > 0}">
							
									<c:forEach items="${categorieHistoire}" var="ssitem">
										<c:if test="${ssitem.parent==item.id}">	
										<ul>	
										<li>							
											<a href="home?id=${ssitem.id}" >
										
											${ssitem.nom}
											
											</a></li></ul>
										</c:if>
										
									</c:forEach>
									
									</c:if>
							</div>		
						</c:if>
					</c:forEach>
					
				</ul></li>
			<li class="item3"><a href="home?id=100">${Service}</a>
				<ul>
					<c:forEach items="${categorie}" var="item">
						<c:if test="${item.parent==100}">
							<li class="subitem1 aMenuModeIndiko" id="${item.id}">							
							<a href="shopping?id=${item.id}">
								<c:set var="myVar" value="/shopping?id=${item.id}" />										
									<c:if test="${not( (fn:endsWith(pageContext.request.requestURL, 'shopping.jsp')) and (param.id==item.id))}">	
										${item.nom}
									</c:if>
								<c:if test="${(fn:endsWith(pageContext.request.requestURL, 'shopping.jsp'))  and (param.id==item.id)}">	
									<span class="dejaVisite">	${item.nom}</span>
								</c:if>
							</a>						
							
							</li>
							<div id="m${item.id}" class="menuModeIndiko">
							<c:if test="${categorie.size() > 0}">
							
									<c:forEach items="${categorie}" var="ssitem">
										<c:if test="${ssitem.parent==item.id}">	
										<ul>	
										<li>							
											<a href="shopping?id=${ssitem.id}" >
										
											${ssitem.nom}
											
											</a></li></ul>
										</c:if>
										
									</c:forEach>
									
									</c:if>
							</div>
						</c:if>
					</c:forEach>					
				</ul></li>
							<li class="item4"><a href="home?id=2">${Sortir} </a>
				<ul>
					<c:forEach items="${categorieHistoire}" var="item">
						<c:if test="${item.parent==100}">
							<li class="subitem2 aMenuModeIndikoH" id="${item.id}"><a href="home?id=${item.id}">
								<c:if test="${not( (fn:endsWith(pageContext.request.requestURL, 'home.jsp')) and (param.id==item.id))}">	
										${item.nom}
									</c:if>
								<c:if test="${(fn:endsWith(pageContext.request.requestURL, 'home.jsp'))  and (param.id==item.id)}">	
									<span class="dejaVisite">	${item.nom}</span>
								</c:if>
								</a></li>		
								<div id="mH${item.id}" class="menuModeIndiko">
							<c:if test="${categorieHistoire.size() > 0}">
							
									<c:forEach items="${categorieHistoire}" var="ssitem">
										<c:if test="${ssitem.parent==item.id}">	
										<ul>	
										<li>							
											<a href="home?id=${ssitem.id}" >
										
											${ssitem.nom}
											
											</a></li></ul>
										</c:if>
										
									</c:forEach>
									
									</c:if>
							</div>		
						</c:if>
					</c:forEach>
					
				</ul></li>
			<li id="cacheMenuIndiko" class="subitem3"></li>
		</ul>
<a href="panier" id="retourShopping">&#160;</a>
		<div id="flecheMenu" class="flecheMenuBas">&#160;</div>

	</div>
</jsp:root>