package cn.superid.jcaptcha;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.FunkyBackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomListColorGenerator;
import com.octo.captcha.component.image.color.RandomRangeColorGenerator;
import com.octo.captcha.component.image.deformation.ImageDeformation;
import com.octo.captcha.component.image.deformation.ImageDeformationByFilters;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.fontgenerator.TwistedAndShearedRandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.LineTextDecorator;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.DeformedComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.FileDictionary;
import com.octo.captcha.component.word.wordgenerator.ComposeDictionaryWordGenerator;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;

import java.awt.*;
import java.awt.image.ImageFilter;

/**
 * from https://code.google.com/p/musicvalley/source/browse/trunk/musicvalley/doc/springSecurity/springSecurityIII/src/main/java/com/spring/security/jcaptcha/GMailEngine.java?spec=svn447&r=447
 * JCaptcha验证码图片生成引擎, 仿照JCaptcha2.0编写类似GMail验证码的样式.
 */
public class GMailEngine extends ListImageCaptchaEngine {

    @Override
    protected void buildInitialFactories() {
        //word generator
        WordGenerator wgen = new RandomWordGenerator("ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789");
        RandomRangeColorGenerator colors = new RandomRangeColorGenerator(
                new int[] { 0, 150 }, new int[] { 0, 150 },
                new int[] { 0, 150 });

        // Arial,Tahoma,Verdana,Helvetica,宋体,黑体,幼圆
        Font[] fonts = new Font[] { new Font("Arial", 0, 15),
                new Font("Tahoma", 0, 15), new Font("Verdana", 0, 15),
                new Font("Helvetica", 0, 15), new Font("宋体", 0, 15),
                new Font("黑体", 0, 15), new Font("幼圆", 0, 15) };

        // 设置字符以及干扰线
        RandomRangeColorGenerator lineColors = new RandomRangeColorGenerator(
                new int[] { 150, 255 }, new int[] { 150, 255 }, new int[] {
                150, 255 });
        TextPaster randomPaster = new DecoratedRandomTextPaster(new Integer(4),
                new Integer(4), colors, true,
                new TextDecorator[] { new LineTextDecorator(new Integer(1),
                        lineColors) });
       // BackgroundGenerator back = new UniColorBackgroundGenerator(new Integer( 140), new Integer(45), Color.white);
        BackgroundGenerator back = new FunkyBackgroundGenerator
                (new Integer(100), new Integer(50));
        FontGenerator shearedFont = new RandomFontGenerator(new Integer(30),
                new Integer(0), fonts);

        com.octo.captcha.component.image.wordtoimage.WordToImage word2image =
                new com.octo.captcha.component.image.wordtoimage.ComposedWordToImage(
                        shearedFont, back, randomPaster);
        this.addFactory(
                new com.octo.captcha.image.gimpy.GimpyFactory(wgen,
                        word2image));
    }

}