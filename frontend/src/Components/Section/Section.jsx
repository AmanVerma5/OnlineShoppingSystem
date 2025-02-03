import "./Section.css";
import category from "../../DemoData/category";
import product from "../../DemoData/data";
import Product from "../Product/Product";

const Section = () => {
  return (
    <div>
      {category &&
        category.map((c) => (
          <div key={c.id} className="section container-fluid mt-3">
            <h2>{c.name}</h2>
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
