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
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">
						Contacter <small>Indiko</small>
					</h1>
				</div>
			</div>
			<form method="GET" action="contactForm">
				<div class="row-fluid">
					<div class="col-lg-6">
						<table>
							<tr>
								<td>Email :</td>
								<td><input type='text' name="email" /></td>
							</tr>
							<tr>
								<td>Object :</td>
								<td><input type='text' name="objet" /></td>
							</tr>
							<tr>
								<td>&#x00a0;</td>
								<td>
								<textarea name="message">Votre message </textarea>
								</td>
							</tr>
							
					
							
							<tr>
								<td colspan="2"><input type="submit" value="Envoyer" /></td>
							</tr>
						</table>
					</div>
					<div class="col-lg-6">&#x00a0;</div>
				</div>
				<br />


			</form>

		</tiles:putAttribute>
	</tiles:insertDefinition>
</jsp:root>