import React, {useState, useEffect} from "react";
import Event from "./Event";
import axios from "axios";
import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";


function Events(props){

    const [events, setEvents] = useState([]);
    const [searchQuery, setSearchQuery] = useState("");
    const [filteredEvents, setFilteredEvents] = useState([]);
    

    
    useEffect(() => {
        // Fetch events from the backend API
        const fetchEvents = async () => {
          try {
            const response = await axios.get("http://localhost:8090/event/all");
            // Assuming the response.data is an array of events
            setEvents(response.data);
          } catch (error) {
            console.error("Error fetching events:", error);
          }
        };
    
        fetchEvents();
      }, []);

      useEffect(() => {
        // Filter events based on the search query
        const filtered = events.filter((event) =>
        event.name.toLowerCase().includes(searchQuery.toLowerCase()) &&
        (props.selectedCategory === "All"|| props.selectedCategory === "" || event.category === props.selectedCategory)
        );
        setFilteredEvents(filtered);
      }, [events, searchQuery, props.selectedCategory]);
    
      function handleSearch(e){
        e.preventDefault();
        setSearchQuery(e.target.value);
      };

    return <div className="events-container card-container d-flex flex-wrap gap-3">
    <div className="search-bar ml-auto navbar-collapse d-flex flex-wrap gap-3 justify-content-center align-items-center flex-column search-bar-form">
      <div className="d-flex search-bar-form rounded-pill border border-dark" style={{ padding: '0' }}>
      <FontAwesomeIcon className="search-icon" icon={faMagnifyingGlass} />
        <input
          className="form-control border-0 rounded-pill px-5"
          type="search"
          placeholder="Search"
          aria-label="Search"
          value={searchQuery}
          onChange={handleSearch}
        />
      </div>
      <br />
    </div>

    

    

    {filteredEvents.map((event) => (
      <Event
        key={event.id} // Assuming each event has a unique ID
        eventId={event.id}
        title={event.name}
        date={event.date}
        location={event.location}
        description={event.description}
        img={"http://localhost:8090/images/event-photos/" + event.photo} 
      />
    ))}
  </div>
}

export default Events;