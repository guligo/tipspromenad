<%@ page import="se.tipspromenad.globals.Constants" %>

<%@ include file="../commons/commons.jsp" %>

<!-- style -->
<style>
	.datepicker {
		z-index: 2000;
	}
</style>

<!-- scripts -->
<script type="text/javascript" src="local/js/user-profile.js"></script>
<script type="text/javascript" src="local/js/facebook.js"></script>
<script type="text/javascript" src="local/js/game-save/friend.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		userProfileController.init(
			'<%= Constants.URL.USER_PROFILE_GET_ACTION %>',
			'<%= Constants.URL.USER_PROFILE_UPDATE_ACTION %>',
			{
				'userprofile.message.nameempty': '<spring:message code="userprofile.message.nameempty" />',
				'userprofile.message.nametooshort': '<spring:message code="userprofile.message.nametooshort" />',
				'userprofile.message.nametoolong': '<spring:message code="userprofile.message.nametoolong" />',
				'userprofile.message.wrongdate': '<spring:message code="userprofile.message.wrongdate" />',
				'userprofile.message.countrytooshort': '<spring:message code="userprofile.message.countrytooshort" />',
				'userprofile.message.countrytoolong': '<spring:message code="userprofile.message.countrytoolong" />',
				'userprofile.message.citytooshort': '<spring:message code="userprofile.message.citytooshort" />',
				'userprofile.message.citytoolong': '<spring:message code="userprofile.message.citytoolong" />'
			}
		);
		
		facebookController.init('<%= Constants.URL.HOME_PAGE %>', function() {
			friendController.init();
		});
	});
</script>

<!-- html -->
<div class="modal" id="userProfileDialog" tabindex="-1" role="dialog" aria-labelledby="userProfileDialogLabel" aria-hidden="true" style="display: none;">
	<div class="modal-header">
		<a href="javascript:userProfileController.hideDialog();" class="close">×</a>
		<h3 id="userProfileDialogLabel">
			<spring:message code="userprofile.label" />
		</h3>
	</div>
	<div class="modal-body">
		<form class="form-horizontal">
			<div id="nameUserProfileControlGroup" class="control-group">
				<label class="control-label" for="nameUserProfileInput">
					<spring:message code="userprofile.label.name" />
				</label>
				<div class="controls">
			    	<input type="text" id="nameUserProfileInput" placeholder="Name">
			    </div>
			</div>
			<div id="genderUserProfileControlGroup" class="control-group">
				<label class="control-label" for="genderUserProfileInput">
					<spring:message code="userprofile.label.gender" />
				</label>
				<div class="controls" style="margin-top: 5px;">
			    	<spring:message code="userprofile.label.male" /> <input name="genderUserProfileInput" type="radio" value="MALE" style="margin: 0px;" />
			    	<spring:message code="userprofile.label.female" /> <input name="genderUserProfileInput" type="radio" value="FEMALE" style="margin: 0px;" />
			    </div>
			</div>
			<div class="control-group">
				<label for="birthDateUserProfileControlGroup" class="control-label">
					<spring:message code="userprofile.label.birthday" />
				</label>
				<div class="controls">
					<input id="birthDateUserProfileInput" placeholder="Date of Birth" type="text" />
				</div>
			</div>
			<div id="countryUserProfileControlGroup" class="control-group">
				<label class="control-label" for="countryUserProfileInput">
					<spring:message code="userprofile.label.country" />
				</label>
				<div class="controls">
			    	<input type="text" id="countryUserProfileInput" placeholder="Country">
			    </div>
			</div>
			<div id="cityUserProfileControlGroup" class="control-group">
				<label class="control-label" for="countryUserProfileInput">
					<spring:message code="userprofile.label.city" />
				</label>
				<div class="controls">
			    	<input type="text" id="cityUserProfileInput" placeholder="City">
			    </div>
			</div>			
			<div class="control-group">
			    <div class="controls">
					<a id="facebookConnectButton" class="fb-button" onclick="facebookController.showDialogAndConnect();">
						<spring:message code="userprofile.label.connectfb" />
					</a>
			    </div>
			</div>
		</form>
	</div>
	<div class="modal-footer">
		<a href="javascript:userProfileController.updateUserProfile();" class="btn btn-primary">
			<spring:message code="userprofile.button.update" />
		</a>
		<a href="javascript:userProfileController.hideDialog();" class="btn">
			<spring:message code="userprofile.button.close" />
		</a>
	</div>
</div>
