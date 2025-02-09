import { Link, useNavigate } from "react-router-dom";
import "./Register.css";
import { useState } from "react";
import { toast } from "react-toastify";
import { register } from "../../Services/UserServices";

const Register = () => {

  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [dob, setDob] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const navigate = useNavigate()


  function validateUser() {
    if (firstName === "" || lastName === "") {
      toast.error("Name cannot be empty")
    } else if (email === "") {
      toast.error("Email cannot be empty")
    } else if (dob === "") {
      toast.error("Date of birth cannot be empty")
    } else if (password === "" || confirmPassword === "") {
      toast.error("Password cannot be empty")
    } else if (password !== confirmPassword) {
      toast.error("Passwords do not match")
    } else {
      registerUser()
    }
  }

  async function registerUser() {
    let user = {
      firstName,
      lastName,
      email,
      "dateOfBirth":dob,
      password,
      "userRole":"CUSTOMER"
    };

    const response = await register(user);
    if (response.status == 201) {
      toast.success(response.data.message);
      navigate("/login");
    } else {
      toast.error(response.error.response.data);
    }

    // let users = localStorage.getItem("users");

    // if (users === null) {

    //   localStorage.setItem("users", JSON.stringify([user]));
    // } else {
    //   users = JSON.parse(users);

    //   let userExists = users.some(u => u.email === email);
    //   if (userExists) {
    //     alert("User already exists! Please log in.");
    //     return;
    //   }
    //   users.push(user);
    //   localStorage.setItem("users", JSON.stringify(users));
    // }


  }



  return (
    <div className="register-outer-container">
      <div className="register-inner-container">
        <div className="register-form">
          <h2 style={{ textAlign: "center" }}>Registration Form</h2>
          <div className="register-row">
            <div class="mb-3">
              <label class="form-label">First Name</label>
              <input type="text" class="form-control" placeholder="First Name" onChange={(e) => setFirstName(e.target.value)} />
            </div>
            <div class="mb-3">
              <label class="form-label">Last Name</label>
              <input type="text" class="form-control" placeholder="Last Name" onChange={(e) => setLastName(e.target.value)} />
            </div>
          </div>
          <div className="register-row">
            <div class="mb-3">
              <label class="form-label">Email</label>
              <input type="email" class="form-control" placeholder="Email" onChange={e => setEmail(e.target.value)} />
            </div>
            <div class="mb-3">
              <label class="form-label">Date of birth</label>
              <input type="date" class="form-control" onChange={e => setDob(e.target.value)} />
            </div>
          </div>
          <div className="register-row">
            <div class="mb-3">
              <label class="form-label">Password</label>
              <input type="password" class="form-control" placeholder="Password" onChange={e => setPassword(e.target.value)} />
            </div>
            <div class="mb-3">
              <label class="form-label">Confirm Password</label>
              <input type="password" class="form-control" placeholder="Confirm Password" onChange={e => setConfirmPassword(e.target.value)} />
            </div>
          </div>
          <div>
            <p style={{ textAlign: "center" }}>
              Already have an account? <Link to="/login">Login Here</Link>
            </p>
          </div>
          <div className="register-btn">
            <button type="submit" class="btn btn-primary" onClick={validateUser}>
              Register
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Register;
