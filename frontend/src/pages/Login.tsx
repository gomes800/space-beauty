import React, { useState } from "react";
import { useNavigate, replace } from "react-router-dom";
import { login } from "../api/auth";

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
        <div style={{ maxWidth: 360, margin: "40px auto" }}>
        <h1>Login</h1>

        <form onSubmit={onSubmit}>
            <div>
                <label>User</label>
                <input value={username}
                onChange={(e) => setUsername(e.target.value)}
                autoComplete="username" 
                />
            </div>

            <div style={{ marginTop: 12 }}>
                <label>Password</label>
                <input 
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                type="password"
                autoComplete="current-password" 
                />
            </div>

            {error && <p style={{ color: "crimson" }}>{error}</p>}

            <button style={{ marginTop: 16 }} type="submit">
                Enter
            </button>
        </form>
    </div>
    );
}