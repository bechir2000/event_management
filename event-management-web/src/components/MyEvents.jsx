import React, {useState, useEffect} from "react";
import Event from "./Event";
import axios from "axios";
import UserNavbar from "./UserNavbar";
import Cookies from "js-cookie";
import Footer from "./Footer";

function MyEvents(){

    const organizerId = Cookies.get("userId");

    const [myEvents, setMyEvents] = useState([]);
    
    useEffect(() => {
        // Fetch events from the backend API
        const fetchEvents = async () => {
          try {
            const response = await axios.get(`http://localhost:8090/event/organizer/${organizerId}`);
            // Assuming the response.data is an array of events
            setMyEvents(response.data);
          } catch (error) {
            console.error("Error fetching events:", error);
          }
        };
    
        fetchEvents();
      }, []);

   return <div className="background">
   <UserNavbar active="myEvents" />

   <br /> <br />
 
   <div style={{ display: "flex", justifyContent: "center" }}>
     <a href="/new-event" className="btn btn-lg btn-success">Create new event</a>
   </div>
   <br />
  <div style={{ display: "flex", justifyContent: "center" }}>
    <h1 className="font-style">My Events</h1>
  </div>

  {myEvents.length === 0 && ( // Check if myEvents array is not empty
  <div style={{ display: "flex", justifyContent: "center", margin: "2rem 0" }}>
    <p>Dear user, it appears that you haven't yet embarked on the journey of creating an event.
    <a href="/new-event">Get started now</a> and let the excitement begin!</p>
  </div>
)}
 
   <div className="events-container card-container d-flex flex-wrap gap-3">
     {myEvents.map((event) => (
       <Event
         key={event.id} // Assuming each event has a unique ID
         eventId={event.id}
         title={event.name}
         date={event.date}
         location={event.location}
         description={event.description}
         img={"http://localhost:8090/images/event-photos/" + event.photo} 
       />
     ))}
   </div>

   <Footer/>

 </div>

}

export default MyEvents;