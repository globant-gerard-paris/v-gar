package com.searshc.mygarage.dtos.carprofile;

/**
 *
 * @author rammel.maestre
 */
public class BlockSuggestedServiceDTO {

    private String code;
    private String orderNumber;

    public BlockSuggestedServiceDTO() {
    }

    public BlockSuggestedServiceDTO(String code, String orderNumber) {
        this.code = code;
        this.orderNumber = orderNumber;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the orderNumber
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

}
