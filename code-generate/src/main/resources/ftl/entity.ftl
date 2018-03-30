package ${config.entityPackage};

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ${entity.className} implements Serializable  {

    <#list entity.columns as item>
    <#if item.primaryKey>@TableId</#if>
    <#if item.remarks?? && item.remarks?trim!=''>/*
    * ${item.remarks}
    */</#if>
    private ${item.javaType} ${item.className};
    </#list>

    private static final long serialVersionUID = 1L;

}
