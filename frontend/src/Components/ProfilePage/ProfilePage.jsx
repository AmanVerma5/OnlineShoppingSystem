import { useState } from 'react';
import Profile from '../Profile/Profile';
import Password from '../Password/Password';
import './ProfilePage.css';


const Orders = () => <div><h2>Your Orders</h2></div>;
const Addresses = () => <div><h2>Your Addresses</h2></div>;




const ProfilePage=()=>{
    const [activeComponent, setActiveComponent] = useState("profile");
    const renderComponent = () => {
        switch (activeComponent) {
            case "profile":
                return <Profile />;
            case "orders":
                return <Orders />;
            case "addresses":
                return <Addresses />;
            case "password":
                return <Password />;
            default:
                return <Profile />;
        }
    };
    return(
            <div className="container-fluid profile-page">
                <div className="row inner-container">
                <div className="col-3 profile-left">
                <div className="mt-5 mx-4">
                <div onClick={() => setActiveComponent("profile")} className="menu-item ps-3 pb-3 mb-2 pt-2">Your Profile</div>
                <div onClick={() => setActiveComponent("addresses")} className="menu-item ps-3 pb-3 mb-2 pt-2">Your Addresses</div>
                <div onClick={() => setActiveComponent("password")} className="menu-item ps-3 pb-3 mb-2 pt-2">Change Password</div>
                    <div onClick={() => setActiveComponent("orders")} className="menu-item ps-3 pb-3 mb-2 pt-2">Your Orders</div>
                    
                </div>
                </div>
                <div className="col">{renderComponent()}</div>
            </div>
            </div>
     
        )
}



export default ProfilePage