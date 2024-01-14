<#assign placeholderStart = "#{">
<#assign placeholderEnd = "}">
<#assign primaryKeyName = "">
<#assign primaryKeyType = "">
<#assign primaryKeyTableFieldType = "">
<#assign primaryKeyTableFieldName = "">
<#list model.members() as member>
    <#if member.isPrimaryKey()>
        <#assign primaryKeyName = member.name()>
        <#assign primaryKeyType = member.clazz().getName()>
        <#assign primaryKeyTableFieldType = member.tableFieldType()>
        <#assign primaryKeyTableFieldName = member.tableFieldName()>
    </#if>
</#list>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org/DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageName}.${model.classType()}">

    <resultMap id="${model.className()}ResultMap" type="${packageName}.${model.classType()}">
    <#list model.members() as member>
        <result property="${member.name()}" column="${member.tableFieldName()}" javaType="${member.clazz().getName()}" />
    </#list>
    </resultMap>

    <select id="selectById" resultMap="${model.className()}ResultMap" useCache="false">
        SELECT
        <#list model.members() as member>
            ${member.tableFieldName()}<#if member?has_next>, </#if>
        </#list>
        FROM ${model.tableName()}
        WHERE
            ${primaryKeyTableFieldName} = ${placeholderStart} ${primaryKeyName}, javaType=${primaryKeyType}, jdbcType=${primaryKeyTableFieldType} ${placeholderEnd}
    </select>

    <insert id="insert">
        INSERT INTO ${model.tableName()} (
        <#list model.members() as member>
            ${member.tableFieldName()}<#if member?has_next>, </#if>
        </#list>
        ) VALUES (
        <#list model.members() as member>
            ${placeholderStart} ${model.tableName()}.${member.name()}, javaType=${member.clazz().getName()}, jdbctype=${member.tableFieldType()} ${placeholderEnd}<#if member?has_next>, </#if>
        </#list>
        )
    </insert>

    <update id="update">
        UPDATE ${model.tableName()}
        SET
        <#list model.members() as member>
            <#if member.tableFieldName() != primaryKeyTableFieldName>
            ${member.tableFieldName()} = ${placeholderStart} ${member.name()} ${placeholderEnd}<#if member?has_next>, </#if>
            </#if>
        </#list>
        WHERE
            ${primaryKeyTableFieldName} = ${placeholderStart} ${primaryKeyName}, javaType=${primaryKeyType}, jdbcType=${primaryKeyTableFieldType} ${placeholderEnd}
    </update>

    <delete id="deleteById">
        DELETE FROM ${model.tableName()}
        WHERE
            ${primaryKeyTableFieldName} = ${placeholderStart} ${primaryKeyName}, javaType=${primaryKeyType}, jdbcType=${primaryKeyTableFieldType} ${placeholderEnd}
    </delete>
</mapper>
