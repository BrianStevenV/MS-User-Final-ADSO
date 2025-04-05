package com.example.demo.config.security.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.AntPathMatcher;

import java.util.List;

import static com.example.demo.config.security.utils.ConstantsSecurity.AUTHORIZATION_HEADER;
import static com.example.demo.config.security.utils.ConstantsSecurity.AUTHORIZATION_HEADER_SUBSTRING;
import static com.example.demo.config.security.utils.ConstantsSecurity.BEARER_TOKEN;

public class SecurityUtils {
    private SecurityUtils() {
    }

    private static AntPathMatcher pathMatcher = new AntPathMatcher();

    public static String getToken(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION_HEADER);
        if (header != null && header.startsWith(BEARER_TOKEN)) {
            return header.substring(AUTHORIZATION_HEADER_SUBSTRING);
        }
        return null;
    }

    public static boolean isExcludedPrefixRecursively(String currentRoute, List<String> prefixes) {
        if (prefixes.isEmpty()) {
            return false;
        }

        String prefix = prefixes.get(0);
        if (pathMatcher.matchStart(prefix, currentRoute)) {
            return true;
        } else {

            return isExcludedPrefixRecursively(currentRoute, prefixes.subList(1, prefixes.size()));
        }
    }
}
