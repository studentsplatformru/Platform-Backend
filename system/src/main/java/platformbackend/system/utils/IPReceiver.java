package platformbackend.system.utils;

import javax.servlet.http.HttpServletRequest;

public class IPReceiver {
    public static String getIp(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getRemoteAddr();
    }
}
