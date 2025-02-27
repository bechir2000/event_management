    import { StrictMode } from "react";
    import { createRoot } from "react-dom/client";
    import { ToastContainer } from 'react-toastify';
    import 'react-toastify/dist/ReactToastify.css';
    
    import App from './components/App';
    
    const rootElement = document.getElementById("root");
    const root = createRoot(rootElement);
    
    root.render(
      <StrictMode>
        <App />
        <ToastContainer /> 
      </StrictMode>
    );