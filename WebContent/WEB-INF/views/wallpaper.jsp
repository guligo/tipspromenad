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
			&nbsp;
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
