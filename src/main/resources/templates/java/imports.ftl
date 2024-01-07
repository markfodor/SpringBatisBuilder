<#assign imports = []>
<#list members as member>
    <#assign fullName = member.clazz().getName()>
    <#if !fullName?starts_with("java.lang") && !(imports?seq_contains(fullName))>
        <#assign imports += [fullName]>
    </#if>
</#list>
<#list imports as import>
import ${import};
</#list>
