<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	
	<acme:form-textbox code ="officer.duty.form.label.title" path="title"/>
	<acme:form-moment code ="officer.duty.form.label.initialMoment" path="initialMoment"/>
	<acme:form-moment code ="officer.duty.form.label.endMoment" path="endMoment"/>
	<acme:form-double code ="officer.duty.form.label.workload" path="workload"/>
	<acme:form-textarea code ="officer.duty.form.label.description" path="description"/>
	<acme:form-url code ="officer.duty.form.label.link" path="link"/>
	<jstl:if test="${command == 'show'}" >
	<acme:form-double code ="officer.duty.form.label.executionPeriod" path="executionPeriod" readonly='true'/>
	</jstl:if>
	<acme:form-select path="visibility" code ="officer.duty.form.label.visibility" >
		<acme:form-option code="PUBLIC" value="PUBLIC" selected="${visibility=='PUBLIC'}"/>
		<acme:form-option code="PRIVATE" value="PRIVATE" selected="${visibility=='PRIVATE'}"/>
	</acme:form-select>
	
	<acme:form-submit test="${command == 'show' && checkP == 'true' && checkF == 'false'}" code="officer.duty.form.button.update" action="/officer/duty/update"/>
	<acme:form-submit test="${command == 'show' && checkP == 'true' && checkF == 'false'}" code="officer.duty.form.button.delete" action="/officer/duty/delete"/>
	
	
	<acme:form-submit test="${command == 'create'}" code="officer.duty.form.button.create" action="/officer/duty/create"/>
	<acme:form-submit test="${command == 'delete'}" code="officer.duty.form.button.delete" action="/officer/duty/delete"/>
	<acme:form-submit test="${command == 'update'}" code="officer.duty.form.button.update" action="/officer/duty/update"/>
	<acme:form-return code="officer.duty.form.button.return"/>
	
</acme:form>
