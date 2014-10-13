
package cn.superid.jcaptcha;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.captchastore.CaptchaStore;
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;


public class JCaptcha {
    @Autowired(required = true)
    private  DefaultManageableImageCaptchaService captchaService;
    //new DefaultManageableImageCaptchaService(new FastHashMapCaptchaStore(), new GMailEngine(), 180, 100000, 75000);;

    private JCaptcha(){
    }

    public  DefaultManageableImageCaptchaService getCaptchaService() {
        return this.captchaService;
    }

    public  boolean validateResponse(HttpServletRequest request, String userCaptchaResponse) {
        if (request.getSession(false) == null) return false;
        boolean validated = false;
        try {
            String id = request.getSession().getId();
            validated = this.captchaService.validateResponseForID(id, userCaptchaResponse.toUpperCase()).booleanValue();
        } catch (CaptchaServiceException e) {
            e.printStackTrace();
        }
        return validated;
    }

}
