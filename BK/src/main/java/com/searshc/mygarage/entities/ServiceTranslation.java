package com.searshc.mygarage.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class ServiceTranslation extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private Integer productFlag;

    @NotBlank
    private String description;

    private String category;

    /**
     * @return the productFlag
     */
    public Integer getProductFlag() {
        return productFlag;
    }

    /**
     * @param productFlag the productFlag to set
     */
    public void setProductFlag(Integer productFlag) {
        this.productFlag = productFlag;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

}
