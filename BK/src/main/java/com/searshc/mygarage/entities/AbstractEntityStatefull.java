package com.searshc.mygarage.entities;

import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The base class {@link AbstractEntityStatefull} allow to handle deletions logical, instead of physical.
 *
 * @author Jero
 */
@MappedSuperclass
public abstract class AbstractEntityStatefull extends AbstractEntity {

	private Boolean isActive = true;

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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
		AbstractEntityStatefull entity = (AbstractEntityStatefull) obj;
		return new EqualsBuilder()
						.append(this.getId(), entity.getId())
						.append(this.getIsActive(), entity.getIsActive())
						.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(this.getId())
				.append(this.getIsActive()).hashCode();
	}
}
