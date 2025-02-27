import React, {useState} from "react";
import Footer from "./Footer";
import Header from "./Header";
import Carousel  from "./Carousel";
import Categories from "./Categories";
import Events from "./Events";
import Navbar from "./Navbar";





function Home(){
    const [selectedCategory, setSelectedCategory] = useState("");

    function handleCategoryChange(categoryName){
        setSelectedCategory(categoryName);
    }
    
    return <div>
        <Header/>

        <Navbar active="home"/>
        <Carousel/>
        <Categories onSelectCategory={handleCategoryChange}  />
        <Events selectedCategory={selectedCategory}/>
        
        <Footer/>
    </div>
}

export default Home;