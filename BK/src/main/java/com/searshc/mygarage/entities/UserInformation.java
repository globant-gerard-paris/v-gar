/**
 * 
 */
package com.searshc.mygarage.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * 
 * The {@link UserInformation} is the information of one {@code userId} that are enrolled in the
 * application.
 * 
 * @author Jero
 *
 */
@Entity
public class UserInformation extends AbstractEntity {

	/**
	 * The {@code userId} represent the identifier of user in the application. TODO: It does't yet
	 * the entity relationship, because is still TBD the way that will persist the user in the
	 * application.
	 */
	private Long userId;

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
