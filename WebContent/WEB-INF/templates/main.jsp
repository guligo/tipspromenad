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
		
		<!-- meta data -->
		<meta charset="utf-8">
		
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
		<meta name="description" content="A classic Swedish game - Tipspromenad - for the digital generation.">
		<meta name="contact" content="tipspromenad.nu@gmail.com">
		<meta name="keywords" content="tipspromenad, tipsrunda, quiz walk">
		
		<meta property="og:image" content="http://www.tipspromenad.nu/sandbox/local/img/icon.png" />
		<meta property="og:title" content="Tipspromenad" />
		<meta property="og:description" content="A classic Swedish game - Tipspromenad - for the digital generation." />
		
		<!-- icon -->
		<link href="local/img/icon.ico" rel="shortcut icon" />
		
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
				background: url('local/img/background.jpg') no-repeat center center fixed;
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
				width: 1125px !important;	
				color: #000000;
				text-align: center;
				margin: auto;
				margin-top: 15px;
			}
			
			.footer a {
				color: black;
			}
			
			.footer a:hover {
				color: black;
				text-decoration: underline;
			}
			
			.footer .footer-left {
				float: left;
			}
			
			.footer .footer-right {
				float: right;
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
		<script type="text/javascript" src="3p/bootstrap/js/jquery-1.8.2.min.js"></script>
		<script type="text/javascript" src="3p/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="3p/datepicker/js/bootstrap-datepicker.js"></script>
		<script type="text/javascript" src="3p/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
		<script type="text/javascript" src="local/js/commons.js"></script>
		<script>
			(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
			(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
			m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
			})(window,document,'script','//www.google-analytics.com/analytics.js','ga');
			ga('create', 'UA-45159467-1', 'tipspromenad.nu');
			ga('send', 'pageview');
			
			$(document).ready(function() {
				$("a#tryItOutLink").fancybox({
					'type': 'iframe',
					'scrolling': 'no',
					'width': 1000,
					'height': 625,
					'padding': 10,
					'onComplete': function() {
						var height = window.screen.availHeight;
						if (height <= 800) {
							$("#fancybox-wrap").css({
								'top': '0px',
								'bottom': 'auto',
								'padding-top': '12px'
							});
							$("#fancybox-content").css({
								'height': '626px'
							});
						}
					}
				});
			});
			
			if (!window.console) {
				var console = {
					log: function() {
						// do nothing
					},
					debug: function() {
						// do nothing
					},
					error: function() {
						// do nothing
					}
				};
			}
		</script>
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
					<a class="brand" href="rules.page">
						Tipspromenad.nu
					</a>
					<div class="nav-collapse collapse">
						<c:set var="tab">
							<tiles:getAsString name="tab" />
						</c:set>
						<ul class="nav">
							<li class="dropdown ${tab == 1 ? 'active': ''}">
								<a href="<%= Constants.URL.HOME_PAGE %>" class="dropdown-toggle" data-toggle="dropdown">
									<spring:message code="navbar.home" /> <b class="caret"></b>
								</a>
								<ul class="dropdown-menu">
									<li class="disabled">
										<a>
											<spring:message code="navbar.home.news" />
										</a>
									</li>
									<li>
										<a href="<%= Constants.URL.RULES_PAGE %>">
											<spring:message code="navbar.home.rules" />
										</a>
									</li>
									<li class="disabled">
										<a>
											<spring:message code="navbar.home.gettingstarted" />
										</a>
									</li>
								</ul>
							</li>
							<li class="dropdown ${tab == 2 ? 'active': ''}">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">
									<spring:message code="navbar.community" /> <b class="caret"></b>
								</a>
								<ul class="dropdown-menu">
   									<li class="disabled">
   										<a>
   											<spring:message code="navbar.community.forum" />
   										</a>
   									</li>
   									<li>
   										<a href="<%= Constants.URL.CLUB_LIST_PAGE %>">
   											<spring:message code="navbar.community.clubs" />
   										</a>
   									</li>
   								</ul>
							</li>
							<%--
							<li class="dropdown ${tab == 3 ? 'active': ''}">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">
									<spring:message code="navbar.game" /> <b class="caret"></b>
								</a>
								<ul class="dropdown-menu">
   									<li>
   										<a href="#">
   											<spring:message code="navbar.game.statistics" />
   										</a>
   									</li>
   									<li>
   										<a href="<%= Constants.URL.GAME_LIST_PAGE %>">
   											<spring:message code="navbar.game.challenges" />
   										</a>
   									</li>
   								</ul>
							</li>
							--%>
							<li class="dropdown ${tab == 4 ? 'active': ''}">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">
									<spring:message code="navbar.mytipspromenad" /> <b class="caret"></b>
								</a>
								<ul class="dropdown-menu">
   									<li class="disabled">
   										<a>
   											<spring:message code="navbar.mytipspromenad.eventhub" />
   										</a>
   									</li>
   									<li>
   										<a href="<%= Constants.URL.GAME_LIST_PAGE %>">
   											<spring:message code="navbar.mytipspromenad.editcreate" />
   										</a>
   									</li>
   								</ul>
							</li>
							<%--
							<li class="disabled">
								<a>
									<spring:message code="navbar.store" />
								</a>
							</li>
							--%>
							<li>
								<a id="tryItOutLink" href="wireframes.jsp">
									<spring:message code="navbar.download" />
								</a>
							</li>
						</ul>
						<ul class="nav" style="float: right">
							<sec:authorize ifAnyGranted="ROLE_SIMPLE_USER">
								<li>
									<a data-toggle="dropdown" href="#">
										<c:set var="username">
											<span style="color: white;"><sec:authentication property="principal.name" /></span>
										</c:set>
										<spring:message code="navbar.welcome" arguments="${username}" />
									</a>
									<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
    									<li>
    										<a tabindex="-1" href="javascript:javascript:userProfileController.getUserProfile();">
    											<spring:message code="navbar.profile" />
    										</a>
    									</li>
    									<li class="divider"></li>
    									<li>
    										<a tabindex="-1" href="j_spring_security_logout">
    											<spring:message code="navbar.logout" />
    										</a>
    									</li>
    								</ul>
								</li>
							</sec:authorize>
							<sec:authorize ifNotGranted="ROLE_SIMPLE_USER">
								<li>
									<a href="javascript:loginController.showDialog();">
										<spring:message code="navbar.login" />
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
			<!-- <%= Constants.System.VERSION %>.<%= Constants.System.BUILD_NUMBER %> -->
			<div class="footer-left">
				<div class="fb-like" data-href="https://www.tipspromenad.nu" data-layout="button_count" data-action="like" data-show-faces="true" data-share="true">
				</div>
				<a href="http://www.facebook.com/tipspromenad" target="_blank">facebook.com/tipspromenad</a>
				<a href="http://www.twitter.com/tipspromenad" target="_blank">@tipspromenad</a>
			</div>
			<div class="footer-right">
				Tipspromenad.nu &#169; 2013 - 2014
			</div>
		</div>
		<br />
		
		<!-- dialogs -->
		<sec:authorize ifAnyGranted="ROLE_SIMPLE_USER">
			<jsp:include page="../partials/user-profile.jsp" />
		</sec:authorize>
		<jsp:include page="../partials/error.jsp" />
		<jsp:include page="../partials/login.jsp"></jsp:include>
	</body>	
</html>
