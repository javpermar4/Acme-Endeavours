<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
	
<acme:form>
		<acme:form-textbox code="anonymous.duty.form.label.title" path="title"/>
		<acme:form-moment code="anonymous.duty.form.label.initialMoment" path="initialMoment" />
		<acme:form-moment code="anonymous.duty.form.label.endMoment" path="endMoment" />
		<acme:form-moment code="anonymous.duty.form.label.workload" path="workload"/>	
		<acme:form-moment code="authenticated.duty.form.label.executionPeriod" path="executionPeriod"/>	
		<acme:form-textarea code="anonymous.duty.form.label.description" path="description"/>	
		<acme:form-url code="anonymous.duty.form.label.link" path="link"/>
		<acme:form-select code="anonymous.duty.form.label.isPublic" path="visibility">
		<acme:form-option code="anonymous.duty.form.label.false" value='0' selected="${visibility == 'PRIVATE'}"/>
		<acme:form-option code="anonymous.duty.form.label.true" value='1' selected="${visibility == 'PUBLIC'}"/>
		</acme:form-select>
		<acme:form-return code="anonymous.duty.form.button.return"/>	
</acme:form>