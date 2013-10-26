<%@ page import="se.tipspromenad.globals.Constants" %>
<%@ include file="../commons/commons.jsp" %>

<!DOCTYPE html>
<html lang="en">
	<head>		
		<title>
			<tiles:getAsString name="title" />
		</title>
		
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no" />
		<meta name="description" content="Tipspromenad.nu" />
		
		<!-- styles -->
		<link href="3p/bootstrap/css/bootstrap.css" rel="stylesheet" />
		<link href="3p/datepicker/css/datepicker.css" rel="stylesheet" />		
		<link href="3p/bootstrap/css/bootstrap-responsive.css" rel="stylesheet" />
		<style>
			body {
				padding-top: 40px;
			}
			
			.container {				
				width: 1125px !important;
			}
			
			.footer {
				width: 100%;		
				color: black;
				/* font-weight: bold; */
				text-align: center;
				position: absolute;
				bottom: 5px;
			}
			
			.footer a {
				color: black;
			}
			
			.footer a:hover {
				color: black;
			}
		</style>
		
		<!-- scripts -->
		<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
		<script src="3p/bootstrap/js/jquery-1.8.2.min.js"></script>
		<script src="3p/bootstrap/js/bootstrap.js"></script>
		<script src="3p/datepicker/js/bootstrap-datepicker.js"></script>
		<script src="local/js/commons.js"></script>
		
		<!-- fav and touch icons -->
		<link rel="shortcut icon" href="3p/bootstrap/ico/favicon.ico">
		<link rel="apple-touch-icon-precomposed" sizes="144x144" href="3p/bootstrap/ico/apple-touch-icon-144-precomposed.png">
		<link rel="apple-touch-icon-precomposed" sizes="114x114" href="3p/bootstrap/ico/apple-touch-icon-114-precomposed.png">
		<link rel="apple-touch-icon-precomposed" sizes="72x72" href="3p/bootstrap/ico/apple-touch-icon-72-precomposed.png">
		<link rel="apple-touch-icon-precomposed" href="3p/bootstrap/ico/apple-touch-icon-57-precomposed.png">		
	</head>
	<body>
		<sec:authorize ifAnyGranted="ROLE_SIMPLE_USER">
			<div class="navbar navbar-inverse navbar-fixed-top">
				<div class="navbar-inner">
					<div class="container">
						<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</a>
						<a class="brand" href="#">
							Tipspromenad.nu
						</a>
						<div class="nav-collapse collapse">
							<c:set var="tab">
								<tiles:getAsString name="tab" />
							</c:set>
							<ul class="nav">
								<li class="${tab == 1 ? 'active': ''}">
									<a href="<%= Constants.URL.HOME_PAGE %>">Home</a>
								</li>
								<li>
									<a href="#">Community</a>
								</li>
								<li>
									<a href="#">Game</a>
								</li>
								<li class="dropdown ${tab == 2 ? 'active': ''}">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown">
										My Tipspromenad <b class="caret"></b>
									</a>
									<ul class="dropdown-menu">
    									<li>
    										<a href="#">
    											User Profile
    										</a>
    									</li>
    									<li>
    										<a href="#">
    											Event Hub
    										</a>
    									</li>
    									<li>
    										<a href="<%= Constants.URL.GAME_LIST_PAGE %>">
    											Join, Edit & Create
    										</a>
    									</li>
    								</ul>
								</li>
								<li>
									<a href="#">Store</a>
								</li>
								<li>
									<a href="#">Download Mobile App</a>
								</li>
							</ul>
							<ul class="nav" style="float: right">
								<li>
									<a data-toggle="dropdown" href="#">
										Welcome, <span style="color: white;"><sec:authentication property="principal.name" /></span>!
									</a>
									<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
    									<li>
    										<a tabindex="-1" href="javascript:javascript:userProfileController.getUserProfile();">
    											My profile
    										</a>
    									</li>
    									<li class="divider"></li>
    									<li>
    										<a tabindex="-1" href="j_spring_security_logout">
    											Logout
    										</a>
    									</li>
    								</ul>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</sec:authorize>
		<br />
		<div class="container">
			<tiles:insertAttribute name="content" />
		</div>
		<div class="footer">
			Build #: <a href="http://<%= Constants.System.BUILD_SERVER %>/job/tipspromenad-dev/<%= Constants.System.BUILD_NUMBER %>" target="_blank">
				<%= Constants.System.VERSION %>.<%= Constants.System.BUILD_NUMBER %>
			</a> | &#169; Tipspromenad.nu
		</div>
		
		<!-- dialogs -->
		<jsp:include page="../partials/user-profile.jsp" />
		<jsp:include page="../partials/error.jsp" />
	</body>
	<script>
		(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
		(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
		m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
		})(window,document,'script','//www.google-analytics.com/analytics.js','ga');
		ga('create', 'UA-45159467-1', 'tipspromenad.nu');
		ga('send', 'pageview');
	</script>
</html>
