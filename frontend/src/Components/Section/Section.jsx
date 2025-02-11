import "./Section.css";
import Product from "../Product/Product";
import { useEffect, useState } from "react";
import { getCategories } from "../../Services/ProductService";
import { toast } from "react-toastify";
import axios from "axios";



const Section = () => {

  const [category, setCategory] = useState();
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
    const fetchProducts = (catId) => {
      const response = axios.get(`http://localhost:8080/categories/${catId}/products`)
        .then((response) => {       
          setProduct(response.data)
         })
        .catch((error) => { console.log(error) })
    }
    fetchProducts(2)
  }, [])

  return (
    <div>
      {category &&
        category.map((c) => (
          <div key={c.id} className="section container-fluid mt-3">
            <h2>{c.categoryName}</h2>
            <div className="product-list container-fluid">
              {product
                .filter((p) => p.category_id === c.id)
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
