package ru.ttv.cloudstorage.webapp.webfilter;

import lombok.SneakyThrows;

import javax.faces.application.ResourceHandler;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Timofey Teplykh
 */
@WebFilter("/*")
public class StartFilter implements Filter {

    @Override
    @SneakyThrows
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String relativeRequestURI = request.getRequestURI().substring(request.getContextPath().length());

        boolean resourceExists = request.getServletContext().getResource(relativeRequestURI) != null;
        boolean facesResourceRequest = request.getRequestURI().startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER);
        boolean wsRequest = request.getRequestURI().startsWith(request.getContextPath() + "/ws");

        if (facesResourceRequest || wsRequest) {
            filterChain.doFilter(request, response);
            return;
        }
        if(!resourceExists){
            response.sendRedirect(request.getContextPath());
            return;
        }

        request.getRequestDispatcher("/cloudstorage.xhtml").forward(request, response);
    }

    @Override
    public void destroy() {

    }

}