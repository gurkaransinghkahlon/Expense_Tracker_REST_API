package com.gurkaran.expensetrackerapi.filters;

import com.gurkaran.expensetrackerapi.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String authHeader = httpRequest.getHeader("Authorization");
        if(authHeader != null){
            String authHeaderarr[] = authHeader.split("Bearer");
            if(authHeaderarr.length >1 && authHeaderarr[1] != null ){
                String token= authHeaderarr[1];
                try{
                    Claims claims = Jwts.parser().setSigningKey(Constants.API_SECRET_KEY)
                            .parseClaimsJws(token).getBody();
                    httpRequest.setAttribute("userId",Integer.parseInt(claims.get("userId").toString()));
                }

                catch(Exception e){
                    httpResponse.sendError(HttpStatus.FORBIDDEN.value(),"invalid/expired token");
                    return;
                }
            } else {
                httpResponse.sendError(HttpStatus.FORBIDDEN.value(),"Authorization Token must be Bearer [token]");
                return;
            }
        }
        else{
            httpResponse.sendError(HttpStatus.FORBIDDEN.value(),"Authorization Token must be provided");
            return;
        }
        chain.doFilter(request,response);
    } //always execute first

}
