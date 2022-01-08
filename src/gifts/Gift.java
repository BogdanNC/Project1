package gifts;

public final class Gift {
    private final String productName;
    private final Double price;
    private final String category;

    public Gift(final String productName, final Double price, final String category) {
        this.productName = productName;
        this.price = price;
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public Double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
}
