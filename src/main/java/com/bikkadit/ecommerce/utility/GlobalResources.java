package com.bikkadit.ecommerce.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlobalResources {


    private static Logger getlogger (Class className){
        return LoggerFactory.getLogger(className);
    }
}
