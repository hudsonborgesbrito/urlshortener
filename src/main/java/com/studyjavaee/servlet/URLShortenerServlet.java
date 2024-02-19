package com.studyjavaee.servlet;

import com.studyjavaee.entity.ShortenerURLMapping;
import com.studyjavaee.service.URLShortenerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "URLShortenerServlet", value = "/shortener")
public class URLShortenerServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(URLShortenerServlet.class.getName());
    public static final String LONG_URL = "longURL";
    public static final String SHORT_URL = "shortURL";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("./");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String longUrl = request.getParameter(LONG_URL);
        if(!isValidURL(longUrl)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "The URL informed is invalid");
        }
        ShortenerURLMapping shortenerURLMapping = URLShortenerService.createShortURL(longUrl);
        LOGGER.log(Level.FINE, "Short URL created: {0}", shortenerURLMapping);
        request.setAttribute(SHORT_URL, generateFullShortenedURL(request, shortenerURLMapping.getCode()));
        request.setAttribute(LONG_URL, shortenerURLMapping.getOriginalUrl());
        request.getRequestDispatcher("shorter_result.jsp").forward(request, response);
    }

    boolean isValidURL(String url){
        try {
            if(url == null || !url.startsWith("http")){
                return false;
            }
            URI.create(url).toURL();
            return true;
        }catch (MalformedURLException | NullPointerException | IllegalArgumentException e) {
            LOGGER.log(Level.INFO, "Invalid URL: {0}", url);
            return false;
        }
    }

    private String generateFullShortenedURL(HttpServletRequest request, String shortURLCode){
        return request.getRequestURL().toString().replace(request.getServletPath(), "/") +
                shortURLCode;
    }
}