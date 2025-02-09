package com.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.dtos.product.ProductReqDto;
import com.ecom.dtos.product.ProductRespDto;
import com.ecom.services.ProductService;


@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
	
	@Autowired
	private ProductService prodService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addProduct(@RequestBody ProductReqDto newProduct)
	{			
		try
		{
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(prodService.addProduct(newProduct));
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
			
	}
	
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
//	
//	@GetMapping("/view/{catId}")
//	public ResponseEntity<?> getCategoryAndProducts(@PathVariable Integer id)
//	{
//		try
//		{			
//			return ResponseEntity.ok(prodService.getCategoryAndProducts(id));
//		}
//		catch(RuntimeException e)
//		{
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
//		}
//	}
	
	 @PostMapping("image")
	    public String createAd(
	        @RequestParam("files") MultipartFile[] adsImages,
	        @RequestParam("price") Double price
	        // Add other parameters
	    ) {
	       
	        for (MultipartFile imageFile : adsImages) {
	            System.out.println("Image recieved " + imageFile.getName());
	        }
	        System.out.println("price " + price);
	        // Save the adsImagesString in your database
	        // You can also associate it with other data in your Ads object
	        return "Success";
	    }

}
