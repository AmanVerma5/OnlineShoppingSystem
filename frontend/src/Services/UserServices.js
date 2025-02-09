import axios from "axios";
import { toast } from 'react-toastify';

function createUrl(url) {
    let serverUrl = "http://localhost:8080"
    return `${serverUrl}/${url}`;
}

export async function register(user) {
    try {
        const url = createUrl("users/signup");
        console.log(url)
        const response = await axios.post(url, user);
        console.log(response);
        return response;
    } catch (error) {
        return { status: "error", error}
    }

}

export async function login(email, password) {
    try {
        const url = createUrl("users/signin");
        const response = await axios.post(url, {email, password})
        console.log(response);
        return response;
    } catch (error) {
        toast.error(error.response.data)
        console.log(error);
    }
}