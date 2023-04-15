/**
 * 
 */
package com.student.shop.service;

import com.student.shop.model.News;
import com.student.shop.common.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Student
 * @email student@test.com
 * @date 2023-3-9
 * 
 */
public abstract class BaseService implements
		IBaseService {

	private static final Logger logger = LoggerFactory.getLogger(BaseService.class);

	protected <T> T doIt(Page<News> page, T resultClass, ProcessInvoker processInvoker) {
		T result = initResult();
		processInvoker.process();
		return result;
	}

	private <T> T initResult() {
		return null;
	}
}
