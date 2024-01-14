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
import javax.inject.Inject;

import org.apache.ibatis.annotations.Mapper;

public class ${model.classType()}RepositoryImpl implements ${model.classType()}Repository {

    private final ${model.classType()}Mapper ${model.className()}Mapper;

    @Inject
    public ${model.classType()}RepositoryImpl(final ${model.classType()}Mapper ${model.className()}Mapper) {
        this.${model.className()}Mapper = ${model.className()}Mapper;
    }

    @Override
    public int insert(final ${model.classType()} ${model.className()}) {
        return ${model.className()}Mapper.insert(${model.className()});
    }

    @Override
    public int update(final ${model.classType()} ${model.className()}) {
        return ${model.className()}Mapper.update(${model.className()});
    }

    @Override
    public public int delete(final ${primaryKeyType} ${primaryKeyName}) {
        return ${model.className()}Mapper.delete(${model.className()});
    }

    @Override
    public ${model.classType()} findById(final ${primaryKeyType} ${primaryKeyName}) {
        return ${model.className()}Mapper.findById(${primaryKeyName});
    }

    @Override
    public List<${model.classType()}> findAll() {
        return ${model.className()}Mapper.findAll();
    }
}