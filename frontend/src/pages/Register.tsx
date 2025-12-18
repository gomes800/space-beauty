import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { register } from "../api/auth";
import "../styles/auth.css";

export function Register() {
    const navigate = useNavigate();

    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [error, setError] = useState<string | null>(null);
    const [success, setSuccess] = useState(false);

    async function onSubmit(e: React.FormEvent) {
        e.preventDefault();
        setError(null);

        if (password !== confirmPassword) {
            setError("Passwords do not match.");
            return;
        }

        try {
            await register({ username, email, password });
            setSuccess(true);
            setTimeout(() => {
                navigate("/login");
            }, 2000);
        } catch {
            setError("Registration failed. Try again.");
        }
    }

    return (
        <div className="auth-container">
            <div className="auth-card">
                <h1 className="auth-title">Register</h1>

                {success ? (
                    <div className="success-message">
                        Registration successful! Redirecting to login...
                    </div>
                ) : (
                    <form onSubmit={onSubmit} className="auth-form">
                        <div className="form-group">
                            <label className="form-label">Username</label>
                            <input 
                                className="form-input"
                                value={username}
                                onChange={(e) => setUsername(e.target.value)}
                                autoComplete="username"
                                required
                            />
                        </div>

                        <div className="form-group">
                            <label className="form-label">Email</label>
                            <input 
                                className="form-input"
                                type="email"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                                autoComplete="email"
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
                                autoComplete="new-password"
                                required
                            />
                        </div>

                        <div className="form-group">
                            <label className="form-label">Confirm Password</label>
                            <input 
                                className="form-input"
                                value={confirmPassword}
                                onChange={(e) => setConfirmPassword(e.target.value)}
                                type="password"
                                autoComplete="new-password"
                                required
                            />
                        </div>

                        {error && <p className="error-message">{error}</p>}

                        <button className="auth-button" type="submit">
                            Register
                        </button>

                        <p className="auth-link">
                            Already have an account? <a href="/login">Login</a>
                        </p>
                    </form>
                )}
            </div>
        </div>
    );
}
