package utils.restAssured;

public enum APIResources {

	CreateAccount("createAccrount"), GetAccount("account/"), getTransaction("account/transaction"),
	BillPay("billpay?accountId");

	private String resource;

	APIResources(String resource) {
		this.resource = resource;
	}

	public String getResource() {
		return resource;
	}
}
