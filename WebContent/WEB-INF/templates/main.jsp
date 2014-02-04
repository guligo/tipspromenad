<%@ page contentType="text/html;charset=UTF-8"  %>
<%@ page import="se.tipspromenad.globals.Constants" %>
<%@ page import="org.springframework.web.servlet.support.RequestContext" %>

<%@ include file="../commons/commons.jsp" %>
<% String lang = (new RequestContext(request)).getLocale().getLanguage(); %>

<!DOCTYPE html>
<html lang="en">
	<head>		
		<title>
			<tiles:getAsString name="title" />
		</title>
		
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no" />
		<meta name="description" content="Tipspromenad.nu" />
		
		<!-- icon -->
		<link href="local/img/icon.png" rel="shortcut icon" />
		
		<!-- styles -->
		<link href="3p/bootstrap/css/bootstrap.css" rel="stylesheet" />
		<link href="3p/datepicker/css/datepicker.css" rel="stylesheet" />		
		<link href="3p/bootstrap/css/bootstrap-responsive.css" rel="stylesheet" />
		<link href="3p/famfamfam-flags/css/famfamfam-flags.css" rel="stylesheet" />
		<style>
			html, body {
				height: 100%;
			}
			
			body {
				background: url('local/img/image-new-one-opacity.jpg') no-repeat center center fixed;
				-webkit-background-size: cover;
				-moz-background-size: cover;
				-o-background-size: cover;
				background-size: cover;		
			}
			
			.content {
				width: 1125px !important;
				padding: 25px;
				margin-top: 40px;
				background-color: #ffffff;
				border-color: #000000;
				border-radius: 10px;
				border-width: medium;
			}
			
			.footer {
				width: 100%;		
				color: #000000;
				text-align: center;
				margin-top: 15px;
			}
			
			.footer a {
				color: black;
			}
			
			.footer a:hover {
				color: black;
			}
			
			/**
			 * http://stackoverflow.com/questions/11396478/facebook-login-button-custom-text-reverting
			 */
			a.fb-button {
			    color: #FFF;
			    display: inline-block;
			    text-decoration: none;
			}
			
			.fb-button {
			    background: #5F78AB;
			    background-image: url('http://static.ak.fbcdn.net/rsrc.php/v2/yf/r/S-DbSHszr4D.png');
			    background-repeat: no-repeat;
			    background-position: -1px -133px;
			    border-top: 1px solid #29447E;
			    border-right: 1px solid #29447E;
			    border-bottom: 1px solid #1A356E;
			    border-left: 1px solid #29447E;
			    -webkit-box-shadow: 0 1px 0 rgba(0, 0, 0, 0.1), inset 0 1px 0 #8A9CC2;
			    -moz-box-shadow: 0 1px 0 rgba(0, 0, 0, 0.1), inset 0 1px 0 #8a9cc2;
			    box-shadow: 0 1px 0 rgba(0, 0, 0, 0.1), inset 0 1px 0 #8A9CC2;
			    cursor: pointer;
			    font-family: 'lucida grande', tahoma, verdana, arial, sans-serif;
			    font-size: 12px;
			    font-weight: bold;
			    height: 20px;
			    line-height: 20px;
			    padding: 0px 5px 0px 30px;
			    text-align: left;
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
							<li class="dropdown ${tab == 1 ? 'active': ''}">
								<a href="<%= Constants.URL.HOME_PAGE %>" class="dropdown-toggle" data-toggle="dropdown">
									Home <b class="caret"></b>
								</a>
								<ul class="dropdown-menu">
									<li>
										<%--
										<a href="<%= Constants.URL.NEWS_PAGE %>">
											News
										</a>
										--%>
										<a href="<%= Constants.URL.RULES_PAGE %>">
											Rules
										</a>
										<a href="<%= Constants.URL.GETTING_STARTED_PAGE %>">
											Getting Started
										</a>
									</li>
								</ul>
							</li>
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">
									Community <b class="caret"></b>
								</a>
								<ul class="dropdown-menu">
   									<li>
   										<a href="#">
   											<spring:message code="navbar.forum" />
   										</a>
   									</li>
   									<li>
   										<a href="#">
   											Clubs
   										</a>
   									</li>
   								</ul>
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
   											Event Hub
   										</a>
   									</li>
   									<li>
   										<a href="<%= Constants.URL.GAME_LIST_PAGE %>">
   											Edit & Create
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
							<sec:authorize ifAnyGranted="ROLE_SIMPLE_USER">
								<li>
									<a data-toggle="dropdown" href="#">
										Welcome, <span style="color: white;"><sec:authentication property="principal.name" /></span>!
									</a>
									<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
    									<li>
    										<a tabindex="-1" href="javascript:javascript:userProfileController.getUserProfile();">
    											My Profile
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
							</sec:authorize>
							<sec:authorize ifNotGranted="ROLE_SIMPLE_USER">
								<li>
									<a href="javascript:loginController.showDialog();">
										Login
									</a>
								</li>
							</sec:authorize>
							<li>
								<div class="btn-group">
									<% if ("se".equals(lang)) { %>
										SE
									<% } else { %>
										GB
									<% } %>
									<a class="btn" href="?lang=se"><i class="famfamfam-flag-se"></i></a>
									<a class="btn" href="?lang=en"><i class="famfamfam-flag-gb"></i></a>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<br />
		<div class="content container">
			<tiles:insertAttribute name="content" />
		</div>
		<div class="footer">
			<%-- 
			Build #: <a href="http://<%= Constants.System.BUILD_SERVER %>/job/tipspromenad-dev/<%= Constants.System.BUILD_NUMBER %>" target="_blank">
				<%= Constants.System.VERSION %>.<%= Constants.System.BUILD_NUMBER %>
			</a> | &#169; 2014 Tipspromenad.nu
			--%>
			Tipspromenad.nu &#169; 2014
		</div>
		
		<!-- dialogs -->
		<sec:authorize ifAnyGranted="ROLE_SIMPLE_USER">
			<jsp:include page="../partials/user-profile.jsp" />
		</sec:authorize>
		<jsp:include page="../partials/construction.jsp" />		
		<jsp:include page="../partials/error.jsp" />
		<jsp:include page="../partials/login.jsp"></jsp:include>
	</body>	
</html>
