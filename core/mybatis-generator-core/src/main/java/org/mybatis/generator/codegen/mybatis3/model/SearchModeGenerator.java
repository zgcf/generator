package org.mybatis.generator.codegen.mybatis3.model;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.RootClassInfo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.mybatis.generator.internal.util.JavaBeansUtil.getJavaBeansField;
import static org.mybatis.generator.internal.util.JavaBeansUtil.getJavaBeansGetter;
import static org.mybatis.generator.internal.util.JavaBeansUtil.getJavaBeansSetter;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

/**
 * @author gazhou
 * @version 1.0
 * @date 2020/11/12 15:55
 */
public class SearchModeGenerator extends AbstractJavaGenerator {

    public SearchModeGenerator(String project) {
        super(project);
    }
    @Override
    public List<CompilationUnit> getCompilationUnits() {
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        progressCallback.startTask(getString(
                "Progress.8", table.toString()));
        CommentGenerator commentGenerator = context.getCommentGenerator();

        FullyQualifiedJavaType type = new FullyQualifiedJavaType(
                introspectedTable.getSearchRecordType());
        TopLevelClass topLevelClass = new TopLevelClass(type);
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        commentGenerator.addJavaFileComment(topLevelClass);

        FullyQualifiedJavaType superClass = new FullyQualifiedJavaType(context.getJavaModelGeneratorConfiguration().getTargetPackage()+"."+table.getDomainObjectName());
        if(introspectedTable.getBLOBColumns().size()>0){
            superClass = new FullyQualifiedJavaType(introspectedTable.getRecordWithBLOBsType());
        }
        topLevelClass.setSuperClass(superClass);
        topLevelClass.addImportedType(superClass);

        commentGenerator.addModelClassComment(topLevelClass, introspectedTable);

        List<CompilationUnit> answer = new ArrayList<>();
        answer.add(topLevelClass);
        return answer;
    }

}
