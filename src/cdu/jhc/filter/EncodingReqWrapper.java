package cdu.jhc.filter;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.io.UnsupportedEncodingException;

public class EncodingReqWrapper extends HttpServletRequestWrapper {
    public EncodingReqWrapper(HttpServletRequest request){
        super(request);
    }

    @Override
    public String getParameter(String name) {
        String value = getRequest().getParameter(name);
        if(value != null && !value.equals("")){
            try{
                return new String(value.getBytes("iso-8859-1"),"utf-8");
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
        }
        return value;
    }
}
