package com.searshc.mygarage.entities.record;

import com.searshc.mygarage.entities.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class SuggestedService extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String sku;

    @NotBlank
    private String description;

    /**
     * @return the sku
     */
    public String getSku() {
        return sku;
    }

    /**
     * @param sku the sku to set
     */
    public void setSku(String sku) {
        this.sku = sku;
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

}
