/**
 *
 */
package com.searshc.mygarage.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * The {@link User} is the information of one {@code userId} that are enrolled
 * in the application.
 *
 * @author Jero
 *
 */
@Entity
public class User extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = -2745176863528011598L;

    @Column(name = "syw_id", nullable = false)
    private Long sywId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sywr_member_number", nullable = false)
    private String sywrMemberNumber;

    @Column(name = "family_id", nullable = true)
    private Long familyId;

    /**
     * Is the favorite {@code store} of the {@code userId}.
     */
    @OneToOne(cascade = CascadeType.ALL)
    private Store store;

    /**
     * Is just a simple time stamp of last update.
     */
    @Column(name = "last_update")
    private Date lastUpdate;

    /**
     * @return Return the SywMemberNumber.
     */
    public String getSywrMemberNumber() {
		return sywrMemberNumber;
	}

    /**
     * Set the syw member number.
     * @param sywrMemberNumber
     */
	public void setSywrMemberNumber(String sywrMemberNumber) {
		this.sywrMemberNumber = sywrMemberNumber;
	}

	/**
     * @return the sywId
     */
    public Long getSywId() {
        return sywId;
    }

    /**
     * @param sywId the sywId to set
     */
    public void setSywId(final Long sywId) {
        this.sywId = Validate.notNull(sywId, "The ShopYouWay id cannot be null");
    }

    /**
     * @return the familyId
     */
    public Long getFamilyId() {
        return familyId;
    }

    /**
     * @param familyId the familyId to set
     */
    public void setFamilyId(final Long familyId) {
        this.familyId = familyId;
    }

    @PreUpdate
    @PrePersist
    public void updateTimeStamps() {
        lastUpdate = new Date();
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User rhs = (User) obj;
        return new EqualsBuilder()
                .append(this.sywId, rhs.sywId)
                .append(this.name, rhs.name)
                .append(this.familyId, rhs.familyId).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.sywId)
                .append(this.name)
                .append(this.familyId).hashCode();
    }
}
