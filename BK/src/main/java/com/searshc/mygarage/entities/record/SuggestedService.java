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
    private String systemDescription;

    @NotBlank
    private String description;

    private String link;

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
     * @return the systemDescription
     */
    public String getSystemDescription() {
        return systemDescription;
    }

    /**
     * @param systemDescription the systemDescription to set
     */
    public void setSystemDescription(String systemDescription) {
        this.systemDescription = systemDescription;
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
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }
    
    

}
