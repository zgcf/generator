package org.mybatis.generator.codegen.mybatis3.javamapper.elements;

import org.mybatis.generator.api.dom.java.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author gazhou
 * @version 1.0
 * @date 2020/11/13 16:09
 */
public class InsertBatchMethodGenerator extends AbstractJavaMapperMethodGenerator{
    public InsertBatchMethodGenerator(){
        super();
    }
    @Override
    public void addInterfaceElements(Interface interfaze) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
        Method method = new Method(introspectedTable.getInsertBatchStatementId());
        FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getIntInstance();
        method.setReturnType(returnType);
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setAbstract(true);

        importedTypes.add(returnType);

        FullyQualifiedJavaType parameterType = FullyQualifiedJavaType.getNewListInstance();
        parameterType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getSearchRecordType()));

        importedTypes.add(parameterType);
        method.addParameter(new Parameter(parameterType, "records"));

        String remark ="在表"+introspectedTable.getFullyQualifiedTable()+"批量新增数据(无值的字段为NULL)";
        List<String> list = new ArrayList<>() ;
        list.add(" * @param records 将要新增的记录");
        list.add(" * @return 返回新增的记录数");
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
