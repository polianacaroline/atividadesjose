package br.com.projeto.papelaria.dominio;

public class Pagamento {
private Long idPagamento;
private Pedido pedido;
private TipoPagamento Tipo;
private String descricao;
private Double valor;
private int parcelas;
private Double valorParcelas;


public Long getIdPagamento() {
	return idPagamento;
}
public void setIdPagamento(Long idPagamento) {
	this.idPagamento = idPagamento;
}
public Pedido getPedido() {
	return pedido;
}
public void setPedido(Pedido pedido) {
	this.pedido = pedido;
}
public TipoPagamento getTipo() {
	return Tipo;
}
public void setTipo(TipoPagamento tipo) {
	Tipo = tipo;
}
public String getDescricao() {
	return descricao;
}
public void setDescricao(String descricao) {
	this.descricao = descricao;
}
public Double getValor() {
	return valor;
}
public void setValor(Double valor) {
	this.valor = valor;
}
public int getParcelas() {
	return parcelas;
}
public void setParcelas(int parcelas) {
	this.parcelas = parcelas;
}
public Double getValorParcelas() {
	return valorParcelas;
}
public void setValorParcelas(Double valorParcelas) {
	this.valorParcelas = valorParcelas;
}

}