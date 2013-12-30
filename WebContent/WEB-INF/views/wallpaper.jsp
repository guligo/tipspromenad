<%@ page import="se.tipspromenad.globals.Constants" %>
<%@ include file="../commons/commons.jsp" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>
			Tipspromenad.nu | Coming soon!
		</title>
		
		<!-- meta data -->
		<meta charset="utf-8">
		
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
		<meta name="description" content="A classic Swedish game - Tipspromenad - for the digital generation.">
		<meta name="contact" content="tipspromenad.nu@gmail.com">
		<meta name="keywords" content="tipspromenad, tipsrunda, quiz walk">
		
		<!-- icon -->
		<link href="local/img/icon.ico" rel="shortcut icon" />
		
		<!-- styles -->
		<link href="3p/bootstrap/css/bootstrap.css" rel="stylesheet" />
		<link href="3p/bootstrap/css/bootstrap-responsive.css" rel="stylesheet" />
		<link href="3p/fancybox/jquery.fancybox-1.3.4.css" rel="stylesheet" type="text/css" media="screen" />
		<style>
			html, body {
				height: 100%;
				overflow: hidden;
			}
			
			body {				
				margin: 0px;
				padding: 0px;										
				background: url('local/img/wallpaper.png') no-repeat center center fixed;
				-webkit-background-size: cover;
				-moz-background-size: cover;
				-o-background-size: cover;
				background-size: cover;
				font-family: "HelveticaNeue-Light", "Helvetica Neue Light", "Helvetica Neue", Helvetica, Arial, "Lucida Grande", sans-serif;
			}
			
			a {
				color: white;
				text-decoration: none;
			}
			
			a:hover {
				color: white;
				text-decoration: underline;
			}
			
			div#content {
				color: white;
				font-size: 30px;
				text-align: center;
				margin-top: 125px;
			}
			
			img#wallpaper {
				width: 50%;
			}
						
			div#footer {
				width: 100%;
				padding-top: 25px;
				padding-bottom: 25px;
				color: #ffffff;
				background-color: #4eaecb;				
				text-align: left;
				position: absolute;
				left: 0;
				bottom: 0;
				opacity: 0.90;
			}
			
			span.right {
				float: right;
				margin-right: 25px;
			}
			
			span.left {
				float: left;
				margin-left: 25px;
			}
			
			/* http://css-tricks.com/snippets/css/media-queries-for-standard-devices/ */
			/* Smartphones (portrait and landscape) ----------- */
			@media only screen 
			and (min-device-width : 320px) 
			and (max-device-width : 480px) {
				div#content {
					margin-top: 250px;
					font-size: 50px;
				}
				
				img#wallpaper {
					width: 90%;
				}
				
				div#footer {
					height: 100px;
					padding-top: 75px;
					font-size: 25px;
				}
			}
			
			/* Smartphones (landscape) ----------- */
			@media only screen 
			and (min-width : 321px) {
				div#content {
					margin-top: 25px;
				}
			}
			
			/* Desktops and laptops ----------- */
			@media only screen 
			and (min-width : 1224px) {
				div#content {
					margin-top: 120px;
				}
			}
		</style>
		
		<!-- scripts -->
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
		<script type="text/javascript" src="3p/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
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
		</script>
	</head>
	<body>
		<div id="content">
			<img id="wallpaper" src="local/img/wallpaper-logo.png" />
			<br />
			"A classic Swedish game for the digital generation."
			<br />
			<br />
			Coming soon!
			<br />
			<br />
			<br />
			<a id="tryItOutLink" class="btn btn-info btn-large" href="wireframes.jsp">Mobile App Concept</a>
			<a class="btn btn-info btn-large" href="/sandbox">Web App Beta</a>
		</div>
		<div id="footer">
			<span class="left">
				<a href="http://www.twitter.com/tipspromenad" target="_blank">@tipspromenad</a>
			</span>
			<span class="left">
				<a href="http://www.facebook.com/tipspromenad" target="_blank">facebook.com/tipspromenad</a>
			</span>
			<span class="right">
				Tipspromenad.nu &#169; 2013
			</span>
		</div>
	</body>
</html>
