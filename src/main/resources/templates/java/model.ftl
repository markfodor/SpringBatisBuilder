/**
* ${comment}
*/

package ${model.packageName()};

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
class ${model.className()} {
    <#list model.members() as member>
    private ${member.clazz().getSimpleName()} ${member.name()};
    </#list>
}