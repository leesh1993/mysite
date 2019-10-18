<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="container">
		<h2>
			죄송합니다.<br />요청하신 페이지를 찾을 수 없습니다.
		</h2>
		<div class="content">
			<p>
				방문하시려는 페이지의 주소가 잘못 입력되었거나,<br />페이지의 주소가 변경 혹은 삭제되어 요청하신 페이지를 찾을 수
				없습니다.
			</p>
			<p>입력하신 주소가 정확한지 다시 한번 확인해 주시기 바랍니다.</p>
			<p>
				관련 문의사항은 <a
					onclick="window.open('https://help.naver.com/support/alias/contents2/naverhome/notfound.naver', 'help_naver', 'left=40,top=60,width=650,height=800,toolbar=1,resizable=0'); return false;">네이버
					고객센터</a>에 알려주시면 친절하게 안내해 드리겠습니다.
			</p>
			<p>감사합니다.</p>
		</div>
		<form class="search" style="margin-top: 50px;" name="search"
			action="http://search.naver.com/search.naver" method="get"
			onsubmit="emulAcceptCharset(this);" accept-charset="ks_c_5601-1987">
			<input type="hidden" name="sm" value="nnf_hty">
			<fieldset class="window02">
				<legend>네이버 검색으로 원하시는 페이지를 찾으실 수 있습니다.</legend>
				<input type="text" title="검색" name="query" maxlength="255" value=""
					class="box_window" accesskey="s"> <input
					src="https://s.pstatic.net/static/w8/err/btn_sch.gif"
					onmouseover="this.src='https://s.pstatic.net/static/w8/err/btn_sch_over.gif'"
					onmouseout="this.src='https://s.pstatic.net/static/w8/err/btn_sch.gif'"
					alt="검색" type="image" class="btn_window">
			</fieldset>
		</form>
	</div>
</body>
</html>