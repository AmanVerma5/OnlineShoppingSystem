import axios from 'axios';
import React, { useEffect } from 'react';
import { useState } from 'react';
import { Link } from 'react-router-dom';

function Demo() {
  const [activeTab, setActiveTab] = useState('add-product');
  const [image, setImage] = useState();

  const handleTabClick = (tabName) => {
    setActiveTab(tabName);
  };

  const user = localStorage.getItem('user');
  let token = "";
  if (user != null) {
    token = JSON.parse(user).jwt;
  }

  useEffect(() => {
    axios.get("http://localhost:8080/products/vendor_products_with_image", { headers: { Authorization: `Bearer ${token}` } })
    .then((response) => {
      setImage(response.data[0].image);
    }).catch((error) => {console.log(error);})
  })

  return (
    <div className="dashboard-container">
      <div className="sidebar">
        <button
          className={`sidebar-button ${activeTab === 'add-product' ? 'active' : ''}`}
          onClick={() => handleTabClick('add-product')}
        >
          ADD PRODUCT
        </button>
        <button
          className={`sidebar-button ${activeTab === 'inventory' ? 'active' : ''}`}
          onClick={() => handleTabClick('inventory')}
        >
          INVENTORY
        </button>
        <button
          className={`sidebar-button ${activeTab === 'statistics' ? 'active' : ''}`}
          onClick={() => handleTabClick('statistics')}
        >
          STATISTICS
        </button>
      </div>

      <div className="main-content">
        {activeTab === 'add-product' && (
          <div>
            {/* Add Product Component */}
            <Link to="/vendor/add-product">Add Product</Link>
          </div>
        )}
        {activeTab === 'inventory' && (
          <div>
            {/* Inventory Component */}
            <Link to="/vendor/inventory">Inventory</Link>
          </div>
        )}
        {activeTab === 'statistics' && (
          <div>
            {/* Statistics Component */}
            <Link to="/vendor/statistics">Statistics</Link>
          </div>
        )}
      </div>
          <img src={`data:image/jpeg;base64,${image}`} alt='xyz'/>
      <div>
        
      </div>
    </div>
  );
}

export default Demo;