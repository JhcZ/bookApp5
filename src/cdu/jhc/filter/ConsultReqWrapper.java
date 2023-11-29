package cdu.jhc.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.util.List;

public class ConsultReqWrapper extends HttpServletRequestWrapper {
    List<String> stopwords = null;
    public ConsultReqWrapper(HttpServletRequest request, List<String> stopwords) {
        super(request);
        this.stopwords = stopwords;
    }
    @Override
    public String getParameter(String name) {
        String text = getRequest().getParameter(name);
        if (name.equals("question") || name.equals("reply")) {
            if (text != null) {
                System.out.println("原始text: " + text);
                text = text.replaceAll(" ", "&nbsp;");
                text = text.replaceAll("\n", "<br>");
                for (String s : stopwords) {
                    text = text.replaceAll(s, "*");
                }
                System.out.println("过滤后：" + text);
            }
        }
        return text;
    }
}
