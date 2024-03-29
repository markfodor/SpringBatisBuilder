<#assign primaryKeyName = "">
<#assign primaryKeyType = "">
<#assign primaryKeyImport = "">
<#list model.members() as member>
    <#if member.isPrimaryKey()>
        <#assign primaryKeyName = member.name()>
        <#assign primaryKeyType = member.clazz().getSimpleName()>
        <#if !member.clazz().getName()?starts_with("java.lang")>
            <#assign primaryKeyImport = member.clazz().getName()>
        </#if>
    </#if>
</#list>
/**
* ${model.comment()}
*/

package ${packageName};

<#if !(primaryKeyImport?length == 0)>
import ${primaryKeyImport};
</#if>
import java.util.List;

public interface ${model.classType()}Repository {
    int insert(${model.classType()} ${model.className()});
    int update(${model.classType()} ${model.className()});
    int delete(${primaryKeyType} ${primaryKeyName});
    ${model.classType()} findById(${primaryKeyType} ${primaryKeyName});
    List<${model.classType()}> findAll();
}