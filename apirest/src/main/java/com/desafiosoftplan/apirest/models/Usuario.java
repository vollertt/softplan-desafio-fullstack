package com.desafiosoftplan.apirest.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable{
	
    private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long cd_usuario;
	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private PerfilUsuario perfilUsuario;
	@NotNull
	@Column(nullable = false)
    private boolean status;
	@NotNull
	@Column(nullable = false)
	@Length(max = 50)  
	private String email;
	@NotNull
	@Column(nullable = false)
	@Length(max = 100)  
	private String nome;
	@NotNull
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dt_cadastro;
	
	
	public long getCd_usuario() {
		return cd_usuario;
	}
	public void setCd_usuario(long cd_usuario) {
		this.cd_usuario = cd_usuario;
	}
	public PerfilUsuario getPerfilUsuario() {
		return perfilUsuario;
	}
	public void setPerfilUsuario(PerfilUsuario perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDt_cadastro() {
		return dt_cadastro;
	}
	public void setDt_cadastro(Date dt_cadastro) {
		this.dt_cadastro = dt_cadastro;
	}

	
    
}
