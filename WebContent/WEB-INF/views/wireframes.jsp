<%@ page import="se.tipspromenad.globals.Constants" %>
<%@ include file="../commons/commons.jsp" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- meta data -->		
		<meta property="fb:admins" content="{YOUR_FACEBOOK_USER_ID}" />
		<meta property="fb:app_id" content="{YOUR_APPLICATION_ID}" />
		
		<!-- css -->
		<style>
			html, body  {				
				margin: 0px;
				padding: 0px;
			}
			
			#comments {
				height: 640px;
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
				height: 650px;
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
		</script>
	</head>
	<body>		
		<div id="content">
			<table>
				<tr>
					<td class="comments-cell">
						<div id="comments" class="fb-comments" data-href="http://www.tipspromenad.nu" data-colorscheme="light" data-numposts="5" data-width="350">
						</div>
					</td>
					<td class="wireframe-cell">
						<img src="local/wireframes/splash.png" />
					</td>
				</tr>
			</table>
		</div>
		<div id="loader">
			<img src="local/img/loader.gif" />
		</div>
	</body>
</html>
