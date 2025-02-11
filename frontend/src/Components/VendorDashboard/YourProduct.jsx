import React, { useState, useEffect } from 'react';
import axios from 'axios';

export default function YourProduct() {

    const [products, setProducts] = useState([]); // List of products
    const [editingId, setEditingId] = useState(null); // Product ID being edited
    const [editedProduct, setEditedProduct] = useState({}); // Edited product details
    const [newProduct, setNewProduct] = useState({
        name: '',
        price: '',
        quantityInStock: '',
        description: ''
    }); // New product data for adding
    const [isLoading, setIsLoading] = useState(true); // Loading state for data fetching

    const user = localStorage.getItem('user');
    let token = "";
    if (user != null) {
        token = JSON.parse(user).jwt;
    }

    useEffect(() => {
        // Fetching product list on component mount
        axios.get("http://localhost:8080/products/vendor_products", {
            headers: { Authorization: `Bearer ${token}` }
        })
            .then((response) => {
                setProducts(response.data);
                setIsLoading(false);
            }).catch((error) => {
                setIsLoading(false);
            });
    }, [token]);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setEditedProduct((prev) => ({
            ...prev,
            [name]: value
        }));
    };

    const handleAddInputChange = (e) => {
        const { name, value } = e.target;
        setNewProduct((prev) => ({
            ...prev,
            [name]: value
        }));
    };

    const handleEditClick = (id, product) => {
        setEditingId(id);
        setEditedProduct(product);
    };

    const handleSave = (id) => {
        // Send PUT request to save the updated product
        axios.put(`http://localhost:8080/products/update_product`, editedProduct, {
            headers: { Authorization: `Bearer ${token}` }
        })
            .then((response) => {
                setProducts((prevProducts) =>
                    prevProducts.map((product) =>
                        product.id === id ? { ...product, ...editedProduct } : product
                    )
                );
                setEditingId(null); // Clear editing state
            }).catch((error) => {
                console.error(error);
            });
    };

    const handleDelete = (id) => {
        // Send DELETE request to delete the product
        axios.delete(`http://localhost:8080/products/delete/${id}`, {
            headers: { Authorization: `Bearer ${token}` }
        })
            .then(() => {
                setProducts(products.filter(product => product.id !== id));
            }).catch((error) => {
                console.error(error);
            });
    };

    const handleAddProduct = () => {
        // Send POST request to add new product
        axios.post("http://localhost:8080/products/add", newProduct, {
            headers: { Authorization: `Bearer ${token}` }
        })
            .then((response) => {
                setProducts([...products, response.data]); // Add the new product to the list
                setNewProduct({
                    name: '',
                    price: '',
                    quantityInStock: '',
                    description: ''
                }); // Reset the add product form
            }).catch((error) => {
                console.error(error);
            });
    };

    return (
        <div className="container mt-4">
            <h2 className="mb-3 text-center">📦 Product List</h2>

            <div className="card p-3 shadow-lg">
                {/* Product List */}
                {isLoading ? (
                    <p className="text-center text-muted">Loading products...</p>
                ) : products.length === 0 ? (
                    <p className="text-center text-muted">No products available.</p>
                ) : (
                    <table className="table table-hover">
                        <thead className="table-dark">
                            <tr>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Stock Quantity</th>
                                <th>Description</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {products.map((product) => (
                                <tr key={product.id}>
                                    {editingId === product.id ? (
                                        <>
                                            <td><input type="text" className="form-control" name="name" value={editedProduct.name} onChange={handleInputChange} /></td>
                                            <td><input type="number" className="form-control" name="price" value={editedProduct.price} onChange={handleInputChange} /></td>
                                            <td><input type="number" className="form-control" name="quantityInStock" value={editedProduct.quantityInStock} onChange={handleInputChange} /></td>
                                            <td><input type="text" className="form-control" name="description" value={editedProduct.description} onChange={handleInputChange} /></td>
                                            <td>
                                                <button className="btn btn-success btn-sm me-2" onClick={() => handleSave(product.id)}>✔ Save</button>
                                                <button className="btn btn-secondary btn-sm" onClick={() => setEditingId(null)}>✖ Cancel</button>
                                            </td>
                                        </>
                                    ) : (
                                        <>
                                            <td>{product.name}</td>
                                            <td>{product.price}</td>
                                            <td>{product.quantityInStock}</td>
                                            <td>{product.description}</td>
                                            <td>
                                                <button className="btn btn-primary btn-sm me-2" onClick={() => handleEditClick(product.id, product)}>✏ Edit</button>
                                                <button className="btn btn-danger btn-sm" onClick={() => handleDelete(product.id)}>🗑 Delete</button>
                                            </td>
                                        </>
                                    )}
                                </tr>
                            ))}
                        </tbody>
                    </table>
                )}
            </div>

        </div>
    );
}
