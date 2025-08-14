package com.jotaempresas.curso.entity.enums;

public enum OrderStatus {

	// dessa forma sem explicitar qual indice cada um esta inserido , é perigoso
	// caso venha acescentar mais um item.
	// para isso devemos realizar a expplicitação de cada indice para cada item
	// colocando entre parentese o valor de cada indice (1)...
	// porém isso da erro no java emtão teremos que fazer o seguinte

	WAITING_PAYMENT(1), // AGUARDANDO_PAGAMENTO
	PAID(2), // PAGO
	SHIPPED(3), // ENVIADO
	DELIVERED(4), // ENTREGUE
	CANCELED(5); // CANCELADO

	// vamoms cliar uma atributo chamada code
	private int code;

	// vamos criar o construtor com arqgumento
	private OrderStatus(int code) {
		this.code = code;

	}

	// vamos fazer o get
	public int getCode() {
		return code;
	}

	// método estatico para identificar e retornar o codigo correspondente

	public static OrderStatus enumOrder(int code){

		for (OrderStatus value : OrderStatus.values()) {
			if (value.getCode() == code) {
				return value;
			}

		}

		throw new IllegalArgumentException("Valor invalido para Order Status");
	}

}
