import React from "react";
import AboutItem from "./AboutItem";
import Navbar from "./Navbar";

function About(){
    return(
        <div className="about-us">
        <Navbar active="about"></Navbar>
        <h1>Welcome to Eventopia</h1>
        <AboutItem title="Welcome to Eventopia - Your Ultimate Event Companion!" 
        description="Eventopia is your go-to platform for discovering, attending, and never missing out on any exciting events happening around you. Whether you're a passionate event-goer or an event organizer, Eventopia has everything you need to make your event experiences unforgettable." 
        />

        <AboutItem title="Discover Exciting Events" 
        description="Explore a diverse range of events, from concerts and festivals to conferences and workshops. Our user-friendly interface allows you to easily search and filter events based on your interests, location, and date preferences. Never miss an opportunity to be part of your favorite events." 
        />

<AboutItem title="Seamless Ticket Booking" 
        description="With Eventopia, securing your spot at your chosen event is just a few clicks away. Our secure and streamlined ticket booking system ensures a hassle-free ticket purchasing process. Say goodbye to long queues and welcome the convenience of online ticket booking." 
        />

<AboutItem title="Be an Event Organizer" 
        description="With Eventopia, securing your spot at your chosen event is just a few clicks away. Our secure and streamlined ticket booking system ensures a hassle-free ticket purchasing process. Say goodbye to long queues and welcome the convenience of online ticket booking." 
        />

<AboutItem title="Customize Your Events" 
        description="As an organizer, you have full control over your event's description, schedule, and ticket pricing. Tailor your events to suit your vision and connect with your target audience effectively." 
        />

<AboutItem title="Your Event Journey, Enhanced" 
        description="We are committed to continually enhancing your event journey. Your feedback is invaluable to us, and we strive to make Eventopia the most reliable and enjoyable platform for all event enthusiasts." 
        />

<AboutItem title="" 
        description="Join the Eventopia community today and unlock a world of thrilling events and unforgettable experiences. Whether you're attending events as a guest or creating them as an organizer, Eventopia has got you covered.

Let's embark on an exciting journey of events together - Eventopia awaits you!" 
        />




        </div>
    )

}

export default About;