package com.web.app.webapp.beans;

import com.web.app.webapp.entity.Articles;
import com.web.app.webapp.entity.Bill;
import com.web.app.webapp.entity.facade.ArticlesFacadeLocal;
import com.web.app.webapp.entity.facade.BillFacadeLocal;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "articlesManagedBean")
@RequestScoped
public class ArticlesManagedBean implements Serializable {

    private List<Articles> _articlesList;
    private List<Articles> _cart;
    private List<Bill> _bills;
    private int amountTemp;

    @Inject
    ArticlesFacadeLocal articlesFacadeLocal;

    @Inject
    BillFacadeLocal billFacadeLocal;

    @PostConstruct
    private void init() {
        _articlesList = articlesFacadeLocal.findAll();
        _bills = billFacadeLocal.findAll();
    }

    public ArticlesManagedBean() {

    }

    public List<Articles> getArticlesList() {
        return _articlesList;
    }

    public void setArticlesList(List<Articles> _articlesList) {
        this._articlesList = _articlesList;
    }

    public int getAmountTemp() {
        return amountTemp;
    }

    public void setAmountTemp(int amountTemp) {
        this.amountTemp = amountTemp;
    }

    public List<Bill> getBills() {
        return _bills;
    }

    public void setBills(List<Bill> _bills) {
        this._bills = _bills;
    }

    public String addArticle(Integer articleId) {
        Articles articleTemp = articlesFacadeLocal.find(articleId);
        String amount = amountTemp + "";
        articleTemp.setAmount(amount);
        _cart.add(articleTemp);
        return "";
    }

    public String buy() {
        String bill = null;
        double articleTotalPrice = 0.0;
        double totalPrice = 0.0;
        for (Articles item : _cart) {
            double price = item.getPrice();
            double amountDouble = Double.parseDouble(item.getAmount());
            articleTotalPrice = price * amountDouble;
            bill = item.getId() + " " + item.getName() + " " + item.getAmount() + " " + articleTotalPrice;
            totalPrice += articleTotalPrice;
        }
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        Bill billInsert = new Bill(null, bill, totalPrice, timestamp);
        billFacadeLocal.create(billInsert);
        return "bought";
    }
    
    public String login(){
        return "";
    }

}