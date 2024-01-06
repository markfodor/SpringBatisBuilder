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

import javax.inject.Inject;
import java.util.List;

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