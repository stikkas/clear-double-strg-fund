package ru.insoft.archive.stores.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "STRG_ORGANIZATION")
@NamedQueries({@NamedQuery(name="Organization.updateFund", 
		query = "UPDATE Organization o SET o.fund = :id WHERE o.fund in :ids"
		)})
public class Organization implements Serializable {
	@Id
	@Column(name="organization_id")
	private Long id;

	@Column(name="fund_id")
	private Long fund;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFund() {
		return fund;
	}

	public void setFund(Long fund) {
		this.fund = fund;
	}

}
