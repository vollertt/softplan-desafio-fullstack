package com.desafiosoftplan.apirest.models;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

@Entity
@Table(name="processo")
public class Processo implements Serializable{
	
    private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long cd_processo;
	@NotNull
	@Column(nullable = false)
	@Length(max = 30)  
	private String num_processo;
	@NotNull
	@Column(nullable = false)
	private String ds_processo;
	@Length(max = 5000)  
	private String parecer;
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date dt_parecer_inc;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dt_parecer_mod;
	@NotNull
	@Column(nullable = false)
	private boolean status_finalizado=false;		
	@NotNull
	@Column(name = "cd_usuario_cadastro", nullable = false)
    @JoinColumn(name="cd_usuario")
	private long cd_usuario_cadastro;
	@NotNull
	@Column(name = "cd_usuario_finaliza", nullable = false)
	@JoinColumn(name="cd_usuario")
	private long cd_usuario_finaliza;
	
	
	public long getCd_processo() {
		return cd_processo;
	}
	public void setCd_processo(long cd_processo) {
		this.cd_processo = cd_processo;
	}
	public String getNum_processo() {
		return num_processo;
	}
	public void setNum_processo(String num_processo) {
		this.num_processo = num_processo;
	}
	public String getDs_processo() {
		return ds_processo;
	}
	public void setDs_processo(String ds_processo) {
		this.ds_processo = ds_processo;
	}
	public String getParecer() {
		return parecer;
	}
	public void setParecer(String parecer) {
		this.parecer = parecer;
	}
	public Date getDt_parecer_inc() {
		return dt_parecer_inc;
	}
	public void setDt_parecer_inc(Date dt_parecer_inc) {
		this.dt_parecer_inc = dt_parecer_inc;
	}
	public Date getDt_parecer_mod() {
		return dt_parecer_mod;
	}
	public void setDt_parecer_mod(Date dt_parecer_mod) {
		this.dt_parecer_mod = dt_parecer_mod;
	}
	public boolean isStatus_finalizado() {
		return status_finalizado;
	}
	public void setStatus_finalizado(boolean status_finalizado) {
		this.status_finalizado = status_finalizado;
	}
	public long getCd_usuario_cadastro() {
		return cd_usuario_cadastro;
	}
	public void setCd_usuario_cadastro(long cd_usuario_cadastro) {
		this.cd_usuario_cadastro = cd_usuario_cadastro;
	}
	public long getCd_usuario_finaliza() {
		return cd_usuario_finaliza;
	}
	public void setCd_usuario_finaliza(long cd_usuario_finaliza) {
		this.cd_usuario_finaliza = cd_usuario_finaliza;
	}
		

}
