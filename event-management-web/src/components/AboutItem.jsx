import React from "react";

function AboutItem(props){
    return(
        <div className="about-item">

        <h3>{props.title}</h3>
        <hr></hr>

        <p>{props.description}</p>

        </div>
    )

}

export default AboutItem;