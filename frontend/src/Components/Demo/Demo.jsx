import React, { useState } from "react";

const Demo = () => {
  const [cart, setCart] = useState([
    { id: 1, name: "Floral Print Wrap Dress", color: "Blue", size: "42", price: 20.5, quantity: 2, image: "/blue-dress.png" },
    { id: 2, name: "Floral Print Wrap Dress", color: "Blue", size: "42", price: 30.5, quantity: 1, image: "/red-dress.png" },
  ]);

  const updateQuantity = (id, amount) => {
    setCart(cart.map(item => (item.id === id ? { ...item, quantity: Math.max(1, item.quantity + amount) } : item)));
  };

  const subtotal = cart.reduce((total, item) => total + item.price * item.quantity, 0);
  const discount = 4;
  const total = subtotal - discount;

  return (
    <div className="container py-5">
      <div className="row">
        <div className="col-md-8">
          <div className="card mb-4">
            <div className="card-body">
              <h2 className="mb-4">Shopping Bag</h2>
              {cart.map((item) => (
                <div key={item.id} className="d-flex align-items-center border-bottom pb-3 mb-3">
                  <img src={item.image} alt={item.name} className="me-3" style={{ width: "80px", height: "120px", objectFit: "cover" }} />
                  <div className="flex-grow-1">
                    <h5>{item.name}</h5>
                    <p className="text-muted">Color: {item.color} • Size: {item.size}</p>
                    <p className="fw-bold">${item.price.toFixed(2)}</p>
                  </div>
                  <div className="d-flex align-items-center">
                    <button className="btn btn-outline-secondary btn-sm" onClick={() => updateQuantity(item.id, -1)}>-</button>
                    <span className="mx-2 fs-5">{item.quantity}</span>
                    <button className="btn btn-outline-secondary btn-sm" onClick={() => updateQuantity(item.id, 1)}>+</button>
                  </div>
                  <p className="ms-4 fw-bold text-warning">${(item.price * item.quantity).toFixed(2)}</p>
                </div>
              ))}
            </div>
          </div>
        </div>
        <div className="col-md-4">
          <div className="card">
            <div className="card-body">
              <h5 className="mb-3">Calculated Shipping</h5>
              <select className="form-select mb-2">
                <option>Country</option>
              </select>
              <select className="form-select mb-2">
                <option>State / City</option>
              </select>
              <input type="text" className="form-control mb-3" placeholder="ZIP Code" />
              <button className="btn btn-primary w-100 mb-3">Update</button>
              <h5 className="mb-3">Coupon Code</h5>
              <div className="input-group mb-3">
                <input type="text" className="form-control" placeholder="Coupon Code" />
                <button className="btn btn-outline-secondary">Apply</button>
              </div>
              <div className="p-3 bg-light rounded">
                <p className="d-flex justify-content-between"><span>Cart Subtotal</span><span>${subtotal.toFixed(2)}</span></p>
                <p className="d-flex justify-content-between"><span>Discount</span><span>-${discount.toFixed(2)}</span></p>
                <p className="d-flex justify-content-between fw-bold fs-5"><span>Cart Total</span><span>${total.toFixed(2)}</span></p>
              </div>
              <button className="btn btn-success w-100 mt-3">Checkout</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Demo;
