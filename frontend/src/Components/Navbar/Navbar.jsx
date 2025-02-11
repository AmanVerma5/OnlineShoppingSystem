import { Link } from 'react-router-dom';
import './Navbar.css';
import Searchbar from '../SearchBar/Searchbar';
import logo from '../../images/logo.png';
import cart from '../../images/cart.png';
import { useEffect, useState } from 'react';



const Navbar = ({ flag }) => {

  const [show, setShow] = useState('true')
  const [user, setUser] = useState();

  useEffect(() => {
    setShow(flag)
    let u = localStorage.getItem("user");
    if (u !== null) {
      u = JSON.parse(u);
      setUser(u);
    }
  }, [flag])


  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark" id="navbar" aria-label="Offcanvas navbar large">
      <div className="container-fluid">
        <Link className="navbar-brand logo" to="/">
          QUICKSHOPR <span><img src={logo} style={{ position: 'relative', top: '-2px', height: "20px", width: "20px" }} alt="" /></span>
        </Link>
        {
          show && (
            <> <div className="d-flex mt-3 mt-lg-0 search-container" role="search">
              <Searchbar />
            </div>
              <button className="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar2" aria-controls="offcanvasNavbar2">
                <span className="navbar-toggler-icon"></span>
              </button>

              <div className="offcanvas offcanvas-end text-bg-dark links" tabindex="-1" id="offcanvasNavbar2" aria-labelledby="offcanvasNavbar2Label">
                <div className="offcanvas-header">
                  <h5 className="offcanvas-title logo" id="offcanvasNavbar2Label">QUICKSHOPR</h5>
                  <button type="button" className="btn-close btn-close-white" data-bs-dismiss="offcanvas" data-bs-target="#offcanvasResponsive" aria-label="Close"></button>
                </div>
                <div className="offcanvas-body">
                  <ul className="navbar-nav justify-content-end flex-grow-1 pe-3">
                    {user ? <><li className="nav-item">
                      <Link className="nav-link active" aria-current="page" to="/profile">{user.name}</Link>
                    </li> </> : <> <li><Link className="nav-link active" aria-current="page" to="/login">Login</Link></li>
                      <li><Link className="nav-link active" aria-current="page" to="/vendor/register">Become a Seller</Link></li>
                    </>}
                    <li className="nav-item">
                      <Link className="nav-link" to="/cart">
                        <img src={cart} height="30px" alt="cart logo" />
                      </Link>
                    </li>
                    {user && <li className="nav-item">
                        <div className="nav-link active">Logout</div>
                      </li>}

                    {/* <li className="nav-item dropdown">
                      <Link className="nav-link dropdown-toggle" to="#" role="button" data-bs-toggle="dropdown">
                        Dropdown
                      </Link>
                      <ul className="dropdown-menu">
                        <li><Link className="dropdown-item" to="#">Action</Link></li>
                        <li><Link className="dropdown-item" to="#">Another action</Link></li>
                        <li>
                          <hr className="dropdown-divider" />
                        </li>
                        <li><Link className="dropdown-item" to="#">Something else here</Link></li>
                      </ul>
                    </li> */}
                  </ul>
                </div>
              </div></>
          )
        }

      </div>
    </nav>
  )
}




export default Navbar