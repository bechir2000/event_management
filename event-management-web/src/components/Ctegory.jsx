import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";




function Category(props){

  function handleClick(){
    props.onClick(props.name);
  }

  return <button type="button" className="btn btn-warning btn-lg category" onClick={handleClick}>
    <FontAwesomeIcon icon={props.icon} /> {props.name}
  </button>

}

export default Category;