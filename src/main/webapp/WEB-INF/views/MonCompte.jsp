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
						Créer un compte <small>Indiko</small>
					</h1>
				</div>
			</div><br/><br/>
			<form:form method="POST" commandName="Utilisateurs">
				<div class="row-fluid" style="font-size: large;">
					<div class="col-lg-6">
						<table>
							<tr>
								<td>Identifiant :</td>
								<td><form:input path="login" /></td>
							</tr>
							<tr>
								<td>Nom :</td>
								<td><form:input path="nom" /></td>
							</tr>
							<tr>
								<td>Prénom :</td>
								<td><form:input path="prenom" /></td>
							</tr>
							<tr>
								<td>Mot de passe :</td>
								<td><form:password path="motDePasse" /></td>
							</tr>
							<tr>
								<td>Civilité :</td>
								<td><form:radiobutton path="genre" value="M" label="M." />
									<form:radiobutton path="genre" value="F" label="Mme" /></td>
							</tr>
													<tr>
								<td>Adresse :</td>
								<td><form:textarea path="adresse1" /></td>
							</tr>
							<tr>
								<td>Ville :</td>
								<td><form:input path="ville" /></td>
							</tr>
							<tr>
								<td>Code postal :</td>
								<td><form:input path="code_postal" /></td>
							</tr>
							<tr>
								<td>Email :</td>
								<td><form:input path="email" /></td>
							</tr>

							<tr>
								<td>Téléphone (fixe ou portable):</td>
								<td><form:input path="portable_perso" /></td>
							</tr>
							<tr><td>&#x00a0;</td><td>&#x00a0;</td></tr>
							<tr>
								<td colspan="2"><input type="submit" value="S'enregistrer" /></td>
							</tr>
						</table>
					</div>
					<div class="col-lg-6">&#x00a0;</div>
				</div>
				<br />


			</form:form>

		</tiles:putAttribute>
	</tiles:insertDefinition>
</jsp:root>