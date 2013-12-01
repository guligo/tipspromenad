<%@ page import="se.tipspromenad.globals.Constants" %>
<%@ include file="../commons/commons.jsp" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- meta data -->		
		<meta property="fb:admins" content="178951022252693" />
		<meta property="fb:app_id" content="493009277428978" />
		
		<!-- css -->
		<style>
			html, body  {				
				margin: 0px;
				padding: 0px;
			}
			
			#comments {
				height: 625px;
				padding: 0 10px 0 0;
				overflow-y: scroll;
			}
			
			.comments-cell {
				/* overflow: hidden; */
			}
			
			.wireframe-cell {
				width: 100%;
				text-align: center;				
			}
			
			.wireframe-cell img {
				height: 625px;
			}
			
			#content {
				display: none;
			}
			
			#content table {
				border-spacing: 0;
				border-collapse: collapse;
			}
			
			#loader {
				width: 100%;
				height: 100%;
				padding-top: 300px;
				text-align: center;				
			}
			
			.standard {
				position: absolute;
				opacity: 0.5;
				border-style: solid;
				border-color: #c21717;
			}
			
			.back {
				width: 8px;
				height: 20px;
				left: 548px;
				top: 103px;
			}
			
			.button {
				width: 118px;
				height: 18px; 
			}
			
			.marker {
				width: 30px;
				height: 30px;
			}
			
			.tab {
				width: 40px;
				height: 20px;
			}
			
			#createmain {
				display: block;
			}
			
			#create1 {
				display: none;
			}
			
			#create2 {
				display: none;
			}
			
			#create25 {
				display: none;
			}
			
			#create3 {
				display: none;
			}
			
			#join {
				display: none;
			}
			
			#playlist {
				display: none;
			}
			
			#playmain1 {
				display: none;
			}
			
			#playmain2 {
				display: none;
			}
			
			#playquiz {
				display: none;
			}		
		</style>
		<!--[if IE]>
		<style type="text/css">
			.standard {
				background-color: rgba(255, 0, 0, 0);
			}
			
			.wireframe-cell {
				padding-left: 18px;
			}
		</style>
		<![endif]-->
		
		<!-- scripts -->
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
		<script>
			(function(d, s, id) {
				var js, fjs = d.getElementsByTagName(s)[0];
				if (d.getElementById(id)) return;
				js = d.createElement(s); js.id = id;
				js.src = "//connect.facebook.net/en_GB/all.js#xfbml=1";
				fjs.parentNode.insertBefore(js, fjs);
			} (document, 'script', 'facebook-jssdk'));
			
			$(window).load(function() {
				FB.Event.subscribe("xfbml.render", function(response) {
					$('#loader' ).css('display', 'none' );
					$('#content').css('display', 'block');
				});
			});
			
			function transit(from, to) {
				from.css('display', 'none');
				to  .css('display', 'block');
			}
		</script>
	</head>
	<body>		
		<div id="content">
			<table>
				<tr>
					<td class="comments-cell">
						<div id="comments" class="fb-comments" data-href="www.tipspromenad.nu/wireframes.jsp" data-colorscheme="light" data-numposts="5" data-width="350">
						</div>
					</td>
					<td class="wireframe-cell">
						<div id="createmain">
							<img src="local/wireframes/createmain.png" />
							<a href="javascript:transit($('#createmain'), $('#playlist'));" class="standard tab" style="left: 668px; top: 143px;"></a>
							<a href="javascript:transit($('#createmain'), $('#create1'));" class="standard button" style="left: 628px; top: 226px;"></a>
						</div>
						<div id="create1">
							<img src="local/wireframes/create1.png" />
							<a href="javascript:transit($('#create1'), $('#createmain'));" class="standard back"></a>
							<a href="javascript:transit($('#create1'), $('#create2'));" class="standard button" style="left: 693px; top: 510px;"></a>
						</div>
						<div id="create2">
							<img src="local/wireframes/create2.png" />
							<a href="javascript:transit($('#create2'), $('#createmain'));" class="standard back"></a>
							<a href="javascript:transit($('#create2'), $('#create25'));" class="standard marker" style="left: 557px; top: 503px;"></a>
						</div>
						<div id="create25">
							<img src="local/wireframes/create25.png" />
							<a href="javascript:transit($('#create25'), $('#createmain'));" class="standard back"></a>
							<a href="javascript:transit($('#create25'), $('#create3'));" class="standard button" style="left: 628px; top: 510px;"></a>
						</div>
						<div id="create3">
							<img src="local/wireframes/create3.png" />
							<a href="javascript:transit($('#create3'), $('#createmain'));" class="standard back"></a>
							<a href="javascript:transit($('#create3'), $('#createmain'));" class="standard button" style="left: 628px; top: 510px;"></a>
						</div>
						<div id="playlist">
							<img src="local/wireframes/playlist.png" />
							<a href="javascript:transit($('#playlist'), $('#createmain'));" class="standard tab" style="left: 610px; top: 143px;"></a>
							<a href="javascript:transit($('#playlist'), $('#playmain1'));" class="standard marker" style="left: 747px; top: 360px;"></a>		
						</div>
						<div id="playmain1">
							<img src="local/wireframes/playmain1.png" />
							<a href="javascript:transit($('#playmain1'), $('#playlist'));" class="standard back"></a>
							<a href="javascript:transit($('#playmain1'), $('#playquiz'));" class="standard marker" style="left: 650px; top: 289px;"></a>
						</div>
						<div id="playquiz">
							<img src="local/wireframes/playquiz.png" />
							<a href="javascript:transit($('#playquiz'), $('#playmain2'));" class="standard" style="width: 58px; height: 27px; left: 659px; top: 502px;"></a>							
						</div>
						<div id="playmain2">
							<img src="local/wireframes/playmain2.png" />
							<a href="javascript:transit($('#playmain2'), $('#playlist'));" class="standard  back"></a>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div id="loader">
			<img src="local/img/loader.gif" />
		</div>
	</body>
</html>
