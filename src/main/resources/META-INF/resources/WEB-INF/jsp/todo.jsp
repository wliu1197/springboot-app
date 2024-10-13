<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<html>

<head>
	<title>Todo page</title>
</head>
<jsp:include page="scriptlibs.jsp" />
<style>
tr {
  text-align: center;
}
</style>
<body>
<div class="container">
<jsp:include page="logout.jsp" />
	<h1>Welcome ${name}</h1>
	${errorMessage}
	 <div class="row">
	  <div class="col-sm-12">
	  		<c:if test="${not empty NotTodoFound}">
	  			<div class="alert alert-warning" role="alert">${NotTodoFound}</div>
  			</c:if>
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
					<c:forEach items="${todos}" var="todo">
						<tr>
							<td>${todo.id}</td>
							<td>${todo.description}</td>
							<td>${todo.targetDate}</td>
							<td>${todo.done}</td>
							<td>
								<div>
									<span>
										<form action="/update-todo" method="GET" style="display: inline;">
											<input id="todoId" name="todoId" type="hidden" value="${todo.id}">
											<input type="submit" class="btn btn-primary" value="Update">
										</form>
									</span>
									<span>
										<form action="/delete-todo" method="POST" style="display: inline;">
											<input id="todoId" name="todoId" type="hidden" value="${todo.id}">
											<input type="submit" class="btn btn-danger" value="Delete">
										</form>
									</span>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addTodoModal">
			  Add to do
			</button>
			
		</div>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="addTodoModal" tabindex="-1" aria-labelledby="addTodoModal" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      
      <!--  springframework from use modelAttribute to map db object-->
      <form:form action="/add-todo" method="POST" modelAttribute="todo">
	      <div class="modal-body">
			
			<div class="row g-2 align-items-center">
				<div class="col-3">
					<label for="description" class="col-form-label">Description:</label>
				</div>
				<div class="col-8">
					<form:input type="text" id="description" class="form-control" path="description" required="required"/>
					<form:errors path="description"/>
				</div>
			</div>
			<br>
			
			<div class="row g-2 align-items-center">
				<div class="col-3">
					<label for="targetDate" class="col-form-label">TARGET DATE:</label>
				</div>
				<div class="col-8">
					<form:input type="date" id="targetDate" class="form-control" path="targetDate"/>
				</div>
			</div>
	      </div>
	      <div class="modal-footer">
	       	<input type="submit" class="btn btn-primary" value="Save">
	      </div>
      </form:form>
    </div>
  </div>
</div>
</body>
</html>