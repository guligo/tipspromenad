<%@ page import="se.tipspromenad.globals.Constants" %>

<!-- scripts -->
<script type="text/javascript" src="local/js/user-profile.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		userProfileController.init(
			'<%= Constants.URL.USER_PROFILE_GET_ACTION %>',
			'<%= Constants.URL.USER_PROFILE_UPDATE_ACTION %>'			
		);
	});
</script>

<!-- html -->
<div class="modal" id="userProfileDialog" tabindex="-1" role="dialog" aria-labelledby="userProfileDialogLabel" aria-hidden="true" style="display: none;">
	<div class="modal-header">
		<a href="javascript:userProfileController.hideDialog();" class="close">×</a>
		<h3 id="userProfileDialogLabel">Edit user profile</h3>
	</div>
	<div class="modal-body">
		<form class="form-horizontal">
			<div id="firstNameUserProfileControlGroup" class="control-group">
				<label class="control-label" for="firstNameUserProfileInput">First name</label>
				<div class="controls">
			    	<input type="text" id="firstNameUserProfileInput" placeholder="&lt;first name&gt;">
			    </div>
			</div>
			<div id="lastNameUserProfileControlGroup" class="control-group">
				<label class="control-label" for="lastNameUserProfileInput">Last name</label>
				<div class="controls">
			    	<input type="text" id="lastNameUserProfileInput" placeholder="&lt;last name&gt;">
			    </div>
			</div>
			<div id="imageProfileControlGroup" class="control-group">
				<label class="control-label" for="imageUserProfileInput">Picture</label>
				<div class="controls">
			    	<input id="imageUserProfileInput" type="text" />
			    </div>
			</div>
			<div id="genderUserProfileControlGroup" class="control-group">
				<label class="control-label" for="genderUserProfileInput">Gender</label>
				<div class="controls" style="margin-top: 5px;">
			    	Male <input name="genderUserProfileInput" type="radio" value="MALE" style="margin: 0px;" checked="checked" />
			    	Female <input name="genderUserProfileInput" type="radio" value="FEMALE" style="margin: 0px;" />
			    </div>
			</div>
		</form>
	</div>
	<div class="modal-footer">
		<a href="javascript:userProfileController.updateUserProfile();" class="btn btn-primary">Update</a>
		<a href="javascript:userProfileController.hideDialog();" class="btn">Close</a>
	</div>
</div>
