import { Link } from "react-router-dom";
import "./Product.css";

const Product = ({ item }) => {
  return (
    <div class="card mx-2 product-container" style={{ width: "18rem" }}>
      <img src={item.image} class="card-img-top" alt="..." style={{height:"50%"}}/>
      <div class="card-body">
        <h5 class="card-title">{item.name}</h5>
        <p class="card-text">
          Some quick example text to build on the card title and make up the
          bulk of the card's content.
        </p>
      </div>
      <ul class="list-group list-group-flush">
        <li class="list-group-item">Price: ${item.price}</li>
      </ul>
      
      <div class="card-body">
        <Link to="#" class="card-link">
          Card link
        </Link>
        <Link to="#" class="card-link">
          Another link
        </Link>
      </div>
    </div>
  );
};

export default Product;
