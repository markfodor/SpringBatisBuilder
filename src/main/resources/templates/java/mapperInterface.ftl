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

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ${model.classType()}Mapper {
    int insert(${model.classType()} ${model.className()});
    int update(${model.classType()} ${model.className()});
    int delete(@Param("${primaryKeyName}") ${primaryKeyType} ${primaryKeyName});
    ${model.classType()} findById(@Param("${primaryKeyName}") ${primaryKeyType} ${primaryKeyName});
    List<${model.classType()}> findAll();
}