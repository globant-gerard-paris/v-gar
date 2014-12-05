package com.searshc.mygarage.dtos.carprofile;

import com.searshc.mygarage.entities.record.RecommendedService;

/**
 *
 * @author rammel.maestre
 */
public class RecommendedServicesInformationDTO {

    private int totalRecommendedServices;
    private RecommendedService recommendedService;

    /**
     * @return the totalRecommendedServices
     */
    public int getTotalRecommendedServices() {
        return totalRecommendedServices;
    }

    /**
     * @param totalRecommendedServices the totalRecommendedServices to set
     */
    public void setTotalRecommendedServices(int totalRecommendedServices) {
        this.totalRecommendedServices = totalRecommendedServices;
    }

    /**
     * @return the recommendedService
     */
    public RecommendedService getRecommendedService() {
        return recommendedService;
    }

    /**
     * @param recommendedService the recommendedService to set
     */
    public void setRecommendedService(RecommendedService recommendedService) {
        this.recommendedService = recommendedService;
    }

}
