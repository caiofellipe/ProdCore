package br.com.api.prodcore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
public class UserRoles {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "usuario_id")
	private Long usuarioId;
	
	@Column(name = "role_id")
	private Long rolesId;
	
	public Long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	public Long getRolesId() {
		return rolesId;
	}
	public void setRolesId(Long rolesId) {
		this.rolesId = rolesId;
	}
	
}
