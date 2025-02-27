import React from "react";



function Footer(){
    var year= new Date().getFullYear();

    return <div className="container">
        <hr></hr>
        <br></br> 
        <footer><p> Copyright © {year}</p></footer>
    </div>
}

export default Footer;