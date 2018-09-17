package com.demo.test;

import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet("/LoginUserServlet")
public class LoginUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginUserServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // response.setContentType("text/html");
        // 设置字符编码为UTF-8, 这样支持汉字显示
        // response.setCharacterEncoding("UTF-8");

        response.setContentType("text/html;charset=utf-8");

        /** 设置响应头允许ajax跨域访问 **/
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        JSONObject json=JsonReader.receivePost(request);
        System.out.println("获取数据"+json);

        Writer out = response.getWriter();
        JSONObject object = new JSONObject();
        object.put("message","接收数据成功！！");
        out.write(object.toString());
        out.flush();
        //UserService userService=new UserService();

        //将建json对象转换为java对象
//        User loginUser = (User)JSONObject.toBean(json,User.class);
//        User user = userService.LoginUser(loginUser);
//        JSONObject jsonObject=new JSONObject() ;
//        if(user!=null){
//            //将java对象转换为json对象
//            jsonObject.put("user", JSONObject.fromObject(user));
//            jsonObject.put("message", "用户登录成功！");
//        }else{
//
//            jsonObject.put("message", "用户登录失败！");
//        }
//        out.write(jsonObject.toString());
//        out.flush();
    }
}
