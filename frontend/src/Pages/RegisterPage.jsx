import Navbar from "../Components/Navbar/Navbar"
import Register from "../Components/Register/Register"



const RegisterPage=()=>{
    return(
        <div className="register-page">
        <Navbar flag={false}/>
        <Register/>
        </div>
    )
}


export default RegisterPage