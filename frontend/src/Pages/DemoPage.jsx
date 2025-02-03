import { useEffect, useRef, useState } from "react"
import Demo from "../Components/Demo/Demo"
import Navbar from "../Components/Navbar/Navbar"



const DemoPage=()=>{

    const [navbarHeight, setNavbarHeight] = useState(0);


    useEffect(()=>{
        const navbar = document.querySelector(".navbar");
        if (navbar) {
            setNavbarHeight(navbar.offsetHeight);
        }
    
        console.log(navbar)
    },[])

    return(
        <>
        <Navbar />
        <Demo height={navbarHeight}/>
        </>
    )
}

export default DemoPage