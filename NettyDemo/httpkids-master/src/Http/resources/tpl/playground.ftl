<html>
  <head>
	  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	  <meta charset="utf8">
	  <script src="/kids/pub/jquery-3.3.1.slim.min.js"></script>
	  <script src="/kids/pub/popper.min.js"></script>
	  <script src="/kids/pub/bootstrap.min.js"></script>
	  <script src="/kids/pub/axios.min.js"></script>
	  <link rel="stylesheet" type="text/css" href="/kids/pub/bootstrap.min.css"/>
  </head>
  <body>
    <div class="container">
      <div class="jumbotron">
      	<h1 class="display-4">PlayGround it is</h1>
      	<hr class="my-4">
      	<#list req.allParams() as name, values>
      		<#list values as value>
      			${name} = ${value} <br>
      		</#list>
      	</#list>
      </div>
    </div>
  </body>
</html>