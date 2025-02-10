import { useState } from 'react';
import { toast } from 'react-toastify';
import axios from 'axios';

export default function Password() {
    // Define state variables for the password inputs
    const [oldPassword, setOldPassword] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [confirmNewPassword, setConfirmNewPassword] = useState('');

    const token = JSON.parse(localStorage.getItem("user")).jwt;
    // Handle the Save button click
    function handleSave() {
        // You can perform validation or API call here
        if (newPassword !== confirmNewPassword) {
            toast.error('New password and confirmation do not match!');
            return;
        }

        axios.put("http://localhost:8080/users/change_password", { "currentPassword": oldPassword, newPassword }, {
            headers: { Authorization: `Bearer ${token}` }
        }) //Replace Here
            .then((response) => {
                const data = response.data;
                toast.success("Password Updated Successfully")
            })
            .catch((error) => toast.error(error.response.data));

        // Optionally reset the form or show success
        setOldPassword('');
        setNewPassword('');
        setConfirmNewPassword('');
    }

    return (
        <div className="user-profile mt-5 mx-5">
            <div className="mb-3">
                <label className="form-label">Old Password</label>
                <input
                    type="password"
                    className="form-control"
                    value={oldPassword}
                    onChange={(e) => setOldPassword(e.target.value)}
                />
            </div>
            <div className="mb-3">
                <label className="form-label">New Password</label>
                <input
                    type="password"
                    className="form-control"
                    value={newPassword}
                    onChange={(e) => setNewPassword(e.target.value)}
                />
            </div>
            <div className="mb-3">
                <label className="form-label">Confirm New Password</label>
                <input
                    type="password"
                    className="form-control"
                    value={confirmNewPassword}
                    onChange={(e) => setConfirmNewPassword(e.target.value)}
                />
            </div>

            <div className="mb-3 text-center change-btn">
                <button className="btn btn-success" onClick={handleSave}>
                    Save Changes
                </button>
            </div>
        </div>
    );
}
