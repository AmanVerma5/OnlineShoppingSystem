import React, { useState, useEffect } from "react";
import axios from "axios";
import { toast } from "react-toastify";

const Profile = () => {
    const [isEditable, setIsEditable] = useState(false);
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [mobile, setMobile] = useState("");
    const [dob, setDob] = useState("");

    const token = JSON.parse(localStorage.getItem("user")).jwt;

    useEffect(() => {
        axios.get("http://localhost:8080/users/user_details", {
            headers: { Authorization: `Bearer ${token}`}
        }) //Replace Here
            .then((response) => {
                const data = response.data;
                setFirstName(data.firstName);
                setLastName(data.lastName);
                setEmail(data.email);
                setMobile(data.mobileNo);
                setDob(data.dateOfBirth);
            })
            .catch((error) => console.error("Error fetching profile data:", error));
    }, []);

  
    const handleSave = () => {
        const updatedProfile = { firstName, lastName, email, "mobileNo":mobile, "dateOfBirth":dob };

        axios.put("http://localhost:8080/users/update", updatedProfile, {
            headers: {Authorization : `Bearer ${token}`}
        }) // Replace Here
            .then(() => {
                toast.success("Profile Updated Successfully");
                setIsEditable(false);
            })
            .catch((error) => console.error("Error updating profile:", error));
    };

    return (
        <div className="user-profile mt-5 mx-5">
            <div className="mb-3">
                <label className="form-label">First Name</label>
                <input
                    type="text"
                    className="form-control"
                    value={firstName}
                    onChange={(e) => setFirstName(e.target.value)}
                    readOnly={!isEditable}
                />
            </div>

            <div className="mb-3">
                <label className="form-label">Last Name</label>
                <input
                    type="text"
                    className="form-control"
                    value={lastName}
                    onChange={(e) => setLastName(e.target.value)}
                    readOnly={!isEditable}
                />
            </div>

            <div className="mb-3">
                <label className="form-label">Email</label>
                <input
                    type="email"
                    className="form-control"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    disabled
                />
            </div>

            <div className="mb-3">
                <label className="form-label">Mobile Number</label>
                <input
                    type="text"
                    className="form-control"
                    value={mobile}
                    onChange={(e) => setMobile(e.target.value)}
                    readOnly={!isEditable}
                />
            </div>

            <div className="mb-3">
                <label className="form-label">Date of Birth</label>
                <input
                    type="date"
                    className="form-control"
                    value={dob}
                    onChange={(e) => setDob(e.target.value)}
                    readOnly={!isEditable}
                />
            </div>
            <div className="mb-3 text-center change-btn">
            {isEditable ? (
                <button className="btn btn-success" onClick={handleSave}>
                    Save Changes
                </button>
            ) : (
                <button className="btn btn-primary" onClick={() => setIsEditable(true)}>
                    Edit
                </button>
            )}
            </div>
        </div>
    );
};

export default Profile;
