import { useState } from "react";
import axios from "axios";

export default function AddAddress({ setAddingAddress }) {



    const [addressLine1, setAddressLine1] = useState("");
    const [addressLine2, setAddressLine2] = useState("");
    const [city, setCity] = useState("");
    const [state, setState] = useState("");
    const [country, setCountry] = useState("");
    const [pincode, setPincode] = useState("");

    const token = JSON.parse(localStorage.getItem("user")).jwt;

    function handleAddAddress() {
        axios.post("http://localhost:8080/users/add_address", {
            addressLine1, addressLine2, city, state, country, pincode
        }, { headers: { Authorization: `Bearer ${token}` } })
            .then((response) => {
                console.log(response);
            }).catch((error) => {
                console.log(error);
            })
        setAddingAddress(false)
    }

    return (
        <div className="address-container mt-5">
            <div className="card p-3 shadow-lg">
                <div className="mb-4">
                    <h4 className="text-center mb-4">Add New Address</h4>
                    <div className="row">
                        <div className="col-md-6">
                            <label className="form-label">Flat</label>
                            <input
                                type="text"
                                className="form-control mb-2"
                                placeholder="Address Line 1"
                                value={addressLine1}
                                onChange={(e) => setAddressLine1(e.target.value)}
                            />
                            <label className="form-label">Area</label>
                            <input
                                type="text"
                                className="form-control mb-2"
                                placeholder="Address Line 2"
                                value={addressLine2}
                                onChange={(e) => setAddressLine2(e.target.value)}
                            />
                            <label className="form-label">City</label>
                            <input
                                type="text"
                                className="form-control mb-2"
                                placeholder="City"
                                value={city}
                                onChange={(e) => setCity(e.target.value)}
                            />
                        </div>
                        <div className="col-md-6">
                            <label className="form-label">State</label>
                            <input
                                type="text"
                                className="form-control mb-2"
                                placeholder="State"
                                value={state}
                                onChange={(e) => setState(e.target.value)}
                            />
                            <label className="form-label">Country</label>
                            <input
                                type="text"
                                className="form-control mb-2"
                                placeholder="Country"
                                value={country}
                                onChange={(e) => setCountry(e.target.value)}
                            />
                            <label className="form-label">Pincode</label>
                            <input
                                type="text"
                                className="form-control mb-2"
                                placeholder="Pincode"
                                value={pincode}
                                onChange={(e) => setPincode(e.target.value)}
                            />
                        </div>
                    </div>
                    <div className="text-center mt-4">
                        <button className="btn btn-success" onClick={handleAddAddress}>Add Address</button>
                    </div>
                </div>
            </div>
        </div>
    )
}