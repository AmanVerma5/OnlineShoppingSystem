import './ListProduct.css';





const ListProduct=({product})=>{
    return(
        <div className='list-product-container'>
            <div className='product-image'>
                <img src={product.image} alt=""/>
            </div>
            <div className='product-details'>
                <div className='product-name'><strong>Name:</strong> {product.name}</div>
                <div className='product-desc'><strong>Description:</strong> {product.description}</div>
                <div className='product-price'><strong>Price:</strong> {product.price}</div>
                <div className='addtocart-btn'>
                    <button className='btn btn-info'>Add to cart</button>
                </div>
            </div>
        </div>
    )
}

export default ListProduct