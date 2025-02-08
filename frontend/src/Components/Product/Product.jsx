import { useState } from "react";
import "./Product.css";

const Product = ({ item }) => {
  const [add, setAdd] = useState(true);

  function switchAddState() {
    if (add === true) {
      setAdd(false);
    } else {
      setAdd(true);
    }
  }

  return (
    <div className="card mx-2 product-container" style={{ width: "18rem" }}>
      <img
        src={item.image}
        class="card-img-top"
        alt="..."
        style={{ height: "50%" }}
      />
      <div className="card-body">
        <h5 className="card-title">{item.name}</h5>
        <p className="card-text">
          Some quick example text to build on the card title and make up the
          bulk of the card's content.
        </p>
      </div>
      <ul className="list-group list-group-flush">
        <li className="list-group-item">Price: ${item.price}</li>
      </ul>

      {add ? (
        <div className="btn btn-warning m-1" onClick={switchAddState}>
          Add to Cart
        </div>
      ) : (
        <div className="btn btn-warning m-1" onClick={switchAddState}>
          Remove from Cart
        </div>
      )}
    </div>
  );
};

export default Product;
