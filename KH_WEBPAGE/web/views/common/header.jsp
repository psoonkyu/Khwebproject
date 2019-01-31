<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.rclass.member.vo.Member"%>
<%
	Member loginMember =(Member) session.getAttribute("loginMember");
//saveid 가져오기
// cookie 는 request의 getcookies 로 가져올수 있음
Cookie[] cookies = request.getCookies();
String saveId = null;

if (cookies != null){
	
for(Cookie c : cookies) {
			String key = c.getName();
			String value = c.getValue();
			if (key.equals("saveId")) {
				saveId = value;
				break;
			}
		}
	}
%>
<!--  값이 있으면 들어오고 없으면 null -->
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello MVC</title>
<link rel='stylesheet'
	href='<%=request.getContextPath()%>/css.style.css' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Namum+Myeongjo'
	rel="stylesheet">
</head>
<body>
	<header>
		<h1>Hello MVC</h1>
		<div class='login-container'>
			<%
if (loginMember == null){
%>
			<form id='LoginFrm' action='<%=request.getContextPath() %>/Login'
				method="post" onsubmit="return validate();">
				<table>
					<tr>
						<td><input type="text" name="userId" placeholder="아이디"
							value='<%=saveId != null ? saveId : "" %>' /></td>
						<td></td>
					</tr>
					<tr>
						<td><input type="password" name="password" placeholder="비밀번호" /></td>
						<td><input type="submit" value="로그인" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="checkbox" name="saveId"
							id="saveId" <%=saveId != null?"checked" : "" %> /> <label
							for="saveId">아이디저장</label></td>
					</tr>
				</table>
			</form>
			<% 
                   } else {
             %>
		</div>
		<script>
fucntion validate(){
	if ($("[name='userId']").val().trim().length == 0){
		alert('id 에 공백이 있으면 안됩니다.');
		$("[name='userId']").focus();
		return false;
	}
	if ($("[name='password']").val().trim().length == 0){
		alert('PASSWORD 에 공백이 있으면 안됩니다.');
		$("[name='password']").focus();
		return false;
	}
	return true;
	
	}

</script>
		<nav>
			<ul class="main-nav">
				<li class="home"><a href="<%=request.getContextPath() %>">HOME</a></li>
				<li class="notice"><a href="<%=request.getContextPath() %>">공지사항</a></li>
				<li class="board"><a href="<%=request.getContextPath() %>">게시판</a></li>
				<li class="photo-board"><a
					href="<%=request.getContextPath() %>">갤러리</a></li>
				<% if(loginMamber !=null&&"admin".equals(loginMember.getUserId())) { %>
				<li id="admin-member"><a
					href="<%=request.getContextPath() %>/admin/MemberList"> 회원관리<span
						class="animate_line"></span></a></li>
				<%  
				} 
				%>
			</ul>
		</nav>
	</header>