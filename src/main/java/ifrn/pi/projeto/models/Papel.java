package ifrn.pi.projeto.models;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Papel")
public class Papel implements GrantedAuthority, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID papelid;
	@Column(nullable = false, unique = true)
	private String nome;

	public UUID getPapelid() {
		return papelid;
	}

	public void setPapelid(UUID papelid) {
		this.papelid = papelid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String getAuthority() {
// TODO Auto-generated method stub
		return this.nome.toString();
	}

}
