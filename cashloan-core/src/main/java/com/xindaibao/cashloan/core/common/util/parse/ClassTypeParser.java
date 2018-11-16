package com.xindaibao.cashloan.core.common.util.parse;

/**
 * Class类型解析器
 * @author
 *
 */
public interface ClassTypeParser {
	<T> T parse(String content, Class<T> valueType);
}
