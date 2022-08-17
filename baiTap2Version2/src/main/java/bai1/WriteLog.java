package bai1;

import org.apache.log4j.Logger;

public class WriteLog {
    private static final Logger LOGGER =  LogFactory4J.getLogger();
    public void visit(User user, String levels) {
        if (levels.equals("INFO")) {
            LOGGER.info("user number: "+user.getUser());
        }else {
            LOGGER.debug("user number: "+user.getUser());
        }
    }
}
