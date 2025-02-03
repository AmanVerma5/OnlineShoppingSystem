import Carousel from "../Components/Carousel/Carousel"
import Footer from "../Components/Footer/Footer"
import Navbar from "../Components/Navbar/Navbar"
import Section from "../Components/Section/Section"



const HomePage=()=>{
    return(
        <>
        <Navbar flag={true}/>
        <Carousel/>
        <Section/>
        <Footer/>
        </>
    )
}


export default HomePage