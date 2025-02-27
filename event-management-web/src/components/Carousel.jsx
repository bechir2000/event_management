import React from "react"
import CarouselItem from "./CarouselItem";

function Carousel(){

    return <div id="carouselExampleInterval" className="carousel slide" data-bs-ride="carousel">
  <div className="carousel-indicators">
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" className="active" aria-current="true" aria-label="Slide 1"></button>
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
  </div>
  
  <div className="carousel-inner">

  <div className="carousel-item active" data-bs-interval="4000">
      <img src="/images/about_the_eif.jpg"className="d-block w-100" alt=""/>
      <div className="carousel-caption d-none d-md-block">
        <h5>Welcome to Eventopia</h5>
        <p>Experience the pulse of excitement and unlock a world of extraordinary events.</p>
      </div>
    </div>

    <CarouselItem src="/images/4K-Fireworks-Wallpaper-1080p.jpg" title="Experience Events Like Never Before" message="Join us on a thrilling adventure, sign up now and create lasting memories." />
    <CarouselItem src="/images/Annotation 2023-07-28 102055.png" title="Unleash Your Creativity" message="Be the mastermind behind unforgettable experiences. Use our app to easily create and organize your own exceptional events." />

  </div>

  <button className="carousel-control-prev" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="prev">
    <span className="carousel-control-prev-icon" aria-hidden="true"></span>
    <span className="visually-hidden">Previous</span>
  </button>
  <button className="carousel-control-next" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="next">
    <span className="carousel-control-next-icon" aria-hidden="true"></span>
    <span className="visually-hidden">Next</span>
  </button>
</div>

}

export default Carousel;
