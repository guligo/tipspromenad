<%@ include file="../commons/commons.jsp" %>

<div class="accordion" id="accordion2">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#origins">Origins</a>
		</div>
		<div id="origins" class="accordion-body collapse in">
			<div class="accordion-inner">
				<spring:message code="rules.origins" />
			</div>
		</div>
	</div>
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#rules">Rules</a>
		</div>
		<div id="rules" class="accordion-body collapse">
			<div class="accordion-inner">
				<spring:message code="rules.rules" />
			</div>
		</div>
	</div>
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#participants">Participants </a>
		</div>
		<div id="participants" class="accordion-body collapse">
			<div class="accordion-inner">
				<spring:message code="rules.participants" />
			</div>
		</div>
	</div>
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#help">Help</a>
		</div>
		<div id="help" class="accordion-body collapse">
			<div class="accordion-inner">
				<spring:message code="rules.help" />
			</div>
		</div>
	</div>
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#contribution">Contribution</a>
		</div>
		<div id="contribution" class="accordion-body collapse">
			<div class="accordion-inner">
				<spring:message code="rules.contribution" />
			</div>
		</div>
	</div>
</div>
