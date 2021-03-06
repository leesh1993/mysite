<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@page import="kr.co.itcen.mysite.repository.GuestbookDao"%>
<%@page import="kr.co.itcen.mysite.vo.GuestbookVo"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% pageContext.setAttribute("newline","\n"); %>

<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<form:form
					modelAttribute="guestbookVo"
					action="${pageContext.servletContext.contextPath }/guestbook/insert"
					method="post">
					<table>
						<tr>
							<td>이름</td>
							<td><form:input type="text" path="name"/></td>
							
							<spring:hasBindErrors name="guestbookVo">
								<c:if test='${errors.hasFieldErrors("name") }'>
									<p style="font-weight:bold; color:red; text-align:left; padding-left:0">
										<spring:message code='${errors.getFieldError("name").codes[0] }' text='${errors.getFieldError("name").defaultMessage }' />
									</p>
								</c:if>
							</spring:hasBindErrors>
					
							<td>비밀번호</td>
							<td><form:input type="password" path="password"/></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="contents" cols=70 rows=5></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form:form>
				<ul>
					<li><br> <c:set var="count" value="${fn:length(list) }" />
						<c:forEach items="${list }" var="vo" varStatus="status">
							<br>
							<table width=510 border=1>
								<tr>
									<td>[${count-status.index }]</td>
									<td>${vo.name }</td>
									<td>${vo.reg_date }</td>
									<td><a
										href="${pageContext.servletContext.contextPath }/guestbook/delete/${vo.no}">삭제</a></td>
								</tr>
								<tr>
									<td colspan=4>${fn:replace(vo.contents, newline, "<br>") }
									</td>
								</tr>
							</table>
						</c:forEach> <br></li>
				</ul>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>