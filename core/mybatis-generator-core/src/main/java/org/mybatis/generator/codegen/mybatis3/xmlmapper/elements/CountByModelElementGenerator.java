package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

/**
 * @author gazhou
 * @version 1.0
 * @date 2020/11/16 16:20
 */
public class CountByModelElementGenerator extends AbstractXmlElementGenerator{
    public CountByModelElementGenerator(){
        super();
    }
    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("select");
        answer.addAttribute(new Attribute("id", introspectedTable.getSelectByModelStatementId()));
        answer.addAttribute(new Attribute("resultType", "java.lang.Integer"));

        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType(
                introspectedTable.getSearchRecordType());
        answer.addAttribute("parameterType",parameterType.getFullyQualifiedName());

        StringBuilder sb = new StringBuilder();
        sb.append("select count(*) from ");
        sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());

        answer.addElement(new TextElement(sb.toString()));
        answer.addElement(getSelectByModeWhereElement());


        parentElement.addElement(answer);
    }
}
