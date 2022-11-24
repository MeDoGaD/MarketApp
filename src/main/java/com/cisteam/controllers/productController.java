package com.cisteam.controllers;

import com.cisteam.Repository.ProductDAO;
import com.cisteam.Repository.ProductDAOImpl;
import com.cisteam.models.Cart;
import com.cisteam.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class productController {

    @Autowired
    private ProductDAOImpl productDAO;


/////////////////////////////////////for Admins **********************************************************
    @RequestMapping(value = "/getAllProducts-admin")
    public ModelAndView getAllProducts(ModelMap map){
        return new ModelAndView("all-Product","products",productDAO.getAllProducts());
    }

    @RequestMapping(value = "/add-product-admin",method = RequestMethod.GET)
    public String addProduct(){
        return "add-product";
    }

    @RequestMapping(value = "/add-product-admin",method = RequestMethod.POST)
    public String addProductToDB(@RequestParam String name,@RequestParam int quantity,@RequestParam double price){
        productDAO.addProduct(new Product(name,quantity,price));
        return "redirect:getAllProducts";
    }

    @RequestMapping(value = "/edit-product-admin",method = RequestMethod.GET)
    public String editProduct(@RequestParam int id, ModelMap modelMap){
        modelMap.addAttribute("product",productDAO.getProductById(id));
        return "edit-product";
    }

    @RequestMapping(value = "/edit-product-admin",method = RequestMethod.POST)
    public String editProductInDB(Product product){
        productDAO.editProduct(product);
        return "redirect:getAllProducts";
    }

    @RequestMapping(value = "/delete-product-admin",method = RequestMethod.GET)
    public String deleteProduct(@RequestParam int id){
        productDAO.deleteProduct(id);
        return "redirect:getAllProducts";
    }

    ///////////////////////////////////// for Users ***************************************

    @RequestMapping(value = "/viewProducts")
    public ModelAndView getAllProductsForUser(ModelMap map){
        return new ModelAndView("user-allProduct","products",productDAO.getAllProducts());
    }

    @RequestMapping(value = "/add-product-user",method = RequestMethod.GET)
    public String addProductToCart(@RequestParam("id") int id,@RequestParam int quantity){
        Product product=productDAO.getProductById(id);
        product.setQuantity(quantity);
        Cart.myCart.IncrementTotalBy(product.getQuantity()*product.getPrice());
        Cart.myCart.addProductToCart(product);
        return "redirect:viewProducts";
    }

    @RequestMapping(value = "/cart")
    public ModelAndView cart(ModelMap map){
        ModelAndView mv=new ModelAndView("user-productcart");
        mv.addObject("cart_products",Cart.myCart.getProducts());
        mv.addObject("total",Cart.myCart.getTotal_Price());
        return mv;
    }

    @RequestMapping(value = "/delete-product-cart",method = RequestMethod.GET)
    public String deleteProductFromCart(@RequestParam int id){
         Cart.myCart.removeProductFromCart(id);
         return "redirect:cart";
    }

}
