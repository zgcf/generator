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
package org.mybatis.generator.codegen.mybatis3.javamapper.elements;

import org.mybatis.generator.api.dom.java.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author gazhou
 * @version 1.0
 * @date 2020/11/13 14:44
 */
public class CountByModelMethodGenerator extends AbstractJavaMapperMethodGenerator {
    public CountByModelMethodGenerator(){
        super();
    }
    @Override
    public void addInterfaceElements(Interface interfaze) {
        Method method = new Method(introspectedTable.getCountByModelStatementId());

        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setAbstract(true);

        FullyQualifiedJavaType parameterType =  new FullyQualifiedJavaType(introspectedTable.getSearchRecordType());

        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
        importedTypes.add(parameterType);
        method.addParameter(new Parameter(parameterType, "searchVO"));

        String remark ="在表"+introspectedTable.getFullyQualifiedTable()+"新增一条记录(没有值的字段为默认值)";
        List<String> list = new ArrayList<>() ;
        list.add(" * @param record 将要新增的记录");
        list.add(" * @return 返回新增记录数");
        context.getCommentGenerator().addGeneralMethodComment(method,remark,list);

        addMapperAnnotations(method);

        if (context.getPlugins().clientInsertSelectiveMethodGenerated(
                method, interfaze, introspectedTable)) {
            addExtraImports(interfaze);
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }

    public void addMapperAnnotations(Method method) {
        // extension point for subclasses
    }

    public void addExtraImports(Interface interfaze) {
        // extension point for subclasses
    }
}
