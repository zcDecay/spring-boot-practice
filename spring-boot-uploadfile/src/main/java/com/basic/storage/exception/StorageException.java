package com.basic.storage.exception;

/**
 * @Description 异常处理类
 * @Author zcc
 * @Date 19/01/07
 */
public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
