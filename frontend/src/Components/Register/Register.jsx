import { Link } from "react-router-dom";
import "./Register.css";

const Register = () => {
  return (
    <div className="register-outer-container">
      <div className="register-inner-container">
        <form className="register-form">
          <h2 style={{ textAlign: "center" }}>Registration Form</h2>
          <div className="register-row">
            <div class="mb-3">
              <label for="exampleInputEmail1" class="form-label">
                First Name
              </label>
              <input
                type="text"
                class="form-control"
                id="exampleInputEmail1"
                aria-describedby="emailHelp"
              />
            </div>
            <div class="mb-3">
              <label for="exampleInputPassword1" class="form-label">
                Last Name
              </label>
              <input
                type="text"
                class="form-control"
                id="exampleInputPassword1"
              />
            </div>
          </div>
          <div className="register-row">
            <div class="mb-3">
              <label for="exampleInputEmail1" class="form-label">
                Email
              </label>
              <input
                type="email"
                class="form-control"
                id="exampleInputEmail1"
                aria-describedby="emailHelp"
              />
            </div>
            <div class="mb-3">
              <label for="exampleInputPassword1" class="form-label">
                Date of birth
              </label>
              <input
                type="date"
                class="form-control"
                id="exampleInputPassword1"
              />
            </div>
          </div>
          <div className="register-row">
            <div class="mb-3">
              <label for="exampleInputEmail1" class="form-label">
                Password
              </label>
              <input
                type="password"
                class="form-control"
                id="exampleInputEmail1"
                aria-describedby="emailHelp"
              />
            </div>
            <div class="mb-3">
              <label for="exampleInputPassword1" class="form-label">
                Confirm Password
              </label>
              <input
                type="password"
                class="form-control"
                id="exampleInputPassword1"
              />
            </div>
          </div>
          <div>
            <p style={{ textAlign: "center" }}>
              Already have an account? <Link to="/login">Login Here</Link>
            </p>
          </div>
          <div className="register-btn">
            <button type="submit" class="btn btn-primary">
              Register
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Register;
