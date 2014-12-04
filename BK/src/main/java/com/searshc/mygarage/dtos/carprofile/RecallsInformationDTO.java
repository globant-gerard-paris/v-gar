package com.searshc.mygarage.dtos.carprofile;

/**
 *
 * @author rammel.maestre
 */
public class RecallsInformationDTO {

    private int totalRecalls;
    private LastRecallDTO lastRecall;

    /**
     * @return the totalRecalls
     */
    public int getTotalRecalls() {
        return totalRecalls;
    }

    /**
     * @param totalRecalls the totalRecalls to set
     */
    public void setTotalRecalls(int totalRecalls) {
        this.totalRecalls = totalRecalls;
    }

    /**
     * @return the lastRecall
     */
    public LastRecallDTO getLastRecall() {
        return lastRecall;
    }

    /**
     * @param lastRecall the lastRecall to set
     */
    public void setLastRecall(LastRecallDTO lastRecall) {
        this.lastRecall = lastRecall;
    }

}
