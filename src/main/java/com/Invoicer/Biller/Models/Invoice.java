package com.Invoicer.Biller.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;

@Document(collection = "invoices")
public class Invoice {
    @Id
    private String payerName;
    private String payerEmail;
    private Instant dueDate;
    private String invoiceNo;
    private ArrayList<Product> products;
    private String freeTextOne;
    private String freeTextTwo;
    private Integer templateType;
    private String footer;
    private Double total;
    private String createdBy;
    private Instant creationTime;

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPayerEmail() {
        return payerEmail;
    }

    public void setPayerEmail(String payerEmail) {
        this.payerEmail = payerEmail;
    }

    public Instant getDueDate() {
        return dueDate;
    }

    public void setDueDate(Instant dueDate) {
        this.dueDate = dueDate;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public String getFreeTextOne() {
        return freeTextOne;
    }

    public void setFreeTextOne(String freeTextOne) {
        this.freeTextOne = freeTextOne;
    }

    public String getFreeTextTwo() {
        return freeTextTwo;
    }

    public void setFreeTextTwo(String freeTextTwo) {
        this.freeTextTwo = freeTextTwo;
    }

    public Integer getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Integer templateType) {
        this.templateType = templateType;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Instant creationTime) {
        this.creationTime = creationTime;
    }
}
