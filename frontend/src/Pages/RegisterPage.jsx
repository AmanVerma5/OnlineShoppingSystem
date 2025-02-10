import Navbar from "../Components/Navbar/Navbar"
import Register from "../Components/Register/Register"



const RegisterPage=({role})=>{
    return(
        <div className="register-page">
        <Navbar flag={false}/>
        <Register role={`${role}`}/>
        </div>
    )
}


export default RegisterPage