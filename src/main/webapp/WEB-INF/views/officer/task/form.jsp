<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	
	<acme:form-textbox code ="officer.task.form.label.title" path="title"/>
	<acme:form-moment code ="officer.task.form.label.initialMoment" path="initialMoment"/>
	<acme:form-moment code ="officer.task.form.label.endMoment" path="endMoment"/>
	<acme:form-double code ="officer.task.form.label.workload" path="workload"/>
	<acme:form-textarea code ="officer.task.form.label.description" path="description"/>
	<acme:form-url code ="officer.task.form.label.link" path="link"/>
	<jstl:if test="${command == 'show'}" >
	<acme:form-double code ="officer.task.form.label.executionPeriod" path="executionPeriod" readonly='true'/>
	</jstl:if>
	<acme:form-select path="visibility" code ="officer.task.form.label.visibility" >
		<acme:form-option code="PUBLIC" value="PUBLIC" selected="${visibility=='PUBLIC'}"/>
		<acme:form-option code="PRIVATE" value="PRIVATE" selected="${visibility=='PRIVATE'}"/>
	</acme:form-select>
	
	<acme:form-submit test="${command == 'show' && checkP == 'true' && checkF == 'false'}" code="officer.task.form.button.update" action="/officer/task/update"/>
	<acme:form-submit test="${command == 'show' && checkP == 'true' && checkF == 'false'}" code="officer.task.form.button.delete" action="/officer/task/delete"/>
	
	
	<acme:form-submit test="${command == 'create'}" code="officer.task.form.button.create" action="/officer/task/create"/>
	<acme:form-submit test="${command == 'delete'}" code="officer.task.form.button.delete" action="/officer/task/delete"/>
	<acme:form-submit test="${command == 'update'}" code="officer.task.form.button.update" action="/officer/task/update"/>
	<acme:form-return code="officer.task.form.button.return"/>
	
</acme:form>
