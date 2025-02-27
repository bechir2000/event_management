import React from "react";
import Footer from "./Footer";
import Header from "./Header";
import UserNavbar from "./UserNavbar";




function Client(){
    return <div>
        <Header />
        <UserNavbar active="home" />
        
        
        <Footer/>
    </div>
}

export default Client;