<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:tiles="http://tiles.apache.org/tags-tiles"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<jsp:output omit-xml-declaration="yes" />

	<jsp:directive.page contentType="text/html; charset=utf8" />

	<tiles:insertDefinition name="defaultTemplate">
		<tiles:putAttribute name="body">
			<!-- Page Header -->
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">
						Shopping <small>Réglement</small>
					</h1>
				</div>
			</div>
Réglement de ${panier.totalHt} via Paypal

<form id="frm" action="${actionURL}">
<input type="hidden" name="cmd" value="_xclick"/>
<input type="hidden" name="item_name" value="Indiko"/>
<input type="hidden" name="image_url" value="http://indiko.fr/Indiko/resources/Images/logo.png"/>
<input type="hidden" name="business" value="${businessKey}"/>
<input type="hidden" name="return" value="http://indiko.fr/validationCommande"/>
  <input type="hidden" name="notify_url" value="http://indiko.fr/validationCommande">
  <input type='hidden' name='rm' value='2'>
<input type="hidden" name="cancel_return" value="http://indiko.fr/panier"/>
<input type="hidden" name="currency_code" value="EUR"/>
<input type="hidden" name="item_name" value="commande Indiko"/>
<input type="hidden" name="amount" value="${montant}"/>

</form>
Redirection vers Paypal...
<script type="text/javascript">
    $(this.document).ready(function () {
        var frm = $("form");
        frm.submit();
    });
</script>
		</tiles:putAttribute>
	</tiles:insertDefinition>
</jsp:root>
