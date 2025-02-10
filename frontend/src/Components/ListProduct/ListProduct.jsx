import { useState } from 'react';
import './ListProduct.css';  // Custom CSS for further styling if needed

const ListProduct = ({ product }) => {
    const [quantity, setQuantity] = useState(1);  // Default quantity set to 1

    // Handle increase quantity
    const handleIncrease = () => {
        setQuantity(prevQuantity => prevQuantity + 1);
    };

    // Handle decrease quantity
    const handleDecrease = () => {
        if (quantity > 1) {
            setQuantity(prevQuantity => prevQuantity - 1);
        }
    };

    return (
        <div className="card mb-3 shadow-sm">
            <div className="row no-gutters">
                <div className="col-md-4">
                    <img src={product.image} alt={product.name} className="img-fluid rounded-start" />
                </div>
                <div className="col-md-8">
                    <div className="card-body">
                        <h5 className="card-title">{product.name}</h5>
                        <p className="card-text">{product.description}</p>
                        <p className="card-text"><strong>Price:</strong> ${product.price}</p>

                        {/* Quantity Section */}
                        <div className="d-flex align-items-center">
                            <span className='me-3'><strong>Quantity:</strong></span>
                            <button
                                className="btn btn-outline-danger btn-sm me-2 d-flex justify-content-center align-items-center"
                                onClick={handleDecrease}
                            >
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="bi bi-dash-circle" viewBox="0 0 16 16">
                                    <path d="M4 8a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7A.5.5 0 0 1 4 8"/>
                                </svg>
                            </button>
                            <span className="fw-bold">{quantity}</span>
                            <button
                                className="btn btn-outline-success btn-sm ms-2 d-flex justify-content-center align-items-center"
                                onClick={handleIncrease}
                            >
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="bi bi-plus" viewBox="0 0 16 16">
                                    <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4"/>
                                </svg>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ListProduct;
