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

import javax.inject.Inject;
// TODO check needed annotations
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
// TODO implement OpenAPI-generated interface
class ${model.classType()}Controller {

    private final ${model.classType()}Service ${model.className()}Service;

    @Inject
    public ${model.classType()}(final ${model.classType()} ${model.className()}) {
        this.${model.className()}Service = ${model.className()};
    }

    // TODO check return type
    @PreAuthorize("hasAuthority('SCOPE_urn:something:whatever')")
    public ${model.classType()} insert${model.classType()}(@RequestBody ${model.classType()} ${model.className()}) {
        final int affectedRows = ${model.className()}Repository.insert(${model.className()});
        // TODO handle error based on return value
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // TODO check return type
    @PreAuthorize("hasAuthority('SCOPE_urn:something:whatever')")
    public ResponseEntity<Void> update${model.classType()}(final ${model.classType()} ${model.className()}) {
        final int affectedRows = ${model.className()}Repository.update(${model.className()});
        // TODO handle error based on return value
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // TODO check return type and HTTP method for mapping
    @PreAuthorize("hasAuthority('SCOPE_urn:something:whatever')")
    public ResponseEntity<Void> delete${model.classType()}ById(final ${primaryKeyType} ${primaryKeyName}) {
        final int affectedRows =  ${model.className()}Repository.delete(${primaryKeyName});
        // TODO handle error based on return value
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PreAuthorize("hasAuthority('SCOPE_urn:something:whatever')")
    public ResponseEntity<Void> find${model.classType()}ById(@PathVariable("${primaryKeyName}") ${primaryKeyType} ${primaryKeyName}) {
        ${model.className()}Repository.findById(${primaryKeyName});
        // TODO handle return type
    }

    @PreAuthorize("hasAuthority('SCOPE_urn:something:whatever')")
    public ResponseEntity<Void> findAll() {
        ${model.className()}Repository.findAll();
        // TODO handle return type
    }
}