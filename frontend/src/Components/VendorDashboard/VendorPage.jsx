import { useState } from 'react';
import "./VendorPage.css"
import AddProduct from './AddProduct';
import YourProduct from './YourProduct';



const VendorPage = () => {
    const [activeComponent, setActiveComponent] = useState("profile");
    const renderComponent = () => {
        switch (activeComponent) {
            case "allproducts":
                return <YourProduct />;
            case "addproduct":
                return <AddProduct />;
            default:
                return <YourProduct />;
        }
    };
    return (
        <div className="container-fluid profile-page">
            <div className="row inner-container">
                <div className="col-3 profile-left">
                    <div className="mt-5 mx-4">
                        <div onClick={() => setActiveComponent("allproducts")} className="menu-item ps-3 pb-3 mb-2 pt-2">Your Products</div>
                        <div onClick={() => setActiveComponent("addproduct")} className="menu-item ps-3 pb-3 mb-2 pt-2">Add Products</div>

                    </div>
                </div>
                <div className="col">{renderComponent()}</div>
            </div>
        </div>

    )
}



export default VendorPage