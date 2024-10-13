<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
	<title>My Login web page</title>
</head>
<body>
	<h1>Welcome ${name}</h1>
	
	<div style="color:red">${failedLoginMessage}</div>
	
	<form action="/validateuser" method="POST">
		Username: <input id="username" name="username" type="text">
		Password: <input id="poassword" name="password" type="text">
		<input type="submit">
	</form>
</body>
</html>