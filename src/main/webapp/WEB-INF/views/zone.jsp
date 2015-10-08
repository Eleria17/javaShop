<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:tiles="http://tiles.apache.org/tags-tiles"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:tg="urn:jsptagdir:/WEB-INF/tags/"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	
	<jsp:output omit-xml-declaration="yes" />

	<jsp:directive.page contentType="text/html; charset=utf8" />

	<tiles:insertDefinition name="defaultTemplate">
		<tiles:putAttribute name="body">
				<spring:url value="/resources/Images/white.jpg" var="white" />
<!-- Page Header -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">${zone.nom}
                    <small>Culture et boutiques</small>
                    
                </h1>
            </div>
        </div>
        <!-- /.row -->
			<c:set var="columnProperties" scope="request" />
			<c:set var="columnLabels" scope="request" />
			<c:set var="columnMaxLengths" scope="request" />
			<c:set var="columnTypes" scope="request" />
			<c:set var="columnDatePatterns" scope="request" />
			
			<div class="row-fluid">
				<div class="col-lg-6 imgPrinCol"  style="padding:0;background-color:black;">
				
					<spring:url
						value="/resources/Images/zone/${zone.id}.jpg"
						var="imageAvatar" />
					<img class="poplightBS img-responsive illusPrincipal" alt="Avatar" src="${imageAvatar}"
						title="Image" onerror="this.src='${white}'" />
				</div> 
				<div class="col-lg-6 carteCol"  style="padding:0;">
					<div id="map-canvas">Carte</div>
				</div>
			</div>
				<div class="row  row-lg-height">
			<div class="col-lg-6">
			<c:forEach items="${gallerie}" var="item" varStatus="count">
				
				<a href="#" data-width="500" data-rel="popup${count.index}" class=" aGallerie"><img
						src="resources/Images/zone/${zone.id}_gallerie/${item}" class="poplightBS galleriePage" /></a>
									
			</c:forEach>
			</div>	
				<div class="col-lg-6 resumeDiv">
				<div class="resumeLieu">
				<div id="ResumeLieuCourt">
					${zone.texte}</div></div>
				<a href="#" id="suiteArticle">suite...</a>
				</div>
			</div>.
			
			<div class="row-fluid row-lg-height">
				<c:forEach items="${listeHistoire.pageList}" var="item">
					<div class="col-lg-4 colListe" ><div class="itemPage" >
				<div class="itemHaut">	<H4 style="margin :0;">		
					<a href="lieu?id=${item.id}">
							<c:out
								value="${item.nom}" /></a></H4></div>
					<div class="itemGauche">
				
						<a href="lieu?id=${item.id}"> 
						<spring:url value="/resources/Images/culture/${item.id}.jpg" var="imageAvatar" />
						 <img class="img-responsive itemImage" alt="Avatar" src="${imageAvatar}"	title="Image" 
							onerror="this.src='/resources/Images/white.jpg'" /> </a>
							</div><div class="itemDroit">
						${item.textecourt}<br/>&#160;</div>
					</div></div>
				</c:forEach>
			</div>
				<div class="row-fluid row-lg-height">
				<c:forEach items="${listeBoutiques.pageList}" var="item">
					<div class="col-lg-4 colListe" ><div class="itemPage" >
						<div class="itemHaut">	<H4 style="margin :0;"> <a href="boutique?id=${item.id}">	<c:out
								value="${item.nom}" /></a></H4></div>
					
					<div class="itemGauche">
				
						
						<a href="boutique?id=${item.id}"> 
						<spring:url value="/resources/Images/boutiques/${item.id}.jpg" var="imageAvatar" />
				
						 <img class="img-responsive itemImage" alt="Avatar" src="${imageAvatar}"	title="Image" 
							onerror="this.src='${white}'" /> </a>
							</div><div class="itemDroit">
						<small>${item.textecourt}  </small><br/>&#160;</div>
					</div></div>
				</c:forEach>
			</div>
			<div id="paginDiv">
			<c:url value="/zone" var="pagedLink">
<c:param name="id" value="${ID}"/>
<c:param name="page" value="~"/>
</c:url>
<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>
</div>


        <!-- /.row -->
		</tiles:putAttribute>
	</tiles:insertDefinition>
    <div class="row">
			<div class="col-lg-12">
			 <div class="modal fade  bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">  
     <!--  <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                <h4 class="modal-title"></h4>
            </div>   -->       
          <div class="modal-body">                
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
     </div>
     </div>
							<script type="text/javascript">
var map;
function initialize() {
  var mapOptions = {    zoom: 15,    center: new google.maps.LatLng(<c:out value=' ${zone.local_x}'/>  ,	<c:out value='${zone.local_y}'/> )  };
  map = new google.maps.Map(document.getElementById('map-canvas'),      mapOptions);
var maPos = new google.maps.LatLng(<c:out value='${zone.local_x}'/>  ,	<c:out value='${zone.local_y}'/> );
var marker = new google.maps.Marker({   position: maPos,   map: map,   title: " addslashes(${zone.nom}) " });
<c:forEach items="${eltCart}" var="item">
maPos = new google.maps.LatLng(<c:out value='${item.local_x}'/>  ,	<c:out value='${item.local_y}'/> );
marker = new google.maps.Marker({   position: maPos,   map: map,   title: " addslashes(${item.nom}) ", url:' ${item.url} '});
google.maps.event.addListener(marker, 'click', function() {
    window.location.href = this.url;
});

</c:forEach>
}
google.maps.event.addDomListener(window, 'load', initialize);
var articleId =<c:out value='${zone.id}'/>;
var type=1;
</script> 
</jsp:root>
