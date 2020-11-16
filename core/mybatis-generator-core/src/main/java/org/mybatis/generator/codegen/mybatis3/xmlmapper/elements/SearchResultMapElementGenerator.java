package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;

import java.util.List;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

/**
 * @author gazhou
 * @version 1.0
 * @date 2020/11/13 17:24
 */
public class SearchResultMapElementGenerator extends AbstractXmlElementGenerator{
    public SearchResultMapElementGenerator() {
        super();
    }
    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("resultMap");

        answer.addAttribute(new Attribute("id", introspectedTable.getSearchResultMapId()));

        String returnType = introspectedTable.getSearchRecordType();
        answer.addAttribute(new Attribute("type", returnType));


        String extendsName;
        if (introspectedTable.getRules().generateRecordWithBLOBsClass()) {
            extendsName = introspectedTable.getResultMapWithBLOBsId();
        } else {
            // table has BLOBs, but no BLOB class - BLOB fields must be
            // in the base class
            extendsName = introspectedTable.getBaseResultMapId();
        }

        answer.addAttribute(new Attribute("extends", extendsName));

        context.getCommentGenerator().addComment(answer);

        if (introspectedTable.isConstructorBased()) {
            addResultMapConstructorElements(answer);
        } else {
            addResultMapElements(answer);
        }

        if (context.getPlugins()
                .sqlMapResultMapWithBLOBsElementGenerated(answer,
                        introspectedTable)) {
            parentElement.addElement(answer);
        }
    }

    private void addResultMapElements(XmlElement answer) {

    }

    private void addResultMapConstructorElements(XmlElement answer) {

    }
}
