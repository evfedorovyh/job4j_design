package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
        boolean bool = true;
        byte b = 100;
        short sh = 5183;
        char ch = 'r';
        int i = 574993;
        long l = 354832L;
        float f = 4e2F;
        double d = 1243e5D;
        LOG.debug("Var info:\n bool={},\n b={},\n sh={},\n ch={},\n i={},\n l={},\n f={},\n d={},\n",
                bool, b, sh, ch, i, l, f, d);
    }
}
