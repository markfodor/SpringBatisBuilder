<#assign primaryKeyName = "">
<#assign primaryKeyType = "">
<#list model.members() as member>
    <#if member.isPrimaryKey()>
        <#assign primaryKeyName = member.name()>
        <#assign primaryKeyType = member.clazz().getSimpleName()>
    </#if>
</#list>
/**
* ${model.comment()}
*/

package ${model.packageName()};

import java.util.List;

public interface ${model.classType()}Repository {
    int insert(${model.classType()} ${model.className()});
    int update(${model.classType()} ${model.className()});
    int delete(${primaryKeyType} ${primaryKeyName});
    ${model.classType()} findById(${primaryKeyType} ${primaryKeyName});
    List<${model.classType()}> findAll();
}