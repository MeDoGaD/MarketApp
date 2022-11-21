package com.cisteam.controllers;

import com.cisteam.Repository.ProductDAO;
import com.cisteam.Repository.ProductDAOImpl;
import com.cisteam.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class productController {

    @Autowired
    private ProductDAOImpl productDAO;



    @RequestMapping(value = "/getAllProducts")
    public ModelAndView getAllProducts(ModelMap map){
        return new ModelAndView("all-Product","products",productDAO.getAllProducts());
    }

    @RequestMapping(value = "/add-product",method = RequestMethod.GET)
    public String addProduct(){
        return "add-product";
    }

    @RequestMapping(value = "/add-product",method = RequestMethod.POST)
    public String addProductToDB(@RequestParam String name,@RequestParam int quantity,@RequestParam double price){
        productDAO.addProduct(new Product(name,quantity,price));
        return "redirect:getAllProducts";
    }

    @RequestMapping(value = "/edit-product",method = RequestMethod.GET)
    public String editProduct(@RequestParam int id, ModelMap modelMap){
        modelMap.addAttribute("product",productDAO.getProductById(id));
        return "edit-product";
    }

    @RequestMapping(value = "/edit-product",method = RequestMethod.POST)
    public String editProductInDB(Product product){
        productDAO.editProduct(product);
        return "redirect:getAllProducts";
    }

    @RequestMapping(value = "/delete-product",method = RequestMethod.GET)
    public String deleteProduct(@RequestParam int id){
        productDAO.deleteProduct(id);
        return "redirect:getAllProducts";
    }
}
