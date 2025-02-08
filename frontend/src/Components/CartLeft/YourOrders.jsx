import { useEffect, useState } from "react";
import "./YourOrders.css";
import product from "../../DemoData/data";
import ListProduct from "../ListProduct/ListProduct";

const YourOrders = () => {
  const [containerHeight, setContainerHeight] = useState("100vh");

  function calculateHeight() {
    const cartTitle = document.querySelector(".cart-title");
    const navbar = document.querySelector(".navbar");

    const navbarHeight = navbar ? navbar.offsetHeight : 0;
    const cartTitleHeight = cartTitle ? cartTitle.offsetHeight : 0;

    console.log("Navbar Height:", navbarHeight);
    console.log("Cart Title Height:", cartTitleHeight);

    const remainingHeight = window.innerHeight - navbarHeight - cartTitleHeight - 30;
    setContainerHeight(`${remainingHeight}px`);
  }

  useEffect(() => {
    calculateHeight(); // Run after initial render
    window.addEventListener("resize", calculateHeight);
    
    return () => window.removeEventListener("resize", calculateHeight); // Cleanup
  }, []);

  return (
    <div className="row left-container mt-3 mx-2">
      <div className="container">
        <h2 className="cart-title">Your Cart</h2>
        <div className="scrollable-container" style={{ height: containerHeight, overflowY: "auto" }}>
          {product &&
            product.map((p) => (
              <div key={p.id} className="product-container">
                <ListProduct product={p} />
              </div>
            ))}
        </div>
      </div>
    </div>
  );
};

export default YourOrders;
