<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container">

		<form>
		  <div class="form-group">
		    <label for="userName">UserName:</label>
		    <input type="email" class="form-control" placeholder="Enter UserName" id="userName">
		  </div>
		  
		  <div class="form-group">
		    <label for="email">Email:</label>
		    <input type="email" class="form-control" placeholder="Enter Email" id="email">
		  </div>
		  
		  <div class="form-group">
		    <label for="password">Password:</label>
		    <input type="password" class="form-control" placeholder="Enter Password" id="password">
		  </div>
		  
		</form>
	  <button id="btn-save" class="btn btn-primary">회원가입완료</button>

</div>

<!-- 여기서 /를 쓰면 바로 static으로 찾아간다 -->
<script src="/blog/js/user.js"></script>

<%@ include file="../layout/footer.jsp" %>



