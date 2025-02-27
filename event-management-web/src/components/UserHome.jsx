import React, {useState, useEffect} from "react";
import Footer from "./Footer";
import Header from "./Header";
import Carousel  from "./Carousel";
import Categories from "./Categories";
import Events from "./Events";
import UserNavbar from "./UserNavbar";
import Cookies from "js-cookie"
import { useNavigate } from "react-router-dom";




function UserHome(props){

    const [selectedCategory, setSelectedCategory] = useState("");
    const [userType, setUserType]=useState("");
    const [userId, setUserId]=useState("");


    const navigate = useNavigate();

    useEffect(() => {
        // Check if the 'authToken' cookie exists
        const authToken = Cookies.get("authToken");
        if (authToken) {
          setUserType(Cookies.get("userType"));
          setUserId(Cookies.get("userId"));
        } else {
            return navigate("/login");
        }
      }, []);


    function handleCategoryChange(categoryName){
        setSelectedCategory(categoryName);
    }


    return <div>
        <Header/>
        <UserNavbar active="home"/>
        <Carousel/>
        <Categories onSelectCategory={handleCategoryChange}  />
        <Events selectedCategory={selectedCategory}/>
        
        <Footer/>
    </div>
        
    
        
}

export default UserHome;