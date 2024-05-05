package de.thi.informatik.edi.shop.shopping.messages;

import de.thi.informatik.edi.shop.shopping.model.Cart;
import de.thi.informatik.edi.shop.shopping.model.CartEntry;

import java.util.UUID;

public class ArticleAddedMessage extends CartMessage {
    private UUID id;
    private UUID article;
    private String name;
    private int count;
    private double price;

    public ArticleAddedMessage() {
        super("added");
    }

    public UUID getId() {
        return id;
    }

    public UUID getArticle() {
        return article;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public double getPrice() {
        return price;
    }

    public static ArticleAddedMessage fromCartEntry(CartEntry entry, Cart cart) {
        ArticleAddedMessage message = new ArticleAddedMessage();
        message.article = entry.getId();
        message.count = entry.getCount();
        message.id = cart.getId();
        message.name = entry.getName();
        message.price = entry.getPrice();
        return message;
    }
}
