import React, { useState } from "react";

const Address = () => {
    const [addresses, setAddresses] = useState([
        { id: 1, street: "123 Main St", city: "New York", zip: "10001" },
        { id: 2, street: "456 Elm St", city: "Los Angeles", zip: "90001" },
        { id: 3, street: "789 Oak St", city: "Chicago", zip: "60601" }
    ]);

    const [editingId, setEditingId] = useState(null);
    const [editedAddress, setEditedAddress] = useState({});

    // Handle Edit Click
    const handleEditClick = (id, address) => {
        setEditingId(id);
        setEditedAddress(address);
    };

    // Handle Input Change
    const handleInputChange = (e) => {
        setEditedAddress({ ...editedAddress, [e.target.name]: e.target.value });
    };

    // Handle Save
    const handleSave = (id) => {
        setAddresses(addresses.map(addr => (addr.id === id ? editedAddress : addr)));
        setEditingId(null);
    };

    // Handle Delete
    const handleDelete = (id) => {
        setAddresses(addresses.filter(addr => addr.id !== id));
    };

    return (
        <div className="container mt-4">
            <h2 className="mb-3 text-center">📍 Address List</h2>
            <div className="card p-3 shadow-lg">
                {addresses.length === 0 ? (
                    <p className="text-center text-muted">No addresses available.</p>
                ) : (
                    <table className="table table-hover">
                        <thead className="table-dark">
                            <tr>
                                <th>Street</th>
                                <th>City</th>
                                <th>ZIP Code</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {addresses.map(address => (
                                <tr key={address.id}>
                                    {editingId === address.id ? (
                                        <>
                                            <td><input type="text" className="form-control" name="street" value={editedAddress.street} onChange={handleInputChange} /></td>
                                            <td><input type="text" className="form-control" name="city" value={editedAddress.city} onChange={handleInputChange} /></td>
                                            <td><input type="text" className="form-control" name="zip" value={editedAddress.zip} onChange={handleInputChange} /></td>
                                            <td>
                                                <button className="btn btn-success btn-sm me-2" onClick={() => handleSave(address.id)}>✔ Save</button>
                                                <button className="btn btn-secondary btn-sm" onClick={() => setEditingId(null)}>✖ Cancel</button>
                                            </td>
                                        </>
                                    ) : (
                                        <>
                                            <td>{address.street}</td>
                                            <td>{address.city}</td>
                                            <td>{address.zip}</td>
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
        </div>
    );
};

export default Address;
