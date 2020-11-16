package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * @author gazhou
 * @version 1.0
 * @date 2020/11/16 15:48
 */
public class BaseChooseOrderByElementgenerator extends AbstractXmlElementGenerator {
    public  BaseChooseOrderByElementgenerator(){
        super();
    }
    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("sql");
        answer.addAttribute(new Attribute("id", introspectedTable.getBaseChooseOrderById()));

        XmlElement chooseElement = new XmlElement("choose");

        XmlElement whenElement = new XmlElement("when");
        whenElement.addAttribute("test",introspectedTable.getOrderFlagName() + " == 0");
        chooseElement.addElement(whenElement);

        XmlElement whenAscElement = new XmlElement("when");
        whenAscElement.addAttribute("test",introspectedTable.getOrderFlagName() + " == 1");
        whenAscElement.addElement(new TextElement("order by ${"+ introspectedTable.getOrderFieldName()+"} asc"));
        chooseElement.addElement(whenAscElement);

        XmlElement whenDescElement = new XmlElement("when");
        whenDescElement.addAttribute("test",introspectedTable.getOrderFlagName() + " == 2");
        whenDescElement.addElement(new TextElement("order by ${"+ introspectedTable.getOrderFieldName()+"} desc"));
        chooseElement.addElement(whenDescElement);

        XmlElement whenOtherElement = new XmlElement("when");
        whenOtherElement.addAttribute("test",introspectedTable.getOrderFlagName() + " == -1");
        whenOtherElement.addElement(new TextElement("order by ${"+ introspectedTable.getOrderFieldName()+"}"));
        chooseElement.addElement(whenOtherElement);

        XmlElement otherwiseElement = new XmlElement("otherwise");
        chooseElement.addElement(otherwiseElement);

        answer.addElement(chooseElement);

        parentElement.addElement(answer);
    }
}
