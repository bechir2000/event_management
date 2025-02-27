import React from "react";
import Category from "./Ctegory";
import { faReplyAll, faMusic, faMasksTheater, faGlobe, faPersonRunning, faHeartPulse, faMicrochip, faGraduationCap, faChampagneGlasses } from "@fortawesome/free-solid-svg-icons";



function Categories(props) {
    
    function handleCategoryClick(categoryName){
      props.onSelectCategory(categoryName);
    };

    return <div className="container">

    <h2> Categories</h2> <br />
        <Category name="All" icon={faReplyAll} onClick={handleCategoryClick}/>
        <Category name="Music" icon={faMusic} onClick={handleCategoryClick}/>
        <Category name="Arts & Culture" icon={faMasksTheater} onClick={handleCategoryClick}/>
        <Category name="Business & Networking" icon={faGlobe} onClick={handleCategoryClick}/>
        <Category name="Sports" icon={faPersonRunning} onClick={handleCategoryClick}/>
        <Category name="Health & Wellness" icon={faHeartPulse} onClick={handleCategoryClick}/>
        <Category name="Technology" icon={faMicrochip} onClick={handleCategoryClick}/> 
        <Category name="Education" icon={faGraduationCap} onClick={handleCategoryClick}/>
        <Category name="Food & Drink" icon={faChampagneGlasses} onClick={handleCategoryClick} />

        <hr></hr>

    </div>
    

}

export default Categories;