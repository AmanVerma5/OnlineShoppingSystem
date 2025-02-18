import "./Section.css";
import Product from "../Product/Product";
import { useEffect, useState } from "react";
import { getCategories } from "../../Services/ProductService";
import { toast } from "react-toastify";
import axios from "axios";

const Section = () => {
  const [category, setCategory] = useState([]);
  const [product, setProduct] = useState([]);

  useEffect(() => {
    const fetchCategories = async () => {
      const response = await getCategories();
      if (response.data != null) {
        console.log(response.data);
        setCategory(response.data);
      } else {
        toast.error(response.error)
      }
    }
    fetchCategories();
  }, []);

  useEffect(() => {
    setProduct([]);
    
    const fetchAllProducts = async () => {
      try {
        const promises = category.map(category => 
          axios.get(`http://localhost:8080/categories/${category.id}/products`)
        );
        
        const responses = await Promise.all(promises);
        
        // Map through responses and add categoryId to each product
        const allProducts = responses.flatMap(response => {
          const categoryId = response.data.id; // Get the category ID from the response
          return response.data.products.map(product => ({
            ...product,
            categoryId // Add categoryId to each product
          }));
        });
        
        setProduct(allProducts);
      } catch (error) {
        console.log(error);
        toast.error("Error fetching products");
      }
    };
  
    if (category.length > 0) {
      fetchAllProducts();
    }
  }, [category]);


  return (
    <div>
      {category &&
        category.map((c) => (
          <div key={c.id} className="section container-fluid mt-3">
            <h2>{c.categoryName}</h2>
            <div className="product-list container-fluid">
              {product
                .filter((p) => p.categoryId === c.id)
                .map((p) => (
                  <Product key={p.id} item={p} />
                ))}
            </div>
          </div>
        ))}
    </div>
  );
};

export default Section;