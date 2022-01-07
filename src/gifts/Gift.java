package gifts;

public final class Gift {
    private final String productName;
    private final Long cost;
    private final String category;

    public Gift(final String productName, final Long cost, final String category) {
        this.productName = productName;
        this.cost = cost;
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public Long getCost() {
        return cost;
    }

    public String getCategory() {
        return category;
    }
}
