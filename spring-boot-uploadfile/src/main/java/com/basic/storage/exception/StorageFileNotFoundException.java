package com.basic.storage.exception;

/**
 * @Description 文件没有找到异常处理
 * @Author zcc
 * @Date 19/01/07
 */
public class StorageFileNotFoundException extends RuntimeException {

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
