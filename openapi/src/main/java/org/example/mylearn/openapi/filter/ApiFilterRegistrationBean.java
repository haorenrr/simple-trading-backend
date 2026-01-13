package org.example.mylearn.openapi.filter;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.example.mylearn.common.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Component
public class ApiFilterRegistrationBean extends FilterRegistrationBean<Filter>{
    static final Logger logger = LoggerFactory.getLogger(ApiFilterRegistrationBean.class);

    @PostConstruct
    public void init() {
        setOrder(20);
        setFilter(new ApiFilter());
        setUrlPatterns(List.of("/api/*"));
        logger.info("{} init over.", this.getClass().getName());
    }

    class ApiFilter implements Filter {
        @Override
        public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
                throws IOException, ServletException {
            // 尝试认证用户:
            HttpServletRequest httpRequest = (HttpServletRequest) req;
            String authHeader = httpRequest.getHeader("Authorization");
            logger.trace("runing into {}, get authHeader:{}", this.getClass().getName(), authHeader);
            String userId = authHeader == null ? null : parseUserFromAuthorization(authHeader);
            if (userId == null) {
                chain.doFilter(req, resp); // 匿名身份:
            } else {
                // 用户身份:
                try (UserContext ctx = new UserContext(userId)) {
                    chain.doFilter(req, resp);
                }
            }
        }

        private String parseUserFromAuthorization(String authHeader) {
            var realAuthHeader = authHeader.stripLeading();
            if (realAuthHeader.startsWith("Basic ")) {
                // 用Base64解码:
                //String eap = new String(Base64.getDecoder().decode(realAuthHeader.substring(6).trim()));
                String eap = realAuthHeader.substring(6).trim(); // 测试阶段，使用明文
                // 分离email:password
                int pos = eap.indexOf(':');
                String userID = eap.substring(0, pos);
                String passwd = eap.substring(pos + 1);
                // 验证:
                //UserProfileEntity p = userService.signin(userID, passwd);
                logger.trace("userID='{}', passwd='{}'", userID, passwd);
                return userID.isEmpty()? null : userID;
            }
            return null;
        }
    }
}
