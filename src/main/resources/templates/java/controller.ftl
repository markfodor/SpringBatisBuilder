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
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ${model.classType()}Controller {

    private final ${model.classType()}Service ${model.className()}Service;

    @Inject
    public ${model.classType()}(final ${model.classType()}Service ${model.className()}Service) {
        this.${model.className()}Service = ${model.className()}Service;
    }

    // TODO check return type
    @PreAuthorize("hasAuthority('SCOPE_urn:something:whatever')")
    public ResponseEntity<Void> insert${model.classType()}(@RequestBody ${model.classType()} ${model.className()}) {
        final int affectedRows = ${model.className()}Service.insert${model.classType()}(${model.className()});
        // TODO handle error based on affectedRows
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // TODO check return type
    @PreAuthorize("hasAuthority('SCOPE_urn:something:whatever')")
    public ResponseEntity<Void> update${model.classType()}(final ${model.classType()} ${model.className()}) {
        final int affectedRows = ${model.className()}Service.update${model.classType()}(${model.className()});
        // TODO handle error based on affectedRows
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // TODO check return type and HTTP method for mapping
    @PreAuthorize("hasAuthority('SCOPE_urn:something:whatever')")
    public ResponseEntity<Void> delete${model.classType()}ById(final ${primaryKeyType} ${primaryKeyName}) {
        final int affectedRows =  ${model.className()}Service.delete${model.classType()}(${primaryKeyName});
        // TODO handle error based on affectedRows
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PreAuthorize("hasAuthority('SCOPE_urn:something:whatever')")
    public ResponseEntity<${model.classType()}> find${model.classType()}ById(@PathVariable("${primaryKeyName}") final ${primaryKeyType} ${primaryKeyName}) {
        final ${model.classType()} ${model.className()} = ${model.className()}Service.find${model.classType()}ById(${primaryKeyName});
        return ResponseEntity.status(HttpStatus.OK).body(${model.className()});
    }

    @PreAuthorize("hasAuthority('SCOPE_urn:something:whatever')")
    public ResponseEntity<List<${model.classType()}>> findAll() {
        final List<${model.classType()}> ${model.className()}List = ${model.className()}Service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(${model.className()}List);
    }
}