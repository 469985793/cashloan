package ${packageName}.${moduleName}.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ${commonName}.web.controller.BaseController;
import ${packageName}.${moduleName}.service.${ClassName}Service;

 /**
 * ${functionName}Controller
 * 
 * @author ${classAuthor}
 * @version 1.0.0
 * @date ${classDate}
 */
@Controller
public class ${ClassName}Controller extends BaseController {

	@Resource
	private ${ClassName}Service ${className}Service;

}
