package cdu.jhc.listener;

import cdu.jhc.model.AdminUser;
import cdu.jhc.model.User;
import cdu.jhc.util.ParseUtil;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


@WebListener
public class AdminLogListener implements ServletContextListener, HttpSessionAttributeListener,HttpSessionListener {
    File logFile;
    FileWriter fileWriter;
    BufferedWriter bufferedWriter;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //监听web应用程序启动事件

        //网络启动时，确认保存文件的日志目录是否存在
        String logDirPath = sce.getServletContext().getRealPath("/log");
        File logDir = new File(logDirPath);
        if(!logDir.exists()){
            System.out.println("日志目录不存在，新建：" + logDir.mkdir());
        }
        if(!logDir.isDirectory()){
            System.out.println("日志目录有误，删除：" + logDir.delete());
            System.out.println("日志目录重建：" + logDir.mkdir());
        }
        //创建日志文件
        logFile = new File(logDirPath + "/" + ParseUtil.today() + ".txt");
        //打开日志文件，准备记录日志
        try{
            fileWriter = new FileWriter(logFile,true);
            bufferedWriter = new BufferedWriter(fileWriter);
        }catch (Exception e){
            System.out.println("日志文件打开异常：" + e.getMessage());
        }
        System.out.println("观察contextInitialized事件源：" + sce.getSource().getClass());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 监听web应用程序停止运行事件
        // 网站关闭时，停止日志记录
        try{
            bufferedWriter.close();
            fileWriter.close();
        }catch (IOException e){
            System.out.println("日志文件关闭异常：" + e.getMessage());
        }
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        //监听会话范围中添加属性的事件
        //管理员登录时会在会话范围内保存登录管理员的信息，此处用于在日志文件中记录管理员登录的时间
        if(se.getName().equals("admin")){
            AdminUser admin = (AdminUser) se.getValue();
            log(admin,"登录");
            System.out.println("观察attributeAdded事件源：" + se.getSource().getClass());
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        // 监听会话范围中action属性的值发生改变的事件
        // 此处用于在日志文件中记录管理员添加图书、重置顾客密码、回复咨询等操作时间
        AdminUser admin = (AdminUser) se.getSession().getAttribute("admin");
        String action = (String) se.getSession().getAttribute("action");
        log(admin,action);
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // 监听会话创建事件
        // 会话创建时，添加action属性，用于记录管理员除登录、注销外的其他行为，比如添加图书、回复咨询等
        se.getSession().setAttribute("action","");
        System.out.println("观察sessionCreated事件源：" + se.getSource().getClass());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // 监听会话销毁事件
        // 会话失效时，在日志文件中记录管理员注销时间
        AdminUser admin = (AdminUser) se.getSession().getAttribute("admin");
        log(admin,"注销");
    }

    private void log(User user,String action){
        if(user == null || action == null){
            return;
        }
        String info = user.getName() + "(" + user.getId() + ")" + action + " : " + ParseUtil.now() + "\n";

        try{
            bufferedWriter.append(info);
            bufferedWriter.flush();
        }catch (IOException e){
            System.out.println("日志写入异常：" + e.getMessage());
        }
    }
}
