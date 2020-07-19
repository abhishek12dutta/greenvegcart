package com.cart.greenveg.admin.common;

public class AdminBackEndException extends Exception{

    private String errorCode;
    private String errorMsg;
    private String additionalInfo;

    public AdminBackEndException(AdminBackEndErrorCodes adminBackEndErrorCodes){
        super(adminBackEndErrorCodes.getDescription());
        this.errorCode = adminBackEndErrorCodes.getCode();
        this.errorMsg = adminBackEndErrorCodes.getDescription();
    }

    public AdminBackEndException(AdminBackEndErrorCodes adminBackEndErrorCodes,String additionalInfo){
        super(adminBackEndErrorCodes.getDescription());
        this.errorCode = adminBackEndErrorCodes.getCode();
        this.errorMsg = adminBackEndErrorCodes.getDescription();
        this.additionalInfo = additionalInfo;
    }

    public AdminBackEndException(AdminBackEndErrorCodes adminBackEndErrorCodes,String additionalInfo, Throwable t){
        super(adminBackEndErrorCodes.getDescription(),t);
        this.errorCode = adminBackEndErrorCodes.getCode();
        this.errorMsg = adminBackEndErrorCodes.getDescription();
        this.additionalInfo = additionalInfo;
    }

    public AdminBackEndException(String errorCode, String errorMsg){
        super(errorMsg);
        this.errorCode = errorCode;

    }

    public AdminBackEndException(Throwable cause,String errorCode, String errorMsg){
        super(errorMsg,cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;

    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
