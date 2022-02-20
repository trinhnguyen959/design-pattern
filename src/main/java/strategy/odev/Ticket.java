package strategy.odev;

public class Ticket {
    private IPromotedStrategy promotedStrategy;
    private Double price;
    private String name;

    public Ticket() {
    }

    public Ticket(IPromotedStrategy promotedStrategy) {
        this.promotedStrategy = promotedStrategy;
    }

    public IPromotedStrategy getPromotedStrategy() {
        return promotedStrategy;
    }

    public void setPromotedStrategy(IPromotedStrategy promotedStrategy) {
        this.promotedStrategy = promotedStrategy;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPromotedPrice() {
        return promotedStrategy.doDiscount(price);
    }
}
