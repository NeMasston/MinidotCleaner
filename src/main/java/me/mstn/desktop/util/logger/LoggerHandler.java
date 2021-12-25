package me.mstn.desktop.util.logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class LoggerHandler extends Handler {

    @Override
    public void publish(LogRecord record) {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        Date date = new Date(record.getMillis());

        sb.append("[")
                .append(simpleDateFormat.format(date))
                .append("]")
                .append(" ")
                .append("[")
                .append(record.getLoggerName())
                .append("]")
                .append(" ")
                .append(record.getMessage());
        System.out.println(sb.toString());
    }

    @Override
    public void flush() {

    }

    @Override
    public void close() throws SecurityException {

    }

}
