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
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;

import java.util.Iterator;

/**
 * @author gazhou
 * @version 1.0
 * @date 2020/11/16 12:57
 */
public class BaseSelectWhereElementGenerator extends AbstractXmlElementGenerator{
    public BaseSelectWhereElementGenerator(){
        super();
    }
    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("sql");
        answer.addAttribute(new Attribute("id", introspectedTable.getBaseSelectWhereId()));

        context.getCommentGenerator().addComment(answer);

        for (IntrospectedColumn introspectedColumn : introspectedTable
                .getAllColumns()){
            XmlElement element = new XmlElement("if");
            String property = introspectedColumn.getJavaProperty();
            element.addAttribute("test",property + " != null and "+ property +"!= ''");
            StringBuilder sb = new StringBuilder();
            sb.append("and ");
            sb.append(MyBatis3FormattingUtilities.getRenamedColumnNameForResultMap(introspectedColumn));
            sb.append(" = ");
            sb.append("#{");
            sb.append(property);
            sb.append(",jdbcType=");
            sb.append(introspectedColumn.getJdbcTypeName());
            sb.append("}");
            element.setValue(sb.toString());
            answer.addElement(element);
        }
        parentElement.addElement(answer);
    }
}
