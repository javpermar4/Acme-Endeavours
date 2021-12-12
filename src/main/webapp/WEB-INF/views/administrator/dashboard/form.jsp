<%--
- form.jsp
-
- Copyright (C) 2012-2021 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h2>
	<acme:message code="administrator.dashboard.form.title.general-indicators"/>
</h2>
<h2>
	<acme:message code="administrator.dashboard.form.title.duty"/>
</h2>

<table class="table table-sm">
	<caption>
		<acme:message code="administrator.dashboard.form.title.general-indicators"/>
	</caption>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.totalpublicduties"/>
		</th>
		<td>
			<acme:print value="${totalPublicDuties}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.totalprivateduties"/>
		</th>
		<td>
			<acme:print value="${totalPrivateDuties}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.totalduties"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfPublicPrivateDuties}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-execute-period"/>
		</th>
		<td>
			<acme:print value="${averageNumberOfDutyExecutionPeriods}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.totalFinishedDuties"/>
		</th>
		<td>
			<acme:print value="${totalFinishedDuties}"/>
		</td>
	</tr>
	<tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.totalNonFinishedDuties"/>
		</th>
		<td>
			<acme:print value="${totalNonFinishedDuties}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.totalDutyFinishedAndNotFinished"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfFinishedNonFinishedDuties}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.stdDevDutyExecutionPeriods"/>
		</th>
		<td>
			<acme:print value="${stdDevDutyExecutionPeriods}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minExecutionPeriod"/>
		</th>
		<td>
			<acme:print value="${minExecutionPeriod}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maxExecutionPeriod"/>
		</th>
		<td>
			<acme:print value="${maxExecutionPeriod}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maxWorkload"/>
		</th>
		<td>
			<acme:print value="${maxWorkload}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minWorkload"/>
		</th>
		<td>
			<acme:print value="${minWorkload}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.averageNumberOfDutyWorkloads"/>
		</th>
		<td>
			<acme:print value="${averageNumberOfDutyWorkloads}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.stdDevDutyWorkloads"/>
		</th>
		<td>
			<acme:print value="${stdDevDutyWorkloads}"/>
		</td>
	</tr>
	
</table>
