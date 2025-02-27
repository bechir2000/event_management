import React from "react"

function Navbar(props){
      return <div>
      <nav className="navbar navbar-expand-lg bg-body-tertiary" data-bs-theme="dark" style={{ padding: "0" }}>
      <div className="container-fluid" style={{ padding: "0" }}>
      <a className="navbar-brand" href="/home" style={{ padding: "0" }}>
      <img src="/images/logo.png" alt="Eventopia Logo" className="navbar-logo" />
        Eventopia
      </a>
          <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
          </button>
          
            <div className="links ml-auto"> 
              <ul className="navbar-nav"> 
                <li className="nav-item">
                  <a className={props.active ==="home" ? "nav-link active": "nav-link"} aria-current="page" href="/home">Home</a>
                </li>
                <li className="nav-item">
                  <a className={props.active ==="about" ? "nav-link active": "nav-link"} href="/about">About Us</a>
                </li>
                <li className="nav-item">
                  <a className={props.active ==="contact" ? "nav-link active": "nav-link"} href="/contact">Contact</a>
                </li>
                <li className="nav-item">
                  <a className="btn btn-outline-warning" href="/login">Log in</a>
                </li>    
              </ul>
            </div>
        </div>
      </nav>
    </div>
    } 

    export default Navbar ;