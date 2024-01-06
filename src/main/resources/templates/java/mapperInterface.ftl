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