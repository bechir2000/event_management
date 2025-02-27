import React, { useState } from "react";
import UserNavbar from "./UserNavbar";
import Cookies from "js-cookie";
import axios from "axios";
import { useNavigate} from "react-router-dom";
import { toast } from "react-toastify";
import Footer from "./Footer";



function EventForm() {
  const [name, setName] = useState("");
  const [date, setDate] = useState("");
  const [time, setTime] = useState("");
  const [description, setDescription] = useState("");
  const [location, setLocation] = useState("");
  const [price, setPrice] = useState("");
  const [number, setNumber] = useState("");
  const [image, setImage] = useState(null);

  const [submitted, setSubmitted] = useState(false);

  const [nameError, setNameError] = useState(false);
  const [dateError, setDateError] = useState(false);
  const [descriptionError, setDescriptionError] = useState(false);
  const [priceError, setPriceError] = useState(false);
  const [numberError, setNumberError] = useState(false);
  const [locationError, setLocationError] = useState(false);
  const [categoryError, setCategoryError]=useState(false);



  function handleImageChange(e){
    setImage(e.target.files[0]);
  };

  function handlePriceChange(e){
    setPrice(e.target.value);
    setPriceError(false);
  };

  function handleNumberChange(e){
    setNumber(e.target.value);
    setNumberError(false);

  };

  function handleNameChange(e){
    setName(e.target.value);
    setNameError(false);

  };

  function handleDateChange(e) {
    setDate(e.target.value);
    setDateError(false);

  };

  function handleTimeChange(e){
    setTime(e.target.value);
  };

  function handleLocationChange (e)  {
    setLocation(e.target.value);
    setLocationError(false);
  };

  function handleDescriptionChange(e)  {
    setDescription(e.target.value);
    setDescriptionError(false);

  };

  const navigate = useNavigate();

  async function handleSubmit (e){
    e.preventDefault();

    setSubmitted(true);

    let isValid = true;

    if (name.length < 4) {
      isValid = false;
      setNameError(true);
    }


    if (location.length < 4) {
      isValid = false;
      setLocationError(true);
    }

    if (description.length < 30) {
      isValid = false;
      setDescriptionError(true);
    }

    if(e.target.category ==="Category"){
      isValid = false;
      setCategoryError(true);
    }
    const currentDate = new Date();
    const selectedDate = new Date(`${date}`);
    if (selectedDate <= currentDate) {
      isValid = false;
      setDateError(true);
    }

    if (Number(number) <= 0 || isNaN(Number(number))) {
      isValid = false;
      setNumberError(true);
    }

    if (Number(price) < 0 || isNaN(Number(price))) {
      isValid = false;
      setPriceError(true);
    }

    if (isValid) {

      //create an event data (json) to send it to the backend   
      const eventData = new FormData();
        eventData.append("name", name);
        eventData.append("date", date);
        eventData.append("time", time);
        eventData.append("location", location);
        eventData.append("description", description);
        eventData.append("category", e.target.category.value);
        eventData.append("maxNumOfAttendees", e.target.number.value);
        eventData.append("price", price);
        eventData.append("image", image); // Append the image file to the form data
    try {

      // Get organizer ID and token from cookies
      const organizerId = Cookies.get("userId");
      const token = Cookies.get("authToken");

      const response = await axios.post(
        `http://localhost:8090/event/organizer/${organizerId}`,
        eventData,
        {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "multipart/form-data", // Set content type to multipart/form-data

          },
        }
      );

      // Show toast notification for successful creation
    toast.success("The event has been successfully created.");

      // Redirect 
      navigate('/organizer');
      
      // Reset form fields
      setName("");
      setDate("");
      setTime("");
      setDescription("");
      setLocation("");
      setPrice("");
      setNumber("");
      setImage(null);
      setSubmitted(false);
    } catch(error){
      console.error("Error:", error);
      toast.error("Sorry! the event wasn't created due to an error, please try later.");

    }
  };
}

  return (<div className="background">
  <UserNavbar active="myEvents" />
  <div className="form-container2 form-background ">
    <div className="login-box">
      <h1 className="login-heading">New Event</h1>

      <form className="login-form" onSubmit={handleSubmit} encType="multipart/form-data">
        <div className="mb-3">
          <input
            type="text"
            className={`form-control ${nameError ? "is-invalid" : submitted && name ? "is-valid" : ""}`}
            placeholder="Event Name"
            name="name"
            value={name}
            onChange={handleNameChange}
            required
          />
          {nameError && <div className="invalid-feedback">Name must have at least 4 characters</div>}
        </div>

        <div className="mb-3">
          <div className="row g-3">
            <div className="col">
              <input
                type="date"
                className={`form-control ${dateError ? "is-invalid" : submitted && date ? "is-valid" : ""}`}
                placeholder="Date"
                aria-label="date"
                name="date"
                value={date}
                onChange={handleDateChange}
                required
              />
              {dateError && <div className="invalid-feedback">Selected date must be in the future</div>}
            </div>
            <div className="col">
              <input
                type="time"
                className="form-control"
                placeholder="Time"
                aria-label="time"
                name="time"
                value={time}
                onChange={handleTimeChange}
                required
              />
            </div>
          </div>
        </div>

        <div className="mb-3">
          <input
            type="text"
            className={`form-control ${locationError ? "is-invalid" : submitted && location ? "is-valid" : ""}`}
            placeholder="Location"
            name="location"
            value={location}
            onChange={handleLocationChange}
            required
          />
          {locationError && <div className="invalid-feedback">Valid location is required</div>}
        </div>

        <div className="mb-3">
          <select
            className={`form-select ${categoryError ? "is-invalid" : submitted ? "is-valid" : ""}`}
            aria-label="Default select example"
            name="category"
            required
          >
            <option value="">Category</option>
            <option value="Music">Music</option>
            <option value="Arts & Culture">Arts & Culture</option>
            <option value="Business & Networking">Business & Networking</option>
            <option value="Sports">Sports</option>
            <option value="Health & Wellness">Health & Wellness</option>
            <option value="Technology">Technology</option>
            <option value="Education">Education</option>
            <option value="Food & Drink">Food & Drink</option>
          </select>
          {categoryError && <div className="invalid-feedback">Category is required</div>}
        </div>

        <div className="mb-3">
          <textarea
            className={`form-control ${
              descriptionError ? "is-invalid" : submitted && description ? "is-valid" : ""
            }`}
            rows="3"
            name="description"
            placeholder="Describe your event"
            value={description}
            onChange={handleDescriptionChange}
            required
          />
          {descriptionError && (
            <div className="invalid-feedback">Too short Description</div>
          )}
        </div>

        <div className="row g-3">
          <div className="col">
            <input
              type="number"
              className={`form-control ${numberError ? "is-invalid" : submitted && number > 0 ? "is-valid" : ""}`}
              placeholder="Attendees Number"
              name="number"
              value={number}
              onChange={handleNumberChange}
              required
            />
            {numberError && <div className="invalid-feedback">Number of attendees must be greater than 0</div>}
          </div>
          <div className="col">
            <input
              type="number"
              className={`form-control ${priceError ? "is-invalid" : submitted && price > 0 ? "is-valid" : ""}`}
              placeholder="Price (DT)"
              name="price"
              value={price}
              onChange={handlePriceChange}
              required
            />
            {priceError && <div className="invalid-feedback">Price must be greater than or equal 0</div>}
          </div>
        </div>
        <br />

        <div className="mb-3">
          <input
            type="file" // Use the "file" input type for image upload
            accept="image/*" // Allow only image files
            className="form-control"
            onChange={handleImageChange} // Call the handleImageChange function on file selection
          />
        </div>
        <button type="submit" className="btn btn-primary btn-lg login-button">
          Save
        </button>
      </form>
    </div>
  </div>
  <Footer/>
</div>
  );
}


export default EventForm;