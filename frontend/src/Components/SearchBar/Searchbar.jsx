import './Searchbar.css';
import searchlogo from '../../images/searchlogo.png';




const Searchbar=()=>{

    return(
        <div className='searchbar'>
                <div className='search-logo mx-2'>
                    <img style={{position:'relative', top:'-2px'}} src={searchlogo} width="20px" alt="logo" />
                </div>
                <div className='search-input'>
                    <input style={{position:'relative', top:'-1px'}} type="text" name="search" id="search"  placeholder='Search here' />
                </div>
        </div>
    )


}




export default Searchbar


