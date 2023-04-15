/*
 * www.yiji.com Inc.
 * Copyright (c) 2023/35 All Rights Reserved.
 */

package com.student.shop.test.suite;

import com.student.shop.test.service.AdminTest;
import com.student.shop.test.service.OrderTest;
import com.student.shop.test.service.UserTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author student
 * @version 2023/3/30
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({AdminTest.class, OrderTest.class, UserTest.class})
public class AllTestsSuite {

}
