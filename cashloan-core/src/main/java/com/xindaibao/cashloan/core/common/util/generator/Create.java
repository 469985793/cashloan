package com.xindaibao.cashloan.core.common.util.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Create {
	
	public static final Logger logger = LoggerFactory.getLogger(Create.class);
	public static void main(String[] args) {
		Create ot=new Create();
		ot.test();
	}
	
	public void test(){

		// 数据库连接信息
		String url = "jdbc:mysql://172.16.90.38:3306/cashloan_master1.0.1?useUnicode=true&characterEncoding=utf8";
		String MysqlUser = "root";
		String mysqlPassword = "erongdu.com";
		
		// 数据库及数据表名称
		String database = "cashloan_master1.0.1";
		String table = "cl_rc_simple_voices_count";
		
		// 配置作者及Domain说明
		String classAuthor = "xx";
		String functionName = "风控数据统计-（简）通话记录统计";
 
		// 公共包路径 (例如 BaseDao、 BaseService、 BaseServiceImpl)
		String commonName ="com.rongdu.cashloan.core.common";
		
		String packageName ="com.rongdu.cashloan.rc";
		String moduleName = "simpleCount";

		//Mapper文件存储地址  默认在resources中
		String batisName = "config/mappers";
		String db="mysql";
		
		//类名前缀
		String classNamePrefix = "simpleVoicesCount";

		try {
			MybatisGenerate.generateCode(db,url, MysqlUser, mysqlPassword, database, table,commonName,packageName,batisName,moduleName,classAuthor,functionName,classNamePrefix);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

}
