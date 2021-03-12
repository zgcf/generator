/**
 *    Copyright 2006-2020 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
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
