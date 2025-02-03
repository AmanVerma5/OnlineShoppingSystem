import { Link } from 'react-router-dom'
import './Login.css'



const Login=({height})=>{
    console.log(height)
    
    return(
        <div className='outer-login'>
        <form className="inner-login">
        <div className="welcome-msg">Welcome back! 👋</div>
        <h2>Sign in to your account</h2>
  <div class="mb-3">
    <label for="exampleInputEmail1" class="form-label">Email address</label>
    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"/>
  </div>
  <div class="mb-3">
    <label for="exampleInputPassword1" class="form-label">Password</label>
    <input type="password" class="form-control" id="exampleInputPassword1"/>
  </div>
  <div class="mb-3">
    <p className='registerhere-link'>Don't have an account? <Link to="/register">Register Here</Link></p>
  </div>
  <button type="submit" class="btn btn-primary">Login</button>
</form>
</div>
    )
}

export default Login;
