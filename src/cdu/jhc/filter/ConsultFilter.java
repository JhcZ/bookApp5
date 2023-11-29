package cdu.jhc.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "stopWordsFilter", urlPatterns = {"/customer/consult/add", "/admin/consult/reply"},
        initParams = @WebInitParam(name = "filePath",
                value = "WEB-INF/stopWords.txt"))
public class ConsultFilter implements Filter {
    List<String> stopwords = new ArrayList<>();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String path = filterConfig.getInitParameter("filePath");
        try {
            String realPath = filterConfig.getServletContext().getRealPath(path);
            File file = new File(realPath);
            if (file.exists()) {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line = bufferedReader.readLine();
                while (line != null) {
                    if (line.length() > 0) {
                        stopwords.add(line.trim());
                    }
                    line = bufferedReader.readLine();
                }
                bufferedReader.close();
                fileReader.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        filterChain.doFilter(new ConsultReqWrapper(req,stopwords),servletResponse);
    }
}
