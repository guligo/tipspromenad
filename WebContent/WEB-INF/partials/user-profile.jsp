<%@ page import="se.tipspromenad.globals.Constants" %>

<!-- style -->
<style>
	.datepicker {
		z-index: 2000;
	}
</style>

<!-- scripts -->
<script type="text/javascript" src="local/js/user-profile.js"></script>
<script type="text/javascript" src="local/js/facebook.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		userProfileController.init(
			'user/profile',
			'user/profile'			
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
		<h3 id="userProfileDialogLabel">My Profile</h3>
	</div>
	<div class="modal-body">
		<form class="form-horizontal">
			<div id="nameUserProfileControlGroup" class="control-group">
				<label class="control-label" for="nameUserProfileInput">Name</label>
				<div class="controls">
			    	<input type="text" id="nameUserProfileInput" placeholder="Name">
			    </div>
			</div>
			<div id="genderUserProfileControlGroup" class="control-group">
				<label class="control-label" for="genderUserProfileInput">Gender</label>
				<div class="controls" style="margin-top: 5px;">
			    	Male <input name="genderUserProfileInput" type="radio" value="MALE" style="margin: 0px;" />
			    	Female <input name="genderUserProfileInput" type="radio" value="FEMALE" style="margin: 0px;" />
			    </div>
			</div>
			<div class="control-group">
				<label for="birthDateUserProfileControlGroup" class="control-label">Date of Birth</label>
				<div class="controls">
					<input id="birthDateUserProfileInput" placeholder="Date of Birth" type="text" />
				</div>
			</div>
			<div id="countryUserProfileControlGroup" class="control-group">
				<label class="control-label" for="countryUserProfileInput">Country</label>
				<div class="controls">
			    	<input type="text" id="countryUserProfileInput" placeholder="Country">
			    </div>
			</div>
			<div id="cityUserProfileControlGroup" class="control-group">
				<label class="control-label" for="countryUserProfileInput">City</label>
				<div class="controls">
			    	<input type="text" id="cityUserProfileInput" placeholder="City">
			    </div>
			</div>			
			<div class="control-group">
			    <div class="controls">
					<a id="facebookConnectButton" class="fb-button" onclick="facebookController.showDialogAndConnect();">Connect with FB</a>
			    </div>
			</div>
		</form>
	</div>
	<div class="modal-footer">
		<a href="javascript:userProfileController.updateUserProfile();" class="btn btn-primary">Update</a>
		<a href="javascript:userProfileController.hideDialog();" class="btn">Close</a>
	</div>
</div>
