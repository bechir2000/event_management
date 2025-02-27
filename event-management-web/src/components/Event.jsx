import React from "react";
import { useNavigate } from "react-router-dom";



function Event(props){

  const navigate=useNavigate();

  function handleClick(){
    navigate(`/event/${props.eventId}`);
  }

    return <div className="card-container">
    <div className="card mb-3" style={{ maxWidth: "29.5rem" }}>
      <div className="row g-0">
        <div className="col-5">
          <div className="card-img-top" style={{ height: "100%" }}>
            <img src={props.img} className="img-fluid rounded-start h-100 w-100" alt="event" />
          </div>
        </div>
        <div className="col-6" style={{ paddingRight: "0" }}>
          <div className="card-body d-flex flex-column justify-content-between h-100" style={{ paddingRight: "0" }}>
            <div>
              <h5 className="card-title">{props.title}</h5>
              <div className="d-flex justify-content-between">
                <p><small className="text-body-secondary">Date: {props.date}</small></p>
                <p><small className="text-body-secondary">Location: {props.location}</small></p>
              </div>
              <p className="card-text description">{props.description}</p>
            </div>
            <button className="btn btn-success" onClick={handleClick} style={{ alignSelf: "flex-end", marginTop: "0.5rem" }}> 
                  See More
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
        
}

export default Event;