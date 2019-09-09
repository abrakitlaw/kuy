package com.android.domain;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version BaseResponseResult, v 0.1 2019-09-10 02:43 by Abraham Ginting
 */
public class BaseResponseResult {

    private boolean success;
    private String errorCode;
    private String errorMessage;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
