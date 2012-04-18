package br.cti.dt3d.genericos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.content.res.AssetManager;
import android.util.Log;

public class Database {
	
	FileInputStream file;
	List<Produto> lista = new ArrayList<Produto>();
	boolean fim=false;
	String path="/assets/";
	String filename1 = "db1.csv";
	String filename2 = "db2.csv";
	String filename3 = "db3.csv";
	
	
	public Database(AssetManager a){
				
		BufferedReader input1=null;
		BufferedReader input2=null;
		BufferedReader input3=null;
		String linha = "00";
		String dados[];
		Produto prod;
				
		try{
			//File file = new File(path, filename);
			InputStream i1 = a.open(filename1);
			input1 =  new BufferedReader(new InputStreamReader(i1));
			InputStream i2 = a.open(filename2);
			input2 =  new BufferedReader(new InputStreamReader(i2));
			InputStream i3 = a.open(filename3);
			input3 =  new BufferedReader(new InputStreamReader(i3));
		}catch(FileNotFoundException f){
			fim=true;
		}catch(IOException io){
			fim=true;
		}
		
		try{
			while((linha=input1.readLine())!=null){

				dados=linha.split(";");	
					prod = new Produto(dados[0].trim(),dados[1].trim(),dados[2].trim(),dados[3].trim(),
							dados[4].trim(),dados[5].trim());
					lista.add(prod);
			}
			while((linha=input2.readLine())!=null){
				
				dados=linha.split(";");
				prod = new Produto(dados[0].trim(),dados[1].trim(),dados[2].trim(),dados[3].trim(),
						dados[4].trim(),dados[5].trim());
				lista.add(prod);
			}
			while((linha=input3.readLine())!=null){
				
				dados=linha.split(";");
				prod = new Produto(dados[0].trim(),dados[1].trim(),dados[2].trim(),dados[3].trim(),
						dados[4].trim(),dados[5].trim());
				lista.add(prod);
			}
		} catch (IOException e) {
			fim=true;
		}catch(NullPointerException n){
			fim=true;
		}
		try{
			input1.close();
			input2.close();
			input3.close();
		
		}catch(IOException e){
		}catch(NullPointerException n){
		}
	}
	
	public List<Produto> procuraNome(String nome){
		
		List<Produto> result = new ArrayList<Produto>();
		int i;
		int tamanho;
		Produto p;
		
		tamanho= lista.size();
		
		nome = nome.trim();
		for(i=0;i<tamanho;i++){
			p = lista.get(i);
			if(nome.equals(p.nome))result.add(p);
		}
		Log.v("Gen",String.valueOf(result.size()));
		return result;
		
	}
	
	public List<Produto> procuraSubst(String subst){
		
		List<Produto> result = new ArrayList<Produto>();
		int i;
		int tamanho;
		Produto p;
		
		tamanho= lista.size();
		subst = subst.toLowerCase();
		for(i=0;i<tamanho;i++){
			p = lista.get(i);
			if(p.subst.toLowerCase().equals(subst))result.add(p);
		}
		return result;
		
	}
}
