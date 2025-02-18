import axios from "axios";


function createUrl(url) {
    let serverUrl = "http://localhost:8080"
    return `${serverUrl}/${url}`;
}

const user = localStorage.getItem("user");
let token = "";
if (user != null) {
    token = JSON.parse(user).jwt;
}

export async function getCategories() {
    try {
        const url = createUrl("categories/view");
        const response = await axios.get(url);
        return response;
    } catch (error) {
        return { status: "error", error };
    }
}