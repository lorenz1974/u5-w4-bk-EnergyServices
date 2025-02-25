import React from 'react';
import { createRoot } from "react-dom/client";
import "./index.css";
import Login from "./components/Login.jsx";

import "bootstrap/dist/css/bootstrap.min.css";

createRoot(document.getElementById("root")).render(
  <div>
    {" "}
    <Login />
  </div>
);
