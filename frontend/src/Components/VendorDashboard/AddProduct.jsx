import axios from "axios";
import { useState, useEffect } from "react";
import { getCategories } from "../../Services/ProductService";
import { toast } from "react-toastify";

export default function AddProduct() {

    const [productName, setProductName] = useState(""); // Product Name
    const [price, setPrice] = useState("");             // Price
    const [stockQuantity, setStockQuantity] = useState(""); // Stock Quantity
    const [categoryId, setCategoryId] = useState("");   // Category ID
    const [description, setDescription] = useState(""); // Description
    const [adsImages, setAdsImages] = useState([]);      // Product Images (file)
    const [categories, setCategories] = useState([]);    // Categories for dropdown

    const user = localStorage.getItem("user");
    let token = "";
    if (user != null) {
        token = JSON.parse(user).jwt;
    }

    // Fetch categories on component mount
    useEffect(() => {
        const fetchCategory= async ()=>{

            const response = await getCategories();
            if (response.data != null) {
                setCategories(response.data);
            } else {
                toast.error(response.error)
            }
        }
        fetchCategory();
    }, [token]);

    const handleImageChange = (e) => {
        setAdsImages(e.target.files); // Set selected files
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const formData = new FormData();

        // Append the form fields to the FormData object
        formData.append("name", productName);
        formData.append("price", price);
        formData.append("quantityInStock", stockQuantity);
        formData.append("description", description);
        formData.append("categoryId", categoryId);

        // Append the files to the FormData object
        for (let i = 0; i < adsImages.length; i++) {
            formData.append("myfile", adsImages[i]);
        }

        try {
            // Send the POST request to the server
            const response = await axios.post("http://localhost:8080/products/add_product", formData, {
                headers: {
                    "Content-Type": "multipart/form-data", // Important for file upload
                    Authorization: `Bearer ${token}`,
                }
            });

            // Handle the response as needed
            console.log("Product added successfully:", response.data);

        } catch (error) {
            console.error("Error adding product:", error.response ? error.response.data : error.message);
        }
    };

    return (
        <div className="user-profile mt-5 mx-5">
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label className="form-label">Product Name</label>
                    <input
                        type="text"
                        className="form-control"
                        value={productName}
                        onChange={(e) => setProductName(e.target.value)}
                    />
                </div>

                <div className="mb-3">
                    <label className="form-label">Price</label>
                    <input
                        type="number"
                        className="form-control"
                        value={price}
                        onChange={(e) => setPrice(e.target.value)}
                    />
                </div>

                <div className="mb-3">
                    <label className="form-label">Stock Quantity</label>
                    <input
                        type="number"
                        className="form-control"
                        value={stockQuantity}
                        onChange={(e) => setStockQuantity(e.target.value)}
                    />
                </div>

                <div className="mb-3">
                    <label className="form-label">Category</label>
                    <select
                        className="form-control"
                        value={categoryId}
                        onChange={(e) => setCategoryId(e.target.value)}
                    >
                        <option value="" disabled>Select Category</option>
                        {categories.map((categoryItem) => (
                            <option key={categoryItem.id} value={categoryItem.id}>
                                {categoryItem.categoryName}
                            </option>
                        ))}
                    </select>
                </div>

                <div className="mb-3">
                    <label className="form-label">Product Images</label>
                    <input
                        type="file"
                        className="form-control"
                        multiple
                        onChange={handleImageChange}
                    />
                </div>

                <div className="mb-3">
                    <label className="form-label">Description</label>
                    <textarea
                        className="form-control"
                        value={description}
                        onChange={(e) => setDescription(e.target.value)}
                    />
                </div>

                <div className="mb-3 text-center">
                    <button className="btn btn-success" type="submit">
                        Add Product
                    </button>
                </div>
            </form>
        </div>
    );
}
