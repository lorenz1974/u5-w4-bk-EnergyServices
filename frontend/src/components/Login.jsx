import React from 'react';
import { useState } from "react";
import axios from "axios";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    try {
      const response = await axios.post("http://localhost:8080/api/auth/login", {
        username,
        password,
      });
      const token = response.data.token; 
      localStorage.setItem("token", token); 
      alert("Login riuscito!");
      window.location.href = "/dashboard"; 
    } catch (err) {
      setError("Credenziali errate. Riprova.");
    }
    console.log({ username, password });

  };

  return (
    <div className="d-flex justify-content-center align-items-center vh-100">
    <div className="p-5 m-5">
      <h2 className="text-center">Login</h2>
      {error && <div className="alert alert-danger">{error}</div>}
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label className="form-label">Username</label>
          <input
            type="text"
            className="form-control"
            placeholder="Inserisci il tuo username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Password</label>
          <input
            type="password"
            className="form-control"
            placeholder="Inserisci la tua password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <button type="submit" className="btn btn-primary w-100">
          Accedi
        </button>
      </form>
    </div>
  </div>
  
  
  );
};

export default Login;
