import React from "react";
import Home from "./Home";
import Login from "./Login";
import Signup from "./Signup"
import EventForm from "./EventForm"
import { BrowserRouter, Routes, Route} from 'react-router-dom';
import UserHome from "./UserHome";
import Admin from "./Admin"
import About from "./About";
import MyEvents from "./MyEvents";
import EventPage from "./EventPage";
import MyAccount from "./MyAccount";
import MyTickets from "./MyTickets";




function App(){
    return <BrowserRouter>
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/home" element={<Home />} />
      <Route path="/login" element={<Login />} />
      <Route path="/signup" element={<Signup />} />
      <Route path="/new-event" element={<EventForm />} />
      <Route path="/client" element={<UserHome user="CLIENT" />} />
      <Route path="/organizer" element={<UserHome user="ORGANIZER" />} />
      <Route path="/admin" element={<Admin />} />
      <Route path="/about" element={<About />} />
      <Route path="/myEvents" element={<MyEvents />} />
      <Route path="/new-event" element={<EventForm />} />
      <Route path="/event/:eventId" element={<EventPage />} />
      <Route path="/MyAccount/:userId" element={<MyAccount />} />
      <Route path="/myTickets/:userId" element={<MyTickets />} />





    </Routes>
  </BrowserRouter>
}

export default App;