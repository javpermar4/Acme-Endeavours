<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
	
<acme:list>
		<acme:list-column code="officer.duty.list.label.title" path="title" width="20%"/>
		<acme:list-column code="officer.duty.list.label.initialMoment" path="initialMoment" width="20%"/>
		<acme:list-column code="officer.duty.list.label.endMoment" path="endMoment" width="20%"/>
		<acme:list-column code="officer.duty.list.label.executionPeriod" path="executionPeriod" width="20%"/>
		<acme:list-column code="officer.duty.list.label.workload" path="workload" width="20%"/>	
		<acme:list-column code="officer.duty.list.label.description" path="description" width="60%"/>	
		<acme:list-column code="officer.duty.list.label.visibility" path="visibility" width="20%"/>
		
</acme:list>
	
	
	
	 <%-- test="${command == 'show' && finalMode == 'false' }" --%>
	
	