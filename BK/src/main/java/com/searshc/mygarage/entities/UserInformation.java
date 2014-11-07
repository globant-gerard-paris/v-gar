/**
 * 
 */
package com.searshc.mygarage.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * 
 * The {@link UserInformation} is a person that are enrolled in the application.
 * 
 * @author Jero
 *
 */
@Entity
public class UserInformation extends AbstractEntity {

	/**
	 * Is the favorite {@code store} of the {@code userId}.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	private Store store;

	/**
	 * Is just a simple time stamp of {@link UserInformation} class.
	 */

	@Column(name = "last_update")
	private Date lastUpdate;

	@PreUpdate
	@PrePersist
	public void updateTimeStamps() {
		lastUpdate = new Date();
	}

	private Long userId;

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
