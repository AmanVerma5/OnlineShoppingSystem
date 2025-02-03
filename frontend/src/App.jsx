
import { Route, Routes } from "react-router-dom";
import "./App.css";
import HomePage from "./Pages/HomePage";
import LoginPage from "./Pages/LoginPage";
import RegisterPage from "./Pages/RegisterPage";
import ProductListPage from "./Pages/ProductListPage";
import DemoPage from "./Pages/DemoPage";
function App() {
  return (
    <Routes>
      <Route path="/" element={<HomePage/>}/>
      <Route path="login" element={<LoginPage/>}/>
      <Route path="register" element={<RegisterPage/>}/>
      <Route path="products" element={<ProductListPage/>}/>
      <Route path="demo" element={<DemoPage/>}/>
    </Routes>
  );
}

export default App;
