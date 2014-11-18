package com.searshc.mygarage.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
public class ConfirmedVehicle extends AbstractEntity implements Serializable {

    /**
     * The Serial Version UID
     */
    private static final long serialVersionUID = -2472653437246767303L;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_user_information_id"))
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "family_vehicle_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_family_vehicle_id"))
    private FamilyVehicle familyVehicle;

    public ConfirmedVehicle() {
        this.user = null;
        this.familyVehicle = null;
    }

    /**
     * @param user
     * @param familyVehicle
     * @param isConfirmed
     */
    public ConfirmedVehicle(final User user, final FamilyVehicle familyVehicle) {
        super();
        this.user = Validate.notNull(user, "The User cannot be null");
        this.familyVehicle = Validate.notNull(familyVehicle, "The Vehicle cannot be null");
    }

    /**
     * @return the userInformation
     */
    public User getUserInformation() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the vehicle
     */
    public FamilyVehicle getVehicle() {
        return familyVehicle;
    }

    /**
     * @param familyVehicle the vehicle to set
     */
    public void setVehicle(FamilyVehicle familyVehicle) {
        this.familyVehicle = familyVehicle;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.user)
                .append(this.familyVehicle).hashCode();
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
        ConfirmedVehicle rhs = (ConfirmedVehicle) obj;
        return new EqualsBuilder()
                .append(this.user, rhs.user)
                .append(this.familyVehicle, rhs.familyVehicle).isEquals();
    }

}
