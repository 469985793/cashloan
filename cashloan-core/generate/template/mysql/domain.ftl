package ${packageName}.${moduleName}.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * ${functionName}实体
 * 
 * @author ${classAuthor}
 * @version 1.0.0
 * @date ${classDate}



 */
 public class ${ClassName} implements Serializable {

    private static final long serialVersionUID = 1L;

    <#list list as item>
    <#if item.columnName == "id">
    /**
    * 主键Id
    */
    private Long id;

    <#else >
    /**
    * ${item.columnComment}
    */
    private ${item.dataType} ${item.columnName};

    </#if >
    </#list>

    <#list list as item>
    <#if item.columnName == "id">
    /**
    * 获取主键Id
    *
    * @return id
    */
    public Long getId(){
        return id;
    }

    /**
    * 设置主键Id
    * 
    * @param 要设置的主键Id
    */
    public void setId(Long id){
        this.id = id;
    }

    <#else >
    /**
    * 获取${item.columnComment}
    *
    * @return ${item.columnComment}
    */
    public ${item.dataType} get${item.columnNameUpper}(){
        return ${item.columnName};
    }

    /**
    * 设置${item.columnComment}
    * 
    * @param ${item.columnName} 要设置的${item.columnComment}
    */
    public void set${item.columnNameUpper}(${item.dataType} ${item.columnName}){
        this.${item.columnName} = ${item.columnName};
    }

    </#if >
    </#list>
}