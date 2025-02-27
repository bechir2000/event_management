import React from "react";
import { useNavigate } from "react-router-dom";

function Ticket(props){

    const navigate= useNavigate();
    
    function handleClick(){
        navigate(`/event/${props.eventId}`);
    }


   return <div class="card text-bg-light mb-3" style={{ maxWidth: "18rem" }}>
  <div class="card-header d-flex justify-content-center">Ticket N°{props.ticketId} </div>
  <div class="card-body">
    <h5 class="card-title">Event : {props.eventName} </h5>
    <h5 class="card-title">Owner : {props.firstName} {props.lastName}</h5>
    <br/>
    
    <p className="card-text">
        <small className="text-body-secondary"> Ticket Price: {props.price}DT.</small>
    </p>
    <p className="card-text">
        <small className="text-body-secondary"> Date : {props.date}</small>
    </p>
    <p className="card-text">
        <small className="text-body-secondary"> Time : {props.time}</small>
    </p>
    <p className="card-text">
        <small className="text-body-secondary">Location : {props.location}</small>
    </p>
  </div>
  <div className="card-footer d-flex justify-content-center">
        <button className="btn btn-success" onClick={handleClick}>See event</button>           
    </div>

</div>
}

export default Ticket;
