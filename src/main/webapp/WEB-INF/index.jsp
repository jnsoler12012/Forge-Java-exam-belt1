<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<body>
    <header class="bg-dark text-light p-2 mb-5">
    	<h1>Login &amp; Registration</h1>
    </header>
    <main class="row p-2">
    	
    	<div class="col">
    		<h2>Register</h2>
            <c:if test="${ errorRegister != null }">
		        <p class="text-danger"><c:out value="${ errorRegister }"/></p>
		    </c:if>
    		<form:form class="ml-3" action="/register" method="POST" modelAttribute="user">
    			<div class="m-4 form-group row">
    				<form:input class="form-control col-6" path="firstName" placeholder="first name"/>
    				<form:errors class="text-danger col-6" path="firstName"/>
    			</div>
    			<div class="m-4 form-group row">
    				<form:input class="form-control col-6" type="email" path="email" placeholder="email"/>
    				<form:errors class="text-danger col-6" path="email"/>
    			</div>
    			<div class="m-4 form-group row">
    				<form:input class="form-control col-6" type="password" path="password" placeholder="password"/>
    				<form:errors class="text-danger col-6" path="password"/>
    			</div>
    			<div class="m-4 form-group row">
    				<form:input class="form-control col-6" type="password" path="passwordConfirmation" placeholder="confirm password"/>
    				<form:errors class="text-danger col-6" path="passwordConfirmation"/>
    			</div>
    			<input class="m-4 btn btn-success reg_btn" type="submit" value="Register">
    		</form:form>
    	</div>
        
    </main>
</body>
</html>