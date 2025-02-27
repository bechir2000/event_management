import React from "react";
import { faUser, faPenToSquare, faU } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";


function MyAccountCard(props){
return <div className="d-flex justify-content-center align-items-center" style={{ minHeight: "85vh" }}>
<div className="card text-bg-light mb-3" style={{ width: "40rem" }} >
<div className="card-header d-flex align-items-center justify-content-center">
<FontAwesomeIcon icon={faUser} style={{ paddingRight: "1rem" }}/>
{props.firstName} {props.lastName}
</div>
<div className="card-body">
    <h6 className="card-title accountDetails">Your account details:</h6>
    <div className="d-flex align-items-center justify-content-center details">
    <div className="card" style={{ width: "15rem" }}>
  <ul className="list-group list-group-flush">
    <li className="list-group-item">First Name</li>
    <li className="list-group-item">Last Name</li>
    <li className="list-group-item">Email Address</li>
    <li className="list-group-item">Phone Number</li>

  </ul>
</div>
<div className="card" style={{ width: "15rem" }}>
  <ul className="list-group list-group-flush">
    <li className="list-group-item">{props.firstName}</li>
    <li className="list-group-item">{props.lastName} </li>
    <li className="list-group-item">{props.email} </li>
    <li className="list-group-item">{props.phoneNumber} </li>
  </ul>
</div>
</div>
<div className="d-flex align-items-center justify-content-center">
<button className="btn btn-outline-success" style={{ marginTop: "2rem" }}><FontAwesomeIcon icon={faPenToSquare} style={{ paddingRight: "1rem" }}/>Edit</button>
</div>
</div>
  
  </div> 
  </div>

}

export default MyAccountCard;