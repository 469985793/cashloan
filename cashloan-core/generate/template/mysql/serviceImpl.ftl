package ${packageName}.${moduleName}.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ${commonName}.mapper.BaseMapper;
import ${commonName}.service.impl.BaseServiceImpl;
import ${packageName}.${moduleName}.mapper.${ClassName}Mapper;
import ${packageName}.${moduleName}.domain.${ClassName};
import ${packageName}.${moduleName}.service.${ClassName}Service;


/**
 * ${functionName}ServiceImpl
 * 
 * @author ${classAuthor}
 * @version 1.0.0
 * @date ${classDate}



 */
 
@Service("${className}Service")
public class ${ClassName}ServiceImpl extends BaseServiceImpl<${ClassName}, Long> implements ${ClassName}Service {
	
    private static final Logger logger = LoggerFactory.getLogger(${ClassName}ServiceImpl.class);
   
    @Resource
    private ${ClassName}Mapper ${className}Mapper;

	@Override
	public BaseMapper<${ClassName}, Long> getMapper() {
		return ${className}Mapper;
	}
	
}