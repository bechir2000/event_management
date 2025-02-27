import React, {useState, useEffect} from "react";
import Ticket from "./Ticket";
import axios from "axios";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useNavigate, useParams } from "react-router-dom";
import UserNavbar from "./UserNavbar";
import Footer from "./Footer";
import Cookies from "js-cookie";


function MyTickets(props){

    const [tickets, setTickets] = useState([]);
    const [token, setToken]=useState("");
    const { userId } = useParams(); // Get the userId from URL parameters
    const [userType, setUserType]=useState("");
    const [userData, setUserData] = useState(null);
    const [userDataFetched, setUserDataFetched] = useState(false);


    
    const navigate = useNavigate();

useEffect(() => {
  // Check if the 'authToken' cookie exists
  const authToken = Cookies.get("authToken");
  if (authToken) {
    setToken(authToken);
    setUserType(Cookies.get("userType"));    
  } else {
    return navigate("/login");
  }
}, []);

useEffect(() => {
    async function fetchUserData() {
      try {
        let response;
        if (userType === "ORGANIZER") {
          response = await axios.get(
            `http://localhost:8090/organizer/${userId}`,
            {
              headers: {
                Authorization: `Bearer ${token}`,
              },
            }
          );
        } else if (userType === "CLIENT") {
          response = await axios.get(
            `http://localhost:8090/client/${userId}`,
            {
              headers: {
                Authorization: `Bearer ${token}`,
              },
            }
          );
        } else if (userType === "ADMIN") {
          response = await axios.get(
            `http://localhost:8090/admin/${userId}`,
            {
              headers: {
                Authorization: `Bearer ${token}`,
              },
            }
          );
        }
        setUserData(response.data);
        setUserDataFetched(true); 
        console.log(userData);
      } catch (error) {
        console.error("Error fetching user data:", error);
      }
    
    }
  
    fetchUserData();
  }, [userId, token]);

  function handleBuyNowClick(){
    if(userType==="ORGANIZER"){
    navigate("/organizer");
    } else if(userType==="CLIENT"){
      navigate("/client")
    }
  }

  

useEffect(() => {
  // Wait until user data is fetched before fetching tickets
  if (userDataFetched) {
    // Fetch events from the backend API
    const fetchTickets = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8090/ticket/user/${userId}`,
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );
        setTickets(response.data);
        console.log(response);
      } catch (error) {
        console.error("Error fetching tickets:", error);
      }
    };

    fetchTickets();
  }
}, [userDataFetched, userId, token]);

if(!userData){
    return <div>Loading ...</div>
}


    return <div className="background">
    <UserNavbar active="myTickets" />

  <div style={{ display: "flex", justifyContent: "center", marginTop:"2rem"}}>
    <h1 className="font-style">My Tickets</h1>
  </div>


{tickets.length === 0 && ( // Check if myEvents array is not empty
  <div style={{ display: "flex", justifyContent: "center", margin: "2rem 0" }}>
    <p>Dear user, we noticed that you haven't purchased any ticket yet. 
    <a href="" onClick={handleBuyNowClick}>Buy your tickets now</a> and unlock the door to a world of delightful experiences!</p>
  </div>
)}
    
    <div className="tickets-container card-container d-flex flex-wrap gap-3">

    {tickets.map((ticket) => (
      <Ticket
        key={ticket.id} // Assuming each event has a unique ID
        ticketId={ticket.id}
        eventId={ticket.event.id}
        eventName={ticket.event.name}
        firstName={userData.firstName}
        lastName={userData.lastName}
        price={ticket.price}
        date={ticket.event.date}
        time={ticket.event.time}
        location={ticket.event.location} 
      />
    ))}
  </div>

  <Footer />
  </div>
}

export default MyTickets;