package io.github.edsuns.util;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Edsuns@qq.com on 2022/10/29.
 */
@ParametersAreNonnullByDefault
public class HttpServlets {

    /**
     * 特别注意，必须在代理服务器（如Nginx）覆盖客户端发出的`X-Forwarded-xxx`请求头，以防止伪造
     */
    public static String getClientIpAddress(@Nullable HttpServletRequest request) {
        if (request == null) {
            return "0.0.0.0";
        }
        String ipList = request.getHeader("X-Forwarded-For");
        if (ipList != null && ipList.length() > 0 && !"unknown".equalsIgnoreCase(ipList)) {
            int index = ipList.indexOf(',');
            if (index > 0) {
                return ipList.substring(0, index);
            }
            return ipList;
        }
        return request.getRemoteAddr();
    }
}
