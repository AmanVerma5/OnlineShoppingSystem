
import { Route, Routes } from "react-router-dom";
import "./App.css";
import HomePage from "./Pages/HomePage";
import LoginPage from "./Pages/LoginPage";
import RegisterPage from "./Pages/RegisterPage";
import ProductListPage from "./Pages/ProductListPage";
import DemoPage from "./Pages/DemoPage";
import CartPage from "./Pages/CartPage";
import { ToastContainer } from "react-toastify";
import UserProfile from "./Pages/UserProfile";
import VendorDashboardPage from "./Pages/VendorDashboardPage";
function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="login" element={<LoginPage />} />
        <Route path="user/register" element={<RegisterPage role={`CUSTOMER`} />} />
        <Route path="vendor/register" element={<RegisterPage role={`VENDOR`} />} />
        <Route path="products" element={<ProductListPage />} />
        <Route path="demo" element={<DemoPage />} />
        <Route path="cart" element={<CartPage />} />
        <Route path="profile" element={<UserProfile />} />
        <Route path="vendor" element={<VendorDashboardPage />} />
      </Routes>
      <ToastContainer />
    </>

  );
}

export default App;
