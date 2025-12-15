import { http } from "./http";

export type LoginRequest = { username: string; password: string };
export type LoginResponse = { token: string };

export async function login(data: LoginRequest) {
  const res = await http.post<LoginResponse>("/auth/login", data);
  return res.data;
}

export type RegisterRequest = {
  username: string;
  email: string;
  password: string;
};

export async function register(data: RegisterRequest) {
  const res = await http.post<void>("/auth/register", data);
  return res.status;
}
