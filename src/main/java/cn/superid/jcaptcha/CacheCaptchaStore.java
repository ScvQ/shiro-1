package cn.superid.jcaptcha;

import com.octo.captcha.Captcha;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.captchastore.CaptchaAndLocale;
import com.octo.captcha.service.captchastore.CaptchaStore;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Locale;

/**
 * Created by peng on 2014/9/25.
 */
public class CacheCaptchaStore implements CaptchaStore {
    private Cache<String,CaptchaAndLocale> store;

    public CacheCaptchaStore(CacheManager cacheManager){
         store = cacheManager.getCache("jCaptchaCache");
     }

    @Override
    public boolean hasCaptcha(String s) {
       return store.get(s)!=null;
    }

    @Override
    public void storeCaptcha(String s, Captcha captcha) throws CaptchaServiceException {
        store.put(s,new CaptchaAndLocale(captcha));
    }

    @Override
    public void storeCaptcha(String s, Captcha captcha, Locale locale) throws CaptchaServiceException {
        store.put(s,new CaptchaAndLocale(captcha,locale));
    }

    @Override
    public boolean removeCaptcha(String s) {
        if(store.get(s)!=null){
            store.remove(s);
            return true;
        }
        return false;
    }

    @Override
    public Captcha getCaptcha(String s) throws CaptchaServiceException {
        return store.get(s)!=null?store.get(s).getCaptcha():null;
    }

    @Override
    public Locale getLocale(String s) throws CaptchaServiceException {
        return store.get(s)!=null?store.get(s).getLocale():null;
    }

    @Override
    public int getSize() {
        return store.size();
    }

    @Override
    public Collection getKeys() {
        return store.keys();
    }

    @Override
    public void empty() {
         store.clear();
    }
    @Override
    public void initAndStart() {

    }

    @Override
    public void cleanAndShutdown() {
        store.clear();
    }
}
