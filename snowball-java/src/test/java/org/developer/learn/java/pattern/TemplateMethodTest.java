package org.developer.learn.java.pattern;

import org.developer.learn.java.design.pattern.templateMethod.AbstractDisplay;
import org.developer.learn.java.design.pattern.templateMethod.CharDisplay;
import org.developer.learn.java.design.pattern.templateMethod.StringDisplay;
import org.junit.Test;

/**
 * @ClassName TemplateMethodTest
 * @Description:
 * @Author lfq
 * @Date 2020/11/2
 **/
public class TemplateMethodTest {

    @Test
    public void display() {
        AbstractDisplay d1 = new CharDisplay('H');
        AbstractDisplay d2 = new StringDisplay("hello world");
        AbstractDisplay d3 = new StringDisplay("这是个美丽的世界");

        d1.display();
        d2.display();
        d3.display();
    }
}
