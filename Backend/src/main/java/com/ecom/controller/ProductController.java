package com.ecom.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.dtos.ApiResponse;
import com.ecom.dtos.product.ProductRespDto;
import com.ecom.services.ProductService;


@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService prodService;
	
	
	@GetMapping("/view")
	public ResponseEntity<?> getAllProducts()
	{
		List<ProductRespDto> list = prodService.getAllProducts();
		
		if(list.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();	
		}
		else
		{
			return ResponseEntity.ok(list);	
		}
	}
	
	@PutMapping("/purchase/{id}/{qty}")
	public ResponseEntity<?> purchaseProduct(@PathVariable Integer id, @PathVariable int qty)
	{
		return ResponseEntity.ok(prodService.purchaseProduct(id,qty));
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Integer id)
	{
		return ResponseEntity.ok(prodService.deleteProduct(id));
	}		
	
	@PostMapping("/add/{catId}")
    public ResponseEntity<?> addProduct(
        @RequestParam("myfile") MultipartFile[] adsImages,
        @RequestParam("name") String name,
        @RequestParam("price") Double price,
        @RequestParam("quantityInStock") int quantityInStock,
        @RequestParam("description") String description, 
        @PathVariable Integer catId
    ) throws IOException {

        try
		{
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(prodService.addProduct(adsImages,name,price,quantityInStock,description,catId));
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
    }
	

	
	

}
