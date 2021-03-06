package com.web.app.webapp.beans;

import com.web.app.webapp.entity.Articles;
import com.web.app.webapp.entity.Bill;
import com.web.app.webapp.entity.facade.ArticlesFacadeLocal;
import com.web.app.webapp.entity.facade.BillFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
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
    private List<Articles> _cart = new ArrayList<>();
    private List<Bill> _bills;
    private int amountTemp;
    private Integer articleId = null;
    private String articleName = null;
    private int articleAmount = 0;
    private double articlePrice = 0.0;
    private String bill;

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

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public int getArticleAmount() {
        return articleAmount;
    }

    public void setArticleAmount(int articleAmount) {
        this.articleAmount = articleAmount;
    }

    public double getArticlePrice() {
        return articlePrice;
    }

    public void setArticlePrice(double articlePrice) {
        this.articlePrice = articlePrice;
    }

    public String addArticle(Integer articleId) {
        Articles articleTemp = articlesFacadeLocal.find(articleId);
        String amount = amountTemp + "";
        
        Integer amountInt = Integer.parseInt(articleTemp.getAmount());
        articleTemp.setAmount(amount);
        _cart.add(articleTemp);
        return "";
    }

    public String login() {
        return "login";
    }

    public String buy() {
        double articleTotalPrice = 0.0;
        double totalPrice = 0.0;
        for (Articles item : _cart) {
            double price = item.getPrice();
            double amountDouble = Double.parseDouble(item.getAmount());
            articleTotalPrice = price * amountDouble;
            bill = item.getId().toString() + " " + item.getName() + " " + item.getAmount() + " " + articleTotalPrice;
            totalPrice += articleTotalPrice;
        }
        Date date = new Date();
        Bill billInsert = new Bill();
        billInsert.setBillContent(bill);
        billInsert.setCreatedAt(date);
        billInsert.setTotalPrice(totalPrice);
        billFacadeLocal.create(billInsert);
        return "bought";
    }

    public String addArticle() {
        String articleAmountString = articleAmount + "";
        Articles articleTemp = new Articles(null, articleName, articleAmountString, articlePrice);
        articlesFacadeLocal.create(articleTemp);
        init();
        return "index";
    }

    public String removeArticle(Integer articleId) {
        Articles articleTemp = articlesFacadeLocal.find(articleId);
        articlesFacadeLocal.remove(articleTemp);
        init();
        return "index";
    }

    public String updateArticle() {
        Articles articleTemp = articlesFacadeLocal.find(articleId);
        String articleAmountString = articleAmount + "";
        if (articleTemp != null) {
            articleTemp.setName(articleName);
            articleTemp.setAmount(articleAmountString);
            articleTemp.setPrice(articlePrice);
            articlesFacadeLocal.edit(articleTemp);
            init();
        }
        return "write";
    }

}
