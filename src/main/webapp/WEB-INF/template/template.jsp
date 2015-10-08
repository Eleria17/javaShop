<!DOCTYPE html>	
<html>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Commerce La Rochelle">
        <meta name="author" content="Eleria Conseil">
        <title>javaShop</title>
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script> 
        <!--<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.1.2/jquery-ui.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/ui.sortable.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/ui.core.js"></script>
        -->

        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/resources/js/indiko.js"></script>	
        <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDY0kkJiTPVd2U7aTOAwhc9ySH6oHxOIYM&sensor=false">
        </script>


        <link href="${pageContext.request.contextPath}/resources/css/Chrome.css" rel="stylesheet">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/Images/favicon.png" />
    </head>
    <body>
        <div class="container-fluid" >
      <!--      <div class="row-fluid row-sm-height" >
                <tiles:insertAttribute name="barreRecherche" />
            </div> --> 
            <div class="row-fluid row-sm-height" >
                <aside class="col-lg-2 col-md-2 col-xs-3  col-sm-3  wrapper " style="/*z-index:2*/" >
                    <tiles:insertAttribute name="menu" />
                </aside>
                <div class="col-lg-8 col-md-8 col-xs-9 col-sm-9" style="/*z-index:0*/" >
                    <tiles:insertAttribute name="body" />
                </div>
                <aside class="col-lg-2 col-md-2 col-xs-0 col-sm-0 visible-lg" id="bandeauPub">
                 <tiles:insertAttribute name="barreRecherche" />
                    <tiles:insertAttribute name="bandeauPub" />
                </aside>
            </div>
        </div>  
        <tiles:insertAttribute name="footer" />
    </body>
</html>