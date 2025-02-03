import ListProduct from "../Components/ListProduct/ListProduct"
import Navbar from "../Components/Navbar/Navbar"
import  product from '../DemoData/data';


const ProductListPage=()=>{
    return(
        <>
        <Navbar flag={true}/>
        <div className="productlist-container">
        {
          product && product.map((p)=>(
            <ListProduct product={p}/>
          ))  
        }
        </div>
        </>
    )
}

export default ProductListPage