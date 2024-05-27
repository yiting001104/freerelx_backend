package tw.team.project.util;

import java.io.IOException;

import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(
		urlPatterns = {"/hotel/products/find"}
)
public class JsonWebTokenFilter implements Filter {
	private JsonWebTokenUtility jsonWebTokenUtility;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext application = filterConfig.getServletContext();
		ApplicationContext context = (ApplicationContext) application.getAttribute(
				WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		this.jsonWebTokenUtility = context.getBean("jsonWebTokenUtility", JsonWebTokenUtility.class);
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String method = request.getMethod();
		if(!"OPTIONS".equals(method)) {
			//是否有"已登入"的資訊
			String auth = request.getHeader("Authorization");
			System.out.println("auth"+auth);
			JSONObject user = processAuthorizationHeader(auth);
			System.out.println(user);
			System.out.println("===============");
			if(user==null || user.length()==0) {
				//沒有：是否要阻止使用者呼叫？
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				response.addHeader("Access-Control-Allow-Credentials", "true");
				response.addHeader("Access-Control-Allow-Origin", "*");
				response.addHeader("Access-Control-Allow-Headers", "*");
                return;
			}
		}
		chain.doFilter(request, response);
	}
    private JSONObject processAuthorizationHeader(String auth) {
        if(auth!=null && auth.length()!=0) {
            String token = auth.substring(7);           //Bearer
            String data = jsonWebTokenUtility.validateEncryptedToken(token);
            if(data!=null && data.length()!=0) {
                return new JSONObject(data);
            }
        }
        return null;
    }
}
