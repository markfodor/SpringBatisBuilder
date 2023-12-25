/**
* ${comment}
*/

package ${model.packageName()};

// TODO delete unused imports
mport java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.time.Instant;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
class ${model.classType()} {
    <#list model.members() as member>
    private ${member.clazz().getSimpleName()} ${member.name()};
    </#list>
}