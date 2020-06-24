package com.victorpereira.mymarketplace.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.victorpereira.mymarketplace.services.validation.ClientInsert;

@ClientInsert
public class ClientNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Mandatory")
	@Length(min = 5, max = 120, message = "Size must be between 5 and 120 characters")
	private String name;
	
	@NotEmpty(message = "Mandatory")
	@Length(message = "Invalid e-mail")
	private String email;
	
	@NotEmpty(message = "Mandatory")
	private String cpfOrCnpj;
	
	private Integer type;
	
	@NotEmpty(message = "Mandatory")
	private String password;
	
	@NotEmpty(message = "Mandatory")
	private String publicPlace;
	
	@NotEmpty(message = "Mandatory")
	private String number;
	
	private String complement;
	
	private String neighborhood;

	@NotEmpty(message = "Mandatory")
	private String cep;

	@NotEmpty(message = "Mandatory")
	private String tel1;
	
	private String tel2;
	
	private String tel3;
	
	private Integer cityId;
	
	public ClientNewDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOrCnpj() {
		return cpfOrCnpj;
	}

	public void setCpfOrCnpj(String cpfOrCnpj) {
		this.cpfOrCnpj = cpfOrCnpj;
	}

	public Integer getType() {
		return type;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPublicPlace() {
		return publicPlace;
	}

	public void setPublicPlace(String publicPlace) {
		this.publicPlace = publicPlace;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTel1() {
		return tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public String getTel3() {
		return tel3;
	}

	public void setTel3(String tel3) {
		this.tel3 = tel3;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
}
