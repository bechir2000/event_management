import React, {useState, useEffect} from "react";
import UserNavbar from "./UserNavbar";
import Cookies from "js-cookie"
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";
import Footer from "./Footer";
import Header from "./Header";
import axios from "axios";
import MyAccountCard from "./MyAccountCard";

function MyAccount(props){

    const { userId } = useParams(); // Get the userId from URL parameters
    const [userType, setUserType]=useState("");
    const [userData, setUserData] = useState(null);
    const [token, setToken]=useState("");



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
          } catch (error) {
            console.error("Error fetching user data:", error);
          }
        }
      
        fetchUserData();
      }, [userId, token]);

      if (!userData) {
        return <div>Loading...</div>;
      }
    
    
    return<div className="background">
        <UserNavbar active="myAccount"/>
        <MyAccountCard firstName={userData.firstName} lastName={userData.lastName} email={userData.email} phoneNumber={userData.phoneNumber} />
        <Footer />
    </div>
        
    
        
}

export default MyAccount;