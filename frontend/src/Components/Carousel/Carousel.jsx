import c1 from'../../images/c1.jpg';
import c2 from '../../images/c2.jpg';
import i23 from '../../images/i23.jpg';

const Carousel=()=>{
    return(
        <div id="carouselExampleFade" class="carousel slide carousel-fade">
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src={c1} class="d-block w-100" alt="..."/>
    </div>
    <div class="carousel-item">
      <img src={c2} class="d-block w-100" alt="..."/>
    </div>
    <div class="carousel-item">
      <img src={i23} class="d-block w-100" alt="..."/>
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>
    )
}



export default Carousel