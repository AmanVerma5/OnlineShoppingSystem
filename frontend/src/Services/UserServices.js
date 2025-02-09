import axios from "axios";

function createUrl(url) {
    let serverUrl = "http://localhost:8080"
    return `${serverUrl}/${url}`;
}

export async function register(user) {
    try {
        const url = createUrl("/users/signup");
        const response = await axios.post(url, user);
        console.log(response.data);
        return response.data;
    } catch (error) {
        return { status: "error", error}
    }
    

}

export function login(params) {

}