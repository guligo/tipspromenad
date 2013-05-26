<%@ page import="se.tipspromenad.globals.Constants" %>

<!-- styles -->
<style>
	#mapContainer {
		width: 100%;
		height: 450px;
	}

	#mapContainer img {
		max-width: inherit;
	}
</style>

<!-- scripts -->
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAaLIt-WZIIb1WHNklEUjC6wOMUBXPwDOQ&sensor=true"></script>
<script type="text/javascript">
	var tabs  = ['questionsTab', 'mapTab', 'friendsTab', 'finishTab'];
	var panes = ['questionsTabContainer', 'mapTabContainer', 'friendsTabContainer', 'finishTabContainer'];
	var tab   = 0;
	
	var lockTabs = function() {
		$('#' + tabs[tab]).addClass('active');
		for (var i = 1; i < tabs.length; i++) {
			$('#' + tabs[i] + ' a').attr('href', '#');
			$('#' + tabs[i]).attr('class', 'disabled');
		}
	};
	
	var unlockNextTab = function() {
		if (tab < tabs.length - 1) {
			tab = tab + 1;
			for (var i = 0; i < tab; i++) {
				$('#' + tabs[i]).removeClass('active');
			}
			$('#' + tabs[tab] + ' a').attr('href', '#' + panes[tab]);
			$('#' + tabs[tab] + ' a').click();
			$('#' + tabs[tab]).attr('class', 'active');
		}
	};
	
	$(document).ready(function() {
		lockTabs();
		var mapInitialized = false;
		$('#mapTab a').bind('click', function (e) {
			mapController.initMap();
			mapController.initData();
		});
	});
</script>

<!-- html -->
<div class="tabbable">
	<ul class="nav nav-pills">
		<li id="questionsTab">
			<a href="#questionsTabContainer" data-toggle="tab">
				Questions
			</a>
		</li>
		<li id="mapTab">
			<a href="#mapTabContainer" data-toggle="tab">
				Map
			</a>
		<li>
		<li id="friendsTab">
			<a href="#friendsTabContainer" data-toggle="tab">
				Friends
			</a>
		<li id="finishTab">
			<a href="#finishTabContainer" data-toggle="tab">
				Finish
			</a>
		<li>
	</ul>
	<div class="tab-content">
		<div id="questionsTabContainer" class="tab-pane active">
			<%@ include file="../partials/game-save/game.jsp" %>
			<%@ include file="../partials/game-save/questions.jsp" %>
		</div>
		<div id="mapTabContainer" class="tab-pane">
			<%@ include file="../partials/game-save/map.jsp" %>
		</div>
		<div id="friendsTabContainer" class="tab-pane">
			<%@ include file="../partials/game-save/friends.jsp" %>
		</div>
		<div id="finishTabContainer" class="tab-pane">
			<%@ include file="../partials/game-save/finish.jsp" %>
		</div>
	</div>
</div>
