import OrderSummary from "../Components/CartRight/OrderSummary";
import Navbar from "../Components/Navbar/Navbar"
import YourOrders from './../Components/CartLeft/YourOrders';
;

const CartPage=()=>{
    return(
        <>
        <Navbar flag={false}/>
        <div className="cart-page-outer">
            <div className="cart-page container-fluid">
            <YourOrders/>
            <OrderSummary/>
            </div>
        </div>
        </>
    )
}


export default CartPage


