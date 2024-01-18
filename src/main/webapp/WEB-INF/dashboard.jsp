<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <header class="bg-dark p-2 mb-3">
		<h1 class="text-light">Student<span class="text-primary">Resources</span>App</h1>

		<form id="logoutForm" method="POST" action="/logout">
    		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
    		<input type="submit" class="btn btn-success" value="Logout">
  		</form>
	</header>
	<main class="text-center">
		<h2>All Students</h2>
		<table class="table table-striped w-50 m-auto">
			<thead>
				<tr>
					<th>Guest name</th>
					<th># Guests</th>
					<th>Arrived At</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ tabletsUser }" var="student">
				<tr>
					<td>${ tabletsUser.GuestName }</td>
					<td>${ tabletsUser.NumberOfGuests }</td>
					<td>${ tabletsUser.contact.Notes }</td>
					<td>actiosnsn</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</main>
</body>
</html>