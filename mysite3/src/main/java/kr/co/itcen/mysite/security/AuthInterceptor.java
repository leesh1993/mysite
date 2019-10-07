package kr.co.itcen.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.co.itcen.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(
		HttpServletRequest request,
		HttpServletResponse response,
		Object handler)
		throws Exception {
		
		//1. handler 종류(DefaultServletHttpRequestHandler, HandlerMethod) 
		if( handler instanceof HandlerMethod == false ) {
			return true;
		}
		
		//2. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		//3. @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
	
		//4. @Auth가 없으면 class type에 있을 수 있으므로...
		if(auth == null) {
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
			
			if(auth == null) {		
				return true;
			}
					
		}

		//5. @Auth가 class나 method에 붙어 있기 때문에 인증 여부를 체크한다.
		HttpSession session = request.getSession();
		
		if(session == null || session.getAttribute("authUser") == null) {
			response.sendRedirect(request.getContextPath()+ "/user/login");
			return false;
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		//6. Method의 @Auth의 Role 가져오기
		String role = auth.role().toString();

		//7. 메소드의 @Auth의 Role이 "USER"인 경우.
		//   인증만 되어 있으면 모두 통과
		if("USER".equals(role)) {
			if("USER".equals(authUser.getRole())){
				return true;
			}
		}
		
		//8. 메소드의 @Auth의 Role이 "ADMIN"인 경우
		// -- 과제
		if("ADMIN".equals(role)) {
			if("ADMIN".equals(authUser.getRole())){
				return true;
			}
		}
		
		return false;
	}

}