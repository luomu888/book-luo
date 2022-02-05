package com.luo.web;

import com.google.gson.Gson;
import com.luo.pojo.User;
import com.luo.service.UserService;
import com.luo.service.impl.UserServiceImpl;
import com.luo.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    private UserService userService=new UserServiceImpl();

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        boolean existsUsername = userService.existsUsername(username);
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("existsUsername",existsUsername);

        Gson gson=new Gson();
        String json=gson.toJson(resultMap);

        resp.getWriter().write(json);//����Ӧ���ķ�ʽ�ش�����
    }

    /**
     * ����ע��Ĺ���
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // ��ȡSession�е���֤��
        String token= (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // ɾ�� Session�е���֤��
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        //  1����ȡ����Ĳ���
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String email=req.getParameter("email");
        String code = req.getParameter("code");

        User user=WebUtils.copyParamToBean(req.getParameterMap(),new User());

        //  2����� ��֤���Ƿ���ȷ
        if (token!=null&&token.equalsIgnoreCase(code)){
//            3����� �û����Ƿ����
            if (userService.existsUsername(username)){
                System.out.println("�û���[" + username + "]�Ѵ���!");
                // �ѻ�����Ϣ�����浽Request����
                req.setAttribute("msg","�û����Ѵ���");
                req.setAttribute("username",username);
                req.setAttribute("email",email);
//                ����ע��ҳ��
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else {
                //����
                // ���浽���ݿ�
                userService.registUser(new User(null,username,password,email));
//                ����ע��ɹ�ҳ�� regist_success.jsp
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }else{
            // �ѻ�����Ϣ�����浽Request����
            System.out.println("��֤��[" + code + "]����");
            req.setAttribute("msg","��֤�����");
            req.setAttribute("username",username);
            req.setAttribute("email",email);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }

    }

    /**
     * �����¼�Ĺ���
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1����ȡ����Ĳ���
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        // ���� userService.login()��¼����ҵ��
        User loginUser=userService.login(new User(null,username,password,null));
        // �������null,˵����¼ ʧ��!
        if (loginUser==null){
            // �Ѵ�����Ϣ���ͻ��Եı�����Ϣ�����浽Request����
            req.setAttribute("msg","�û����������");
            req.setAttribute("username",username);
            //   ���ص�¼ҳ��
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else {
            // ��¼ �ɹ�
            // �����û���¼����Ϣ��Session����
            req.getSession().setAttribute("user",loginUser);
            //�����ɹ�ҳ��login_success.jsp
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }

    }

    /**
     * ע��
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1������Session���û���¼����Ϣ����������Session��
        req.getSession().invalidate();
//        2���ض�����ҳ�����¼ҳ�棩
        resp.sendRedirect(req.getContextPath());
    }
}
