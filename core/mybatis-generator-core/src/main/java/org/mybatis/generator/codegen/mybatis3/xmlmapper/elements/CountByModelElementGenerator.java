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
        answer.addAttribute(new Attribute("id", introspectedTable.getCountByModelStatementId()));
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
