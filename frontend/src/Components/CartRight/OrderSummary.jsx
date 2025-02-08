import './OrderSummary.css';

const OrderSummary=()=>{
    return(
        <div className="mt-3 right-container container">
            <h2 className='order-summary mt-3'>Order Summary</h2>
            <div className="mt-3">Total Items:</div>
            <div className="mt-3">Cart Total:</div>
            <div className="mt-3">Delivery Address:<span></span></div>
            <button className="btn btn-info mt-3">Change Address</button>
            <div className="btn btn-info mt-3">Pay Now</div>
        </div>
    )
}

export default OrderSummary