package com.gitlab.pawelkorniak.clientapp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


import java.util.Objects;

@Entity
@Table(name="orders")
public class Order {
    private @Id
    @GeneratedValue Long id;
    private String content;

    Order() {}

    Order(String order) {

        this.content = order;
    }

    public Long getId() {
        return this.id;
    }

    public String getContent() {
        return this.content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Order))
            return false;
        Order employee = (Order) o;
        return Objects.equals(this.id, employee.id) && Objects.equals(this.content, employee.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.content);
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + this.id + ", order='" + this.content + '\'' + '}';
    }
}
