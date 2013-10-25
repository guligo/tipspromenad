<%@page import="se.tipspromenad.globals.Constants"%>
<%@ include file="../commons/commons.jsp" %>

<!DOCTYPE html>
<html lang="en">
	<head>		
		<title>
			Tipspromenad.nu | Coming soon!
		</title>
		
		<!-- styles -->
		<style>
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
		</style>
	</head>
	<body>
		<div id="content">
			<img src="local/img/wallpaper-logo.png" style="width: 50%; height: 50%;" />
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
	<script>
		(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
		(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
		m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
		})(window,document,'script','//www.google-analytics.com/analytics.js','ga');
		ga('create', 'UA-45159467-1', 'tipspromenad.nu');
		ga('send', 'pageview');
	</script>
</html>
