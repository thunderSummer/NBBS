package com.jvmup.nbbs.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 控制器基类，目前提供日志打印功能
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-14 17:52
 **/
public abstract class BaseController {
    protected Logger logger = LogManager.getLogger(this);
}
