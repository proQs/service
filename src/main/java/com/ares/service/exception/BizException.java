package com.ares.service.exception;

import lombok.Data;

import java.util.Arrays;

@Data
public class BizException extends RuntimeException {
    private static final long serialVersionUID = -1L;

    protected String code;
    protected Object[] args;
    protected String msg;

    public BizException(String code, String message) {
        super(message, null);
        this.code = code;
        this.msg = message;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(getClass().getSimpleName());
        buf.append("[code='" + getCode() + "'");
        buf.append(", args=" + Arrays.toString(args));
        buf.append(", msg='" + msg + "']");
        String message = getLocalizedMessage();
        buf.append((message != null) ? ": " + message : "");
        StackTraceElement[] traces = getStackTrace();
        buf.append(traces.length == 0 ? "" : ": at " + traces[0]);
        return buf.toString();
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
