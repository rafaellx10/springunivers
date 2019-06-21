package open.digytal.webapi.model.cadastro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_cidade")
public class Cidade {
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer ibge;
	@Column(length=50,nullable=false)
	private String nome;
	@Column(length=2,nullable=false)
	private String estado;
	
	public Integer getIbge() {
		return ibge;
	}
	public void setIbge(Integer ibge) {
		this.ibge = ibge;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}

