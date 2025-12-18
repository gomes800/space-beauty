import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { login } from "../api/auth";
import "../styles/auth.css";

export function Login() {
    const navigate = useNavigate();

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState<string | null>(null);

    async function onSubmit(e: React.FormEvent) {
        e.preventDefault();
        setError(null);

        try {
            const { token } = await login({ username, password });
            localStorage.setItem("token", token);
            navigate("/", { replace: true});
        } catch {
            setError("Login failed. Check username and password.");
        }
    }

    return (
        <div className="auth-container">
            <div className="auth-card">
                <h1 className="auth-title">Login</h1>

                <form onSubmit={onSubmit} className="auth-form">
                    <div className="form-group">
                        <label className="form-label">User</label>
                        <input
                            className="form-input"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            autoComplete="username"
                            required
                        />
                    </div>

                    <div className="form-group">
                        <label className="form-label">Password</label>
                        <input
                            className="form-input"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            type="password"
                            autoComplete="current-password"
                            required
                        />
                    </div>

                    {error && <p className="error-message">{error}</p>}

                    <button className="auth-button" type="submit">
                        Enter
                    </button>

                    <p className="auth-link">
                        Don't have an account? <a href="/register">Register</a>
                    </p>
                </form>
            </div>
        </div>
    );
}