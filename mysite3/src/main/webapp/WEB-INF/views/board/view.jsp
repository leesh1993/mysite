<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% pageContext.setAttribute("newline","\n"); %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js" type="text/javascript"></script>
<script>
$(function(){
	

	
	$("#btn-download-file").click(function(){
		alert("파일명 [${fileUploadVo.original_name}] 다운로드 시작하겠습니다. ");
		
		// ajax 통신
		$.ajax({
			url: "${pageContext.servletContext.contextPath }/api/board/download?titleNo=" + ${boardVo.no},
			type: "get",
			dataType: "json",
			data: ""
		});
	});
	
	
	
});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${boardVo.title }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${fn:replace(boardVo.contents,pageScope.newline, '<br>')} 
							</div>
						</td>
					</tr>
					<tr>
						<c:if test="${fileO eq 'fileO' }">
						<td class="label">파일업로드</td>
						<td>${fileUploadVo.original_name}
						<!-- <td style="cursor:hand" onClick="location.href='${pageContext.servletContext.contextPath }/board/filedownload'">${fileUploadVo.original_name} -->
						<input id="btn-download-file" type="button" value="다운로드">
						</td>
						</c:if>
					</tr>
				</table>
				<div class="bottom">
					<a href="${pageContext.servletContext.contextPath }/board/list/${cCount }">글목록</a>
					<c:if test="${loginO eq 'loginO'}">
					<a href="${pageContext.servletContext.contextPath }/board/boardreply/${boardVo.g_no }/${boardVo.o_no }/${boardVo.depth }/${cCount }">답글달기</a>
				    </c:if>
				    <c:if test="${boardUserNo eq authUserNo}">
					<a href="${pageContext.servletContext.contextPath }/board/modify/${boardVo.no }/${cCount }">글수정</a>
					</c:if>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>