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

package ${model.packageName()};

<#if !(primaryKeyImport?length == 0)>
import ${primaryKeyImport};
</#if>
import java.util.List;
import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class ${model.classType()}Service {

    private final ${model.classType()}RepositoryImpl ${model.className()}Repository;

    @Inject
    public ${model.classType()}Service(final ${model.classType()}RepositoryImpl ${model.className()}Repository) {
        this.${model.className()}Repository = ${model.className()}Repository;
    }

    public int insert${model.classType()}(final ${model.classType()} ${model.className()}) {
        return ${model.className()}Repository.insert(${model.className()});
    }

    public int update${model.classType()}(final ${model.classType()} ${model.className()}) {
        return ${model.className()}Repository.update(${model.className()});
    }

    public int delete${model.classType()}ById(final ${primaryKeyType} ${primaryKeyName}) {
        return ${model.className()}Repository.delete(${primaryKeyName});
    }

    public ${model.classType()} find${model.classType()}ById(final ${primaryKeyType} ${primaryKeyName}) {
        return ${model.className()}Repository.findById(${primaryKeyName});
    }

    public List<${model.classType()}> findAll() {
        return ${model.className()}Repository.findAll();
    }
}