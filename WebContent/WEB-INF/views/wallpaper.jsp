<%@ page import="se.tipspromenad.globals.Constants" %>
<%@ include file="../commons/commons.jsp" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>
			Tipspromenad.nu | Coming soon!
		</title>
		
		<!-- styles -->
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
				height: 50px;
				padding-top: 30px;
				color: #ffffff;
				background-color: #4eaecb;				
				text-aligh: left;
				position: absolute;
				left: 0;
				bottom: 0;
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
		<script>
			(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
			(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
			m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
			})(window,document,'script','//www.google-analytics.com/analytics.js','ga');
			ga('create', 'UA-45159467-1', 'tipspromenad.nu');
			ga('send', 'pageview');
		</script>
	</head>
	<body>
		<div id="content">
			<img id="wallpaper" src="local/img/wallpaper-logo.png" />
			<br />
			"A classic Swedish game for the digital generation."
			<br />
			<br />
			Comming soon!
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
