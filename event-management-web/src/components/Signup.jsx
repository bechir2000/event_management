import React, { useState } from "react";
import Navbar from "./Navbar";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faFacebookF, faGoogle } from "@fortawesome/free-brands-svg-icons";
import axios from "axios";
import { useNavigate} from "react-router-dom";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import validator from 'validator';
import Footer from "./Footer";



function Signup() {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [submitted, setSubmitted] = useState(false);


  const [firstNameError, setFirstNameError] = useState(false);
  const [lastNameError, setLastNameError] = useState(false);
  const [emailError, setEmailError] = useState(false);
  const [phoneNumberError, setPhoneNumberError] = useState(false);
  const [passwordError, setPasswordError] = useState(false);

  function handleEmailChange  (e) {
    setEmail(e.target.value);
    setEmailError(false);
  };

  function handlePasswordChange (e) {
    setPassword(e.target.value);
    setPasswordError(false);
  };

  function handleFirstNameChange (e) {
    setFirstName(e.target.value);
    setFirstNameError(false);
  };

  function handleLastNameChange (e) {
    setLastName(e.target.value);
    setLastNameError(false);
  };

  function handlePhoneNumberChange  (e) {
    setPhoneNumber(e.target.value);
    setPhoneNumberError(false);
  };


  const navigate = useNavigate();

  async function handleSubmit (e){
    e.preventDefault();

    setSubmitted(true);

    let isValid = true;

    if (firstName.length < 2) {
      setFirstNameError(true);
      isValid = false;
    }

    if (lastName.length < 2) {
      setLastNameError(true);
      isValid = false;
    }

    if (!validator.isEmail(email)) {
      setEmailError(true);
      isValid = false;
    }

    if (!validator.isMobilePhone(phoneNumber, 'any', { strictMode: false })) {
      setPhoneNumberError(true);
      isValid = false;
    }

    if (!validator.isStrongPassword(password, { minSymbols: 0, minNumbers: 1 })) {
      setPasswordError(true);
      isValid = false;
    }

    if (isValid) {
      //create a login data (json) to send it to the backend 
    const SignupData = {
      firstName,
      lastName,
      phoneNumber,
      email,
      password,
    };

    try {

      var response="";
      // Send the login data to the backend API endpoint
      if(e.target.userType.value ==="client"){
        response = await axios.post("http://localhost:8090/client/register", SignupData);
      } else if (e.target.userType.value === "organizer"){
        response = await axios.post("http://localhost:8090/organizer/register", SignupData);
      };

      // Show toast notification for successful registration
    toast.success("Your account has been successfully created, Login and start your journey now!");

    // Redirect to login page 
      navigate('/login');

    // Reset form fields after successful registration
    setEmail("");
    setPassword("");
    setFirstName("");
    setLastName("");
    setPhoneNumber("");

    } catch (error) {
      // Handle errors (e.g., display an error message to the user)
     toast.error("Signup failed, the email address is already in use!");
     setEmailError(true);
    }
    }

    
  };

  return (
  <div className="background">
    <Navbar active="login"/> 
    <div className="form-container2">
      <div className="login-box">
        <h1 className="login-heading">Register</h1>

        <form className="login-form" onSubmit={handleSubmit}>
          <div className="mb-3">
            <div className="row g-3">
              <div className="col">
                <input
                  type="text"
                  className={`form-control ${submitted && firstNameError ? 'is-invalid' : submitted && firstName ? 'is-valid' : ''}`}
                  placeholder="First name"
                  aria-label="First name"
                  name="firstName"
                  value={firstName}
                  onChange={handleFirstNameChange}
                  required
                />
                {submitted && firstNameError && <div className="invalid-feedback">First name must have at least 2 characters</div>}
              </div>
              <div className="col">
                <input
                  type="text"
                  className={`form-control ${submitted && lastNameError ? 'is-invalid' : submitted && lastName ? 'is-valid' : ''}`}
                  placeholder="Last name"
                  aria-label="Last name"
                  name="lastName"
                  value={lastName}
                  onChange={handleLastNameChange}
                  required
                />
                {submitted && lastNameError && <div className="invalid-feedback">Last name must have at least 2 characters</div>}
              </div>
            </div>
          </div>

          <div className="mb-3">
            <input
              type="email"
              className={`form-control ${submitted && emailError ? 'is-invalid' : submitted && email ? 'is-valid' : ''}`}
              id="email"
              placeholder="Email Address"
              name="email"
              value={email}
              onChange={handleEmailChange}
              required
            />
            {submitted && emailError && <div className="invalid-feedback">Invalid email address</div>}
          </div>

          <div className="mb-3">
            <input
              type="phoneNumber"
              className={`form-control ${submitted && phoneNumberError ? 'is-invalid' : submitted && phoneNumber ? 'is-valid' : ''}`}
              id="phoneNumber"
              placeholder="Phone Number"
              name="phoneNumber"
              value={phoneNumber}
              onChange={handlePhoneNumberChange}
              required
            />
            {submitted && phoneNumberError && <div className="invalid-feedback">Invalid phone number</div>}
          </div>

          <div className="mb-3">
            <input
              type="password"
              className={`form-control ${submitted && passwordError ? 'is-invalid' : submitted && password ? 'is-valid' : ''}`}
              id="password"
              placeholder="Password"
              name="password"
              value={password}
              onChange={handlePasswordChange}
              required
            />
            {submitted && passwordError && <div className="invalid-feedback">Password must be at least 8 characters long, with uppercase, lowercase, and digits</div>}
          </div>

          <div className="mb-3">
            <div className="row g-3">
              <div className="col-md-4">
                <label className="label">Signup As</label>
              </div>
              <div className="col-md-8">
                <select className="form-select" aria-label="Default select example" name="userType" required>
                  <option value="client">Client</option>
                  <option value="organizer">Organizer</option>
                </select>
              </div>
            </div>
          </div>

          <button type="submit" className="btn btn-primary">Sign Up</button>

             <div className="divider-container">
                <hr className="divider" />
                <span className="divider-text">or</span>
                <hr className="divider" />
            </div>

            <div className="social-login-buttons">
                <button className="btn btn-outline-primary">
                    Signup with Facebook <br></br> <FontAwesomeIcon icon={faFacebookF} />    
                </button>
                <button className="btn btn-outline-danger">
                    Signup with Google <br></br> <FontAwesomeIcon icon={faGoogle} />   
                </button>
            </div>
        </form>

        
        </div>
    </div>
    <Footer/>
    </div>
  );
}

export default Signup;