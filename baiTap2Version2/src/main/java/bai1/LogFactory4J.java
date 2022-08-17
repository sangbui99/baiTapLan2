package bai1;

import org.apache.log4j.Logger;

public class LogFactory4J {
    public static Logger getLogger() {
        Logger logger = Logger.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
        return logger;
    }
}
