package pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Orders {
	@JsonProperty("orders")
	private List<OrderDetails> orders;

	public List<OrderDetails> getOrder() {
		return orders;
	}

	public void setOrder(List<OrderDetails> orders) {
		this.orders = orders;
	}

}
