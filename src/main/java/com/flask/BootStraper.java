package com.flask;

import com.flask.framework.DispatcherServlet;
import com.flask.mybatis.bean.User;
import com.flask.mybatis.session.SqlSession;
import com.flask.mybatis.session.SqlSessionFactory;
import com.flask.mybatis.session.SqlSessionFactoryBuilder;
import com.flask.mybatis.mapper.UserMapper;
import com.flask.servlet.ProjectConfigBean;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: 启动引导类
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/11/20
 */
public class BootStraper {
    /**
     * 工作空间 - 也就是 war 包的发布目录
     *
     * @param args
     */
    public static final String workSpace = "./";

    public static Map<Object, ProjectConfigBean> projectConfigBeans = new ConcurrentHashMap<Object, ProjectConfigBean>();

    public static void test() throws ServletException {
        DispatcherServlet t = new DispatcherServlet();
        t.init(new ServletConfig() {
            @Override
            public String getServletName() {
                return null;
            }

            @Override
            public ServletContext getServletContext() {
                return null;
            }

            @Override
            public String getInitParameter(String s) {
                return null;
            }

            @Override
            public Enumeration<String> getInitParameterNames() {
                return null;
            }
        });
    }

    public static void test2() {
        // build factory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build("application.properties");
        // 打开 SqlSession
        SqlSession session = sqlSessionFactory.openSession();
        System.out.println(session);

        // session.selectOne("com.flask.mybatis.mapper.UserMapper.getUserOne");
        // 读取 mapper 文件

        UserMapper userMapper = session.getMapper(UserMapper.class);
//        User u = userMapper.getUserOne();
//        System.out.println(u);

        User u2 = userMapper.getUser(2);
        System.out.println(u2);
    }

    public static void main(String[] args) throws ServletException {
        test2();

//        ProjectLoader projectLoader = null;
//        try {
//            projectLoader = new ProjectLoader("flask");
//            BootStraper.projectConfigBeans.put("flask", new ProjectConfigBean());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//
//        projectLoader.load();
//        // 1, 检查目录下是否有项目部署
//        Set<String> projects = ProjectChecker.check(workSpace);
//        if (projects != null) {
//            for (String project: projects) {
//                ProjectConfigBean projectConfigBean = new ProjectConfigBean();
//                projectConfigBeans.put(project,  projectConfigBean);
//            }
//
//        }
//
//        // 服务启动
//        WebServerStarter.start();
    }
}
