package cn.superid.web.controller;

import cn.superid.Constants;
import cn.superid.dao.Form.SimpleResponse;
import cn.superid.dao.Form.UserForm;
import cn.superid.entity.UserEntity;
import cn.superid.jcaptcha.JCaptcha;
import cn.superid.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by peng on 2014/9/18.
 */
@Controller
public class testController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public SimpleResponse showLoginForm(UserForm userForm) {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return new SimpleResponse(0, "You have login");//已经登录过
        }
        try {
            SecurityUtils.getSubject().login(new UsernamePasswordToken(userForm.getUsername(), userForm.getPassword()));
        } catch (UnknownAccountException e) {
            return new SimpleResponse(1, "username/password error");
        } catch (IncorrectCredentialsException e) {
            return new SimpleResponse(2, "username/password error");
        } catch (ExcessiveAttemptsException e) {
            return new SimpleResponse(3, "ExcessiveAttempts");
        } catch (Exception e) {
            return new SimpleResponse(4, e.getMessage());
        }
        return new SimpleResponse(0, "success");
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public SimpleResponse register(HttpServletRequest request) {
        if(!JCaptcha.validateResponse(request,request.getParameter("validationCode")))
        {
            return new SimpleResponse(1,"validationCode error");
        }
        return null;
    }


    @RequestMapping(value = "/alliance/{allianceId}/chat/{userId}", method = RequestMethod.POST)
    public SimpleResponse Chat(@PathVariable int allianceId, @PathVariable int userId, HttpServletRequest request) {
        String type = request.getParameter("type");
        StringBuffer sb = new StringBuffer();
        sb.append(allianceId);
        sb.append(":");
        sb.append(type);
        sb.append(":chat:");
        sb.append(userId);
        return SecurityUtils.getSubject().isPermitted(sb.toString()) ?
                new SimpleResponse(0, Constants.SUCCESS) : new SimpleResponse(0, Constants.NO_PERMISSION);

    }

    @RequestMapping(value = "/jCaptcha.jpg", method = RequestMethod.GET)
    public void ValidationCode(HttpServletRequest request, HttpServletResponse response) {
        response.setDateHeader("Expires", 0L);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String id = request.getRequestedSessionId();
        BufferedImage bi = JCaptcha.captchaService.getImageChallengeForID(id);
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            ImageIO.write(bi, "jpg", out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
