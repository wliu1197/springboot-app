<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
<jsp:include page="scriptlibs.jsp" />
	<title>Welcome page</title>
</head>
<body>
<jsp:include page="logout.jsp" />
	<h1>Welcome ${name}</h1>
	
	${NotTodoFound}
	<form action="todo-list" method="POST">
		<input id="username" name="username" type="text" value="${name}">
		<input type="submit" value="Show to do List">
	</form>
	
</body>
</html>