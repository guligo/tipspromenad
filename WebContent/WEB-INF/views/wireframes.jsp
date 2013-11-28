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
			
			#playmain {
				display: none;
			}
			
			#playquiz {
				display: none;
			}
			
			#result {
				display: none;
			}			
		</style>
		
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
						<div id="comments" class="fb-comments" data-href="www.tipspromenad.nu" data-colorscheme="light" data-numposts="5" data-width="350">
						</div>
					</td>
					<td class="wireframe-cell">
						<div id="createmain">
							<img src="local/wireframes/createmain.png" />
							<a href="javascript:transit($('#createmain'), $('#playlist'));" style="width: 20px; height: 20px; background-color: yellow; position: absolute; left: 666px; top: 141px; opacity: 0.5; border-radius: 999px;"></a>
							<a href="javascript:transit($('#createmain'), $('#create1'));"  style="width: 20px; height: 20px; background-color: yellow; position: absolute; left: 629px; top: 228px; opacity: 0.5; border-radius: 999px;"></a>
						</div>
						<div id="create1">
							<img src="local/wireframes/create1.png" />
							<a href="javascript:transit($('#create1'), $('#create2'));"    style="width: 20px; height: 20px; background-color: yellow; position: absolute; left: 666px; top: 141px; opacity: 0.5; border-radius: 999px;"></a>
							<a href="javascript:transit($('#create1'), $('#create2'));"    style="width: 20px; height: 20px; background-color: yellow; position: absolute; left: 693px; top: 511px; opacity: 0.5; border-radius: 999px;"></a>
							<a href="javascript:transit($('#create1'), $('#createmain'));" style="width: 20px; height: 20px; background-color: yellow; position: absolute; left: 785px; top: 570px; opacity: 0.5; border-radius: 999px;"></a>
						</div>
						<div id="create2">
							<img src="local/wireframes/create2.png" />
							<a href="javascript:transit($('#create2'), $('#create25'));" style="width: 20px; height: 20px; background-color: yellow; position: absolute; left: 550px; top: 500px; opacity: 0.5; border-radius: 999px;"></a>
							<a href="javascript:transit($('#create2'), $('#create1'));"  style="width: 20px; height: 20px; background-color: yellow; position: absolute; left: 785px; top: 570px; opacity: 0.5; border-radius: 999px;"></a>
						</div>
						<div id="create25">
							<img src="local/wireframes/create25.png" />
							<a href="javascript:transit($('#create25'), $('#create3'));" style="width: 20px; height: 20px; background-color: yellow; position: absolute; left: 750px; top: 141px; opacity: 0.5; border-radius: 999px;"></a>
							<a href="javascript:transit($('#create25'), $('#create3'));" style="width: 20px; height: 20px; background-color: yellow; position: absolute; left: 630px; top: 511px; opacity: 0.5; border-radius: 999px;"></a>
						</div>
						<div id="create3">
							<img src="local/wireframes/create3.png" />
							<a href="javascript:transit($('#create3'), $('#create2'));" style="width: 20px; height: 20px; background-color: yellow; position: absolute; left: 785px; top: 570px; opacity: 0.5; border-radius: 999px;"></a>
						</div>
						<div id="playlist">
							<img src="local/wireframes/playlist.png" />
							<a href="javascript:transit($('#playlist'), $('#playmain'));"   style="width: 20px; height: 20px; background-color: yellow; position: absolute; left: 752px; top: 367px; opacity: 0.5; border-radius: 999px;"></a>
							<a href="javascript:transit($('#playlist'), $('#createmain'));" style="width: 20px; height: 20px; background-color: yellow; position: absolute; left: 607px; top: 140px; opacity: 0.5; border-radius: 999px;"></a>							
						</div>
						<div id="playmain">
							<img src="local/wireframes/playmain.png" />
							<a href="javascript:transit($('#playmain'), $('#playquiz'));" style="width: 20px; height: 20px; background-color: yellow; position: absolute; left: 650px; top: 290px; opacity: 0.5; border-radius: 999px;"></a>
							<a href="javascript:transit($('#playmain'), $('#playlist'));" style="width: 20px; height: 20px; background-color: yellow; position: absolute; left: 785px; top: 570px; opacity: 0.5; border-radius: 999px;"></a>				
						</div>
						<div id="playquiz">
							<img src="local/wireframes/playquiz.png" />
							<a href="javascript:transit($('#playquiz'), $('#playlist'));" style="width: 20px; height: 20px; background-color: yellow; position: absolute; left: 785px; top: 570px; opacity: 0.5; border-radius: 999px;"></a>							
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
