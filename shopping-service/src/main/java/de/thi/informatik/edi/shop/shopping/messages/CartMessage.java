package de.thi.informatik.edi.shop.shopping.messages;

public class CartMessage {
    private String type;

    public CartMessage() {

    }

    public CartMessage(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
