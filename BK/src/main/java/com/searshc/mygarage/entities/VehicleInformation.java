package com.searshc.mygarage.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * The {@link VehicleInformation} is the relationship between {@link Vehicle} and
 * {@link UserInformation} and the rest of information regarding to the vehicle of the User.
 * 
 * @author Jero
 *
 */
@Entity
public class VehicleInformation extends AbstractEntity {

	@OneToMany()
	private List<Record> records;

	@Column(name = "tangible_id") // ncdb
	private Long tangibleId;

	@Column(name = "family_id")
	private Long familyId;

	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}

	public Long getTangibleId() {
		return tangibleId;
	}

	public void setTangibleId(Long tangibleId) {
		this.tangibleId = tangibleId;
	}

	public Long getFamilyId() {
		return familyId;
	}

	public void setFamilyId(Long familyId) {
		this.familyId = familyId;
	}

}
