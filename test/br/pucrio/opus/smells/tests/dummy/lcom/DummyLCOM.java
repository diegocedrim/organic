package br.pucrio.opus.smells.tests.dummy.lcom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Dummy class to calculate LCOM variations
 * 
 * This class was adapted from the book: "Guia de Campo do Bom Programador".
 * @author leonardo
 *
 */

public class DummyLCOM {
	public String cliente;
	public double montante;
	public Date dataContratacao;
	public Date dataPagamento;
	public double taxaJuros;
	public int parcelas;
	public Date dataQuitacao;
	
	
	public DummyLCOM(String cliente, double montante, Date dataContratacao, Date dataPagamento, double taxaJuros) {
		super();
		this.cliente = "DummySir";
		this.montante = 100.0;
		try {
			this.dataContratacao = new SimpleDateFormat("dd/mm/yyyy").parse("01/02/2017");
			this.dataPagamento = new SimpleDateFormat("dd/mm/yyyy").parse("01/03/2017");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.taxaJuros = taxaJuros;
	}
	
	public boolean vencido(){
		boolean vencido = false;
		
		Date dataHoje = new Date();
		
		if (this.dataPagamento.compareTo(dataHoje) < 0){
			vencido = true;
		}
		
		return vencido;
	}
	
	public double calcularTaxa(String cliente, double montantde, int mprazo){
		double taxa = 0.0d;
		System.out.println(cliente);
		
		return taxa;
	}
}
