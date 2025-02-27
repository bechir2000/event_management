import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Cookies from "js-cookie";
import UserNavbar from "./UserNavbar";
import Navbar from "./Navbar";
import {faCartShopping} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Footer from "./Footer";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { toast } from "react-toastify";



function EventPage(){
   
    const { eventId } = useParams(); // Get the eventId from URL parameters
    const [eventData, setEventData] = useState(null);
    const [userType, setUserType]=useState("");
    const [isLoggedIn, setIsLoggedIn]=useState(false);



    const navigate=useNavigate();

    useEffect(() => {
      // Check if the 'authToken' cookie exists
      const authToken = Cookies.get("authToken");
      if (authToken) {
        setIsLoggedIn(true)
        setUserType(Cookies.get("userType"));
      } else {
        navigate("/login");
      }
    }, []);
    
      useEffect(() => {
          // Fetch the event data based on the eventId
          // Replace this with your actual API call or data fetching logic
         async function fetchEventData(){
            try {
              const response = await fetch(`http://localhost:8090/event/${eventId}`);
              const data = await response.json();
              setEventData(data);
            } catch (error) {
              console.error("Error fetching event data:", error);
            }
          };
      
          fetchEventData();
        }, [eventId]);

    async function handleBuyTicketClick(){
      const userId = Cookies.get("userId");
      const token = Cookies.get("authToken");
      console.log(userId);

      try {
        const price= eventData.price; 
        
        const ticketData={
          price
        }
  
        const response = await axios.post(
          `http://localhost:8090/ticket/event/${eventId}/user/${userId}`,
          ticketData,
          {
            headers: {
              Authorization: `Bearer ${token}`,  
            },
          }
        );
  
        // Show toast notification for successful creation
      toast.success("Conratulations! You bought a ticket");
  
        // Redirect 
        navigate(`/MyTickets/${userId}`);
      } catch(error){
      console.error("Error:", error);
      toast.error("Sorry! an error has occured, please try again.");

    }
  }

    
      if (!eventData) {
        return <div>Loading...</div>;
      }
    
      return (
      <div className="background">
        { isLoggedIn ? <UserNavbar user={userType}/> : <Navbar /> } 
        <div className="container">
          <div className="d-flex justify-content-center align-items-center" style={{ minHeight: "85vh" }}>
            <div className="card-deck" style={{ width: "80%" }}>
              <div className="card mb-3">
                  <img
                    src={"http://localhost:8090/images/event-photos/" + eventData.photo}
                    className="card-img-top"
                    alt="event"
                    style={{ width: "100%", height: "18rem", objectFit: "cover" }}
                  />
                <div className="card-body">
                  <h3 className="card-title d-flex justify-content-center ">{eventData.name}</h3> 
                  <h6>Description :</h6>
                  <p className="card-text">{eventData.description}</p> <br/>
                  <p className="card-text">
                    <small className="text-body-secondary"> Date : {eventData.date}</small>
                  </p>
                  <p className="card-text">
                    <small className="text-body-secondary">Location : {eventData.location}</small>
                  </p>
                </div>
                <div className="card-footer d-flex justify-content-center">
                <div>
                  <p className="text-body-secondary mb-1">Ticket Price: {eventData.price} DT</p>
                  <button className="btn btn-success" onClick={handleBuyTicketClick}> <FontAwesomeIcon icon={faCartShopping} /> Buy ticket</button>
                </div>
              </div>
              </div>
            </div>
          </div>
        </div>
        <Footer/>
      </div>
    );   
        
}

export default EventPage;