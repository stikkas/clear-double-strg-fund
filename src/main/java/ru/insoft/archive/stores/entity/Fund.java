package ru.insoft.archive.stores.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Благодатских С.
 */
@Entity
@Table(name = "STRG_FUND")
@NamedQueries({
	@NamedQuery(name = "Fund.deleteByIds",
			query = "DELETE FROM Fund f where f.id in :ids"),
	@NamedQuery(name = "Fund.getDoubles",
			query = "SELECT f.id, f.number FROM Fund f WHERE f.number IN "
			+ "(SELECT f.number FROM Fund f GROUP BY f.archive, f.number, "
			+ "f.prefix, f.suffix HAVING COUNT(f) > 1) ORDER BY f.number")
})
public class Fund implements Serializable {

	@Id
	@Column(name = "fund_id")
	private Long id;

	@Column(name = "archive_id")
	private Long archive;

	@Column(name = "fund_num")
	private Integer number;

	@Column(name = "suffix")
	private String suffix;

	@Column(name = "prefix")
	private String prefix;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getArchive() {
		return archive;
	}

	public void setArchive(Long archive) {
		this.archive = archive;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

}
