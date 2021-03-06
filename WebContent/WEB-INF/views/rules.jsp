<%@ include file="../commons/commons.jsp" %>

<!-- scripts -->
<script type="text/javascript">
	$(document).ready(function() {
		$('#rulesToggle').click();
	});
</script>

<!-- styles -->
<style>
	.text {
		width: 600px;
		height: 300px;
		margin-top: 5px;
		margin-bottom: 15px;
		padding: 10px;
		border-radius: 5px;
		vertical-align: middle;
		color: #777777;
		background-color: #f7f7f7;
		display: table;
	}
	
	.text div {
		display: table-cell;
		vertical-align: middle;
	}
	
	.text-left {
		float: left;
	}
	
	.text-right {
		float: right;
		margin-left: 100px;
	}
	
	.image {
		width: 300px;
		height: 300px;
		margin-top: 5px;
		margin-bottom: 15px;
		padding: 10px;
		border-radius: 5px;
		background-color: #f1f1f1;
	}
	
	.image-left {
		float: left;
	}
	
	.image-right {
		float: right;
	}
</style>

<!-- html -->
<div class="accordion" id="accordion">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a id="rulesToggle" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#rules">
				<spring:message code="rules.rules" />
			</a>
		</div>
		<div id="rules" class="accordion-body collapse">
			<div class="accordion-inner">
				<div class="text text-right">
					<div>
						<spring:message code="rules.rules.text" />
					</div>
				</div>
				<img class="image image-left" src="local/img/placeholder-rules.jpg"></img>
			</div>
		</div>
	</div>
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#participants">
				<spring:message code="rules.participants" />
			</a>
		</div>
		<div id="participants" class="accordion-body collapse">
			<div class="accordion-inner">
				<div class="text text-left">
					<div>
						<spring:message code="rules.participants.text" />
					</div>
				</div>
				<img class="image image-right" src="local/img/placeholder-participants.jpg"></img>
			</div>
		</div>
	</div>
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#we">
				<spring:message code="rules.we" />
			</a>
		</div>
		<div id="we" class="accordion-body collapse">
			<div class="accordion-inner">
				<div class="text text-right">
					<div>
						<spring:message code="rules.we.text" />
					</div>
				</div>
				<img class="image image-left" src="local/img/placeholder-we.jpg"></img>
			</div>
		</div>
	</div>
</div>
