<#assign primaryKeyName = "">
<#assign primaryKeyType = "">
<#list model.members() as member>
    <#if member.isPrimaryKey()>
        <#assign primaryKeyName = member.name()>
        <#assign primaryKeyType = member.clazz().getSimpleName()>
    </#if>
</#list>
/**
* ${comment}
*/

package ${model.packageName()};

import javax.inject.Inject;
// TODO check needed annotations
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/${model.className()}")
// TODO implement OpenAPI-generated interface
class ${model.classType()}Controller {

    private final ${model.classType()}Service ${model.className()}Service;

    @Inject
    public ${model.classType()}(final ${model.classType()} ${model.className()}) {
        this.${model.className()}Service = ${model.className()};
    }

    // TODO check return type
    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_urn:something:whatever')")
    public ${model.classType()} insert${model.classType()}(@RequestBody ${model.classType()} ${model.className()}) {
        return ${model.className()}Repository.insert(${model.className()});
    }

    // TODO check return type
    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_urn:something:whatever')")
    public int update${model.classType()}(final ${model.classType()} ${model.className()}) {
        return ${model.className()}Repository.update(${model.className()});
    }

    // TODO check return type and HTTP method for mapping
    @PreAuthorize("hasAuthority('SCOPE_urn:something:whatever')")
    public int delete${model.classType()}ById(final ${primaryKeyType} ${primaryKeyName}) {
        return ${model.className()}Repository.delete(${primaryKeyName});
    }

    @GetMapping("/${primaryKeyName}")
    @PreAuthorize("hasAuthority('SCOPE_urn:something:whatever')")
    public ${model.classType()} find${model.classType()}ById(@PathVariable("${primaryKeyName}") ${primaryKeyType} ${primaryKeyName}) {
        return ${model.className()}Repository.findById(${primaryKeyName});
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_urn:something:whatever')")
    public List<${model.classType()}> findAll() {
        return ${model.className()}Repository.findAll();
    }
}