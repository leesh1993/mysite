<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<div id="navigation">
			<ul>
				<li><a href="<%=request.getContextPath() %>">이성훈</a></li>
				<li><a href="<%=request.getContextPath() %>/guestbook/list">방명록</a></li>
				<li><a href="<%=request.getContextPath() %>/board/list/${1 }">게시판</a></li>
			</ul>
		</div>