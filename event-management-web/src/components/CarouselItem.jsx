import React from "react";


function CarouselItem(props){

    return <div className="carousel-item" data-bs-interval="5000">
      <img src={props.src} className="d-block w-100" alt=""/>
      <div className="carousel-caption d-none d-md-block">
        <h5>{props.title}</h5>
        <p>{props.message}</p>
      </div>
    </div>
}

export default CarouselItem;
