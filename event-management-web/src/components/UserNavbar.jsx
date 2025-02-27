import React, {useState, useEffect} from "react";
import Cookies from "js-cookie";
import { useNavigate } from "react-router-dom";




function UserNavbar(props){

  const [loggedOut, setLoggedOut] = useState(false);
  const [ userId, setUserId ]= useState("");
  const [userType, setUserType]=useState("");


  // Use useHistory hook to get access to the history object
  const navigate = useNavigate();
  useEffect(() => { 
    // Check if the 'authToken' cookie exists
    const authToken = Cookies.get("authToken");
    if (authToken) {
        setUserType(Cookies.get("userType"));
        setUserId(Cookies.get("userId"));
    } else {
        return navigate("/login");
    }
  }, []);

  function handleMyAccountClick(){
    navigate(`/MyAccount/${userId}`);
  }

  function handleMyTicketsClick(){
    navigate(`/MyTickets/${userId}`);
  }
  
  const handleLogout = () => {
    // Clear all user-related cookies
    Cookies.remove("authToken");
    Cookies.remove("userId");
    Cookies.remove("userType");

    // Set the state to loggedOut to trigger the Redirect component
    setLoggedOut(true);
  };


  // If the user has logged out, redirect them to the home page
  if (loggedOut) {
    navigate('/home');
  }

  if (userType === "CLIENT"){
    return <div>
    <nav className="navbar navbar-expand-lg bg-body-tertiary" data-bs-theme="dark" style={{ padding: "0" }}>
      <div className="container-fluid" style={{ padding: "0" }}>
      <a className="navbar-brand" href="/client" style={{ padding: "0" }}>
      <img src="/images/logo.png" alt="Eventopia Logo" className="navbar-logo" />
        Eventopia
      </a>
    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span className="navbar-toggler-icon"></span>
    </button>

    <div className="links ml-auto">
      <ul className="navbar-nav">
        <li className="nav-item">
          <a className={props.active === "home" ? "nav-link active" : "nav-link"} aria-current="page" href="/client">Home</a>
        </li>
        <li className="nav-item">
          <button className={props.active === "myTickets" ? "nav-link active" : "nav-link"} onClick={handleMyTicketsClick}>My Tickets</button>
        </li>
        <li className="nav-item">
          <button className={props.active === "myAccount" ? "nav-link active" : "nav-link"} onClick={handleMyAccountClick}>My Account</button>
        </li>

        <li className="">
          <button className="btn btn-outline-danger" onClick={handleLogout}>Log out</button>
        </li>
      </ul>
    </div>
  </div>
</nav>
  </div>
  } else if (userType === "ORGANIZER"){
    return <div>
    <nav className="navbar navbar-expand-lg bg-body-tertiary" data-bs-theme="dark" style={{ padding: "0" }}>
      <div className="container-fluid" style={{ padding: "0" }}>
      <a className="navbar-brand" href="/organizer" style={{ padding: "0" }}>
      <img src="/images/logo.png" alt="Eventopia Logo" className="navbar-logo" />
        Eventopia
      </a>
        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"></span>
        </button>
          <div className="links ml-auto"> 
            <ul className="navbar-nav"> 
              <li className="nav-item">
                <a className={props.active =="home" ? "nav-link active": "nav-link"} aria-current="page" href="/organizer">Home</a>
              </li>
              <li className="nav-item">
                <a className={props.active =="myEvents" ? "nav-link active": "nav-link"} href="/myEvents">My Events</a>
              </li>
              <li className="nav-item">
                <button className={props.active =="myTickets" ? "nav-link active": "nav-link"} onClick={handleMyTicketsClick}>My Tickets</button>
              </li>
              <li className="nav-item">
                <button className={props.active =="myAccount" ? "nav-link active": "nav-link"} onClick={handleMyAccountClick}>My Account</button>
              </li>

              <li className="nav-item">
                <button  className="btn btn-outline-danger" onClick={handleLogout}>Log out</button>
              </li>
            </ul>
          </div>
      </div>
    </nav>
  </div>
  } else if(userType === "ADMIN") {
    return <div>
    <nav className="navbar navbar-expand-lg bg-body-tertiary" data-bs-theme="dark" style={{ padding: "0" }}>
      <div className="container-fluid" style={{ padding: "0" }}>
      <a className="navbar-brand" href="/admin" style={{ padding: "0" }}>
      <img src="/images/logo.png" alt="Eventopia Logo" className="navbar-logo" />
        Eventopia
      </a>
        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"></span>
        </button>
          <div className="links ml-auto"> 
            <ul className="navbar-nav"> 
              <li className="nav-item">
                <a className={props.active =="home" ? "nav-link active": "nav-link"} aria-current="page" href="/admin">Home</a>
              </li>
              <li className="nav-item">
                <a className={props.active =="users" ? "nav-link active": "nav-link"} href="/users">Users</a>
              </li>
              <li className="nav-item">
                <a className={props.active =="events" ? "nav-link active": "nav-link"} href="/events">Events</a>
              </li>
              <li className="nav-item">
                <button className={props.active =="myAccount" ? "nav-link active": "nav-link"} onClick={handleMyAccountClick}>My Account</button>
              </li>
              <li className="nav-item">
              <button  className="btn btn-outline-danger" onClick={handleLogout}>Log out</button>
              </li>
            </ul>
          </div>
      </div>
    </nav>
  </div>
  } 
}

export default UserNavbar;

