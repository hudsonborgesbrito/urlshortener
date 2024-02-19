package com.studyjavaee.servlet;


import com.studyjavaee.service.URLShortenerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "BaseServlet", value = "/")
public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String shortURLCode = request.getServletPath().substring(1);
        try {
            if(!isValidShortURLCode(shortURLCode)) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "The URL informed is invalid");
            }else {
                response.sendRedirect(URLShortenerService.findLongURL(shortURLCode));
            }
        }
        catch( IllegalArgumentException illegalArgumentException){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "The URL informed is could not be found in our records");
        }
        catch (IOException e){
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "The Original URL cannot be opened");
        }
    }

    boolean isValidShortURLCode(String shortURLCode){
        if(shortURLCode == null || shortURLCode.isEmpty()){
            return false;
        }
        String regex = "^[a-zA-Z0-9]{4,8}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(shortURLCode);
        return matcher.matches();
    }
}