package com.mballem.curso.boot.domain;
import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@SuppressWarnings("serial")
@Entity
@Table(name = "FUNCIONARIOS")
public class Funcionario extends AbstractEntity<Long> {
	
	/*########################################################################################################*/


//	@NotBlank
//	@Size(max = 9)
//	@Column(nullable = false, unique = true)
//	private String matricula;


	@NotBlank
	@Size(max = 255, min = 3)
	@Column(nullable = false, unique = true)		
	private String nome;

	@NotNull
	@Size(max = 9)
	@Column(nullable = false, unique = true)
	private String matricula;


	@NotNull
	@PastOrPresent(message = "{PastOrPresent.funcionario.dataEntrada}")
	@DateTimeFormat(iso = ISO.DATE)//Aqui é o formato de como será salvo
	@Column(name = "data_entrada", nullable = false, columnDefinition = "DATE")
	private LocalDate dataEntrada;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_saida", nullable = true, columnDefinition = "DATE")
	private LocalDate dataSaida;
	
	/*########################################################################################################*/
	@Valid//estou dizendo que esse objeto deve ser validado conforme as instruções que tenho na classe Endereco
	@OneToOne(cascade = CascadeType.ALL)//cascade diz que se alterarmos funcionario também alteraremos endereco
	@JoinColumn(name = "endereco_id_fk")
	private Endereco endereco;
	
	@NotNull(message = "{NotNull.funcionario.cargo}")
	@ManyToOne
	@JoinColumn(name = "cargo_id_fk")
	private Cargo cargo;

	public String getNome() {
		return nome;
	}
	
	/*########################################################################################################*/
	
	public void setNome(String nome) {
		this.nome = nome;
	}

//	public BigDecimal getSalario() {
//		return salario;
//	}
//
//	public void setSalario(BigDecimal salario) {
//		this.salario = salario;
//	}


	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDate getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDate dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

//	public String getMatricula() {
//		return matricula;
//	}
//
//	public void setMatricula(String matricula) {
//		this.matricula = matricula;
//	}
}
