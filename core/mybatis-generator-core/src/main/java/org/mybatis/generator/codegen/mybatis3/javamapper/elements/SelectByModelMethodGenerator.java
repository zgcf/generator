package org.mybatis.generator.codegen.mybatis3.javamapper.elements;

import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.AbstractJavaProviderMethodGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author gazhou
 * @version 1.0
 * @date 2020/11/13 13:59
 */
public class SelectByModelMethodGenerator extends AbstractJavaMapperMethodGenerator {

    public SelectByModelMethodGenerator() {
        super();
    }

    @Override
    public void addInterfaceElements(Interface interfaze) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();

        Method method = new Method(introspectedTable.getSelectByModelStatementId());

        FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getNewListInstance();
        returnType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getSearchRecordType()));
        method.setReturnType(returnType);
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setAbstract(true);

        importedTypes.add(returnType);

        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType(introspectedTable.getSearchRecordType());

        importedTypes.add(parameterType);
        method.addParameter(new Parameter(parameterType, "searchVO"));

        String remark ="在表"+introspectedTable.getFullyQualifiedTable()+"按照条件查找记录(有值的字段为条件)";
        List<String> list = new ArrayList<>() ;
        list.add(" * @param record 将要新增的记录");
        list.add(" * @return 返回查找出的记录");
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
