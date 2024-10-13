<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<html>

<head>
	<title>Todo page</title>
</head>
<jsp:include page="scriptlibs.jsp" />

<body>


<!--  
	<div class="container">
	<h1>Update Todo </h1>
	 <div class="row">
	  <div class="col-sm-8">
	  <form action="/update-todo" method="POST">
			<table class="table">
				<thead>
					<tr>
						<th>ID</th>
						<th>DESCRIPTION</th>
						<th>TARGET DATE</th>
						<th>IS DONE?</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>				
					<tr>
						<td>${todo.id}</td>
						<td><input type="text" id="description" name="description" value="${todo.description}"></td>
						<td><input type="date" id="targetDate" name="targetDate" value="${todo.targetDate}"></td>
						<td><input type="checkbox" id="done" name="done" value="${todo.done}"></td>
						<td>
							<input id="todoId" name="todoId" type="hidden" value="${todo.id}">
							<input type="submit" class="btn btn-primary" value="Update">
							<a class="btn btn-primary" href="/todo-list?username=${validateduser}">Cancel</a>
						</td>
					</tr>
				</tbody>
			</table>
		</form>			
		</div>
	</div>
</div>

-->


<div class="container">
<jsp:include page="logout.jsp" />
	<h1>Update Todo </h1>
	 <div class="row">
	  <div class="col-sm-8">
	   <form:form action="/update-todo" method="POST" modelAttribute="todo">
	 
			<table class="table">
				<thead>
					<tr>
						<th>ID</th>
						<th>DESCRIPTION</th>
						<th>TARGET DATE</th>
						<th>IS DONE?</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>				
					<tr>
						<td>${todo.id}</td>
						<td><form:input type="text" id="description" name="description" path="description" value="${todo.description}"/></td>
						<td><form:input type="date" id="targetDate" name="targetDate" path="targetDate" value="${todo.targetDate}"/></td>
						<td><form:checkbox id="done" name="done" path="done" value="${todo.done}"/></td>
						<td>
							<form:input id="todoId" name="id" type="hidden"  path="id" value="${todo.id}"/>
							<input type="submit" class="btn btn-primary" value="Update"/>
							<a class="btn btn-primary" href="/todo-list?username=${validateduser}">Cancel</a>
						</td>
					</tr>
				</tbody>
			</table>
		</form:form>			
		</div>
	</div>
</div>
</body>
</html>