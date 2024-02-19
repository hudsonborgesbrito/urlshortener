package com.studyjavaee.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebFilter(urlPatterns={"/*"})
public class URLShortenerFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(URLShortenerFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long before = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        long after = System.currentTimeMillis();
        String path = ((HttpServletRequest)servletRequest).getRequestURI();
        LOGGER.log(Level.FINE, () -> String.format("%s : %s ms", path, (after - before)));
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
