package cn.superid.web.controller;

import cn.superid.Constants;
import cn.superid.dao.Form.SimpleResponse;
import cn.superid.dao.Form.UserForm;
import cn.superid.entity.UserEntity;
import cn.superid.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by peng on 2014/9/18.
 */
@Controller
public class testController {
    @Autowired
    private IUserService userService;
    @RequestMapping(value = "/login" ,method = RequestMethod.POST)
    public SimpleResponse showLoginForm(UserForm userForm) {
        if(SecurityUtils.getSubject().isAuthenticated()) {
            return new SimpleResponse(0,"You have login");//已经登录过
        }
        try {
            SecurityUtils.getSubject().login(new UsernamePasswordToken(userForm.getUsername(), userForm.getPassword()));
        }catch (UnknownAccountException e){
            return new SimpleResponse(1,"username/password error");
        }catch (IncorrectCredentialsException e){
            return new SimpleResponse(2,"username/password error");
        }catch (ExcessiveAttemptsException e) {
            return new SimpleResponse(3,"ExcessiveAttempts");
        }catch (Exception e){
            return new SimpleResponse(4,e.getMessage());
        }
        return new SimpleResponse(0,"success");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public UserEntity register(UserForm uf){
       return userService.createUser(uf);
    }

    @RequestMapping(value = "/alliance/{allianceId}/chat/{userId}",method = RequestMethod.POST)
    public SimpleResponse Chat(@PathVariable int allianceId,@PathVariable int userId,HttpServletRequest request){
        String type = request.getParameter("type");
        StringBuffer sb = new StringBuffer();
        sb.append(allianceId);
        sb.append(":");
        sb.append(type);
        sb.append(":chat:");
        sb.append(userId);
        return   SecurityUtils.getSubject().isPermitted(sb.toString())?
                    new SimpleResponse(0,Constants.SUCCESS):new SimpleResponse(0,Constants.NO_PERMISSION);

    }
}
