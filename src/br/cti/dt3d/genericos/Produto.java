package br.cti.dt3d.genericos;

public class Produto {
	
	String lab, preco, apresentacao, nome;
	String subst;
	String hospt;
	
	public Produto(String subst, String lab, String nome, String apresentacao, String preco,
			String hospt){
		this.nome = nome;
		this.apresentacao=apresentacao;
		this.hospt=hospt;
		this.lab=lab;
		this.preco=preco;
		this.subst=subst;
	}
}
