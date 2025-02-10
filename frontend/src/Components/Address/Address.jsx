import React, { useEffect, useState } from "react";
import axios from 'axios';
import { useNavigate } from "react-router-dom";
import AddAddress from "./AddAddress";
import './Address.css';

const Address = () => {
    // State to manage the list of addresses
    const [addresses, setAddresses] = useState([]);

    // State for managing editing
    const [editingId, setEditingId] = useState(null);
    const [editedAddress, setEditedAddress] = useState({});
    const [addingAddress, setAddingAddress] = useState(false);

    // States for the new address fields
    const [addressLine1, setAddressLine1] = useState("");
    const [addressLine2, setAddressLine2] = useState("");
    const [city, setCity] = useState("");
    const [state, setState] = useState("");
    const [country, setCountry] = useState("");
    const [pincode, setPincode] = useState("");
    const navigate = useNavigate();

    // Handle Edit Click
    const handleEditClick = (id, address) => {
        setEditingId(id);
        setEditedAddress(address);
    };

    // Handle Input Change for edited address
    const handleInputChange = (e) => {
        setEditedAddress({ ...editedAddress, [e.target.name]: e.target.value });
    };

    // Handle Save for edited address
    const handleSave = (id) => {
        setAddresses(addresses.map(addr => (addr.id === id ? editedAddress : addr)));
        console.log(editedAddress);
        axios.put(`http://localhost:8080/users/update_address`, editedAddress, { headers: { Authorization: `Bearer ${token}` } })
            .then((response) => console.log(response))
            .catch((error) => console.log(error))
        setEditingId(null);
    };

    // Handle Delete Address
    const handleDelete = (id) => {
        axios.delete(`http://localhost:8080/users/delete_address/${id}`, { headers: { Authorization: `Bearer ${token}` } })
            .then((response) => console.log(response))
            .catch((error) => console.log(error))
        setAddresses(addresses.filter(addr => addr.id !== id));
    };

    // Handle Save New Address
    const handleAddAddress = () => {
        setAddingAddress(true)

    };

    const token = JSON.parse(localStorage.getItem("user")).jwt;

    useEffect(() => {
        axios.get("http://localhost:8080/users/addresses", { headers: { Authorization: `Bearer ${token}` } })
            .then((response) => {
                console.log(response);
                if (response.data.length === 0) {
                    return;
                }
                setAddresses(response.data);

            }).catch(error => {
                console.log(error);
            })
    }, [])

    return (
        addingAddress ? (<AddAddress setAddingAddress={setAddingAddress} />) : (<div className="container mt-4">
            <h2 className="mb-3 text-center">📍 Address List</h2>

            <div className="card p-3 shadow-lg">

                {/* Address List */}
                {addresses.length === 0 ? (
                    <p className="text-center text-muted">No addresses available.</p>
                ) : (
                    <table className="table table-hover">
                        <thead className="table-dark">
                            <tr>
                                <th>Flat/House no</th>
                                <th>Area/Street</th>
                                <th>City</th>
                                <th>State</th>
                                <th>Country</th>
                                <th>ZIP Code</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {addresses.map(address => (
                                <tr key={address.id}>
                                    {editingId === address.id ? (
                                        <>
                                            <td><input type="text" className="form-control" name="addressLine1" value={editedAddress.addressLine1} onChange={handleInputChange} /></td>
                                            <td><input type="text" className="form-control" name="addressLine2" value={editedAddress.addressLine2} onChange={handleInputChange} /></td>
                                            <td><input type="text" className="form-control" name="city" value={editedAddress.city} onChange={handleInputChange} /></td>
                                            <td><input type="text" className="form-control" name="state" value={editedAddress.state} onChange={handleInputChange} /></td>
                                            <td><input type="text" className="form-control" name="country" value={editedAddress.country} onChange={handleInputChange} /></td>
                                            <td><input type="text" className="form-control" name="pincode" value={editedAddress.pincode} onChange={handleInputChange} /></td>
                                            <td>
                                                <button className="btn btn-success btn-sm me-2" onClick={() => handleSave(address.id)}>✔ Save</button>
                                                <button className="btn btn-secondary btn-sm" onClick={() => setEditingId(null)}>✖ Cancel</button>
                                            </td>
                                        </>
                                    ) : (
                                        <>
                                            <td>{address.addressLine1}</td>
                                            <td>{address.addressLine2}</td>
                                            <td>{address.city}</td>
                                            <td>{address.state}</td>
                                            <td>{address.country}</td>
                                            <td>{address.pincode}</td>
                                            <td>
                                                <button className="btn btn-primary btn-sm me-2" onClick={() => handleEditClick(address.id, address)}>✏ Edit</button>
                                                <button className="btn btn-danger btn-sm" onClick={() => handleDelete(address.id)}>🗑 Delete</button>
                                            </td>
                                        </>
                                    )}
                                </tr>
                            ))}
                        </tbody>
                    </table>
                )}
            </div>
            <div className="text-center mt-4">
                <button className="btn btn-success" onClick={handleAddAddress}>Add Address</button>
            </div>
        </div>)
    );
};

export default Address;
