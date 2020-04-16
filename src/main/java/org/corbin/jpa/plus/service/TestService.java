package org.corbin.jpa.plus.service;

import org.corbin.jpa.plus.entity.Test;
import org.corbin.jpa.plus.repositort.TestRepository;
import org.corbin.jpa.plus.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author xiesu
 */
@Service
public class TestService extends BaseServiceImpl<TestRepository,Test,Integer> {

}
