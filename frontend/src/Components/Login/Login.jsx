import { Link, useNavigate } from "react-router-dom";
import "./Login.css";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import { login } from "../../Services/UserServices";


const Login = () => {
  const navigate = useNavigate()

  const [email, setEmail] = useState();
  const [password, setPassword] = useState();

  useEffect(() => {
    //   let user=JSON.parse(localStorage.getItem("user"));
    //  // console.log(user);
    //   if(user!==null){
    //     navigate("/")
    //   }


  })

  async function loginUser() {
    // let users=JSON.parse(localStorage.getItem("users"));
    // let user=users.filter((u)=>u.email===email)
    // console.log(user)
    //console.log(typeof password)
    // if(user.length===0){
    //   toast.error("User does not exists")
    // }else if(user[0].password!==password){
    //   toast.error("Password do not match")
    // }else{
    //   localStorage.setItem("user",JSON.stringify(user))
    //   navigate("/")

    const response = await login(email, password);
    if (response.status == 200) {
      toast.success(response.data.message);
      localStorage.setItem("user",JSON.stringify(response.data))
      navigate("/")
    }
    
  }


  return (
    <div className="outer-login">
      <div className="inner-login">
        <div className="welcome-msg">Welcome back! 👋</div>
        <h2>Sign in to your account</h2>
        <div class="mb-3">
          <label class="form-label">Email address</label>
          <input
            type="email"
            class="form-control"
            placeholder="abc@gmail.com"
            onChange={e => setEmail(e.target.value)}
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Password</label>
          <input type="password" class="form-control" placeholder="Password" onChange={e => setPassword(e.target.value)} />
        </div>
        <div className="mb-3">
          <p className="registerhere-link">
            Don't have an account? <Link to="/register">Register Here</Link>
          </p>
        </div>
        <button type="submit" class="btn btn-primary" onClick={loginUser}>
          Login
        </button>
      </div>
    </div>
  );
};

export default Login;
