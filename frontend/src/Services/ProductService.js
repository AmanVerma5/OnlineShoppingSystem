import axios from "axios";


function createUrl(url) {
    let serverUrl = "http://localhost:8080"
    return `${serverUrl}/${url}`;
}

const token = JSON.parse(localStorage.getItem("user")).jwt;

export async function getCategories() {
    try {
        const url = createUrl("categories/view");
        const response = await axios.get(url, { headers: { Authorization: `Bearer ${token}` } });
        return response;
    } catch (error) {
        return { status: "error", error };
    }
}