import React, { useState } from "react";
import Navbar from "./Navbar";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faFacebookF, faGoogle } from "@fortawesome/free-brands-svg-icons";
import axios from "axios";
import Cookies from "js-cookie";
import { useNavigate} from "react-router-dom";
import { toast } from "react-toastify";
import Footer from "./Footer";


function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  function handleEmailChange(e) {
    setEmail(e.target.value);
  };

  function handlePasswordChange(e){
    setPassword(e.target.value);
  };

  const navigate = useNavigate();

  async function handleSubmit(e){
    e.preventDefault();

    //create a login data (json) to send it to the backend 
    const loginData = {
      email,
      password,
    };

    try {
      // Send the login data to the backend API endpoint
      const response = await axios.post("http://localhost:8090/authenticate", loginData);

      Cookies.set("authToken", response.data.token, { expires: 7 }); //save the token with 7 days expiration in a cookie
      Cookies.set("userId", response.data.userId);
      Cookies.set("userType", response.data.role);

      
      // Reset form fields after successful login
      setEmail("");
      setPassword("");

      // Redirect the user to his page
      const role=response.data.role;
      if(role==="CLIENT"){
        navigate('/client');
      } else if(role==="ORGANIZER"){
        navigate('/organizer');
      } if(role==="ADMIN"){
        navigate('/admin');
      }

    } catch (error) {
      // Show toast notification for login error
      toast.error("Login failed, Please check your credentials.");

      // Reset form fields after login error
      setEmail("");
      setPassword("");
    }
  };

  return (<div className="background">
    <Navbar active="login"/> 
    <div className="form-container1 ">
    <div className="login-box">
        
        <h1 className="login-heading">Sign In</h1>
        <form className="login-form" onSubmit={handleSubmit}>
             <div className="mb-3">
             
               <input type="email" className="form-control" id="email" placeholder="Email Address" name="email" 
                   value={email}
                   onChange={handleEmailChange}
                   required
               />
             </div>

             <div className="mb-3">
               <input type="password" className="form-control" id="password" placeholder="Password" name="password"
                       value={password}
                       onChange={handlePasswordChange}
                       required />
             </div>
             <button type="submit" className="btn btn-primary btn-lg login-button">Login</button>

             <p>Don't have an account ? <a href="/signup">Register now</a></p>

             <div className="divider-container">
                <hr className="divider" />
                <span className="divider-text">or</span>
                <hr className="divider" />
            </div>

            <div className="social-login-buttons">
                <button className="btn btn-outline-primary">
                    Sign in with Facebook <br></br> <FontAwesomeIcon icon={faFacebookF} />    
                </button>
                <button className="btn btn-outline-danger">
                    Sign in with Google <br></br> <FontAwesomeIcon icon={faGoogle} />   
                </button>
            </div>

        </form>
        </div>
    </div>
    <Footer/>
    </div>
    

  );
}

export default Login;